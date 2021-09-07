package com.georgeciachir.email.creation;

import com.georgeciachir.crypto.Encryption;
import com.georgeciachir.email.client.RetryPolicy;
import com.georgeciachir.template.TemplateType;

import java.util.Objects;
import java.util.UUID;

import static com.georgeciachir.email.creation.ContentAssembler.contentAssembler;

public final class Draft {

    private final UUID id;
    private final StringBuilder message;
    private final TemplateType templateType;
    private final String emailAddress;
    private final RetryPolicy retryPolicy;
    private final Encryption encryption;

    private Draft(DraftBuilder draftBuilder) {
        this.id = UUID.randomUUID();
        this.message = draftBuilder.getMessage();
        this.templateType = draftBuilder.getTemplateType();
        this.emailAddress = draftBuilder.getEmailAddress();
        this.retryPolicy = draftBuilder.getRetryPolicy();
        this.encryption = draftBuilder.getEncryptionStrategy();
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return contentAssembler().assembleFrom(this);
    }

    public boolean isExternalEmail() {
        return !this.emailAddress.endsWith("@company.com");
    }

    public StringBuilder getMessage() {
        return message;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public Encryption getEncryptionStrategy() {
        return encryption;
    }

    public TemplateType getTemplateType() {
        return templateType;
    }

    public static Draft draftFrom(TemplateType templateType,
                                  String content,
                                  String to,
                                  RetryPolicy retryPolicy,
                                  Encryption encryption) {
        return Draft.builder()
                .withTemplate(templateType)
                .withMessage(content)
                .withEmailAddress(to)
                .withRetryPolicy(retryPolicy)
                .withEncryptionStrategy(encryption)
                .build();
    }

    public static DraftBuilder builder() {
        return new DraftBuilder();
    }

    public static class DraftBuilder {

        private StringBuilder message;
        private String emailAddress;
        private TemplateType templateType;
        private RetryPolicy retryPolicy;
        private Encryption encryption;

        public DraftBuilder withMessage(String message) {
            this.message = new StringBuilder(message);
            return this;
        }

        public DraftBuilder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public DraftBuilder withTemplate(TemplateType templateType) {
            this.templateType = templateType;
            return this;
        }

        public DraftBuilder withRetryPolicy(RetryPolicy retryPolicy) {
            this.retryPolicy = retryPolicy;
            return this;
        }

        public DraftBuilder withEncryptionStrategy(Encryption encryption) {
            this.encryption = encryption;
            return this;
        }

        public Draft build() {
            if (Objects.isNull(templateType)) {
                templateType = TemplateType.NONE;
            }
            if (Objects.isNull(retryPolicy)) {
                retryPolicy = RetryPolicy.NONE;
            }
            if (Objects.isNull(encryption)) {
                encryption = Encryption.NONE;
            }
            return new Draft(this);
        }

        public StringBuilder getMessage() {
            return message;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public TemplateType getTemplateType() {
            return templateType;
        }

        public RetryPolicy getRetryPolicy() {
            return retryPolicy;
        }

        public Encryption getEncryptionStrategy() {
            return encryption;
        }
    }

}
