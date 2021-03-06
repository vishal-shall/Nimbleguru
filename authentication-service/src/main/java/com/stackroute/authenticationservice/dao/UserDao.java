package com.stackroute.authenticationservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.authenticationservice.model.DAOUser;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {

    DAOUser findByEmailAndPassword(String email,String password);
    DAOUser findByEmail(String email);

}