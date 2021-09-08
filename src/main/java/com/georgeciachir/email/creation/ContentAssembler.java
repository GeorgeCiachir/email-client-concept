package com.georgeciachir.email.creation;

import static com.georgeciachir.email.creation.template.TemplateProvider.templateFor;
import static com.georgeciachir.infrastructure.resourcelocator.ResourceLocatorProvider.getResourceLocator;

public class ContentAssembler {

    private ContentAssembler() {
    }

    public static ContentAssembler contentAssembler() {
        return new ContentAssembler();
    }

    public String assembleFrom(DraftContent draftContent, boolean externalEmail) {
        String disclaimer = externalEmail
                ? getResourceLocator().getDisclaimer()
                : "";

        return templateFor(draftContent.getTemplateType())
                .createContent(draftContent, disclaimer);
    }
}
