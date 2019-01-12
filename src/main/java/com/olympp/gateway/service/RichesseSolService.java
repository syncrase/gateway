package com.olympp.gateway.service;

import com.olympp.gateway.domain.RichesseSol;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RichesseSol.
 */
public interface RichesseSolService {

    /**
     * Save a richesseSol.
     *
     * @param richesseSol the entity to save
     * @return the persisted entity
     */
    RichesseSol save(RichesseSol richesseSol);

    /**
     * Get all the richesseSols.
     *
     * @return the list of entities
     */
    List<RichesseSol> findAll();
    /**
     * Get all the RichesseSolDTO where Plante is null.
     *
     * @return the list of entities
     */
    List<RichesseSol> findAllWherePlanteIsNull();


    /**
     * Get the "id" richesseSol.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RichesseSol> findOne(Long id);

    /**
     * Delete the "id" richesseSol.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
