package com.georgeciachir.template;

import com.georgeciachir.resourcelocator.ResourceLocator;

import java.util.Objects;

public interface TemplateBuilder {

    TemplateBuilder withBody(String body);

    TemplateBuilder withDisclaimer(String disclaimer);

    Template build();

    default void validate() {
        Objects.requireNonNull(getResourceLocator(), "The resource locator should be provided for the template");
    }

    ResourceLocator getResourceLocator();
}
