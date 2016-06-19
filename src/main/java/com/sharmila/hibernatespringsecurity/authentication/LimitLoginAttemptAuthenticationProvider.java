/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.authentication;

import com.sharmila.hibernatespringsecurity.dao.UserDetailsDao;
import com.sharmila.hibernatespringsecurity.entity.UserAttempts;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 *
 * @author sharmila
 */
@Component("authenticationProvider")

public class LimitLoginAttemptAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    public UserDetailsDao userDetailsDao;

    @Autowired
    @Qualifier("userDetailsService")
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            Authentication authe = super.authenticate(authentication);
        //if the user reaches here, that means login is success else the exception will be thrown

            userDetailsDao.resetFailedAttempst(authentication.getName());
            return authe;
        } 
       catch (BadCredentialsException b) {
            //invalid login attempts occur,user attempts should be inserted
            userDetailsDao.updateFailedUserAttempts(authentication.getName());
            throw b;
        } catch (LockedException l) {
            //user gets Locked

            String error = "";
            UserAttempts userAttempts = (UserAttempts) userDetailsDao.getUserAttempts(authentication.getName());
            if (userAttempts != null) {
                Date lastAttemptDate = userAttempts.getLastModified();
                error = "The account is locked for user " + authentication.getName() + " <br> last attempt was on " + lastAttemptDate;
                
            } else {

                System.out.println("This is auto message");
                l.getMessage();
            }
            
            throw new LockedException(error);
        }
    }

    public UserDetailsDao getUserDetailsDao() {
        return userDetailsDao;
    }

    public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }

}
