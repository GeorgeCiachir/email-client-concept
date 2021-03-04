package com.georgeciachir.email.creation;

import com.georgeciachir.resourcelocator.FolderResourceLocator;
import com.georgeciachir.resourcelocator.ResourceLocator;
import com.georgeciachir.template.TemplateFactory;

public class ContentAssembler {

    private final ResourceLocator resourceLocator = new FolderResourceLocator();

    private ContentAssembler() {
    }

    public static ContentAssembler contentAssembler() {
        return new ContentAssembler();
    }

    public String assembleFrom(Draft draft) {
        String disclaimer = draft.isExternalEmail()
                ? resourceLocator.getDisclaimer()
                : "";

        return TemplateFactory.builderFor(draft.getTemplateType())
                .withBody(draft.getMessage().toString())
                .withDisclaimer(disclaimer)
                .build()
                .getContent();
    }
}
