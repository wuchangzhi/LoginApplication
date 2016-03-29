package com.ckt.chauncy.myapplication.util;

import android.util.Log;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ckt on 16-3-29.
 */
public class AESEncryptor {
    private final static String HEX = "0123456789ABCDEF";
    private final static String DEFAULTSEED = "12345678";

    /**
     * AES加密
     */
    public static String encrypt(String... encrypted) {
        if(encrypted[0].length() == 0){
            return "";
        }
        byte[] rawKey;
        try {
            if (encrypted.length == 2) {
                rawKey = getRawKey(encrypted[1].getBytes());
            } else {
                rawKey = getRawKey(DEFAULTSEED.getBytes());
            }

            byte[] result = encrypt(rawKey, encrypted[0].getBytes());
            return toHex(result);
        } catch (Exception e) {
            Log.d("test","encrypt faild");
            throw new RuntimeException("encrypt faild");
        }
    }

    /**
     * AES解密
     */
    public static String decrypt(String... encrypted) {
        if(encrypted[0].length() == 0){
            return "";
        }
        byte[] rawKey;
        try {
            if (encrypted.length == 2) {
                rawKey = getRawKey(encrypted[1].getBytes());
            } else {
                rawKey = getRawKey(DEFAULTSEED.getBytes());
            }

            byte[] enc = toByte(encrypted[0]);
            byte[] result = decrypt(rawKey, enc);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("test","decrypt faild");
            throw new RuntimeException("decrypt faild");
        }
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }


    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}