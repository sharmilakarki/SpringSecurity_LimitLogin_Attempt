/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.dao.impl;

import com.sharmila.hibernatespringsecurity.dao.UserDetailsDao;
import com.sharmila.hibernatespringsecurity.entity.UserAttempts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.LockedException;

/**
 *
 * @author sharmila
 */

public class UserDetailsDaoImpl extends JdbcDaoSupport implements UserDetailsDao {

    private static final String SQL_USERS_UPDATE_LOCKED = "UPDATE USER SET accountNonLocked = ? WHERE username = ?";
    private static final String SQL_USERS_COUNT = "SELECT count(*) FROM USER WHERE username = ?";

    private static final String SQL_USER_ATTEMPTS_GET = "SELECT * FROM USER_ATTEMPTS WHERE username = ?";
    private static final String SQL_USER_ATTEMPTS_INSERT = "INSERT INTO USER_ATTEMPTS (USERNAME, ATTEMPTS, LASTMODIFIED) VALUES(?,?,?)";
    private static final String SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS = "UPDATE USER_ATTEMPTS SET attempts = attempts + 1, lastmodified = ? WHERE username = ?";
    private static final String SQL_USER_ATTEMPTS_RESET_ATTEMPTS = "UPDATE USER_ATTEMPTS SET attempts = 0, lastmodified = null WHERE username = ?";

    private static final int MAX_ATTEMPTS = 3;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public UserDetailsDaoImpl() {
    }

    public UserDetailsDaoImpl(DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }

    @Override
    public void updateFailedUserAttempts(String username) {

        UserAttempts user = getUserAttempts(username);
        if (user == null) {
            if (isUserExists(username)) {
                // if no record, insert a new
                getJdbcTemplate().update(SQL_USER_ATTEMPTS_INSERT, new Object[]{username, 1, new Date()});
            }
        } else {

            if (isUserExists(username)) {
                // update attempts count, +1
                getJdbcTemplate().update(SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS, new Object[]{new Date(), username});
            }

            if (user.getAttempts() + 1 >= MAX_ATTEMPTS) {
                // locked user
                getJdbcTemplate().update(SQL_USERS_UPDATE_LOCKED, new Object[]{false, username});
                // throw exception
                throw new LockedException("User Account is locked!");
            }

        }

    }

    @Override
    public UserAttempts getUserAttempts(String username) {

        try {

            UserAttempts userAttempts = getJdbcTemplate().queryForObject(SQL_USER_ATTEMPTS_GET,
                    new Object[]{username}, new RowMapper<UserAttempts>() {
                        public UserAttempts mapRow(ResultSet rs, int rowNum) throws SQLException {

                            UserAttempts user = new UserAttempts();
                            user.setId(rs.getInt("id"));
                            user.setUsername(rs.getString("username"));
                            user.setAttempts(rs.getInt("attempts"));
                            user.setLastModified(rs.getDate("lastModified"));

                            return user;
                        }

                    });
            return userAttempts;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public void resetFailedAttempst(String username) {

        getJdbcTemplate().update(
                SQL_USER_ATTEMPTS_RESET_ATTEMPTS, new Object[]{username});

    }

    private boolean isUserExists(String username) {

        boolean result = false;

        int count = getJdbcTemplate().queryForObject(
                SQL_USERS_COUNT, new Object[]{username}, Integer.class);
        if (count > 0) {
            result = true;
        }

        return result;
    }

}
