package com.georgeciachir.email.creation;

import static com.georgeciachir.infrastructure.resourcelocator.ResourceLocatorProvider.getResourceLocator;
import static com.georgeciachir.email.creation.template.TemplateProvider.templateFor;

public class ContentAssembler {

    private ContentAssembler() {
    }

    public static ContentAssembler contentAssembler() {
        return new ContentAssembler();
    }

    public String assembleFrom(Draft draft) {
        String disclaimer = draft.isExternalEmail()
                ? getResourceLocator().getDisclaimer()
                : "";

        return templateFor(draft.getTemplateType())
                .createContent(draft, disclaimer);
    }
}
