package com.olympp.gateway.service;

import com.olympp.gateway.domain.Espece;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Espece.
 */
public interface EspeceService {

    /**
     * Save a espece.
     *
     * @param espece the entity to save
     * @return the persisted entity
     */
    Espece save(Espece espece);

    /**
     * Get all the especes.
     *
     * @return the list of entities
     */
    List<Espece> findAll();


    /**
     * Get the "id" espece.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Espece> findOne(Long id);

    /**
     * Delete the "id" espece.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
