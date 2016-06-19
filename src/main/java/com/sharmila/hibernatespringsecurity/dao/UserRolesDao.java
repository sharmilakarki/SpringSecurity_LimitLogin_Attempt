/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.dao;

import com.sharmila.hibernatespringsecurity.entity.UserRoles;
import java.util.List;

/**
 *
 * @author sharmila
 */
public interface UserRolesDao {
    void insert(UserRoles userRoles);
    void delete(int id);
    void update(UserRoles userRoles);
    List<UserRoles> getAll();
    UserRoles getById(int id);
    UserRoles getByuserId(int userId);
}
