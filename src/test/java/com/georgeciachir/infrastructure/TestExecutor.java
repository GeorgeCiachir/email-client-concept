package com.georgeciachir.infrastructure;

import com.georgeciachir.crypto.EncryptionStrategyTest;
import com.georgeciachir.email.client.RetryEmailClientTest;
import com.georgeciachir.email.creation.EmailTest;

import java.util.ArrayList;
import java.util.List;

import static com.georgeciachir.infrastructure.TestResult.failed;
import static com.georgeciachir.infrastructure.TestResult.passed;

public class TestExecutor {

    public static void main(String[] args) {
        //TODO: this should be provided by an annotation processor that reads a @Test annotation from each test method
        List<TestCase> testCases = new ArrayList<>();
        testCases.addAll(new EncryptionStrategyTest().getTests());
        testCases.addAll(new RetryEmailClientTest().getTests());
        testCases.addAll(new EmailTest().getTests());

        TestReport testReport = testCases.stream()
                .map(TestExecutor::runTest)
                .collect(TestReport::new, TestReport::addTestResult, TestReport::addAll);

        System.out.println(testReport);
    }

    private static TestResult runTest(TestCase testCase) {
        String testName = testCase.getTestName();

        try {
            testCase.getRunnableMethod().run();
            return passed(testName);
        } catch (AssertionError e) {
            return failed(testName, e.getMessage());
        }
    }
}
