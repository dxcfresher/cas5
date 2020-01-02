package com.dxc.integral.identity.dao.impl;

import javax.naming.Name;

import org.springframework.ldap.core.DistinguishedName;

import com.dxc.integral.dao.impl.ldap.GenericDaoLdapImpl;
import com.dxc.integral.dao.impl.ldap.LdapAttributes;
import com.dxc.integral.identity.User;
import com.dxc.integral.identity.dao.IdentityDao;

/**
 * LDAP implementation of {@link IdentityDao}.
 */
public class IdentityDaoLdapImpl extends GenericDaoLdapImpl<User, String> implements IdentityDao {

    protected Name buildDn(User user) {
        DistinguishedName dn = new DistinguishedName();
        dn.add("cn", user.getUserName());

        return dn;
    }

    @Override
    protected String[] getLdapAttributes() {
        return new String[] { LdapAttributes.ALL_MANDATORY, LdapAttributes.PWD_CHANGED_TIME };
    }

}
