package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.FloraisonService;
import com.olympp.gateway.domain.Floraison;
import com.olympp.gateway.repository.FloraisonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Floraison.
 */
@Service
@Transactional
public class FloraisonServiceImpl implements FloraisonService {

    private final Logger log = LoggerFactory.getLogger(FloraisonServiceImpl.class);

    private final FloraisonRepository floraisonRepository;

    public FloraisonServiceImpl(FloraisonRepository floraisonRepository) {
        this.floraisonRepository = floraisonRepository;
    }

    /**
     * Save a floraison.
     *
     * @param floraison the entity to save
     * @return the persisted entity
     */
    @Override
    public Floraison save(Floraison floraison) {
        log.debug("Request to save Floraison : {}", floraison);
        return floraisonRepository.save(floraison);
    }

    /**
     * Get all the floraisons.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Floraison> findAll() {
        log.debug("Request to get all Floraisons");
        return floraisonRepository.findAll();
    }


    /**
     * Get one floraison by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Floraison> findOne(Long id) {
        log.debug("Request to get Floraison : {}", id);
        return floraisonRepository.findById(id);
    }

    /**
     * Delete the floraison by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Floraison : {}", id);
        floraisonRepository.deleteById(id);
    }
}
