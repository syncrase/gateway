package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.RichesseSolService;
import com.olympp.gateway.domain.RichesseSol;
import com.olympp.gateway.repository.RichesseSolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing RichesseSol.
 */
@Service
@Transactional
public class RichesseSolServiceImpl implements RichesseSolService {

    private final Logger log = LoggerFactory.getLogger(RichesseSolServiceImpl.class);

    private final RichesseSolRepository richesseSolRepository;

    public RichesseSolServiceImpl(RichesseSolRepository richesseSolRepository) {
        this.richesseSolRepository = richesseSolRepository;
    }

    /**
     * Save a richesseSol.
     *
     * @param richesseSol the entity to save
     * @return the persisted entity
     */
    @Override
    public RichesseSol save(RichesseSol richesseSol) {
        log.debug("Request to save RichesseSol : {}", richesseSol);
        return richesseSolRepository.save(richesseSol);
    }

    /**
     * Get all the richesseSols.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RichesseSol> findAll() {
        log.debug("Request to get all RichesseSols");
        return richesseSolRepository.findAll();
    }



    /**
     *  get all the richesseSols where Plante is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RichesseSol> findAllWherePlanteIsNull() {
        log.debug("Request to get all richesseSols where Plante is null");
        return StreamSupport
            .stream(richesseSolRepository.findAll().spliterator(), false)
            .filter(richesseSol -> richesseSol.getPlante() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one richesseSol by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RichesseSol> findOne(Long id) {
        log.debug("Request to get RichesseSol : {}", id);
        return richesseSolRepository.findById(id);
    }

    /**
     * Delete the richesseSol by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RichesseSol : {}", id);
        richesseSolRepository.deleteById(id);
    }
}
