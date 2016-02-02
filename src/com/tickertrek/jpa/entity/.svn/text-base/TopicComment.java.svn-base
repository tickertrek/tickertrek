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
public class TopicComment extends BaseEntity{
    @Temporal(value = TemporalType.TIMESTAMP)
    Date           commentdate;
    String    commentedbyId;
    String    commentedbyUName;
    String          comment;
    Integer         expired;
    String    replyTopicId;
    @ManyToOne
    DiscussTopic topic;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }

    public String getCommentedbyId() {
        return commentedbyId;
    }

    public void setCommentedbyId(String commentedby) {
        this.commentedbyId = commentedby;
    }

    public String getCommentedbyUName() {
        return commentedbyUName;
    }

    public void setCommentedbyUName(String commentedbyUName) {
        this.commentedbyUName = commentedbyUName;
    }
    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public String getReplyTopicId() {
        return replyTopicId;
    }

    public void setReplyTopicId(String reply) {
        this.replyTopicId = reply;
    }

    public DiscussTopic getTopic() {
        return topic;
    }

    public void setTopic(DiscussTopic topic) {
        this.topic = topic;
    }
    
    
}
