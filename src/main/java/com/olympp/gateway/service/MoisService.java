package com.olympp.gateway.service;

import com.olympp.gateway.domain.Mois;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Mois.
 */
public interface MoisService {

    /**
     * Save a mois.
     *
     * @param mois the entity to save
     * @return the persisted entity
     */
    Mois save(Mois mois);

    /**
     * Get all the mois.
     *
     * @return the list of entities
     */
    List<Mois> findAll();
    /**
     * Get all the MoisDTO where Recolte is null.
     *
     * @return the list of entities
     */
    List<Mois> findAllWhereRecolteIsNull();
    /**
     * Get all the MoisDTO where Floraison is null.
     *
     * @return the list of entities
     */
    List<Mois> findAllWhereFloraisonIsNull();


    /**
     * Get the "id" mois.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Mois> findOne(Long id);

    /**
     * Delete the "id" mois.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
