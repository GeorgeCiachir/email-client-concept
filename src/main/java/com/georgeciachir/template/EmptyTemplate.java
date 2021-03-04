package com.georgeciachir.template;

import com.georgeciachir.resourcelocator.ResourceLocator;

import java.util.Objects;

public class EmptyTemplate implements Template {

    private final String body;
    private final String disclaimer;

    private EmptyTemplate(Builder builder) {
        this.body = builder.getBody();
        this.disclaimer = builder.getDisclaimer();
    }

    @Override
    public String getContent() {
        if (disclaimer.isEmpty()) {
            return body;
        }
        return body.concat(" and ").concat(disclaimer);
    }

    public static TemplateBuilder builder() {
        return new Builder();
    }

    static class Builder implements TemplateBuilder {

        private String body;
        private String disclaimer;

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
            return new EmptyTemplate(this);
        }

        public String getBody() {
            return body;
        }

        public String getDisclaimer() {
            return disclaimer;
        }

        @Override
        public ResourceLocator getResourceLocator() {
            return null;
        }
    }
}
