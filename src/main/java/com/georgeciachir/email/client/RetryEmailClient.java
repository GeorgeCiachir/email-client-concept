package com.georgeciachir.email.client;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RetryEmailClient implements EmailClient {

    private static final int MAX_ATTEMPTS = 4;

    @Override
    public boolean sendInternal(UUID uuid, String emailAddress, String content) {
        boolean emailSent = false;

        int retriesLeft = MAX_ATTEMPTS;

        while (!emailSent && retriesLeft > 0) {
            emailSent = doSend(uuid, emailAddress, content);
            retriesLeft--;
        }

        return emailSent;
    }

    boolean doSend(UUID uuid, String emailAddress, String content) {
        System.out.println("Attempting to send email with id: " + uuid);
        // Do the actual sending using the content and email address
        return ThreadLocalRandom.current().nextBoolean();
    }
}
