package com.georgeciachir.crypto;

import com.georgeciachir.TestCase;

import java.util.ArrayList;
import java.util.List;

import static com.georgeciachir.Assert.assertEquals;


public class EncryptionStrategyTest {

    public List<TestCase> getTests() {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("testNoEncryption", this::testNoEncryption));
        testCases.add(new TestCase("testMultipleEncryption", this::testMultipleEncryption));
        return testCases;
    }

    private void testNoEncryption() {
        //given
        EncryptionStrategy encryptionStrategy = new EncryptionStrategy();
        String expected = "expected";

        //when
        String actual = encryptionStrategy.apply(expected);

        //then
        assertEquals(actual, expected);
    }

    private void testMultipleEncryption() {
        //given
        EncryptionStrategy encryptionStrategy = new EncryptionStrategy().with(EncryptionType.AES).with(EncryptionType.DES);
        String content = "content";
        String firstEncryption = new AESEncryptor().encrypt(content);
        String secondEncryption = new DESEncryptor().encrypt(firstEncryption);

        //when
        String actual = encryptionStrategy.apply(content);

        //then
        assertEquals(actual, secondEncryption);
    }
}