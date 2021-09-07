package com.georgeciachir.template;

import com.georgeciachir.infrastructure.NotImplementedException;
import com.georgeciachir.resourcelocator.ResourceLocator;
import com.georgeciachir.resourcelocator.ResourceLocatorProvider;

import java.util.HashMap;
import java.util.Map;

import static com.georgeciachir.template.EmptyTemplate.emptyTemplate;
import static com.georgeciachir.template.HtmlTemplate.htmlTemplate;
import static com.georgeciachir.template.TemplateType.CLASSIC;
import static com.georgeciachir.template.TemplateType.NEW_MODEL;
import static com.georgeciachir.template.TemplateType.NONE;

public class TemplateProvider {

    private static final ResourceLocator RESOURCE_LOCATOR = ResourceLocatorProvider.getResourceLocator();
    private static final Map<TemplateType, Template> TEMPLATES;

    static {
        TEMPLATES = new HashMap<>();
        TEMPLATES.put(CLASSIC, htmlTemplate(RESOURCE_LOCATOR));
        TEMPLATES.put(NEW_MODEL, htmlTemplate(RESOURCE_LOCATOR));
        TEMPLATES.put(NONE, emptyTemplate());
    }

    public static Template templateFor(TemplateType type) {
        Template template = TEMPLATES.get(type);
        if (template == null) {
            throw new NotImplementedException("The TemplateFactory must be updated for the specified type");
        }

        return template;
    }
}
