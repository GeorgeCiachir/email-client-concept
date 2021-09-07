package com.georgeciachir.template;

public interface Template {

    String createContent(TemplateType type, String content, String disclaimer);
}
