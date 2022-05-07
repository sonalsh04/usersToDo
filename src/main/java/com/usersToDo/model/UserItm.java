package com.usersToDo.model;

import javax.persistence.*;

@Table(name="user")
@Entity
public class UserItm {

    public UserItm() {
    }

    public UserItm(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uid;
    private String name;
    private String pass;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
