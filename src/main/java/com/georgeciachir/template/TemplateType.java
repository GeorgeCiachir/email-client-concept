package com.georgeciachir.template;

public enum TemplateType {

    CLASSIC("classicBodyTemplate.html"),
    NEW_MODEL("newModelBodylTemplate.html"),
    NONE("");

    private final String fileLocation;

    TemplateType(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        return fileLocation;
    }
}
