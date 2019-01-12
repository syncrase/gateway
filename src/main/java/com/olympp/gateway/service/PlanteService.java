package com.olympp.gateway.service;

import com.olympp.gateway.domain.Plante;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Plante.
 */
public interface PlanteService {

    /**
     * Save a plante.
     *
     * @param plante the entity to save
     * @return the persisted entity
     */
    Plante save(Plante plante);

    /**
     * Get all the plantes.
     *
     * @return the list of entities
     */
    List<Plante> findAll();
    /**
     * Get all the PlanteDTO where Recolte is null.
     *
     * @return the list of entities
     */
    List<Plante> findAllWhereRecolteIsNull();
    /**
     * Get all the PlanteDTO where Floraison is null.
     *
     * @return the list of entities
     */
    List<Plante> findAllWhereFloraisonIsNull();


    /**
     * Get the "id" plante.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Plante> findOne(Long id);

    /**
     * Delete the "id" plante.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
