package com.georgeciachir.crypto.encryptor;

public class NONEncryptor implements Encryptor {

    @Override
    public String apply(String content) {
        return content;
    }
}
