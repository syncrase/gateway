package com.olympp.gateway.service;

import com.olympp.gateway.domain.ClassificationCronquist;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ClassificationCronquist.
 */
public interface ClassificationCronquistService {

    /**
     * Save a classificationCronquist.
     *
     * @param classificationCronquist the entity to save
     * @return the persisted entity
     */
    ClassificationCronquist save(ClassificationCronquist classificationCronquist);

    /**
     * Get all the classificationCronquists.
     *
     * @return the list of entities
     */
    List<ClassificationCronquist> findAll();


    /**
     * Get the "id" classificationCronquist.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ClassificationCronquist> findOne(Long id);

    /**
     * Delete the "id" classificationCronquist.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
