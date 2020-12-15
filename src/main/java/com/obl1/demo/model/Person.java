package com.obl1.demo.model;

import java.util.UUID;

public class Person {

    private String username;
    private String id;

    public Person() { // Spring bruger denne
        if(id == null){
            id = UUID.randomUUID().toString(); // er unik på Verdensplan.
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}