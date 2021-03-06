package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.encryption.GenerateEncryptionPassword;
import com.stackroute.authenticationservice.encryption.GenerateKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.authenticationservice.dao.UserDao;
import com.stackroute.authenticationservice.model.DAOUser;
import com.stackroute.authenticationservice.model.UserDTO;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class JwtUserDetailsService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private GenerateKey generateKey;

    @Autowired
    private GenerateEncryptionPassword generateEncryptionPassword;



    public DAOUser loadUserByUsernameAndPassword(String email,String password){
        DAOUser user = userDao.findByEmailAndPassword(email,password);


        // return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
        // new ArrayList<>());
        return user;
    }
    public DAOUser loadUserByEmail(String email){
        DAOUser user = userDao.findByEmail(email);
        return user;
    }

    public DAOUser save(UserDTO user) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        String key = generateKey.getEncryptionKey();
//        String key= "F21E2A7FB6C68037FAEAA55222E320F7";
        String encryptedpwd = generateEncryptionPassword.getEncryptedPassword(key,user.getPassword());
        DAOUser newUser = new DAOUser();
        newUser.setEmail(user.getEmail());
//        newUser.setPassword(user.getPassword());
        newUser.setPassword(encryptedpwd);
        newUser.setSignKey(key);
        newUser.setRole(user.getRole());
        return userDao.save(newUser);
    }



}