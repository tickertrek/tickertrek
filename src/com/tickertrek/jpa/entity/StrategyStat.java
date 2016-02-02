/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;

import javax.persistence.Entity;

/**
 *
 * @author rajesroy
 */
@Entity
public class StrategyStat extends BaseEntity{
  
    String  field;
    Double   minVal;
    Double   maxVal;
    Double   step;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Double getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Double maxVal) {
        this.maxVal = maxVal;
    }

    public Double getMinVal() {
        return minVal;
    }

    public void setMinVal(Double minVal) {
        this.minVal = minVal;
    }

    public Double getStep() {
        return step;
    }

    public void setStep(Double step) {
        this.step = step;
    }
    
    
    
}
