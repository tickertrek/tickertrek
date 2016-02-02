/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.jpa.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Uttam
 */
@Entity
public class PublicStrategy extends BaseEntity{

   
    String  userid;
    String  strategyname;
    String  type;
    String  listingtype;
    Double  initialbalance;
    String  currency;
    String  instrumentoptions;
    String  description;
    String  market;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date    started;
    Integer ordercount;
    Integer tradecount;
    Integer profittradecount;
    Integer loosingtradecount;
    Double  profitfactor;
    Double  annualreturn;
    Double  overallreturn;
    Double  ytdreturn;
    Double  _3mreturn;
    Double  maxdrawdown;
    Integer sitescore;
    Double  avgwin;
    Double  avgloss;
    Integer viewtimes;
    Integer age;
    Double  power;
    Double  balance;
    Integer followers;
    Integer fee;
    Integer freetrial;
    Double  brokeragefee;
    String  brokeragefeetype;
    String  href;
    Double  totalvalue;
    Double  equity;
    Double grossgain;
    Double grossloss;

    public Double getGrossgain() {
        return grossgain;
    }

    public void setGrossgain(Double grossgain) {
        this.grossgain = grossgain;
    }

    public Double getGrossloss() {
        return grossloss;
    }

    public void setGrossloss(Double grossloss) {
        this.grossloss = grossloss;
    }
    
     @Transient
    List<Trade> opentrades;

    public Double getEquity() {
        return equity;
    }

    public void setEquity(Double equity) {
        this.equity = equity;
    }

    public Double getTotalvalue() {
        return totalvalue;
    }

    public void setTotalvalue(Double totalvalue) {
        this.totalvalue = totalvalue;
    }

   
    public List<Trade> getOpentrades() {
        return opentrades;
    }

    public void setOpentrades(List<Trade> opentrades) {
        this.opentrades = opentrades;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    
    

    
    public Double get3mreturn() {
        return _3mreturn;
    }

    public void set3mreturn(Double _3mreturn) {
        this._3mreturn = _3mreturn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getAnnualreturn() {
        return annualreturn;
    }

    public void setAnnualreturn(Double annualreturn) {
        this.annualreturn = annualreturn;
    }

    public Double getAvgloss() {
        return avgloss;
    }

    public void setAvgloss(Double avgloss) {
        this.avgloss = avgloss;
    }

    public Double getAvgwin() {
        return avgwin;
    }

    public void setAvgwin(Double avgwin) {
        this.avgwin = avgwin;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBrokeragefee() {
        return brokeragefee;
    }

    public void setBrokeragefee(Double brokeragefee) {
        this.brokeragefee = brokeragefee;
    }

    public String getBrokeragefeetype() {
        return brokeragefeetype;
    }

    public void setBrokeragefeetype(String brokeragefeetype) {
        this.brokeragefeetype = brokeragefeetype;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFreetrial() {
        return freetrial;
    }

    public void setFreetrial(Integer freetrial) {
        this.freetrial = freetrial;
    }

    public Double getInitialbalance() {
        return initialbalance;
    }

    public void setInitialbalance(Double initialbalance) {
        this.initialbalance = initialbalance;
    }

    public String getInstrumentoptions() {
        return instrumentoptions;
    }

    public void setInstrumentoptions(String instrumentoptions) {
        this.instrumentoptions = instrumentoptions;
    }

    public String getListingtype() {
        return listingtype;
    }

    public void setListingtype(String listingtype) {
        this.listingtype = listingtype;
    }

    public Integer getLoosingtradecount() {
        return loosingtradecount;
    }

    public void setLoosingtradecount(Integer loosingtradecount) {
        this.loosingtradecount = loosingtradecount;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Double getMaxdrawdown() {
        return maxdrawdown;
    }

    public void setMaxdrawdown(Double maxdrawdown) {
        this.maxdrawdown = maxdrawdown;
    }

    public Double getOverallreturn() {
        return overallreturn;
    }

    public void setOverallreturn(Double overallreturn) {
        this.overallreturn = overallreturn;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getProfitfactor() {
        return profitfactor;
    }

    public void setProfitfactor(Double profitfactor) {
        this.profitfactor = profitfactor;
    }

    public Integer getProfittradecount() {
        return profittradecount;
    }

    public void setProfittradecount(Integer profittradecount) {
        this.profittradecount = profittradecount;
    }

    public Integer getSitescore() {
        return sitescore;
    }

    public void setSitescore(Integer sitescore) {
        this.sitescore = sitescore;
    }

            public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public String getStrategyname() {
        return strategyname;
    }

    public void setStrategyname(String strategyname) {
        this.strategyname = strategyname;
        this.setHref(this.strategyname.replace(' ', '-'));
    }

    public Integer getTradecount() {
        return tradecount;
    }

    public void setTradecount(Integer tradecount) {
        this.tradecount = tradecount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getViewtimes() {
        return viewtimes;
    }

    public void setViewtimes(Integer viewtimes) {
        this.viewtimes = viewtimes;
    }

    public Double getYtdreturn() {
        return ytdreturn;
    }

    public void setYtdreturn(Double ytdreturn) {
        this.ytdreturn = ytdreturn;
    }

    public Integer getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(Integer ordercount) {
        this.ordercount = ordercount;
    }
    
    
    @Override
    public String toString(){
        return "public strategy[Id:"+this.id+", name:"+this.strategyname+"]";
    }

}
