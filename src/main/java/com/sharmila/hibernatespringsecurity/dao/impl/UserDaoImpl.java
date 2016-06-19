/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.dao.impl;

import com.sharmila.hibernatespringsecurity.dao.RoleDao;
import com.sharmila.hibernatespringsecurity.dao.UserDao;
import com.sharmila.hibernatespringsecurity.entity.Role;
import com.sharmila.hibernatespringsecurity.entity.User;
import com.sharmila.hibernatespringsecurity.entity.UserRoles;
import java.util.ArrayList;

import java.util.List;
import org.hibernate.Hibernate;
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
@org.springframework.transaction.annotation.Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RoleDao roleDao;

    private Session session;

    private Transaction transaction;

    public UserDaoImpl() {
    }

    @Override
    public void insert(User user) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
        //sessionFactory.getCurrentSession().saveOrUpdate(user);

    }

    @Override
    public void delete(int id) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        User u = (User) session.get(User.class, new Integer(id));
        if (null != u) {

            for (Role role : u.getRole()) {
                u.getRole().remove(role);

            }

            session.delete(u);

        }

        transaction.commit();
        session.close();

    }

    @Override
    public void update(User user) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        session = sessionFactory.openSession();
        userList = session.createQuery("select u from User u inner join u.role  ").list();
        User user = new User();

        Hibernate.initialize(user.getRole());

        session.close();
        return userList;
    }

    @Override
    public List<User> getFetchEager() {
        List<User> userList = new ArrayList<>();
        session = sessionFactory.openSession();
        userList = session.createQuery("select u from User u join u.role ").list();
        User user = new User();
        session.close();
        return userList;
    }

    @Override
    public User getById(int id) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        User user = (User) session.get(User.class, new Integer(id));

        transaction.commit();
        session.close();
        System.out.println("got by user's id");
        return user;
    }

    @Override
    public User getByRole(String role) {
        session = sessionFactory.openSession();
//        Set<Role> roles=new HashSet<Role>();
        List<User> roles = new ArrayList<>();
        roles = session.createQuery("select u from User u where role=:role").setString("role", role).list();
        if (roles.size() > 0) {
            return roles.get(0);
        }
        session.close();
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getByUserName(String username) {
        session = sessionFactory.openSession();
        List<User> userList = new ArrayList<User>();
        userList = session.createQuery("select u from User u where username=:username").setString("username", username).list();
        if (userList.size() > 0) {
            return userList.get(0);
        }

        session.close();
        return null;

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
