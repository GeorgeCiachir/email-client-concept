package com.georgeciachir.infrastructure;

public class TestCase {

    private final String testName;
    private final Runnable runnable;

    public TestCase(String testName, Runnable runnable) {
        this.testName = testName;
        this.runnable = runnable;
    }

    public String getTestName() {
        return testName;
    }

    public Runnable getRunnableMethod() {
        return runnable;
    }
}
