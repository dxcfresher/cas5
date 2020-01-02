package com.dxc.integral.dao.impl.ldap;

import java.io.Serializable;
import java.util.List;

import javax.naming.Name;
import javax.naming.directory.SearchControls;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import com.dxc.integral.dao.GenericDao;

/**
 * Generic LDAP implementation for {@link GenericDao}.
 */
public abstract class GenericDaoLdapImpl<T, ID extends Serializable> implements
		GenericDao<T, ID> {

    private static final String[] LDAP_ATTRIBUTES = { LdapAttributes.ALL_MANDATORY };

	private LdapTemplate ldapTemplate;

	private AbstractTwoWayContextMapper<T> contextMapper;

	public void create(T newInstance) {
		DirContextAdapter context = new DirContextAdapter();
		contextMapper.mapToContext(newInstance, context);
		ldapTemplate.bind(buildDn(newInstance), newInstance, null);
	}

	@SuppressWarnings("unchecked")
    public T read(String id) {
		AndFilter filter = new AndFilter().and(new EqualsFilter("cn", id));

        SearchControls searchControls = new SearchControls();
        searchControls.setReturningAttributes(getLdapAttributes());

        List<T> results = ldapTemplate.search(DistinguishedName.EMPTY_PATH, filter.encode(),
                searchControls, contextMapper);

		if (results.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		} else if (results.size() > 1) {
			throw new IncorrectResultSizeDataAccessException(1);
		}

		return results.get(0);
	}

    protected String[] getLdapAttributes() {
        return LDAP_ATTRIBUTES;
    }

	public void update(T transientObject) {
		Name dn = buildDn(transientObject);
		DirContextOperations context = ldapTemplate.lookupContext(dn);
		contextMapper.mapToContext(transientObject, context);
		ldapTemplate.modifyAttributes(context);
	}

	public void delete(T persistentObject) {
		ldapTemplate.unbind(buildDn(persistentObject));
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	/**
	 * Builds LDAP Distinguished Name from input Java object.
	 * 
	 * @param transientObject
	 *            Java object.
	 * @return Distinguished Name of the input <code>transientObject</code>.
	 */
	protected abstract Name buildDn(T transientObject);

	public void setContextMapper(AbstractTwoWayContextMapper<T> contextMapper) {
		this.contextMapper = contextMapper;
	}
}
