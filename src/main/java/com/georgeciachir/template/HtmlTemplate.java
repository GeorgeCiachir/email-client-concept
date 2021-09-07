package com.georgeciachir.template;

import com.georgeciachir.resourcelocator.ResourceLocator;

import java.util.Objects;

public final class HtmlTemplate implements Template {

    private final ResourceLocator resourceLocator;

    private HtmlTemplate(ResourceLocator resourceLocator) {
        this.resourceLocator = resourceLocator;
    }

    public static HtmlTemplate htmlTemplate(ResourceLocator resourceLocator) {
        return new HtmlTemplate(resourceLocator);
    }

    @Override
    public String createContent(TemplateType type, String content, String disclaimer) {
        String body = Objects.isNull(content) ? "" : content;
        String template = getTemplate(type);
        String templatedBody = String.format(template, body);

        String bodyAndDisclaimer = templatedBody;
        if (!Objects.isNull(disclaimer) && !disclaimer.isEmpty()) {
            String templatedDisclaimer = String.format(resourceLocator.getDisclaimerTemplate(), disclaimer);
            bodyAndDisclaimer = templatedBody.concat(templatedDisclaimer);
        }

        return String.format(resourceLocator.getFullTemplate(), bodyAndDisclaimer);
    }

    private String getTemplate(TemplateType type) {
        return resourceLocator.getBodyTemplate(type);
    }
}
