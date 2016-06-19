/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.dao;

import com.sharmila.hibernatespringsecurity.entity.User;
import com.sharmila.hibernatespringsecurity.entity.UserRoles;
import java.util.List;

/**
 *
 * @author sharmila
 */
public interface UserDao {
    void insert(User user);
    void delete(int id);
    void update(User user);
    List<User> getAll();
    User getById(int id);
    User getByUserName(String username);
    User getByRole(String role);
    List<User> getFetchEager();

}
