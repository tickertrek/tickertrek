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
 * @author Uttam
 */
@Entity
public class TagUpdate extends BaseEntity {

    @Temporal(value = TemporalType.TIMESTAMP)
    Date           updateDate;
    String         updatedById;
    String         updatedByUName;
    String         updateValue;
    Integer        expired;
    String         replyUpdateId;
    String         updateType;

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public String getReplyUpdateId() {
        return replyUpdateId;
    }

    public void setReplyUpdateId(String replyUpdateId) {
        this.replyUpdateId = replyUpdateId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getUpdateValue() {
        return updateValue;
    }

    public void setUpdateValue(String updateValue) {
        this.updateValue = updateValue;
    }

    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByUName() {
        return updatedByUName;
    }

    public void setUpdatedByUName(String updatedByUName) {
        this.updatedByUName = updatedByUName;
    }
    
    @Override
    public String toString(){
        return "TagUpdate - id:"+this.getId()+", by:"+this.updatedByUName
                +", update:"+this.updateValue
                +", on:"+this.updateDate;
    }

}
