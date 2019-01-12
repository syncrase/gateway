package com.olympp.gateway.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.gateway.domain.TypeFeuillage;
import com.olympp.gateway.service.TypeFeuillageService;
import com.olympp.gateway.web.rest.errors.BadRequestAlertException;
import com.olympp.gateway.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing TypeFeuillage.
 */
@RestController
@RequestMapping("/api")
public class TypeFeuillageResource {

    private final Logger log = LoggerFactory.getLogger(TypeFeuillageResource.class);

    private static final String ENTITY_NAME = "typeFeuillage";

    private final TypeFeuillageService typeFeuillageService;

    public TypeFeuillageResource(TypeFeuillageService typeFeuillageService) {
        this.typeFeuillageService = typeFeuillageService;
    }

    /**
     * POST  /type-feuillages : Create a new typeFeuillage.
     *
     * @param typeFeuillage the typeFeuillage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeFeuillage, or with status 400 (Bad Request) if the typeFeuillage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-feuillages")
    @Timed
    public ResponseEntity<TypeFeuillage> createTypeFeuillage(@RequestBody TypeFeuillage typeFeuillage) throws URISyntaxException {
        log.debug("REST request to save TypeFeuillage : {}", typeFeuillage);
        if (typeFeuillage.getId() != null) {
            throw new BadRequestAlertException("A new typeFeuillage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeFeuillage result = typeFeuillageService.save(typeFeuillage);
        return ResponseEntity.created(new URI("/api/type-feuillages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-feuillages : Updates an existing typeFeuillage.
     *
     * @param typeFeuillage the typeFeuillage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeFeuillage,
     * or with status 400 (Bad Request) if the typeFeuillage is not valid,
     * or with status 500 (Internal Server Error) if the typeFeuillage couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-feuillages")
    @Timed
    public ResponseEntity<TypeFeuillage> updateTypeFeuillage(@RequestBody TypeFeuillage typeFeuillage) throws URISyntaxException {
        log.debug("REST request to update TypeFeuillage : {}", typeFeuillage);
        if (typeFeuillage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeFeuillage result = typeFeuillageService.save(typeFeuillage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeFeuillage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-feuillages : get all the typeFeuillages.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of typeFeuillages in body
     */
    @GetMapping("/type-feuillages")
    @Timed
    public List<TypeFeuillage> getAllTypeFeuillages(@RequestParam(required = false) String filter) {
        if ("plante-is-null".equals(filter)) {
            log.debug("REST request to get all TypeFeuillages where plante is null");
            return typeFeuillageService.findAllWherePlanteIsNull();
        }
        log.debug("REST request to get all TypeFeuillages");
        return typeFeuillageService.findAll();
    }

    /**
     * GET  /type-feuillages/:id : get the "id" typeFeuillage.
     *
     * @param id the id of the typeFeuillage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeFeuillage, or with status 404 (Not Found)
     */
    @GetMapping("/type-feuillages/{id}")
    @Timed
    public ResponseEntity<TypeFeuillage> getTypeFeuillage(@PathVariable Long id) {
        log.debug("REST request to get TypeFeuillage : {}", id);
        Optional<TypeFeuillage> typeFeuillage = typeFeuillageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeFeuillage);
    }

    /**
     * DELETE  /type-feuillages/:id : delete the "id" typeFeuillage.
     *
     * @param id the id of the typeFeuillage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-feuillages/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeFeuillage(@PathVariable Long id) {
        log.debug("REST request to delete TypeFeuillage : {}", id);
        typeFeuillageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
