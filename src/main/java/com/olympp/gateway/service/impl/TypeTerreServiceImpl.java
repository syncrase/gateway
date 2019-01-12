package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.TypeTerreService;
import com.olympp.gateway.domain.TypeTerre;
import com.olympp.gateway.repository.TypeTerreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing TypeTerre.
 */
@Service
@Transactional
public class TypeTerreServiceImpl implements TypeTerreService {

    private final Logger log = LoggerFactory.getLogger(TypeTerreServiceImpl.class);

    private final TypeTerreRepository typeTerreRepository;

    public TypeTerreServiceImpl(TypeTerreRepository typeTerreRepository) {
        this.typeTerreRepository = typeTerreRepository;
    }

    /**
     * Save a typeTerre.
     *
     * @param typeTerre the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeTerre save(TypeTerre typeTerre) {
        log.debug("Request to save TypeTerre : {}", typeTerre);
        return typeTerreRepository.save(typeTerre);
    }

    /**
     * Get all the typeTerres.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeTerre> findAll() {
        log.debug("Request to get all TypeTerres");
        return typeTerreRepository.findAll();
    }



    /**
     *  get all the typeTerres where Plante is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TypeTerre> findAllWherePlanteIsNull() {
        log.debug("Request to get all typeTerres where Plante is null");
        return StreamSupport
            .stream(typeTerreRepository.findAll().spliterator(), false)
            .filter(typeTerre -> typeTerre.getPlante() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one typeTerre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeTerre> findOne(Long id) {
        log.debug("Request to get TypeTerre : {}", id);
        return typeTerreRepository.findById(id);
    }

    /**
     * Delete the typeTerre by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeTerre : {}", id);
        typeTerreRepository.deleteById(id);
    }
}
