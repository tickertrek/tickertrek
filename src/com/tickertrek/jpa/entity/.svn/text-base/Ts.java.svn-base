/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rajesroy
 */
@Entity
public class Ts extends BaseEntity{
    
    String strategyid;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date time;
    Double equity;
    Double totalvalue;
    Double cash;
    Double drawdown;

    @Override
    public String toString(){
        return "Time:"+this.getTime().toString() + " Equity:"+this.getEquity() 
                + " TotalValue:"+ this.getTotalvalue() + " Cash:"+this.getCash()+" DD:"+this.getDrawdown();
    }
    
    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Double getDrawdown() {
        return drawdown;
    }

    public void setDrawdown(Double drawdown) {
        this.drawdown = drawdown;
    }

    public Double getEquity() {
        return equity;
    }

    public void setEquity(Double equity) {
        this.equity = equity;
    }

    public String getStrategyid() {
        return strategyid;
    }

    public void setStrategyid(String strategyid) {
        this.strategyid = strategyid;
    }
   
   
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getTotalvalue() {
        return totalvalue;
    }

    public void setTotalvalue(Double totalvalue) {
        this.totalvalue = totalvalue;
    }
    
    
    
}
