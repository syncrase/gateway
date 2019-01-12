package com.olympp.gateway.service;

import com.olympp.gateway.domain.Famille;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Famille.
 */
public interface FamilleService {

    /**
     * Save a famille.
     *
     * @param famille the entity to save
     * @return the persisted entity
     */
    Famille save(Famille famille);

    /**
     * Get all the familles.
     *
     * @return the list of entities
     */
    List<Famille> findAll();


    /**
     * Get the "id" famille.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Famille> findOne(Long id);

    /**
     * Delete the "id" famille.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
