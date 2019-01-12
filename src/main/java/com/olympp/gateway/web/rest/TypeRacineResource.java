package com.olympp.gateway.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.gateway.domain.TypeRacine;
import com.olympp.gateway.service.TypeRacineService;
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
 * REST controller for managing TypeRacine.
 */
@RestController
@RequestMapping("/api")
public class TypeRacineResource {

    private final Logger log = LoggerFactory.getLogger(TypeRacineResource.class);

    private static final String ENTITY_NAME = "typeRacine";

    private final TypeRacineService typeRacineService;

    public TypeRacineResource(TypeRacineService typeRacineService) {
        this.typeRacineService = typeRacineService;
    }

    /**
     * POST  /type-racines : Create a new typeRacine.
     *
     * @param typeRacine the typeRacine to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeRacine, or with status 400 (Bad Request) if the typeRacine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-racines")
    @Timed
    public ResponseEntity<TypeRacine> createTypeRacine(@RequestBody TypeRacine typeRacine) throws URISyntaxException {
        log.debug("REST request to save TypeRacine : {}", typeRacine);
        if (typeRacine.getId() != null) {
            throw new BadRequestAlertException("A new typeRacine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeRacine result = typeRacineService.save(typeRacine);
        return ResponseEntity.created(new URI("/api/type-racines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-racines : Updates an existing typeRacine.
     *
     * @param typeRacine the typeRacine to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeRacine,
     * or with status 400 (Bad Request) if the typeRacine is not valid,
     * or with status 500 (Internal Server Error) if the typeRacine couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-racines")
    @Timed
    public ResponseEntity<TypeRacine> updateTypeRacine(@RequestBody TypeRacine typeRacine) throws URISyntaxException {
        log.debug("REST request to update TypeRacine : {}", typeRacine);
        if (typeRacine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeRacine result = typeRacineService.save(typeRacine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeRacine.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-racines : get all the typeRacines.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of typeRacines in body
     */
    @GetMapping("/type-racines")
    @Timed
    public List<TypeRacine> getAllTypeRacines(@RequestParam(required = false) String filter) {
        if ("plante-is-null".equals(filter)) {
            log.debug("REST request to get all TypeRacines where plante is null");
            return typeRacineService.findAllWherePlanteIsNull();
        }
        log.debug("REST request to get all TypeRacines");
        return typeRacineService.findAll();
    }

    /**
     * GET  /type-racines/:id : get the "id" typeRacine.
     *
     * @param id the id of the typeRacine to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeRacine, or with status 404 (Not Found)
     */
    @GetMapping("/type-racines/{id}")
    @Timed
    public ResponseEntity<TypeRacine> getTypeRacine(@PathVariable Long id) {
        log.debug("REST request to get TypeRacine : {}", id);
        Optional<TypeRacine> typeRacine = typeRacineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeRacine);
    }

    /**
     * DELETE  /type-racines/:id : delete the "id" typeRacine.
     *
     * @param id the id of the typeRacine to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-racines/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeRacine(@PathVariable Long id) {
        log.debug("REST request to delete TypeRacine : {}", id);
        typeRacineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
