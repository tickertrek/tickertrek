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
public class UserProfile extends PublicUserProfile{
    Double prizeearning;
    Double subscriptionearning;
        
    public Double getPrizeearning() {
        return prizeearning;
    }

    public void setPrizeearning(Double prizeearning) {
        this.prizeearning = prizeearning;
    }   

    public Double getSubscriptionearning() {
        return subscriptionearning;
    }

    public void setSubscriptionearning(Double subscriptionearning) {
        this.subscriptionearning = subscriptionearning;
    }    
    public void init(){
            fullname="";
            email = "";
            contactemail="";
            assetclass="";
            style="0";
            holdingperiod="0";
            experience="0";
            aboutme="";
            profession="";
            nickname="";
            championship=0;
            following=0;
            followers=0;
            sitescore=0;
            userrating=0;
            prizeearning =0.0;
            subscriptionearning =0.0;

    }
    @Override
    public String toString(){
        return "user profile[email:"+this.email
                +", name:"+this.fullname+", username:"+this.nickname
                +"subscriptionearning:"+this.subscriptionearning
                +", prizeearning:"+this.prizeearning+"]";
    }
    
}
