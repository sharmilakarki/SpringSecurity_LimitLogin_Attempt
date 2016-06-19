/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.dao.impl;

import com.sharmila.hibernatespringsecurity.dao.UserRolesDao;
import com.sharmila.hibernatespringsecurity.entity.UserRoles;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sharmila
 */
@Repository
public class UserRolesDaoImpl implements UserRolesDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;
    private Transaction transaction;

    @Override
    public void insert(UserRoles userRoles) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(userRoles);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        UserRoles userRoles = (UserRoles) session.load(UserRoles.class, new Integer(id));
        if (userRoles != null) {
            session.delete(userRoles);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void update(UserRoles userRoles) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(userRoles);
        transaction.commit();
        session.close();
    }

    @Override
    public List<UserRoles> getAll() {
        List<UserRoles> userRolesList = new ArrayList<UserRoles>();
        session = sessionFactory.openSession();

        userRolesList = session.createQuery("select ur from UserRoles ur").list();

        session.close();
        return userRolesList;
    }

    @Override
    public UserRoles getById(int id) {
        session = sessionFactory.openSession();
        UserRoles userRoles = (UserRoles) session.get(UserRoles.class, new Integer(id));

        session.close();
        return userRoles;

    }

    @Override
    public UserRoles getByuserId(int userId) {
        session = sessionFactory.openSession();
        UserRoles userRoles = (UserRoles) session.get(UserRoles.class, new Integer(userId));

        session.close();
        return userRoles;
    }

}
