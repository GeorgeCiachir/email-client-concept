package com.georgeciachir.crypto;

import com.georgeciachir.testframework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static com.georgeciachir.crypto.EncryptionStrategy.encryptionFor;
import static com.georgeciachir.testframework.Assert.assertEquals;


public class EncryptionStrategyTest {

    //TODO: create an annotation processor to read a @Test annotation from each method
    public List<TestCase> getTests() {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("testNoEncryption", this::testNoEncryption));
        testCases.add(new TestCase("testMultipleEncryption", this::testMultipleEncryption));
        return testCases;
    }

    private void testNoEncryption() {
        //given
        EncryptionStrategy encryptionStrategy = EncryptionStrategy.NONE;
        String expected = "expected";

        //when
        String actual = encryptionStrategy.encrypt(expected);

        //then
        assertEquals(actual, expected);
    }

    private void testMultipleEncryption() {
        //given
        EncryptionStrategy encryptionStrategy = encryptionFor(EncryptionType.AES).thenApply(EncryptionType.DES);
        String content = "content";
        String firstEncryption = new AESEncryptor().encrypt(content);
        String secondEncryption = new DESEncryptor().encrypt(firstEncryption);

        //when
        String actual = encryptionStrategy.encrypt(content);

        //then
        assertEquals(actual, secondEncryption);
    }
}
