package com.olympp.gateway.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.gateway.domain.TypeTerre;
import com.olympp.gateway.service.TypeTerreService;
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
 * REST controller for managing TypeTerre.
 */
@RestController
@RequestMapping("/api")
public class TypeTerreResource {

    private final Logger log = LoggerFactory.getLogger(TypeTerreResource.class);

    private static final String ENTITY_NAME = "typeTerre";

    private final TypeTerreService typeTerreService;

    public TypeTerreResource(TypeTerreService typeTerreService) {
        this.typeTerreService = typeTerreService;
    }

    /**
     * POST  /type-terres : Create a new typeTerre.
     *
     * @param typeTerre the typeTerre to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeTerre, or with status 400 (Bad Request) if the typeTerre has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-terres")
    @Timed
    public ResponseEntity<TypeTerre> createTypeTerre(@RequestBody TypeTerre typeTerre) throws URISyntaxException {
        log.debug("REST request to save TypeTerre : {}", typeTerre);
        if (typeTerre.getId() != null) {
            throw new BadRequestAlertException("A new typeTerre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeTerre result = typeTerreService.save(typeTerre);
        return ResponseEntity.created(new URI("/api/type-terres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-terres : Updates an existing typeTerre.
     *
     * @param typeTerre the typeTerre to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeTerre,
     * or with status 400 (Bad Request) if the typeTerre is not valid,
     * or with status 500 (Internal Server Error) if the typeTerre couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-terres")
    @Timed
    public ResponseEntity<TypeTerre> updateTypeTerre(@RequestBody TypeTerre typeTerre) throws URISyntaxException {
        log.debug("REST request to update TypeTerre : {}", typeTerre);
        if (typeTerre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeTerre result = typeTerreService.save(typeTerre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeTerre.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-terres : get all the typeTerres.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of typeTerres in body
     */
    @GetMapping("/type-terres")
    @Timed
    public List<TypeTerre> getAllTypeTerres(@RequestParam(required = false) String filter) {
        if ("plante-is-null".equals(filter)) {
            log.debug("REST request to get all TypeTerres where plante is null");
            return typeTerreService.findAllWherePlanteIsNull();
        }
        log.debug("REST request to get all TypeTerres");
        return typeTerreService.findAll();
    }

    /**
     * GET  /type-terres/:id : get the "id" typeTerre.
     *
     * @param id the id of the typeTerre to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeTerre, or with status 404 (Not Found)
     */
    @GetMapping("/type-terres/{id}")
    @Timed
    public ResponseEntity<TypeTerre> getTypeTerre(@PathVariable Long id) {
        log.debug("REST request to get TypeTerre : {}", id);
        Optional<TypeTerre> typeTerre = typeTerreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeTerre);
    }

    /**
     * DELETE  /type-terres/:id : delete the "id" typeTerre.
     *
     * @param id the id of the typeTerre to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-terres/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeTerre(@PathVariable Long id) {
        log.debug("REST request to delete TypeTerre : {}", id);
        typeTerreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
