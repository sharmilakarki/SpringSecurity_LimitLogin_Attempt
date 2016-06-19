/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.service;

import com.sharmila.hibernatespringsecurity.dao.UserDao;
import com.sharmila.hibernatespringsecurity.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sharmila
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
   
    
            
    public UserService() {
//      getUsers();
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    
    public void insert(User user) {
        userDao.insert(user);

    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public User getByUserName(String username) {
        return userDao.getByUserName(username);
    }
    public User getByRole(String role){
        return userDao.getByRole(role);
    }
     public List<User> getFetchEager(){
        return userDao.getFetchEager();
    }


}
