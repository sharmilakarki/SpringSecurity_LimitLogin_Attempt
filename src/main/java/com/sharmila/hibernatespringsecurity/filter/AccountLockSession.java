/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.filter;

import com.sharmila.hibernatespringsecurity.service.CustomUserDetailsService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sharmila
 */
//@WebFilter(value = "AccountLockSession", urlPatterns = "/login")
//@WebInitParam(name = "max-period", value = "180")

public class AccountLockSession implements Filter {

    private Long maxPeriod;

    public AccountLockSession() {
        
    }

    public AccountLockSession(Long maxPeriod) {
        this.maxPeriod = maxPeriod;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (filterConfig.getInitParameter("max-period") == null) {
            throw new IllegalStateException("max-period must be provided");
        }
        maxPeriod = new Long(filterConfig.getInitParameter("max-period"));

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        if (session != null) {
            long activated = (long) session.getAttribute("activation-time");
            
            if (System.currentTimeMillis() > (activated + maxPeriod)) {
                session.invalidate();
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
