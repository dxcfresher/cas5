package com.dxc.integral.dao.impl.ldap;

import javax.naming.directory.DirContext;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.simple.AbstractParameterizedContextMapper;

/**
 * 2-way mapper between Java Object and LDAP {@link DirContext}.
 */
public abstract class AbstractTwoWayContextMapper<T> extends
		AbstractParameterizedContextMapper<T> {

	/**
	 * Concrete classes must implement this method to map target Java object to
	 * LDAP context.
	 * 
	 * @param transientObject
	 *            Object to be mapped.
	 * @param context
	 *            Current dir context.
	 */
	protected abstract void mapToContext(T transientObject,
			DirContextOperations context);;
}
