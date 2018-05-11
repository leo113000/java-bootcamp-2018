package com.globant.bootcamp.persistence;

import java.util.List;

/**
 * @param <D> ID of the repository
 * @param <E> MODEL to be stored
 */
public interface iRepository<D, E> {
	/**
	 * @return all the elements in the repository
	 */
	List<E> getAll();

	/**
	 * @param id
	 * @return specific element or null
	 */
	E getById(D id);

	/**
	 * Remove an element of the repo by the identification field
	 *
	 * @param id
	 */
	void removeById(D id);

	/**
	 * Save a Model into the repository
	 *
	 * @param data the object to be saved
	 * @return the saved object
	 */
	E save(E data);

}
