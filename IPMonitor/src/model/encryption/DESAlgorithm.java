/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.encryption;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DESAlgorithm {

    private static DESAlgorithm instance = new DESAlgorithm();
    private Key key;
    private Cipher cipher;

    public static DESAlgorithm getInstance() {
        if (instance == null) {
            instance = new DESAlgorithm();
        }
        return instance;
    }

    private DESAlgorithm() {
        byte[] arr = {-4, 53, 7, -72, -25, 1, 105, 38, -125, -104, 37, 84,
            -45, 81, -49, 24};
        key = new SecretKeySpec(arr, "Blowfish");
        try {
            cipher = Cipher.getInstance(key.getAlgorithm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String text) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.encodeBytes(cipher.doFinal(text.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String decrypt(String text) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.decode(text)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
