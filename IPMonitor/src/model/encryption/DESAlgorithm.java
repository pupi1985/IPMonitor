/*
 * IP Monitor is a simple application which monitors your public IP
 * address for changes and lets you set different kinds of notification
 * such as email, audio, pop up or executing a command. It can also run
 * in background as a Windows service or Linux/Mac daemon.
 *
 * Copyright (C) 2007 - 2016  Gabriel Zanetti http://github.com/pupi1985
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package model.encryption;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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
        byte[] arr = {
                -4, 53, 7, -72, -25, 1, 105, 38, -125, -104, 37, 84, -45, 81, -49, 24
        };
        key = new SecretKeySpec(arr, "Blowfish");
        try {
            cipher = Cipher.getInstance(key.getAlgorithm());
        } catch (Exception e) {
        }
    }

    public String encrypt(String text) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.encodeBytes(cipher.doFinal(text.getBytes()));
        } catch (Exception e) {
            return null;
        }

    }

    public String decrypt(String text) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.decode(text)));
        } catch (Exception e) {
            return null;
        }
    }
}
