package com.olympp.gateway.service;

import com.olympp.gateway.domain.Ordre;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Ordre.
 */
public interface OrdreService {

    /**
     * Save a ordre.
     *
     * @param ordre the entity to save
     * @return the persisted entity
     */
    Ordre save(Ordre ordre);

    /**
     * Get all the ordres.
     *
     * @return the list of entities
     */
    List<Ordre> findAll();


    /**
     * Get the "id" ordre.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Ordre> findOne(Long id);

    /**
     * Delete the "id" ordre.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
