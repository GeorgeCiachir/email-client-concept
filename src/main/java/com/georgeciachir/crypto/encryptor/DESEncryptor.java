package com.georgeciachir.crypto.encryptor;

public class DESEncryptor implements Encryptor {

    @Override
    public String apply(String content) {
        return "DES encryption applied over -> " + content;
    }
}
