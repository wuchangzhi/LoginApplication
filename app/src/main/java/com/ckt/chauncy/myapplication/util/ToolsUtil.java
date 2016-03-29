package com.ckt.chauncy.myapplication.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ckt on 16-3-29.
 */
public class ToolsUtil {

    public static String getMD5(String val) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(val.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash) {
            int i = (b & 0xFF);
            if (i < 0x10) hex.append('0');
            hex.append(Integer.toHexString(i));
        }
        return hex.toString();
    }
}
