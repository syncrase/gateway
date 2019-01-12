package com.olympp.gateway.service;

import com.olympp.gateway.domain.InteractionPlantePlante;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing InteractionPlantePlante.
 */
public interface InteractionPlantePlanteService {

    /**
     * Save a interactionPlantePlante.
     *
     * @param interactionPlantePlante the entity to save
     * @return the persisted entity
     */
    InteractionPlantePlante save(InteractionPlantePlante interactionPlantePlante);

    /**
     * Get all the interactionPlantePlantes.
     *
     * @return the list of entities
     */
    List<InteractionPlantePlante> findAll();


    /**
     * Get the "id" interactionPlantePlante.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InteractionPlantePlante> findOne(Long id);

    /**
     * Delete the "id" interactionPlantePlante.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
