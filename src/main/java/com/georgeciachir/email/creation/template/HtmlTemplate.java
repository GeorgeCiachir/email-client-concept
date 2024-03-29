package com.georgeciachir.email.creation.template;

import com.georgeciachir.email.creation.DraftContent;
import com.georgeciachir.infrastructure.resourcelocator.ResourceLocator;

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
    public String createContent(DraftContent draftContent, String disclaimer) {
        String body = draftContent.getMessage();
        String template = getTemplate(draftContent.getHtmlTemplateType());
        String templatedBody = String.format(template, body);

        String bodyAndDisclaimer = templatedBody;
        if (!Objects.isNull(disclaimer) && !disclaimer.isEmpty()) {
            String templatedDisclaimer = String.format(resourceLocator.getDisclaimerTemplate(), disclaimer);
            bodyAndDisclaimer = templatedBody.concat(templatedDisclaimer);
        }

        return String.format(resourceLocator.getFullTemplate(), bodyAndDisclaimer);
    }

    private String getTemplate(HtmlTemplateType type) {
        return resourceLocator.getBodyTemplate(type);
    }
}
