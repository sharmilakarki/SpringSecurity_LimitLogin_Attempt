/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.dao.impl;

import com.sharmila.hibernatespringsecurity.dao.RoleDao;
import com.sharmila.hibernatespringsecurity.entity.Role;
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
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Override
    public void insert(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        session=sessionFactory.openSession();
        transaction=session.beginTransaction();
         Role role = (Role) session.get(Role.class, new Integer(id));
         if(role!=null){
             session.delete(role);
         }
         transaction.commit();
         session.close();
    }

    @Override
    public void update(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> getAll() {
        List<Role> roleList = new ArrayList<>();
        session = sessionFactory.openSession();
        roleList = session.createQuery("select r from Role  r").list();
       
        session.close();
        return roleList;
    }

    @Override
    public Role getById(int id) {
        session = sessionFactory.openSession();
        Role role = (Role) session.get(Role.class, new Integer(id));
        session.close();
        return role;
    }

    @Override
    public Role getByName(String rolename) {
        session = sessionFactory.openSession();
        Role role = (Role) session.get(Role.class, new String(rolename));
        session.close();
        return role;
    }

}
