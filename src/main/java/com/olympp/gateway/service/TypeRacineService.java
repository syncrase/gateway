package com.olympp.gateway.service;

import com.olympp.gateway.domain.TypeRacine;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TypeRacine.
 */
public interface TypeRacineService {

    /**
     * Save a typeRacine.
     *
     * @param typeRacine the entity to save
     * @return the persisted entity
     */
    TypeRacine save(TypeRacine typeRacine);

    /**
     * Get all the typeRacines.
     *
     * @return the list of entities
     */
    List<TypeRacine> findAll();
    /**
     * Get all the TypeRacineDTO where Plante is null.
     *
     * @return the list of entities
     */
    List<TypeRacine> findAllWherePlanteIsNull();


    /**
     * Get the "id" typeRacine.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeRacine> findOne(Long id);

    /**
     * Delete the "id" typeRacine.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
