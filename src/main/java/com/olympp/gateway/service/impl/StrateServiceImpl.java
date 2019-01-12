package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.StrateService;
import com.olympp.gateway.domain.Strate;
import com.olympp.gateway.repository.StrateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Strate.
 */
@Service
@Transactional
public class StrateServiceImpl implements StrateService {

    private final Logger log = LoggerFactory.getLogger(StrateServiceImpl.class);

    private final StrateRepository strateRepository;

    public StrateServiceImpl(StrateRepository strateRepository) {
        this.strateRepository = strateRepository;
    }

    /**
     * Save a strate.
     *
     * @param strate the entity to save
     * @return the persisted entity
     */
    @Override
    public Strate save(Strate strate) {
        log.debug("Request to save Strate : {}", strate);
        return strateRepository.save(strate);
    }

    /**
     * Get all the strates.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Strate> findAll() {
        log.debug("Request to get all Strates");
        return strateRepository.findAll();
    }



    /**
     *  get all the strates where Plante is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Strate> findAllWherePlanteIsNull() {
        log.debug("Request to get all strates where Plante is null");
        return StreamSupport
            .stream(strateRepository.findAll().spliterator(), false)
            .filter(strate -> strate.getPlante() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one strate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Strate> findOne(Long id) {
        log.debug("Request to get Strate : {}", id);
        return strateRepository.findById(id);
    }

    /**
     * Delete the strate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Strate : {}", id);
        strateRepository.deleteById(id);
    }
}
