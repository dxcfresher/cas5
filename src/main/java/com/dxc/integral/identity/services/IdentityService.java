package com.dxc.integral.identity.services;

import javax.naming.NamingException;

/**
 * Interface for managing the identity in Integral.
 */
public interface IdentityService {

    /**
     * Changes <code>user</code>'s password to the input.
     * <code>newPassword</code>.
     * 
     * @param user
     *            User whose password will be changed.
     * @param oldPassword
     *            Old password.
     * @param newPassword
     *            New password.
     */
    void changePassword(String userName, String oldPassword, String newPassword)
            throws NamingException;

    /**
     * Resets password of the user whose name is specified in
     * <code>userName</code>.
     * 
     * @param userName
     *            Username whose password will be reset.
     * @return New password.
     */
    String resetPassword(String userName);
}
