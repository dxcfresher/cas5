package com.dxc.integral.dao;

import java.io.Serializable;

/**
 * Generic DAO for common DAO operations. TODO: To be moved to another package
 * for commonly using in csc-integral-organization, csc-integral-identity, ...
 * 
 * @param <T>
 *            Target entity of the DAO.
 * @param <PK>
 *            Primary Key type of the target entity.
 */
public interface GenericDao<T, PK extends Serializable> {

	/** Persist the newInstance object into database */
	void create(T newInstance);

	/**
	 * Retrieve an object that was previously persisted to the database using
	 * the indicated id as primary key
	 */
	T read(PK id);

	/** Save changes made to a persistent object. */
	void update(T transientObject);

	/** Remove an object from persistent storage in the database */
	void delete(T persistentObject);
}
