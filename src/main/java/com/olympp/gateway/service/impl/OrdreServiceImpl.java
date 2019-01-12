package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.OrdreService;
import com.olympp.gateway.domain.Ordre;
import com.olympp.gateway.repository.OrdreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Ordre.
 */
@Service
@Transactional
public class OrdreServiceImpl implements OrdreService {

    private final Logger log = LoggerFactory.getLogger(OrdreServiceImpl.class);

    private final OrdreRepository ordreRepository;

    public OrdreServiceImpl(OrdreRepository ordreRepository) {
        this.ordreRepository = ordreRepository;
    }

    /**
     * Save a ordre.
     *
     * @param ordre the entity to save
     * @return the persisted entity
     */
    @Override
    public Ordre save(Ordre ordre) {
        log.debug("Request to save Ordre : {}", ordre);
        return ordreRepository.save(ordre);
    }

    /**
     * Get all the ordres.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Ordre> findAll() {
        log.debug("Request to get all Ordres");
        return ordreRepository.findAll();
    }


    /**
     * Get one ordre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Ordre> findOne(Long id) {
        log.debug("Request to get Ordre : {}", id);
        return ordreRepository.findById(id);
    }

    /**
     * Delete the ordre by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ordre : {}", id);
        ordreRepository.deleteById(id);
    }
}
