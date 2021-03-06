package com.stackroute.authenticationservice.encryption;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

@Component
public class GeneratePlainPassword {

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
    public String getPlainPassword(String key,String encrptedPWD) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] bytekey = hexStringToByteArray(key);
        SecretKeySpec sks = new SecretKeySpec(bytekey, GeneratePlainPassword.AES);
        Cipher cipher = Cipher.getInstance(GeneratePlainPassword.AES);
        cipher.init(Cipher.DECRYPT_MODE, sks);
       // byte[] decrypted2 = cipher.doFinal(Base64.getDecoder().decode(encryptedText)hexStringToByteArray(encrptedPWD));
        byte[] decrypted = cipher.doFinal(hexStringToByteArray(encrptedPWD));
        String OriginalPassword = new String(decrypted);
        return OriginalPassword;

    }
}
