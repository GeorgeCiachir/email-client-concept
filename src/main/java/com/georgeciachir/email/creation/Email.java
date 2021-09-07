package com.georgeciachir.email.creation;

import com.georgeciachir.crypto.EncryptionStrategy;
import com.georgeciachir.email.client.RetryPolicy;

import java.util.UUID;

public final class Email {

    private final UUID id;
    private final String content;
    private final RetryPolicy retryPolicy;
    private final EncryptionStrategy encryptionStrategy;
    private final String emailAddress;
    private boolean successfullySent;

    private Email(Draft draft) {
        this.id = draft.getId();
        this.content = draft.getContent();
        this.retryPolicy = draft.getRetryPolicy();
        this.encryptionStrategy = draft.getEncryptionStrategy();
        this.emailAddress = draft.getEmailAddress();
    }

    public static Email emailFrom(Draft draft) {
        return new Email(draft);
    }

    public UUID getId() {
        return id;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getEncryptedContent() {
        return encryptionStrategy.encrypt(content);
    }

    public boolean isExternalEmail() {
        return !this.emailAddress.endsWith("@company.com");
    }

    public boolean isSuccessfullySent() {
        return successfullySent;
    }

    public void setSuccessfullySent(boolean successfullySent) {
        this.successfullySent = successfullySent;
    }
}
