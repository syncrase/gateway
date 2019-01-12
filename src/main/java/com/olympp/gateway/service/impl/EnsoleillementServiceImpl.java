package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.EnsoleillementService;
import com.olympp.gateway.domain.Ensoleillement;
import com.olympp.gateway.repository.EnsoleillementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Ensoleillement.
 */
@Service
@Transactional
public class EnsoleillementServiceImpl implements EnsoleillementService {

    private final Logger log = LoggerFactory.getLogger(EnsoleillementServiceImpl.class);

    private final EnsoleillementRepository ensoleillementRepository;

    public EnsoleillementServiceImpl(EnsoleillementRepository ensoleillementRepository) {
        this.ensoleillementRepository = ensoleillementRepository;
    }

    /**
     * Save a ensoleillement.
     *
     * @param ensoleillement the entity to save
     * @return the persisted entity
     */
    @Override
    public Ensoleillement save(Ensoleillement ensoleillement) {
        log.debug("Request to save Ensoleillement : {}", ensoleillement);
        return ensoleillementRepository.save(ensoleillement);
    }

    /**
     * Get all the ensoleillements.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Ensoleillement> findAll() {
        log.debug("Request to get all Ensoleillements");
        return ensoleillementRepository.findAll();
    }



    /**
     *  get all the ensoleillements where Plante is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Ensoleillement> findAllWherePlanteIsNull() {
        log.debug("Request to get all ensoleillements where Plante is null");
        return StreamSupport
            .stream(ensoleillementRepository.findAll().spliterator(), false)
            .filter(ensoleillement -> ensoleillement.getPlante() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one ensoleillement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Ensoleillement> findOne(Long id) {
        log.debug("Request to get Ensoleillement : {}", id);
        return ensoleillementRepository.findById(id);
    }

    /**
     * Delete the ensoleillement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ensoleillement : {}", id);
        ensoleillementRepository.deleteById(id);
    }
}
