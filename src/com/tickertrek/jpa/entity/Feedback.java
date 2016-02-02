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
public class Feedback extends BaseEntity{
    Integer     feedbacktype;
    
    Date        feedbackdate;
    String      postedById;
    String      postedByemail;
    String      feedbackshort;
    String      feedbacklong;
    
    public String getPostedById() {
        return postedById;
    }
    public void setPostedById(String feedbackby) {
        this.postedById = feedbackby;
    }
    public String getPostedByemail() {
        return postedByemail;
    }
    public void setPostedByemail(String postedByemail) {
        this.postedByemail = postedByemail;
    }
    
    @Temporal(value = TemporalType.TIMESTAMP)
        public Date getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(Date feedbackdate) {
        this.feedbackdate = feedbackdate;
    }

    public String getFeedbacklong() {
        return feedbacklong;
    }

    public void setFeedbacklong(String feedbacklong) {
        this.feedbacklong = feedbacklong;
    }

    public String getFeedbackshort() {
        return feedbackshort;
    }

    public void setFeedbackshort(String feedbackshort) {
        this.feedbackshort = feedbackshort;
    }

    public Integer getFeedbacktype() {
        return feedbacktype;
    }

    public void setFeedbacktype(Integer feedbacktype) {
        this.feedbacktype = feedbacktype;
    }

    
    
    
}
