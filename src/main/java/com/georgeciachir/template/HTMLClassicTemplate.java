package com.georgeciachir.template;

import com.georgeciachir.resourcelocator.ResourceLocator;

import java.util.Objects;

public final class HTMLClassicTemplate implements Template {

    private final String body;
    private final String disclaimer;
    private final ResourceLocator resourceLocator;

    private HTMLClassicTemplate(Builder builder) {
        this.body = builder.getBody();
        this.disclaimer = builder.getDisclaimer();
        this.resourceLocator = builder.getResourceLocator();
    }

    @Override
    public String getContent() {
        String templatedBody = String.format(resourceLocator.getBodyTemplate(TemplateType.CLASSIC), body);
        if (disclaimer.isEmpty()) {
            return templatedBody;
        }
        String templatedDisclaimer = String.format(resourceLocator.getDisclaimerTemplate(), disclaimer);
        String bodyAndDisclaimer = templatedBody.concat(templatedDisclaimer);
        return String.format(resourceLocator.getFullTemplate(), bodyAndDisclaimer);
    }


    public static TemplateBuilder builder(ResourceLocator resourceLocator) {
        return new HTMLNewTemplate.Builder(resourceLocator);
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
            return this;
        }

        @Override
        public Builder withDisclaimer(String disclaimer) {
            return this;
        }

        @Override
        public Template build() {
            this.body = Objects.isNull(body) ? "" : body;
            this.disclaimer = Objects.isNull(disclaimer) ? "" : disclaimer;
            validate();
            return new HTMLClassicTemplate(this);
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
