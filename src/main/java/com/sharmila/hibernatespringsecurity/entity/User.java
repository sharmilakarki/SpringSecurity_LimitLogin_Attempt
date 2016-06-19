/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author sharmila
 */
@Entity
@Table(name = "user")

@NamedQueries(
        {
            @NamedQuery(name = User.UPDATE_LOCKED, query = User.UPDATE_LOCKED_QUERY),
            @NamedQuery(name = User.COUNT_USERS, query = User.COUNT_USERS_QUERY)
        }
)
public class User implements Serializable {

    public static final String UPDATE_LOCKED_QUERY = "UPDATE User u SET u.accountNonLocked=:accountNonLocked where u.userName=:userName";
    public static final String UPDATE_LOCKED = "UPDATE_LOCKED";

    public static final String COUNT_USERS_QUERY = "SELECT count(u) from User u";
    public static final String COUNT_USERS = "COUNT_USERS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "added_date", insertable = false)
    private Date addedDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date", nullable = true)
    private Date modifiedDate;
    @Column(name = "enabled")
    private boolean status;
    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;
    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;
    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired;

    @ManyToMany(cascade = {CascadeType.ALL, CascadeType.MERGE}, fetch = FetchType.EAGER)

    @JoinTable(name = "user_role",
            joinColumns = {
                @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "role_id")})
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Role> role;

    public User() {
    }

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User(int id, String userName, String password, String email, String address, Date addedDate, Date modifiedDate, boolean status, boolean accountNonLocked, boolean accountNonExpired, boolean credentialsNonExpired, Set<Role> role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.addedDate = addedDate;
        this.modifiedDate = modifiedDate;
        this.status = status;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Role> getRole() {
        Hibernate.initialize(role);
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

   
}
