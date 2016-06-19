/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GeneratorType;

/**
 *
 * @author sharmila
 */
@Entity
@Table(name = "user_attempts")
@NamedQueries(
        {
            @NamedQuery(name = UserAttempts.GET_USERATTEMPTS_BY_USERNAME, query = UserAttempts.GET_USERATTEMPTS_BY_USERNAME_QUERY),
            @NamedQuery(name = UserAttempts.UPDATE_USERATTEMPTS_BY_USERNAME, query = UserAttempts.UPDATE_USERATTEMPTS_BY_USERNAME_QUERY)
        })
public class UserAttempts implements Serializable {

    public static final String GET_USERATTEMPTS_BY_USERNAME_QUERY = " from UserAttempts u where u.username= :username";
    public static final String GET_USERATTEMPTS_BY_USERNAME = "GET_USERATTEMPTS_BY_USERNAME";

    public static final String UPDATE_USERATTEMPTS_BY_USERNAME_QUERY = "UPDATE UserAttempts ua SET ua.attempts=:attempts+1 ,ua.LastModified=:LastModified where ua.username=:username";
    public static final String UPDATE_USERATTEMPTS_BY_USERNAME = "UPDATE_USERATTEMPTS_BY_USERNAME";

    public static final String RESET_USERATTEMPTS_BY_USERNAME_QUERY = "UPDATE UserAttempts ua set ua.attempts=0,lastModified=null where ua.username=:username";
    public static final String RESET_USERATTEMPTS_BY_USERNAME = "RESET_USERATTEMPTS_BY_USERNAME";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "attempts")
    private int attempts;
    @Column(name = "lastModified")
    private Date LastModified;

    public UserAttempts() {
    }

    public UserAttempts(int id, String username, int attempts, Date LastModified) {
        this.id = id;
        this.username = username;
        this.attempts = attempts;
        this.LastModified = LastModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Date getLastModified() {
        return LastModified;
    }

    public void setLastModified(Date LastModified) {
        this.LastModified = LastModified;
    }

}
