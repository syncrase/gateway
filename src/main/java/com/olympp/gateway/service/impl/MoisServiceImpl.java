package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.MoisService;
import com.olympp.gateway.domain.Mois;
import com.olympp.gateway.repository.MoisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Mois.
 */
@Service
@Transactional
public class MoisServiceImpl implements MoisService {

    private final Logger log = LoggerFactory.getLogger(MoisServiceImpl.class);

    private final MoisRepository moisRepository;

    public MoisServiceImpl(MoisRepository moisRepository) {
        this.moisRepository = moisRepository;
    }

    /**
     * Save a mois.
     *
     * @param mois the entity to save
     * @return the persisted entity
     */
    @Override
    public Mois save(Mois mois) {
        log.debug("Request to save Mois : {}", mois);
        return moisRepository.save(mois);
    }

    /**
     * Get all the mois.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Mois> findAll() {
        log.debug("Request to get all Mois");
        return moisRepository.findAll();
    }



    /**
     *  get all the mois where Recolte is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Mois> findAllWhereRecolteIsNull() {
        log.debug("Request to get all mois where Recolte is null");
        return StreamSupport
            .stream(moisRepository.findAll().spliterator(), false)
            .filter(mois -> mois.getRecolte() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the mois where Floraison is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Mois> findAllWhereFloraisonIsNull() {
        log.debug("Request to get all mois where Floraison is null");
        return StreamSupport
            .stream(moisRepository.findAll().spliterator(), false)
            .filter(mois -> mois.getFloraison() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one mois by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Mois> findOne(Long id) {
        log.debug("Request to get Mois : {}", id);
        return moisRepository.findById(id);
    }

    /**
     * Delete the mois by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mois : {}", id);
        moisRepository.deleteById(id);
    }
}
