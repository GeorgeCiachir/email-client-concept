package com.georgeciachir.template;

import com.georgeciachir.infrastructure.NotImplementedException;
import com.georgeciachir.resourcelocator.FolderResourceLocator;
import com.georgeciachir.resourcelocator.ResourceLocator;

public class TemplateFactory {

    private static final ResourceLocator RESOURCE_LOCATOR = new FolderResourceLocator();

    public static TemplateBuilder builderFor(TemplateType selector) {
        switch (selector) {
            case CLASSIC:
                return HTMLClassicTemplate.builder(RESOURCE_LOCATOR);
            case NEW_MODEL:
                return HTMLNewTemplate.builder(RESOURCE_LOCATOR);
            case NONE:
                return EmptyTemplate.builder();
            default:
                throw new NotImplementedException("");
        }
    }
}
