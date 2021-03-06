package com.stackroute.authenticationservice.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    //private final Map<String,String> jwttoken;
    private final List<String> jwttoken;

    public JwtResponse(List<String> jwttoken) {
        this.jwttoken = jwttoken;
    }

    public List<String> getToken() {
        return this.jwttoken;
    }
}