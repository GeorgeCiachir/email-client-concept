package com.georgeciachir.email.creation.template;

import com.georgeciachir.infrastructure.exception.NotImplementedException;
import com.georgeciachir.infrastructure.resourcelocator.ResourceLocator;
import com.georgeciachir.infrastructure.resourcelocator.ResourceLocatorProvider;

import java.util.HashMap;
import java.util.Map;

import static com.georgeciachir.email.creation.template.EmptyTemplate.emptyTemplate;
import static com.georgeciachir.email.creation.template.HtmlTemplate.htmlTemplate;
import static com.georgeciachir.email.creation.template.TemplateType.HTML;
import static com.georgeciachir.email.creation.template.TemplateType.NONE;

public class TemplateProvider {

    private static final ResourceLocator RESOURCE_LOCATOR = ResourceLocatorProvider.getResourceLocator();
    private static final Map<TemplateType, Template> TEMPLATES;

    static {
        TEMPLATES = new HashMap<>();
        TEMPLATES.put(HTML, htmlTemplate(RESOURCE_LOCATOR));
        TEMPLATES.put(NONE, emptyTemplate());
    }

    public static Template templateFor(TemplateType type) {
        Template template = TEMPLATES.get(type);
        if (template == null) {
            throw new NotImplementedException("The TemplateProvider must be updated for the specified type");
        }

        return template;
    }
}
