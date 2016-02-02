/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.jpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Uttam
 */
@Entity
public class PublicUserProfile implements Serializable {
    String  id;
   
    String  fullname;
    String  email;
    String  contactemail;
    String  assetclass;
    String  style;
    String  holdingperiod;
    String  experience;
    String  aboutme;
    String  profession;
    String  nickname;
    Integer championship;
    Integer following;
    Integer followers;
    Integer sitescore;
    Integer userrating;
    
    String  userid;
    @Column(nullable = false)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    

    
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
   
    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getAssetclass() {
        return assetclass;
    }

    public void setAssetclass(String assetclass) {
        this.assetclass = assetclass;
    }

    public Integer getChampionship() {
        return championship;
    }

    public void setChampionship(Integer championship) {
        this.championship = championship;
    }

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getHoldingperiod() {
        return holdingperiod;
    }

    public void setHoldingperiod(String holdingperiod) {
        this.holdingperiod = holdingperiod;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getSitescore() {
        return sitescore;
    }

    public void setSitescore(Integer sitescore) {
        this.sitescore = sitescore;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getUserrating() {
        return userrating;
    }

    public void setUserrating(Integer userrating) {
        this.userrating = userrating;
    }

    @Override
    public String toString(){
        return "public profile[email:"+this.email
                +", name:"+this.fullname+", username:"+this.nickname+"]";
    }

}
