package com.georgeciachir.email.creation.template;

public enum HtmlTemplateType {

    CLASSIC("classicTemplate.html"),
    NEW_MODEL("newModelTemplate.html");

    private final String fileLocation;

    HtmlTemplateType(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        return fileLocation;
    }
}
