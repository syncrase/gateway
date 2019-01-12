package com.olympp.gateway.service;

import com.olympp.gateway.domain.Floraison;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Floraison.
 */
public interface FloraisonService {

    /**
     * Save a floraison.
     *
     * @param floraison the entity to save
     * @return the persisted entity
     */
    Floraison save(Floraison floraison);

    /**
     * Get all the floraisons.
     *
     * @return the list of entities
     */
    List<Floraison> findAll();


    /**
     * Get the "id" floraison.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Floraison> findOne(Long id);

    /**
     * Delete the "id" floraison.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
