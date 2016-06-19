/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.filter;

import com.sharmila.hibernatespringsecurity.entity.Role;
import com.sharmila.hibernatespringsecurity.entity.User;
import com.sharmila.hibernatespringsecurity.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author sharmila
 */
//@WebFilter(filterName = "myFilter", urlPatterns = "/login")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        Set<String> roles=AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String username = req.getParameter("username");
        UserService userService = new UserService();

//        User user = userService.login(username);
//        Set<Role> roles = user.getRole();
//        for (Role r : roles) {
//            if (roles.contains("ROLE_USER")) {
//                res.sendRedirect("/userprofile");
//            } else if (roles.contains("ROLE_ADMIN")) {
//                res.sendRedirect("/adminDashboard");
//            }
//        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
