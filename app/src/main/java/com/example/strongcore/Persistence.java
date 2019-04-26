package com.example.strongcore;

import java.io.Serializable;

public class Persistence implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    public Persistence(String email){
        setEmail(email);
    }

    private void setEmail(String email) {
        this.email = email;
    }
    public String getEmail(){return email;}

}
