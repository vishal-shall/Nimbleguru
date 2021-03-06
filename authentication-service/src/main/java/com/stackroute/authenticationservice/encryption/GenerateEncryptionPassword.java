package com.stackroute.authenticationservice.encryption;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
@Component
public class GenerateEncryptionPassword {

    public static final String AES = "AES";

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }
    public String getEncryptedPassword(String key, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] bytekey = hexStringToByteArray(key);
        SecretKeySpec sks = new SecretKeySpec(bytekey, GenerateEncryptionPassword.AES);
        Cipher cipher = Cipher.getInstance(GenerateEncryptionPassword.AES);
        cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
        byte[] encrypted = cipher.doFinal(password.getBytes());
        String encryptedpwd = byteArrayToHexString(encrypted);
        return encryptedpwd;
    }

}