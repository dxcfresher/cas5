package com.dxc.integral.identity.dao.impl;

import org.springframework.ldap.core.DirContextOperations;

import com.dxc.integral.dao.impl.ldap.AbstractTwoWayContextMapper;
import com.dxc.integral.dao.impl.ldap.LdapAttributes;
import com.dxc.integral.identity.User;

/**
 * LDAP Context Mapper for {@link User}.
 */
public class IdentityLdapContextMapper extends AbstractTwoWayContextMapper<User> {

    @Override
    protected void mapToContext(User transientObject, DirContextOperations context) {
        context.setAttributeValue(LdapAttributes.USER_PASSWORD, transientObject.getPassword());
    }

    @Override
    protected User doMapFromContext(DirContextOperations ctx) {
        User user = new User();

        user.setUserName(ctx.getStringAttribute(LdapAttributes.COMMON_NAME));
        user.setPassword(new String((byte[]) ctx.getObjectAttribute(LdapAttributes.USER_PASSWORD)));
        user.setEmail(ctx.getStringAttribute(LdapAttributes.MAIL));

        String pwdChangedTime = ctx.getStringAttribute(LdapAttributes.PWD_CHANGED_TIME);
        if (pwdChangedTime != null) {
            user.setPwdChangedTime(GeneralizedTimeParser.parse(pwdChangedTime));
        } else {
            user.setPwdChangedTime(null);
        }

        return user;
    }

}
