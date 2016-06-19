/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.service;

import com.sharmila.hibernatespringsecurity.dao.UserRolesDao;
import com.sharmila.hibernatespringsecurity.entity.UserRoles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sharmila
 */
@Service
public class UserRolesService {

    @Autowired
    private UserRolesDao userRolesDao;

    public void insert(UserRoles user) {
        userRolesDao.insert(user);

    }

    public void delete(int id) {
        userRolesDao.delete(id);
    }

    public void update(UserRoles user) {
        userRolesDao.update(user);
    }

    public List<UserRoles> getAll() {
        return userRolesDao.getAll();
    }

    public UserRoles getById(int id) {
        return userRolesDao.getById(id);
    }

    public UserRoles getByUserId(int userId){
        return userRolesDao.getByuserId(userId);
    }
}
