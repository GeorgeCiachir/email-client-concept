package com.georgeciachir.resourcelocator;

public class ResourceLocatorProvider {

    public static ResourceLocator getResourceLocator() {
        // do not expose the implementation in this public method
        return getLocator();
    }

    private static ResourceLocator getLocator() {
        return new FolderResourceLocator();
    }
}
