
package com.stackroute.authenticationservice.runner;

import com.stackroute.authenticationservice.dao.UserDao;
import com.stackroute.authenticationservice.encryption.GenerateEncryptionPassword;
import com.stackroute.authenticationservice.encryption.GenerateKey;
import com.stackroute.authenticationservice.model.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private GenerateKey generateKey;
    @Autowired
    private GenerateEncryptionPassword generateEncryptionPassword;

    @Override
    public void run(String... args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
//        String key = generateKey.getEncryptionKey();
//        String encryptedpwd = generateEncryptionPassword.getEncryptedPassword(key, "12345678");
//        DAOUser newUser = new DAOUser();
//        newUser.setEmail("simanta@gmail");
//        newUser.setRole("M");
//        newUser.setPassword(encryptedpwd);
//        newUser.setSignKey(key);
//        if (userDao.findByEmail(newUser.getEmail()) == null) {
//            userDao.save(newUser);
//        }
        Map<String,String> map = new HashMap<>();
        map.put("rachitmahesh@gmail.com","rachit@123");
        map.put("vishalkapoor@gmail.com","vishal@123");
        map.put("ayushsingh@gmail.com","ayush@123");
        map.put("musthakkhan@gmail.com","mustak@123");
        map.put("saikuamar@gmail.com","saikumar@123");
        map.put("shreyash@gmail.com","shreyash@123");
        map.put("avnish@gmail.com","avnish@123");
        map.put("rakshandanoor@gmail.com","noor@123");
        map.put("rajeshsingh@gmail.com","rajesh@123");
        map.put("simanta@gmail.com","simanta@123");
        map.put("sandhya@gmail.com","samdhya@123");
        map.put("deepan@gmail.com","deepan@123");
        map.put("shankar@gmail.com","shankar@123");
        map.put("sachin@gmail.com","sachin@123");
        map.put("saikat@gmail.com","saikat@123");
        map.put("shyam@gmail.com","shyam@123");







        for (Map.Entry<String,String> entry : map.entrySet()){
            String key_sign = generateKey.getEncryptionKey();
            String encryptedpwd2 = generateEncryptionPassword.getEncryptedPassword(key_sign, entry.getValue());
            DAOUser newUser = new DAOUser();
            newUser.setEmail(entry.getKey());
            newUser.setRole("M");
            newUser.setPassword(encryptedpwd2);
            newUser.setSignKey(key_sign);
            if (userDao.findByEmail(newUser.getEmail()) == null) {
                userDao.save(newUser);
            }
        }
//        String key2 = generateKey.getEncryptionKey();
//        String encryptedpwd2 = generateEncryptionPassword.getEncryptedPassword(key2, "1234efgh");
//        DAOUser newUser2 = new DAOUser();
//        newUser2.setEmail("rakshanda@gmail");
//        newUser2.setRole("M");
//        newUser2.setPassword(encryptedpwd2);
//        newUser2.setSignKey(key2);
//        if (userDao.findByEmail(newUser2.getEmail()) == null) {
//            userDao.save(newUser2);
//        }
//
//        String key3 = generateKey.getEncryptionKey();
//        String encryptedpwd3 = generateEncryptionPassword.getEncryptedPassword(key3, "abcdefgh");
//        DAOUser newUser3 = new DAOUser();
//        newUser3.setEmail("sandhya@gmail");
//        newUser3.setRole("M");
//        newUser3.setPassword(encryptedpwd3);
//        newUser3.setSignKey(key3);
//        if (userDao.findByEmail(newUser3.getEmail()) == null) {
//            userDao.save(newUser3);
//
//        }
    }
}