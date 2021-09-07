package com.georgeciachir.infrastructure.crypto.encryptor;

public class AESEncryptor implements Encryptor {

    @Override
    public String apply(String content) {
        return "AES encryption applied over -> " + content;
    }
}
