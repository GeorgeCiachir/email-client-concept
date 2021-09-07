package com.georgeciachir.crypto;

import com.georgeciachir.infrastructure.crypto.encryptor.AESEncryptor;
import com.georgeciachir.infrastructure.crypto.encryptor.DESEncryptor;
import com.georgeciachir.infrastructure.crypto.Encryption;
import com.georgeciachir.infrastructure.crypto.EncryptionType;
import com.georgeciachir.infrastructure.TestCase;

import java.util.ArrayList;
import java.util.List;

import static com.georgeciachir.infrastructure.crypto.Encryption.encrypt;
import static com.georgeciachir.infrastructure.Assert.assertEquals;


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
        Encryption encryption = Encryption.NONE;
        String expected = "expected";

        //when
        String actual = encryption.apply(expected);

        //then
        assertEquals(actual, expected);
    }

    private void testMultipleEncryption() {
        //given
        Encryption encryption = encrypt(EncryptionType.AES).thenEncrypt(EncryptionType.DES);
        String content = "content";
        String firstEncryption = new AESEncryptor().apply(content);
        String secondEncryption = new DESEncryptor().apply(firstEncryption);

        //when
        String actual = encryption.apply(content);

        //then
        assertEquals(actual, secondEncryption);
    }
}
