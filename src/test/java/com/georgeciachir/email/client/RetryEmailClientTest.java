package com.georgeciachir.email.client;

import com.georgeciachir.testframework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.georgeciachir.testframework.Assert.assertEquals;


public class RetryEmailClientTest {

    //TODO: create an annotation processor to read a @Test annotation from each method
    public List<TestCase> getTests() {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("testEmailSendingRetried", this::testEmailSendingRetried));
        return testCases;
    }

    private void testEmailSendingRetried() {
        //given
        NeverSendingEmailClient retryEmailClient = new NeverSendingEmailClient();
        String content = "content";
        String emailAddress = "email@yahoo.com";

        //when
        retryEmailClient.send(UUID.randomUUID(), emailAddress, content);

        //then
        assertEquals(4, retryEmailClient.getAttempts());
    }

    static class NeverSendingEmailClient extends RetryEmailClient {

        int attempts = 0;

        @Override
        boolean doSend(UUID uuid, String emailAddress, String content) {
            attempts++;
            return false;
        }

        int getAttempts() {
            return attempts;
        }
    }
}
