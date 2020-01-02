package com.dxc.integral.identity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * User name
     */
    private String userName;

    /**
     * Password
     */
    private String password;

    /**
     * Email Address
     */
    private String email;

    /**
     * The last time when password was changed, can be null if password was never changed
     */
    private Date pwdChangedTime;

    public User() {
        super();
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, Date pwdChangedTime) {
        this.userName = userName;
        this.password = password;
        this.pwdChangedTime = pwdChangedTime;
    }

    public User(String userName, String password, String emailAddress) {
        this.userName = userName;
        this.password = password;
        this.email = emailAddress;
    }

    public User(String userName, String password, String emailAddress, Date pwdChangedTime) {
        this.userName = userName;
        this.password = password;
        this.email = emailAddress;
        this.pwdChangedTime = pwdChangedTime;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * Set the last time when password was changed, can be null if password was
     * never changed
     */
    public void setPwdChangedTime(Date pwdChangedTime) {
        if (pwdChangedTime == null) {
            this.pwdChangedTime = null;
        } else {
            this.pwdChangedTime = new Date(pwdChangedTime.getTime());
        }
    }

    /**
     * Get the last time when password was changed, can be null if password was
     * never changed
     */
    public Date getPwdChangedTime() {
        if (pwdChangedTime == null) {
            return null;
        } else {
            return new Date(pwdChangedTime.getTime());
        }
    }

}
