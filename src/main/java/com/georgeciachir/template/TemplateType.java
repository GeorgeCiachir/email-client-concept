package com.georgeciachir.template;

public enum TemplateType {

    CLASSIC("classicTemplate.html"),
    NEW_MODEL("newModelTemplate.html"),
    NONE("");

    private final String fileLocation;

    TemplateType(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        return fileLocation;
    }
}
