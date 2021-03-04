package com.georgeciachir.email.client;

import java.util.UUID;

public interface EmailClient {

    default boolean send(UUID uuid, String emailAddress, String content) {
        return sendInternal(uuid, emailAddress, content);
    }

    boolean sendInternal(UUID uuid, String emailAddress, String content);
}
