/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author sharmila
 */

@Entity
@Table(name = "tbl_role")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
   private int id;
    @Column(name = "role")
   private String role;
   
   @ManyToMany(mappedBy = "role",fetch = FetchType.LAZY)
   @Cascade(CascadeType.ALL)
   private Set<User> user;

    public Role() {
    }

    public Role( String role) {
       
        this.role = role;
    }

    public Role(int id, String role, Set<User> user) {
        this.id = id;
        this.role = role;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

   

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", role=" + role + ", user=" + user + '}';
    }
   
    
   
   
   
}
