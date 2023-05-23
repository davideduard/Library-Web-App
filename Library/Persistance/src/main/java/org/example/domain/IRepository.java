package org.example.domain;

import java.io.Serializable;

public interface IRepository<ID extends Serializable, E extends Entity<ID>>{
    /*
        Method that adds an Entity to the repository
        Input: E entity, the entity that wants to be added
        Output: null, if the entity is added successfully
                E, the entity if the add fails
        Exception: throws Repository Exception
     */
    E add(E entity);

    /*
        Method that removes an Entity from the repository
        Input: E entity, the entity that needs to be removed
        Output: E, if the entity is removed successfully

        Exception: throws Repository Exception if the remove operation could not be done
     */
    E remove(E entity);

    E update (E oldEntity, E newEntity);

    Iterable<E> getEntities();

    E search(ID id);
}
