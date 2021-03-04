package com.georgeciachir.crypto;

public class AESEncryptor implements Encryptor {

    @Override
    public String encrypt(String content) {
        return "AES encryption applied over -> " + content;
    }
}
