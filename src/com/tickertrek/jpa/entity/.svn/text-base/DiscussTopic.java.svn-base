/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rajesroy
 */
@Entity
public class DiscussTopic extends BaseEntity{
    String          title;
    
    String     createdbyId;
    String createdbyUName;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date            createdate;
    Integer         postcount;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date            lastpost;
    String    lastcommentId;

    @OneToMany(mappedBy="topic")
    List<TopicComment> topicComments;

    public List<TopicComment> getTopicComments() {
        return topicComments;
    }

    public void setTopicComments(List<TopicComment> topicComments) {
        this.topicComments = topicComments;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreatedbyId() {
        return createdbyId;
    }

    public void setCreatedbyId(String createdby) {
        this.createdbyId = createdby;
    }
    public String getCreatedbyUName() {
        return createdbyUName;
    }

    public void setCreatedbyUName(String createdby) {
        this.createdbyUName = createdby;
    }

    public String getLastcommentId() {
        return lastcommentId;
    }

    public void setLastcommentId(String lastcomment) {
        this.lastcommentId = lastcomment;
    }

    public Date getLastpost() {
        return lastpost;
    }

    public void setLastpost(Date lastpost) {
        this.lastpost = lastpost;
    }

    public Integer getPostcount() {
        return postcount;
    }

    public void setPostcount(Integer postcount) {
        this.postcount = postcount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
