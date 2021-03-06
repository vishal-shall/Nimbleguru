package com.stackroute.authenticationservice.service;


import com.stackroute.authenticationservice.dao.UserDao;
import com.stackroute.authenticationservice.encryption.GenerateEncryptionPassword;
import com.stackroute.authenticationservice.encryption.GenerateKey;
import com.stackroute.authenticationservice.model.DAOUser;
import com.stackroute.authenticationservice.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class RabbitMQReciever implements RabbitListenerConfigurer {
    private UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReciever.class);
    @Autowired
    private GenerateKey generateKey;
    @Autowired
    private GenerateEncryptionPassword generateEncryptionPassword;

    @Autowired
    public RabbitMQReciever(UserDao userDao) {
        this.userDao = userDao;
    }
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void recievedMessage(UserDTO userDTO) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        logger.info("Recieved programmer details" + userDTO.toString());
        String key = generateKey.getEncryptionKey();
//        String key= "F21E2A7FB6C68037FAEAA55222E320F7";
        String encryptedpwd = generateEncryptionPassword.getEncryptedPassword(key,userDTO.getPassword());
        DAOUser newUser = new DAOUser();
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(encryptedpwd);
        newUser.setRole(userDTO.getRole());
        newUser.setSignKey(key);
        userDao.save(newUser);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

}