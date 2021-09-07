package com.georgeciachir.infrastructure.crypto.encryptor;

public class NONEncryptor implements Encryptor {

    @Override
    public String apply(String content) {
        return content;
    }
}
