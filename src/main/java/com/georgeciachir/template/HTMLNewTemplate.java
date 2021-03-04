package com.georgeciachir.template;

import com.georgeciachir.resourcelocator.ResourceLocator;

import java.util.Objects;

public final class HTMLNewTemplate implements Template {

    private final String body;
    private final String disclaimer;
    private final ResourceLocator resourceLocator;

    private HTMLNewTemplate(Builder builder) {
        this.body = builder.getBody();
        this.disclaimer = builder.getDisclaimer();
        this.resourceLocator = builder.getResourceLocator();
    }

    @Override
    public String getContent() {
        String templatedBody = String.format(resourceLocator.getBodyTemplate(TemplateType.NEW_MODEL), body);
        if (disclaimer.isEmpty()) {
            return templatedBody;
        }
        String templatedDisclaimer = String.format(resourceLocator.getDisclaimerTemplate(), disclaimer);
        String bodyAndDisclaimer = templatedBody.concat(templatedDisclaimer);
        return String.format(resourceLocator.getFullTemplate(), bodyAndDisclaimer);
    }

    public static TemplateBuilder builder(ResourceLocator resourceLocator) {
        return new Builder(resourceLocator);
    }

    static class Builder implements TemplateBuilder {

        private final ResourceLocator resourceLocator;
        private String body;
        private String disclaimer;

        Builder(ResourceLocator resourceLocator) {
            this.resourceLocator = resourceLocator;
        }

        @Override
        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        @Override
        public Builder withDisclaimer(String disclaimer) {
            this.disclaimer = disclaimer;
            return this;
        }

        @Override
        public Template build() {
            this.body = Objects.isNull(body) ? "" : body;
            this.disclaimer = Objects.isNull(disclaimer) ? "" : disclaimer;
            validate();
            return new HTMLNewTemplate(this);
        }

        public String getBody() {
            return body;
        }

        public String getDisclaimer() {
            return disclaimer;
        }

        @Override
        public ResourceLocator getResourceLocator() {
            return resourceLocator;
        }
    }
}
