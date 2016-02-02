/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;

import com.tickertrek.util.Constants;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author rajesroy
 */
@Entity
public class Strategy extends PublicStrategy{
    
    Double  earning;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date    lastprocessed;
    String  strategyid;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date    lastactivity;
    Integer maxinactivetime;
    
    Double peaktotalvalue;
    Double peakequity;
    
    @Transient
    Double tsyearlyreturn;
    @Transient
    Double tsmonthlyreturn;
    @Transient
    Double tsdrawdown;        
    public void init(){
       
        strategyname = "";
        type         = Constants.Strategy.TYPE_GENERAL;
        listingtype  = Constants.Strategy.LISTING_PRIVATE;
        initialbalance = 0.0;
        currency    = Constants.Strategy.INR;
        instrumentoptions = Constants.Instrument.STOCK;
        description ="";
        market      = Constants.Market.INDIA;
        started     = new Date();
        tradecount  = 0;
        profittradecount = 0;
        loosingtradecount = 0;
        profitfactor      = 0.0;
        annualreturn      = 0.0;
        overallreturn     = 0.0;
        ytdreturn         = 0.0;
        _3mreturn         = 0.0;
        maxdrawdown       = 0.0;
        sitescore         = 0;
        avgwin            = 0.0;
        avgloss           = 0.0;
        viewtimes         = 0;
        age               = 0;
        power             = 0.0;
        balance           = 0.0;
        followers         = 0;
        fee               = 0;
        freetrial         = 0;
        brokeragefee      = 0.25;
        brokeragefeetype  = Constants.Strategy.BROKERAGE_FEE_PERCENTAGE;
        earning           = 0.0;
        lastprocessed =  new Date();
        lastactivity  = new Date();
        maxinactivetime = 0;
        totalvalue = 0.0;
        equity = 0.0;
        grossgain  =0.0;
        grossloss = 0.0;
    }
    //@Transient
    public String getStrategyid() {
        return strategyid;
    }

    public void setStrategyid(String strategyid) {
        this.strategyid = strategyid;
    }
    
    public Double getEarning() {
        return earning;
    }
    public void setEarning(Double earning) {
        this.earning = earning;
    }    

    
        public Date getLastprocessed() {
        return lastprocessed;
    }

    public void setLastprocessed(Date lastprocessed) {
        this.lastprocessed = lastprocessed;
    }

    public Date getLastactivity() {
        return lastactivity;
    }

    public void setLastactivity(Date lastactivity) {
        this.lastactivity = lastactivity;
    }

    public Integer getMaxinactivetime() {
        return maxinactivetime;
    }

    public void setMaxinactivetime(Integer maxinactivetime) {
        this.maxinactivetime = maxinactivetime;
    }

    public Double getTsmonthlyreturn() {
        return tsmonthlyreturn;
    }

    public void setTsmonthlyreturn(Double tsmonthlyreturn) {
        this.tsmonthlyreturn = tsmonthlyreturn;
    }

    public Double getTsyearlyreturn() {
        return tsyearlyreturn;
    }

    public void setTsyearlyreturn(Double tsyearlyreturn) {
        this.tsyearlyreturn = tsyearlyreturn;
    }

    public Double getTsdrawdown() {
        return tsdrawdown;
    }

    public void setTsdrawdown(Double tsdrawdown) {
        this.tsdrawdown = tsdrawdown;
    }

    public Double getPeakequity() {
        return peakequity;
    }

    public void setPeakequity(Double peakequity) {
        this.peakequity = peakequity;
    }

    public Double getPeaktotalvalue() {
        return peaktotalvalue;
    }

    public void setPeaktotalvalue(Double peaktotalvalue) {
        this.peaktotalvalue = peaktotalvalue;
    }
    
    
   @Override
    public String toString(){
        return "strategy[Id:"+this.id+", name:"+this.strategyname+"]";
    }
    
}
