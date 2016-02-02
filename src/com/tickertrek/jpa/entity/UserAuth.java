/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author rajesroy
 */
@Entity
public class UserAuth extends BaseEntity{
    String password;
    String email;
    String userName;
    String loginType;

    /*UserProfile owner;
    @OneToOne@Column(name="userAuth_id")
    public UserProfile getOwner() {
        return owner;
    }
    public void setOwner(UserProfile owner) {
        this.owner = owner;
    }*/
    String userid;
    @Transient
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    

    @Override
    public String toString(){
        return "user auth[email:"+this.email+", user name:"+this.userName+"]";
    }
    
}
