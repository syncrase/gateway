package com.olympp.gateway.service;

import com.olympp.gateway.domain.TypeFeuillage;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TypeFeuillage.
 */
public interface TypeFeuillageService {

    /**
     * Save a typeFeuillage.
     *
     * @param typeFeuillage the entity to save
     * @return the persisted entity
     */
    TypeFeuillage save(TypeFeuillage typeFeuillage);

    /**
     * Get all the typeFeuillages.
     *
     * @return the list of entities
     */
    List<TypeFeuillage> findAll();
    /**
     * Get all the TypeFeuillageDTO where Plante is null.
     *
     * @return the list of entities
     */
    List<TypeFeuillage> findAllWherePlanteIsNull();


    /**
     * Get the "id" typeFeuillage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeFeuillage> findOne(Long id);

    /**
     * Delete the "id" typeFeuillage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
