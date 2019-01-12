package com.olympp.gateway.service;

import com.olympp.gateway.domain.Strate;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Strate.
 */
public interface StrateService {

    /**
     * Save a strate.
     *
     * @param strate the entity to save
     * @return the persisted entity
     */
    Strate save(Strate strate);

    /**
     * Get all the strates.
     *
     * @return the list of entities
     */
    List<Strate> findAll();
    /**
     * Get all the StrateDTO where Plante is null.
     *
     * @return the list of entities
     */
    List<Strate> findAllWherePlanteIsNull();


    /**
     * Get the "id" strate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Strate> findOne(Long id);

    /**
     * Delete the "id" strate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
