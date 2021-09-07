package com.georgeciachir.crypto;

import com.georgeciachir.crypto.encryptor.Encryptor;

import java.util.function.Function;

import static com.georgeciachir.crypto.EncryptionType.NO_ENCRYPTION;
import static com.georgeciachir.crypto.EncryptorProvider.encryptorFor;

public class Encryption {

    public static final Encryption NONE = new Encryption(encryptorFor(NO_ENCRYPTION));
    private Function<String, String> encryptor;

    private Encryption(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    public static Encryption encrypt(EncryptionType type) {
        return new Encryption(encryptorFor(type));
    }

    public Encryption thenEncrypt(EncryptionType type) {
        encryptor = encryptor.andThen(encryptorFor(type));
        return this;
    }

    public String apply(String content) {
        return encryptor.apply(content);
    }
}
