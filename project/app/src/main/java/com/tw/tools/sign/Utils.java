package com.tw.tools.sign;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    private static final String TAG = "Sign-Utils";

    public static String getMd5(byte[] data) {
        String hashString = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data);
            BigInteger hashInt = new BigInteger(1, digest.digest());
            hashString = String.format("%1$032X", hashInt);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "Md5 algorithm NOT found.", e);
        }
        return hashString.toLowerCase();
    }
}
