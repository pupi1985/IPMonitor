/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification.performers;

import javax.mail.*;
import javax.mail.internet.*;

import model.notification.configuration.MailConfiguration;

public class MailPerformer extends AbstractPerformer {

    private static MailPerformer instance;

    private MailPerformer() {
    }

    public static MailPerformer getInstance() {
        if (instance == null) {
            instance = new MailPerformer();
        }
        return instance;
    }

    public void sendMail(String fromIP, String toIP) throws MessagingException {
        try {
            MimeMessage message = MailConfiguration.getInstance().getMessage(
                    fromIP, toIP);
            Transport transport = MailConfiguration.getInstance().getTransport();
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw e;
        }
    }
}
