package com.olympp.gateway.service;

import com.olympp.gateway.domain.VitesseCroissance;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing VitesseCroissance.
 */
public interface VitesseCroissanceService {

    /**
     * Save a vitesseCroissance.
     *
     * @param vitesseCroissance the entity to save
     * @return the persisted entity
     */
    VitesseCroissance save(VitesseCroissance vitesseCroissance);

    /**
     * Get all the vitesseCroissances.
     *
     * @return the list of entities
     */
    List<VitesseCroissance> findAll();
    /**
     * Get all the VitesseCroissanceDTO where Plante is null.
     *
     * @return the list of entities
     */
    List<VitesseCroissance> findAllWherePlanteIsNull();


    /**
     * Get the "id" vitesseCroissance.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VitesseCroissance> findOne(Long id);

    /**
     * Delete the "id" vitesseCroissance.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
