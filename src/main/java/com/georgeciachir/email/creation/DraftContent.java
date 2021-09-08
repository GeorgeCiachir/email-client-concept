package com.georgeciachir.email.creation;

import com.georgeciachir.email.creation.template.HtmlTemplateType;
import com.georgeciachir.email.creation.template.TemplateType;

public class DraftContent {

    private final String message;
    private final TemplateType templateType;
    private final HtmlTemplateType htmlTemplateType;

    private DraftContent(String message, TemplateType templateType, HtmlTemplateType htmlTemplateType) {
        this.message = message;
        this.templateType = templateType;
        this.htmlTemplateType = htmlTemplateType;
    }

    public static DraftContent draftContent(String message, TemplateType templateType, HtmlTemplateType htmlTemplateType) {
        return new DraftContent(message, templateType, htmlTemplateType);
    }

    public String getMessage() {
        return message;
    }

    public TemplateType getTemplateType() {
        return templateType;
    }

    public HtmlTemplateType getHtmlTemplateType() {
        return htmlTemplateType;
    }
}
