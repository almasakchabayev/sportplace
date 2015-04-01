package com.epam.aa.sportplace.dao;

public interface GenericDao<T> {
    /**
     * @desc Persist the newInstance object into database
     * @param object of type T
     * @return a key of created object
     */
    //TODO:is it ok? or should I put PK?
    Integer create(T object);

    /**
     * @desc Retrieve an object that was previously persisted to the database using the indicated id as primary key
     * @param id, receives id of generic type PK
     * @return object of generic type T
     */
    T read(Integer id);

    /**
     * @desc Save changes made to a persistent object.
     * @param transientObject of type T, i.e. object with id that exists in a database, but with other fields changed
     * @return true if object successfully updated, otherwise false
     */
    boolean update(T transientObject);

    /**
     * @desc Remove an object from persistent storage in the database
     * @param transientObject of type T, i.e. object with id that exists in a database, but with other fields changed
     * @return true if object successfully updated, otherwise false
     */
    boolean delete(T transientObject);
}
