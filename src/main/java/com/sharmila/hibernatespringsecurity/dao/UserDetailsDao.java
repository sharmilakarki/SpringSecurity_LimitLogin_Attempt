/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.dao;

import com.sharmila.hibernatespringsecurity.entity.UserAttempts;

/**
 *
 * @author sharmila
 */
public interface UserDetailsDao {
    void updateFailedUserAttempts(String username);
    void resetFailedAttempst(String username);
    UserAttempts getUserAttempts(String username);
}
