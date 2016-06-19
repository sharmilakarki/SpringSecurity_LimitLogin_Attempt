/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.authentication;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;



public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    
    protected Log logger=LogFactory.getLog(this.getClass());
    
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        handle(request, response, authentication);
        clearAuthenticationAttribute(request);
    }
    
    protected void handle(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException{
        String targetUrl=determineTargetUrl(authentication);
        if(response.isCommitted()){
            logger.debug("Response already commited, cannot redirect to "+targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    
    protected String determineTargetUrl(Authentication authentication){
        boolean isAdmin=false;
        boolean isUser=false;
        
        Collection<? extends GrantedAuthority> authoritites=authentication.getAuthorities();
        for(GrantedAuthority auth:authoritites){
            if(auth.getAuthority().equals("ROLE_USER")){
                isUser=true;
                break;
            }
            if(auth.getAuthority().equals("ROLE_ADMIN")){
                isAdmin=true;
                break;
            }
        }
        if(isUser){
            return "/userprofile";
        }
        else if(isAdmin){
            return "/admin";
        }
        else{
            throw new IllegalStateException();
        }
        
    }
    
    protected void clearAuthenticationAttribute(HttpServletRequest request){
        HttpSession session=request.getSession();
        if(session==null){
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public Log getLogger() {
        return logger;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    
}
