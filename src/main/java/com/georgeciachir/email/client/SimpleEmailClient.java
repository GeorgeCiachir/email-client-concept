package com.georgeciachir.email.client;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleEmailClient implements EmailClient {

    @Override
    public boolean sendInternal(UUID uuid, String emailAddress, String content) {
        return doSend(uuid, emailAddress, content);
    }

    boolean doSend(UUID uuid, String emailAddress, String content) {
        System.out.println("Attempting to send email with id: " + uuid);
        // Do the actual sending using the content and email address
        return ThreadLocalRandom.current().nextBoolean();
    }
}
