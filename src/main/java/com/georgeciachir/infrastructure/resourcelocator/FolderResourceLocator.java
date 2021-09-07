package com.georgeciachir.infrastructure.resourcelocator;

import com.georgeciachir.infrastructure.exception.ResourceNotFoundException;
import com.georgeciachir.email.creation.template.HtmlTemplateType;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderResourceLocator implements ResourceLocator {

    private static final String RESOURCE_NOT_FOUND_MESSAGE = "The requested resource was not found";

    public String getBodyTemplate(HtmlTemplateType templateType) {
        return getFromLocation(templateType.getFileLocation());
    }

    public String getDisclaimerTemplate() {
        return getFromLocation("disclaimerFooterTemplate.html");
    }

    public String getFullTemplate() {
        return getFromLocation("fullTemplate.html");
    }

    public String getDisclaimer() {
        return getFromLocation("disclaimer.txt");
    }

    private String getFromLocation(String location) {
        try {
            URL resource = getClass().getClassLoader().getResource(location);
            Path path = Paths.get(resource.toURI());
            return new String(Files.readAllBytes(path));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE, e);
        }

    }
}
