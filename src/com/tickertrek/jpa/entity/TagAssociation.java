/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.jpa.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Uttam
 */
@Entity
public class TagAssociation extends BaseEntity {
    @Column(nullable=false)
    String          tagId;
    String          tagType;
    @Column(nullable=false)
    String          updateId;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date            updateTime;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString(){
        return "TagAssociation - Id:"+this.getId()
                +", tag Id:"+this.tagId+", tag type:"+this.tagType
                +", update id:"+this.updateId;
    }
    
}
