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
public class Sector extends BaseEntity{
   
    String      strategyid;
    String      sector;
    Double       allocation;

    public Double getAllocation() {
        return allocation;
    }

    public void setAllocation(Double allocation) {
        this.allocation = allocation;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getStrategyid() {
        return strategyid;
    }

    public void setStrategyid(String strategyid) {
        this.strategyid = strategyid;
    }

   
    
    
    
}
