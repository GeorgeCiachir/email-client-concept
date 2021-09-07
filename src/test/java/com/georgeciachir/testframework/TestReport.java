package com.georgeciachir.testframework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestReport {

    private final List<String> passed = new ArrayList<>();
    private final Map<String, String> failed = new HashMap<>();

    public void addTestResult(TestResult testResult) {
        if (testResult.isPassed()) {
            addPassed(testResult.getTestName());
        } else {
            addFailed(testResult.getTestName(), testResult.getFailReason());
        }
    }

    public void addAll(TestReport other) {
        this.passed.addAll(other.getPassed());
        this.failed.putAll(other.getFailed());
    }

    private void addPassed(String testName) {
        passed.add(testName);
    }

    private void addFailed(String testName, String cause) {
        failed.put(testName, cause);
    }

    public List<String> getPassed() {
        return passed;
    }

    public Map<String, String> getFailed() {
        return failed;
    }

    @Override
    public String toString() {
        String passed = "Passed tests = " + this.passed;
        String failed = "Failed tests = " + this.failed;
        return passed + "\n" + failed;
    }
}
