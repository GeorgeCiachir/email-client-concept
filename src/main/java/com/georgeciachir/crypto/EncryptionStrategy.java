package com.georgeciachir.crypto;

import java.util.ArrayList;
import java.util.List;

import static com.georgeciachir.crypto.EncryptionType.AES;
import static com.georgeciachir.crypto.EncryptionType.DES;

public class EncryptionStrategy {

    public static final EncryptionStrategy NONE = new EncryptionStrategy();
    private final List<Encryptor> encryptors = new ArrayList<>();

    private EncryptionStrategy() {
    }

    public static EncryptionStrategy encryptionFor(EncryptionType type) {
        EncryptionStrategy encryptionStrategy = new EncryptionStrategy();
        if (AES.equals(type)) {
            encryptionStrategy.encryptors.add(new AESEncryptor());
        } else if (DES.equals(type)) {
            encryptionStrategy.encryptors.add(new DESEncryptor());
        }
        return encryptionStrategy;
    }

    public EncryptionStrategy thenApply(EncryptionType type) {
        if (AES.equals(type)) {
            encryptors.add(new AESEncryptor());
        } else if (DES.equals(type)) {
            encryptors.add(new DESEncryptor());
        }
        return this;
    }

    public String encrypt(String content) {
        return encryptors.stream()
                .reduce(content,
                        (oldValue, encryptor) -> encryptor.encrypt(oldValue),
                        String::concat);
    }
}
