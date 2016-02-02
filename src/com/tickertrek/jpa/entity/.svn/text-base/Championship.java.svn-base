/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author rajesroy
 */
@Entity
public class Championship extends BaseEntity{
    private String description;
    
    @Column(unique=false, nullable=false)
    private String  name;
    private Integer pcount;
    private Double   prize;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startdate;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date enddate;
    private Integer closingday;
    private Double initialbalance;
    private String currency;
    private String instrumentoptions;
    private String market;
    private String status;
    @Transient
    String championshipid;

    public String getChampionshipid() {
        return championshipid;
    }

    public void setChampionshipid(String championshipid) {
        this.championshipid = championshipid;
    }
    
    
    
    @OneToMany(mappedBy = "championship")
    List<ChampUserProfile> participation;

    public Integer getClosingday() {
        return closingday;
    }

    public void setClosingday(Integer closingday) {
        this.closingday = closingday;
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

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChampUserProfile> getParticipation() {
        return participation;
    }

    public void setParticipation(List<ChampUserProfile> participation) {
        this.participation = participation;
    }

    public Integer getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }

    public Double getPrize() {
        return prize;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
    
    
}
