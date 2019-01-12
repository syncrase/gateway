package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.EspeceService;
import com.olympp.gateway.domain.Espece;
import com.olympp.gateway.repository.EspeceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Espece.
 */
@Service
@Transactional
public class EspeceServiceImpl implements EspeceService {

    private final Logger log = LoggerFactory.getLogger(EspeceServiceImpl.class);

    private final EspeceRepository especeRepository;

    public EspeceServiceImpl(EspeceRepository especeRepository) {
        this.especeRepository = especeRepository;
    }

    /**
     * Save a espece.
     *
     * @param espece the entity to save
     * @return the persisted entity
     */
    @Override
    public Espece save(Espece espece) {
        log.debug("Request to save Espece : {}", espece);
        return especeRepository.save(espece);
    }

    /**
     * Get all the especes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Espece> findAll() {
        log.debug("Request to get all Especes");
        return especeRepository.findAll();
    }


    /**
     * Get one espece by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Espece> findOne(Long id) {
        log.debug("Request to get Espece : {}", id);
        return especeRepository.findById(id);
    }

    /**
     * Delete the espece by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Espece : {}", id);
        especeRepository.deleteById(id);
    }
}
