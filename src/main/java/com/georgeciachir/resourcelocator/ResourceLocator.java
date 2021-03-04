package com.georgeciachir.resourcelocator;

import com.georgeciachir.template.TemplateType;

public interface ResourceLocator {

    String getBodyTemplate(TemplateType templateType);

    String getDisclaimerTemplate();

    String getFullTemplate();

    String getDisclaimer();
}
