package com.georgeciachir.email.service;

import com.georgeciachir.email.client.EmailClientStrategy;
import com.georgeciachir.email.creation.Email;

public class DefaultEmailService implements EmailService {

    public void send(Email email) {
        String encryptedContend = email.getEncryptedContent();

        logExternalEmail(email);

        boolean isSent = EmailClientStrategy
                .getClient(email.getRetryPolicy())
                .send(email.getId(), email.getEmailAddress(), encryptedContend);

        email.setSuccessfullySent(isSent);
    }

    private void logExternalEmail(Email email) {
        if (email.isExternalEmail()) {
            System.out.println("Sending external email with id: " + email.getId() + " to email address: " + email.getEmailAddress());
            System.out.println("The content of the email is: " + email.getEncryptedContent());
            System.out.println("**************************");
            System.out.println();
        }
    }
}
