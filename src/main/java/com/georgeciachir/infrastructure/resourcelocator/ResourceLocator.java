package com.georgeciachir.infrastructure.resourcelocator;

import com.georgeciachir.email.creation.template.HtmlTemplateType;

public interface ResourceLocator {

    String getBodyTemplate(HtmlTemplateType templateType);

    String getDisclaimerTemplate();

    String getFullTemplate();

    String getDisclaimer();
}
