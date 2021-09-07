package com.georgeciachir.infrastructure.crypto;

import com.georgeciachir.infrastructure.crypto.encryptor.AESEncryptor;
import com.georgeciachir.infrastructure.crypto.encryptor.DESEncryptor;
import com.georgeciachir.infrastructure.crypto.encryptor.Encryptor;
import com.georgeciachir.infrastructure.crypto.encryptor.NONEncryptor;
import com.georgeciachir.infrastructure.exception.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

import static com.georgeciachir.infrastructure.crypto.EncryptionType.AES;
import static com.georgeciachir.infrastructure.crypto.EncryptionType.DES;
import static com.georgeciachir.infrastructure.crypto.EncryptionType.NO_ENCRYPTION;

public class EncryptorProvider {

    private static final Map<EncryptionType, Encryptor> ENCRYPTORS;

    static {
        ENCRYPTORS = new HashMap<>();
        ENCRYPTORS.put(AES, new AESEncryptor());
        ENCRYPTORS.put(DES, new DESEncryptor());
        ENCRYPTORS.put(NO_ENCRYPTION, new NONEncryptor());
    }

    public static Encryptor encryptorFor(EncryptionType type) {
        Encryptor encryptor = ENCRYPTORS.get(type);
        if (encryptor == null) {
            throw new NotImplementedException("The EncryptorProvider must be updated for the specified type");
        }

        return encryptor;
    }
}
