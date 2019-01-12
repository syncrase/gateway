package com.olympp.gateway.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.gateway.domain.ClassificationCronquist;
import com.olympp.gateway.service.ClassificationCronquistService;
import com.olympp.gateway.web.rest.errors.BadRequestAlertException;
import com.olympp.gateway.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ClassificationCronquist.
 */
@RestController
@RequestMapping("/api")
public class ClassificationCronquistResource {

    private final Logger log = LoggerFactory.getLogger(ClassificationCronquistResource.class);

    private static final String ENTITY_NAME = "classificationCronquist";

    private final ClassificationCronquistService classificationCronquistService;

    public ClassificationCronquistResource(ClassificationCronquistService classificationCronquistService) {
        this.classificationCronquistService = classificationCronquistService;
    }

    /**
     * POST  /classification-cronquists : Create a new classificationCronquist.
     *
     * @param classificationCronquist the classificationCronquist to create
     * @return the ResponseEntity with status 201 (Created) and with body the new classificationCronquist, or with status 400 (Bad Request) if the classificationCronquist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/classification-cronquists")
    @Timed
    public ResponseEntity<ClassificationCronquist> createClassificationCronquist(@Valid @RequestBody ClassificationCronquist classificationCronquist) throws URISyntaxException {
        log.debug("REST request to save ClassificationCronquist : {}", classificationCronquist);
        if (classificationCronquist.getId() != null) {
            throw new BadRequestAlertException("A new classificationCronquist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClassificationCronquist result = classificationCronquistService.save(classificationCronquist);
        return ResponseEntity.created(new URI("/api/classification-cronquists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /classification-cronquists : Updates an existing classificationCronquist.
     *
     * @param classificationCronquist the classificationCronquist to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated classificationCronquist,
     * or with status 400 (Bad Request) if the classificationCronquist is not valid,
     * or with status 500 (Internal Server Error) if the classificationCronquist couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/classification-cronquists")
    @Timed
    public ResponseEntity<ClassificationCronquist> updateClassificationCronquist(@Valid @RequestBody ClassificationCronquist classificationCronquist) throws URISyntaxException {
        log.debug("REST request to update ClassificationCronquist : {}", classificationCronquist);
        if (classificationCronquist.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClassificationCronquist result = classificationCronquistService.save(classificationCronquist);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, classificationCronquist.getId().toString()))
            .body(result);
    }

    /**
     * GET  /classification-cronquists : get all the classificationCronquists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of classificationCronquists in body
     */
    @GetMapping("/classification-cronquists")
    @Timed
    public List<ClassificationCronquist> getAllClassificationCronquists() {
        log.debug("REST request to get all ClassificationCronquists");
        return classificationCronquistService.findAll();
    }

    /**
     * GET  /classification-cronquists/:id : get the "id" classificationCronquist.
     *
     * @param id the id of the classificationCronquist to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the classificationCronquist, or with status 404 (Not Found)
     */
    @GetMapping("/classification-cronquists/{id}")
    @Timed
    public ResponseEntity<ClassificationCronquist> getClassificationCronquist(@PathVariable Long id) {
        log.debug("REST request to get ClassificationCronquist : {}", id);
        Optional<ClassificationCronquist> classificationCronquist = classificationCronquistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(classificationCronquist);
    }

    /**
     * DELETE  /classification-cronquists/:id : delete the "id" classificationCronquist.
     *
     * @param id the id of the classificationCronquist to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/classification-cronquists/{id}")
    @Timed
    public ResponseEntity<Void> deleteClassificationCronquist(@PathVariable Long id) {
        log.debug("REST request to delete ClassificationCronquist : {}", id);
        classificationCronquistService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
