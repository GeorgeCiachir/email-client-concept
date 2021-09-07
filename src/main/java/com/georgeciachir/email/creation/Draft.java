package com.georgeciachir.email.creation;

import com.georgeciachir.crypto.Encryption;
import com.georgeciachir.email.client.RetryPolicy;
import com.georgeciachir.template.HtmlTemplateType;
import com.georgeciachir.template.TemplateType;

import java.util.Objects;
import java.util.UUID;

import static com.georgeciachir.email.creation.ContentAssembler.contentAssembler;
import static com.georgeciachir.template.TemplateType.HTML;

public final class Draft {

    private final UUID id;
    private final String message;
    private final TemplateType templateType;
    private final HtmlTemplateType htmlTemplateType;
    private final String emailAddress;
    private final RetryPolicy retryPolicy;
    private final Encryption encryption;

    private Draft(DraftBuilder draftBuilder) {
        this.id = draftBuilder.getId();
        this.message = draftBuilder.getMessage();
        this.templateType = draftBuilder.getTemplateType();
        this.htmlTemplateType = draftBuilder.getHtmlTemplateType();
        this.emailAddress = draftBuilder.getEmailAddress();
        this.retryPolicy = draftBuilder.getRetryPolicy();
        this.encryption = draftBuilder.getEncryption();
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

    public String getMessage() {
        return message;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public TemplateType getTemplateType() {
        return templateType;
    }

    public HtmlTemplateType getHtmlTemplateType() {
        return htmlTemplateType;
    }

    public static Draft nonTemplatedDraft(TemplateType templateType,
                                          String content,
                                          String to,
                                          RetryPolicy retryPolicy,
                                          Encryption encryption) {
        return htmlTemplatedDraft(templateType, null, content, to, retryPolicy, encryption);
    }

    public static Draft htmlTemplatedDraft(TemplateType templateType,
                                           HtmlTemplateType htmlTemplateType,
                                           String content,
                                           String to,
                                           RetryPolicy retryPolicy,
                                           Encryption encryption) {
        return Draft.builder()
                .withTemplateType(templateType)
                .withHtmlTemplateType(htmlTemplateType)
                .withMessage(content)
                .withEmailAddress(to)
                .withRetryPolicy(retryPolicy)
                .withEncryption(encryption)
                .build();
    }

    public static DraftBuilder builder() {
        return new DraftBuilder();
    }

    //enable draft editing
    public DraftBuilder toBuilder() {
        return builder().fromExisting(this);
    }

    public static class DraftBuilder {

        private UUID id;
        private String message;
        private String emailAddress;
        private TemplateType templateType;
        private HtmlTemplateType htmlTemplateType;
        private RetryPolicy retryPolicy;
        private Encryption encryption;

        public DraftBuilder fromExisting(Draft draft) {
            return new DraftBuilder()
                    .withId(draft.getId())
                    .withMessage(draft.getMessage())
                    .withEmailAddress(draft.getEmailAddress())
                    .withTemplateType(draft.getTemplateType())
                    .withHtmlTemplateType(draft.getHtmlTemplateType())
                    .withRetryPolicy(draft.getRetryPolicy())
                    .withEncryption(draft.getEncryption());
        }

        private DraftBuilder() {
        }

        private DraftBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        private UUID getId() {
            return this.id != null
                    ? this.id
                    : UUID.randomUUID();
        }

        public DraftBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public DraftBuilder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public DraftBuilder withTemplateType(TemplateType templateType) {
            this.templateType = templateType;
            return this;
        }

        public DraftBuilder withHtmlTemplateType(HtmlTemplateType htmlTemplateType) {
            this.htmlTemplateType = htmlTemplateType;
            return this;
        }

        public DraftBuilder withRetryPolicy(RetryPolicy retryPolicy) {
            this.retryPolicy = retryPolicy;
            return this;
        }

        public DraftBuilder withEncryption(Encryption encryption) {
            this.encryption = encryption;
            return this;
        }

        public Draft build() {
            if (Objects.isNull(this.message)) {
                throw new IllegalStateException("The message can be empty, but not null");
            }

            if (HTML.equals(templateType) && Objects.isNull(htmlTemplateType)) {
                throw new IllegalStateException("A HTML template must be specified");
            }

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

        public String getMessage() {
            return message;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public TemplateType getTemplateType() {
            return templateType;
        }

        public HtmlTemplateType getHtmlTemplateType() {
            return htmlTemplateType;
        }

        public RetryPolicy getRetryPolicy() {
            return retryPolicy;
        }

        public Encryption getEncryption() {
            return encryption;
        }
    }

}
