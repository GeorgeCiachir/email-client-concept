package com.georgeciachir.crypto;

public class DESEncryptor implements Encryptor {

    @Override
    public String encrypt(String content) {
        return "DES encryption applied over -> " + content;
    }
}
