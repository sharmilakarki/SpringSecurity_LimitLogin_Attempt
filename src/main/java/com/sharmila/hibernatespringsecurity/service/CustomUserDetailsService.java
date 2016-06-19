/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.service;

import com.sharmila.hibernatespringsecurity.dao.UserDao;
import com.sharmila.hibernatespringsecurity.entity.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author sharmila
 */
@Service("userDetailsService")
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private DataSource dataSource;
    



    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        com.sharmila.hibernatespringsecurity.entity.User user = userDao.getByUserName(username);

        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    //converting com.....User to org.springframework.security.core.userdetails.User
    private UserDetails buildUserForAuthentication(com.sharmila.hibernatespringsecurity.entity.User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.isStatus(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), authorities);

    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> role) {
        Set<GrantedAuthority> setAutho = new HashSet<GrantedAuthority>();

        //build's users authority
        for (Role r : role) {
            setAutho.add(new SimpleGrantedAuthority(r.getRole()));
        }
        List<GrantedAuthority> result = new ArrayList<>(setAutho);
        return result;
    }

 
}
