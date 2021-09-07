package com.georgeciachir.infrastructure;

public class TestResult {

    private final String testName;
    private final boolean passed;
    private String failReason;

    public static TestResult passed(String testName) {
        return new TestResult(testName);
    }

    public static TestResult failed(String testName, String failReason) {
        return new TestResult(testName, failReason);
    }

    private TestResult(String testName, String failReason) {
        this.passed = false;
        this.testName = testName;
        this.failReason = failReason;
    }

    private TestResult(String testName) {
        this.passed = true;
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }

    public boolean isPassed() {
        return passed;
    }

    public String getFailReason() {
        return failReason;
    }
}
