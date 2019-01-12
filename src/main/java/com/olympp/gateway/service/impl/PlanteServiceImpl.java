package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.PlanteService;
import com.olympp.gateway.domain.Plante;
import com.olympp.gateway.repository.PlanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Plante.
 */
@Service
@Transactional
public class PlanteServiceImpl implements PlanteService {

    private final Logger log = LoggerFactory.getLogger(PlanteServiceImpl.class);

    private final PlanteRepository planteRepository;

    public PlanteServiceImpl(PlanteRepository planteRepository) {
        this.planteRepository = planteRepository;
    }

    /**
     * Save a plante.
     *
     * @param plante the entity to save
     * @return the persisted entity
     */
    @Override
    public Plante save(Plante plante) {
        log.debug("Request to save Plante : {}", plante);
        return planteRepository.save(plante);
    }

    /**
     * Get all the plantes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Plante> findAll() {
        log.debug("Request to get all Plantes");
        return planteRepository.findAll();
    }



    /**
     *  get all the plantes where Recolte is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Plante> findAllWhereRecolteIsNull() {
        log.debug("Request to get all plantes where Recolte is null");
        return StreamSupport
            .stream(planteRepository.findAll().spliterator(), false)
            .filter(plante -> plante.getRecolte() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the plantes where Floraison is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Plante> findAllWhereFloraisonIsNull() {
        log.debug("Request to get all plantes where Floraison is null");
        return StreamSupport
            .stream(planteRepository.findAll().spliterator(), false)
            .filter(plante -> plante.getFloraison() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one plante by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Plante> findOne(Long id) {
        log.debug("Request to get Plante : {}", id);
        return planteRepository.findById(id);
    }

    /**
     * Delete the plante by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Plante : {}", id);
        planteRepository.deleteById(id);
    }
}
