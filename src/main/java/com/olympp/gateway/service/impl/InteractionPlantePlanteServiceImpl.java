package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.InteractionPlantePlanteService;
import com.olympp.gateway.domain.InteractionPlantePlante;
import com.olympp.gateway.repository.InteractionPlantePlanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing InteractionPlantePlante.
 */
@Service
@Transactional
public class InteractionPlantePlanteServiceImpl implements InteractionPlantePlanteService {

    private final Logger log = LoggerFactory.getLogger(InteractionPlantePlanteServiceImpl.class);

    private final InteractionPlantePlanteRepository interactionPlantePlanteRepository;

    public InteractionPlantePlanteServiceImpl(InteractionPlantePlanteRepository interactionPlantePlanteRepository) {
        this.interactionPlantePlanteRepository = interactionPlantePlanteRepository;
    }

    /**
     * Save a interactionPlantePlante.
     *
     * @param interactionPlantePlante the entity to save
     * @return the persisted entity
     */
    @Override
    public InteractionPlantePlante save(InteractionPlantePlante interactionPlantePlante) {
        log.debug("Request to save InteractionPlantePlante : {}", interactionPlantePlante);
        return interactionPlantePlanteRepository.save(interactionPlantePlante);
    }

    /**
     * Get all the interactionPlantePlantes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<InteractionPlantePlante> findAll() {
        log.debug("Request to get all InteractionPlantePlantes");
        return interactionPlantePlanteRepository.findAll();
    }


    /**
     * Get one interactionPlantePlante by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InteractionPlantePlante> findOne(Long id) {
        log.debug("Request to get InteractionPlantePlante : {}", id);
        return interactionPlantePlanteRepository.findById(id);
    }

    /**
     * Delete the interactionPlantePlante by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InteractionPlantePlante : {}", id);
        interactionPlantePlanteRepository.deleteById(id);
    }
}
