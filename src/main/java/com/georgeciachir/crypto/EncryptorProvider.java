package com.georgeciachir.crypto;

import com.georgeciachir.crypto.encryptor.AESEncryptor;
import com.georgeciachir.crypto.encryptor.DESEncryptor;
import com.georgeciachir.crypto.encryptor.Encryptor;
import com.georgeciachir.crypto.encryptor.NONEncryptor;
import com.georgeciachir.infrastructure.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

import static com.georgeciachir.crypto.EncryptionType.AES;
import static com.georgeciachir.crypto.EncryptionType.DES;
import static com.georgeciachir.crypto.EncryptionType.NO_ENCRYPTION;

public class EncryptorProvider {

    private static final Map<EncryptionType, Encryptor> encryptors;

    static {
        encryptors = new HashMap<>();
        encryptors.put(AES, new AESEncryptor());
        encryptors.put(DES, new DESEncryptor());
        encryptors.put(NO_ENCRYPTION, new NONEncryptor());
    }

    public static Encryptor encryptorFor(EncryptionType type) {
        Encryptor encryptor = encryptors.get(type);
        if (encryptor == null) {
            throw new NotImplementedException("The EncryptorProvider must be updated for the specified type");
        }

        return encryptor;
    }
}
