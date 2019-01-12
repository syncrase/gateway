package com.olympp.gateway.service;

import com.olympp.gateway.domain.Recolte;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Recolte.
 */
public interface RecolteService {

    /**
     * Save a recolte.
     *
     * @param recolte the entity to save
     * @return the persisted entity
     */
    Recolte save(Recolte recolte);

    /**
     * Get all the recoltes.
     *
     * @return the list of entities
     */
    List<Recolte> findAll();


    /**
     * Get the "id" recolte.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Recolte> findOne(Long id);

    /**
     * Delete the "id" recolte.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
