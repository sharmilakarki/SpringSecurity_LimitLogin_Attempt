/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.dao;

import com.sharmila.hibernatespringsecurity.entity.Role;
import java.util.List;

/**
 *
 * @author sharmila
 */
public interface RoleDao {
     void insert(Role role);
    void delete(int id);
    void update(Role role);
    List<Role> getAll();
    Role getById(int id);
    Role getByName(String rolename);
}
