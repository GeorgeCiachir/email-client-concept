package com.georgeciachir.resourcelocator;

import com.georgeciachir.template.HtmlTemplateType;

public interface ResourceLocator {

    String getBodyTemplate(HtmlTemplateType templateType);

    String getDisclaimerTemplate();

    String getFullTemplate();

    String getDisclaimer();
}
