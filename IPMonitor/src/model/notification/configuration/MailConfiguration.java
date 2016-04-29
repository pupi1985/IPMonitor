/*
 * IP Monitor is a simple application which monitors your public IP
 * address for changes and lets you set different kinds of notification
 * such as email, audio, pop up or executing a command. It can also run
 * in background as a Windows service or Linux/Mac daemon.
 *
 * Copyright (C) 2007 - 2016  Gabriel Zanetti http://github.com/pupi1985
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package model.notification.configuration;

import model.extras.InfoParser;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.Message.*;
import javax.mail.internet.*;

public class MailConfiguration extends AbstractConfiguration {

    private static MailConfiguration instance;
    private String host;
    private int port;
    private boolean useSSL;
    private boolean authenticationRequired;
    private String user;
    private String password;
    private boolean isHTML;
    private InternetAddress fromAddress;
    private InternetAddress[] toAddresses;
    private String subject;
    private String text;
    private Session session;

    private MailConfiguration() {
        fromAddress = new InternetAddress();
    }

    public static MailConfiguration getInstance() {
        if (instance == null) {
            instance = new MailConfiguration();
        }
        return instance;
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        String protocol = (useSSL) ? "smtps" : "smtp";
        properties.put("mail.transport.protocol", protocol);
        properties.put("mail." + protocol + ".host", host);
        properties.put("mail." + protocol + ".port", port);
        if (authenticationRequired) {
            properties.put("mail." + protocol + ".auth", "true");
        }
        properties.put("mail." + protocol + ".quitwait", "false");
        return properties;
    }

    public Transport getTransport() {
        try {
            return session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MimeMessage getMessage(String fromIP, String toIP)
            throws MessagingException {
        if (authenticationRequired) {
            session = Session.getInstance(getProperties(),
                    new SMTPAuthentication());
        } else {
            session = Session.getInstance(getProperties());
        }
        session.setDebug(false);
        MimeMessage message = new MimeMessage(session);
        message.setHeader("X-Priority", "1");
        message.setFrom(fromAddress);
        message.addRecipients(RecipientType.TO, toAddresses);
        message.setSubject(InfoParser.getInstance().parseField(subject,
                fromIP, toIP));
        message.setContent(InfoParser.getInstance().parseField(text,
                fromIP, toIP), (isHTML) ? "text/html" : "text/plain");
        return message;
    }

    public String getFromAddress() {
        return fromAddress.getAddress();
    }

    public void setFromAddress(String fromAddress) throws AddressException {
        InternetAddress.parse(fromAddress);
        this.fromAddress.setAddress(fromAddress);
    }

    public String getFromName() {
        return fromAddress.getPersonal();
    }

    public void setFromName(String fromName) {
        try {
            this.fromAddress.setPersonal(fromName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isSSL() {
        return useSSL;
    }

    public void setSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    public boolean isAuthenticationRequired() {
        return authenticationRequired;
    }

    public void setAuthenticationRequired(boolean requiresAuthentication) {
        this.authenticationRequired = requiresAuthentication;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String userName) {
        this.user = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHTML() {
        return isHTML;
    }

    public void setHTML(boolean isHTML) {
        this.isHTML = isHTML;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) throws NumberFormatException {
        if (port < 1 || port > 65535) {
            throw new NumberFormatException();
        }
        this.port = port;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getToAddresses() {
        String result = InternetAddress.toString(toAddresses);
        return (result == null ? "" : result);
    }

    public void setToAddresses(String toAddresses) throws AddressException {
        this.toAddresses = InternetAddress.parse(toAddresses);
        if (this.toAddresses == null) {
            this.toAddresses = new InternetAddress[1];
        }
    }

    private class SMTPAuthentication extends Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
        }
    }
}
