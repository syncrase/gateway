package com.olympp.gateway.service;

import com.olympp.gateway.domain.TypeTerre;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TypeTerre.
 */
public interface TypeTerreService {

    /**
     * Save a typeTerre.
     *
     * @param typeTerre the entity to save
     * @return the persisted entity
     */
    TypeTerre save(TypeTerre typeTerre);

    /**
     * Get all the typeTerres.
     *
     * @return the list of entities
     */
    List<TypeTerre> findAll();
    /**
     * Get all the TypeTerreDTO where Plante is null.
     *
     * @return the list of entities
     */
    List<TypeTerre> findAllWherePlanteIsNull();


    /**
     * Get the "id" typeTerre.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeTerre> findOne(Long id);

    /**
     * Delete the "id" typeTerre.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
