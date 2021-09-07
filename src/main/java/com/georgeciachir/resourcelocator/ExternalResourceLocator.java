package com.georgeciachir.resourcelocator;

import com.georgeciachir.infrastructure.NotImplementedException;
import com.georgeciachir.template.HtmlTemplateType;

public class ExternalResourceLocator implements ResourceLocator {

    @Override
    public String getBodyTemplate(HtmlTemplateType templateType) {
        throw new NotImplementedException("Not yet implemented");
    }

    @Override
    public String getDisclaimerTemplate() {
        throw new NotImplementedException("Not yet implemented");
    }

    @Override
    public String getFullTemplate() {
        throw new NotImplementedException("Not yet implemented");
    }

    @Override
    public String getDisclaimer() {
        throw new NotImplementedException("Not yet implemented");
    }
}
