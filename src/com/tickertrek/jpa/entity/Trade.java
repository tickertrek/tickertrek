/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rajesroy
 */
@Entity
public class Trade extends BaseEntity{
    String      strategyid;
    String      exchange;
    String      symbol;
    Integer     quantity;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date        opendate;
    Double       openprice;
    String     openordertype;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date        closedate;
    Double       closeprice;
    String     closeordertype;
    Double       gainloss;
    String      tradetype;

   
    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public String getCloseordertype() {
        return closeordertype;
    }

    public void setCloseordertype(String closeordertype) {
        this.closeordertype = closeordertype;
    }

    public Double getCloseprice() {
        return closeprice;
    }

    public void setCloseprice(Double closeprice) {
        this.closeprice = closeprice;
    }

    public String getExchange() {
        return this.exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Double getGainloss() {
        return this.gainloss;
    }

    public void setGainloss(Double gainloss) {
        this.gainloss = gainloss;
    }

    
    public Date getOpendate() {
        return this.opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    public String getOpenordertype() {
        return this.openordertype;
    }

    public void setOpenordertype(String openordertype) {
        this.openordertype = openordertype;
    }

    public Double getOpenprice() {
        return this.openprice;
    }

    public void setOpenprice(Double openprice) {
        this.openprice = openprice;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStrategyid() {
        return strategyid;
    }

    public void setStrategyid(String strategyid) {
        this.strategyid = strategyid;
    }
    
   

    

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTradetype() {
        return this.tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }
    
    
    
}
