package com.dxc.integral.identity.services.impl;

import java.util.Calendar;
import java.util.Date;

import javax.naming.NamingException;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;

import com.dxc.integral.identity.User;
import com.dxc.integral.identity.dao.IdentityDao;
import com.dxc.integral.identity.services.IdentityService;

/**
 * Implementation of {@link IdentityService} for LDAP.
 */
public class IdentityServiceLdapImpl implements IdentityService {

    private IdentityDao identityDao;

    /**
     * Number of milliseconds in a day
     */
    private static final long MS_IN_DAY = 86400000l;

    public void changePassword(String userName, String oldPassword,
            String newPassword) throws NamingException {
        User user = identityDao.read(userName);

        if (!new LdapShaPasswordEncoder().isPasswordValid(user.getPassword(),
                oldPassword, null)) {
            throw new RuntimeException("Invalid password");
        }

        user.setPassword(newPassword);
        identityDao.update(user);
    }

    public String resetPassword(String userName) {
        User user = identityDao.read(userName);
        String newPassword = "password";//String.valueOf(passwordGenerator.generatePassword());
        user.setPassword(newPassword);
        identityDao.update(user);
        return newPassword;
    }
	
    /**
     * For JUnitTest
     */
    Date getCurrentTime() {
        return new Date();
    }

    public void setIdentityDao(IdentityDao identityDao) {
        this.identityDao = identityDao;
    }

}
