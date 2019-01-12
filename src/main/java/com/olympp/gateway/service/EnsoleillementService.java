package com.olympp.gateway.service;

import com.olympp.gateway.domain.Ensoleillement;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Ensoleillement.
 */
public interface EnsoleillementService {

    /**
     * Save a ensoleillement.
     *
     * @param ensoleillement the entity to save
     * @return the persisted entity
     */
    Ensoleillement save(Ensoleillement ensoleillement);

    /**
     * Get all the ensoleillements.
     *
     * @return the list of entities
     */
    List<Ensoleillement> findAll();
    /**
     * Get all the EnsoleillementDTO where Plante is null.
     *
     * @return the list of entities
     */
    List<Ensoleillement> findAllWherePlanteIsNull();


    /**
     * Get the "id" ensoleillement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Ensoleillement> findOne(Long id);

    /**
     * Delete the "id" ensoleillement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
