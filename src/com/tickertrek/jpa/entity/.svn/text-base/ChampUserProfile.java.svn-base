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
public class ChampUserProfile extends BaseEntity{
    String championshipid;
    String userid;
    String strategyid;
    Integer position;
    String  nickname;
    Double   overallreturn;
    
    public void init(){
        position = 0;
        overallreturn = 0.0;
        nickname = "";
    }

    public String getChampionshipid() {
        return championshipid;
    }

    public void setChampionshipid(String championshipid) {
        this.championshipid = championshipid;
    }

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
    
    
     

   
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Double getOverallreturn() {
        return overallreturn;
    }

    public void setOverallreturn(Double overallreturn) {
        this.overallreturn = overallreturn;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
    
    @Override
    public String toString(){
        return "ChampionshipID:"+this.getChampionshipid()+" StrategyID:"+this.strategyid
                +" UserID:"+this.getUserid() + " nickname:"+this.getNickname();
                
    }
    
    
}
