package com.georgeciachir.email.client;

import java.util.HashMap;
import java.util.Map;

import static com.georgeciachir.email.client.RetryPolicy.NONE;
import static com.georgeciachir.email.client.RetryPolicy.RETRY;

public class EmailClientStrategy {

    private static final Map<RetryPolicy, EmailClient> CLIENTS = new HashMap<>();

    static {
        CLIENTS.put(RETRY, new RetryEmailClient());
        CLIENTS.put(NONE, new SimpleEmailClient());
    }

    public static EmailClient getClient(RetryPolicy retryPolicy) {
        return CLIENTS.get(retryPolicy);
    }
}
