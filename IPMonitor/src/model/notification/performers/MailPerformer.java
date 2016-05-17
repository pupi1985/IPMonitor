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

package model.notification.performers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import model.extras.InfoParser;
import model.notification.configuration.MailConfiguration;

public class MailPerformer extends AbstractPerformer {

    private Session session;
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    private Session getSession() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);

        if (MailConfiguration.getInstance().isAuthenticationRequired()) {
            session = Session.getInstance(getProperties(), new SMTPAuthentication());
        } else {
            session = Session.getInstance(getProperties());
        }
        session.setDebug(false);

        session.setDebugOut(printStream);
        session.setDebug(true);

        return session;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        String protocol = (MailConfiguration.getInstance().isSSL()) ? "smtps" : "smtp";
        properties.put("mail.transport.protocol", protocol);
        properties.put("mail." + protocol + ".host", MailConfiguration.getInstance().getHost());
        properties.put("mail." + protocol + ".port", MailConfiguration.getInstance().getPort());
        properties.put("mail." + protocol + ".connectiontimeout", 30000);
        properties.put("mail." + protocol + ".timeout", 30000);
        properties.put("mail." + protocol + ".quitwait", "false");

        if (MailConfiguration.getInstance().isAuthenticationRequired()) {
            properties.put("mail." + protocol + ".auth", "true");
        }

        return properties;
    }

    private MimeMessage getMessage(String fromIP, String toIP) throws MessagingException {
        InfoParser infoParser = new InfoParser();
        Session session = this.getSession();
        MimeMessage message = new MimeMessage(session);
        message.setFrom(MailConfiguration.getInstance().getFromAddress());
        message.addRecipients(RecipientType.TO, MailConfiguration.getInstance().getToAddresses());
        message.setSubject(infoParser.parseField(MailConfiguration.getInstance().getSubject(), fromIP, toIP));
        message.setContent(infoParser.parseField(MailConfiguration.getInstance().getText(), fromIP, toIP),
                (MailConfiguration.getInstance().isHTML()) ? "text/html" : "text/plain");

        return message;
    }

    public void sendMail(String fromIP, String toIP) throws MessagingException {
        try {
            MimeMessage message = this.getMessage(fromIP, toIP);
            Transport transport = session.getTransport();
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw e;
        } finally {
            try {
                printStream.close();
            } catch (Exception e) {
            }
            try {
                outputStream.close();
            } catch (Exception e) {
            }
        }
    }

    public String getLastMailDebugInformation() {
        try {
            return outputStream.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    private class SMTPAuthentication extends Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(MailConfiguration.getInstance().getUser(),
                    MailConfiguration.getInstance().getPassword());
        }
    }
}
