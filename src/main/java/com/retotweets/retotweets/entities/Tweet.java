package com.retotweets.retotweets.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "TWEETS")
public class Tweet {

    @Id
    @Column(name="user_id")
    private Integer userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="text")
    private String text;

    @Column(name="locale")
    private String locale;

    @Column(name="valid")
    private Boolean valid;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Tweet(Integer userId, String userName, String text, String locale, Boolean valid) {
        this.userId = userId;
        this.userName = userName;
        this.text = text;
        this.locale = locale;
        this.valid = valid;
    }

    public Tweet(){}
}
