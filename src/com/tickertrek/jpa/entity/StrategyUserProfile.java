/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author rajesroy
 */
@Entity
public class StrategyUserProfile extends BaseEntity{
    String userid;
    String strategyid;
    String relation;

    public String getStrategyid() {
        return strategyid;
    }

    public void setStrategyid(String strategyid) {
        this.strategyid = strategyid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    
    
   
    
    
}
