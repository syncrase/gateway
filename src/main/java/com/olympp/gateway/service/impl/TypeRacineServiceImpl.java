package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.TypeRacineService;
import com.olympp.gateway.domain.TypeRacine;
import com.olympp.gateway.repository.TypeRacineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing TypeRacine.
 */
@Service
@Transactional
public class TypeRacineServiceImpl implements TypeRacineService {

    private final Logger log = LoggerFactory.getLogger(TypeRacineServiceImpl.class);

    private final TypeRacineRepository typeRacineRepository;

    public TypeRacineServiceImpl(TypeRacineRepository typeRacineRepository) {
        this.typeRacineRepository = typeRacineRepository;
    }

    /**
     * Save a typeRacine.
     *
     * @param typeRacine the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeRacine save(TypeRacine typeRacine) {
        log.debug("Request to save TypeRacine : {}", typeRacine);
        return typeRacineRepository.save(typeRacine);
    }

    /**
     * Get all the typeRacines.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeRacine> findAll() {
        log.debug("Request to get all TypeRacines");
        return typeRacineRepository.findAll();
    }



    /**
     *  get all the typeRacines where Plante is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TypeRacine> findAllWherePlanteIsNull() {
        log.debug("Request to get all typeRacines where Plante is null");
        return StreamSupport
            .stream(typeRacineRepository.findAll().spliterator(), false)
            .filter(typeRacine -> typeRacine.getPlante() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one typeRacine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeRacine> findOne(Long id) {
        log.debug("Request to get TypeRacine : {}", id);
        return typeRacineRepository.findById(id);
    }

    /**
     * Delete the typeRacine by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeRacine : {}", id);
        typeRacineRepository.deleteById(id);
    }
}
