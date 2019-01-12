package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.ClassificationCronquistService;
import com.olympp.gateway.domain.ClassificationCronquist;
import com.olympp.gateway.repository.ClassificationCronquistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ClassificationCronquist.
 */
@Service
@Transactional
public class ClassificationCronquistServiceImpl implements ClassificationCronquistService {

    private final Logger log = LoggerFactory.getLogger(ClassificationCronquistServiceImpl.class);

    private final ClassificationCronquistRepository classificationCronquistRepository;

    public ClassificationCronquistServiceImpl(ClassificationCronquistRepository classificationCronquistRepository) {
        this.classificationCronquistRepository = classificationCronquistRepository;
    }

    /**
     * Save a classificationCronquist.
     *
     * @param classificationCronquist the entity to save
     * @return the persisted entity
     */
    @Override
    public ClassificationCronquist save(ClassificationCronquist classificationCronquist) {
        log.debug("Request to save ClassificationCronquist : {}", classificationCronquist);
        return classificationCronquistRepository.save(classificationCronquist);
    }

    /**
     * Get all the classificationCronquists.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClassificationCronquist> findAll() {
        log.debug("Request to get all ClassificationCronquists");
        return classificationCronquistRepository.findAll();
    }


    /**
     * Get one classificationCronquist by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClassificationCronquist> findOne(Long id) {
        log.debug("Request to get ClassificationCronquist : {}", id);
        return classificationCronquistRepository.findById(id);
    }

    /**
     * Delete the classificationCronquist by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClassificationCronquist : {}", id);
        classificationCronquistRepository.deleteById(id);
    }
}
