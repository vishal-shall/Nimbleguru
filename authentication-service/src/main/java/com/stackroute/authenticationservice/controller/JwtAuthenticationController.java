package com.stackroute.authenticationservice.controller;

import com.netflix.discovery.converters.Auto;
import com.stackroute.authenticationservice.encryption.GeneratePlainPassword;
import com.stackroute.authenticationservice.model.DAOUser;
import com.stackroute.authenticationservice.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.authenticationservice.service.JwtUserDetailsService;
import com.stackroute.authenticationservice.config.JwtTokenUtil;
import com.stackroute.authenticationservice.model.JwtRequest;
import com.stackroute.authenticationservice.model.JwtResponse;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private GeneratePlainPassword generatePlainPassword;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        //authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        DAOUser usertoAuth = userDetailsService.loadUserByEmail(authenticationRequest.getEmail());
        String key = usertoAuth.getSignKey();
        List<String> mp = new ArrayList<String>();
        String pwdtoAuth = generatePlainPassword.getPlainPassword(usertoAuth.getSignKey(),usertoAuth.getPassword());
        if(pwdtoAuth.equals(authenticationRequest.getPassword())){
            final DAOUser userDetails = userDetailsService
                    .loadUserByEmail(authenticationRequest.getEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);
            mp.add(userDetails.getEmail());
            mp.add(userDetails.getRole());
            mp.add(token);
        }
        else{
            mp.add("UserName or Password Incorrect");
        }
        return ResponseEntity.ok(new JwtResponse(mp));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO users) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(users));
    }

//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }
}
