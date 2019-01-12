package com.olympp.gateway.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.gateway.domain.RichesseSol;
import com.olympp.gateway.service.RichesseSolService;
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
 * REST controller for managing RichesseSol.
 */
@RestController
@RequestMapping("/api")
public class RichesseSolResource {

    private final Logger log = LoggerFactory.getLogger(RichesseSolResource.class);

    private static final String ENTITY_NAME = "richesseSol";

    private final RichesseSolService richesseSolService;

    public RichesseSolResource(RichesseSolService richesseSolService) {
        this.richesseSolService = richesseSolService;
    }

    /**
     * POST  /richesse-sols : Create a new richesseSol.
     *
     * @param richesseSol the richesseSol to create
     * @return the ResponseEntity with status 201 (Created) and with body the new richesseSol, or with status 400 (Bad Request) if the richesseSol has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/richesse-sols")
    @Timed
    public ResponseEntity<RichesseSol> createRichesseSol(@RequestBody RichesseSol richesseSol) throws URISyntaxException {
        log.debug("REST request to save RichesseSol : {}", richesseSol);
        if (richesseSol.getId() != null) {
            throw new BadRequestAlertException("A new richesseSol cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RichesseSol result = richesseSolService.save(richesseSol);
        return ResponseEntity.created(new URI("/api/richesse-sols/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /richesse-sols : Updates an existing richesseSol.
     *
     * @param richesseSol the richesseSol to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated richesseSol,
     * or with status 400 (Bad Request) if the richesseSol is not valid,
     * or with status 500 (Internal Server Error) if the richesseSol couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/richesse-sols")
    @Timed
    public ResponseEntity<RichesseSol> updateRichesseSol(@RequestBody RichesseSol richesseSol) throws URISyntaxException {
        log.debug("REST request to update RichesseSol : {}", richesseSol);
        if (richesseSol.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RichesseSol result = richesseSolService.save(richesseSol);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, richesseSol.getId().toString()))
            .body(result);
    }

    /**
     * GET  /richesse-sols : get all the richesseSols.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of richesseSols in body
     */
    @GetMapping("/richesse-sols")
    @Timed
    public List<RichesseSol> getAllRichesseSols(@RequestParam(required = false) String filter) {
        if ("plante-is-null".equals(filter)) {
            log.debug("REST request to get all RichesseSols where plante is null");
            return richesseSolService.findAllWherePlanteIsNull();
        }
        log.debug("REST request to get all RichesseSols");
        return richesseSolService.findAll();
    }

    /**
     * GET  /richesse-sols/:id : get the "id" richesseSol.
     *
     * @param id the id of the richesseSol to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the richesseSol, or with status 404 (Not Found)
     */
    @GetMapping("/richesse-sols/{id}")
    @Timed
    public ResponseEntity<RichesseSol> getRichesseSol(@PathVariable Long id) {
        log.debug("REST request to get RichesseSol : {}", id);
        Optional<RichesseSol> richesseSol = richesseSolService.findOne(id);
        return ResponseUtil.wrapOrNotFound(richesseSol);
    }

    /**
     * DELETE  /richesse-sols/:id : delete the "id" richesseSol.
     *
     * @param id the id of the richesseSol to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/richesse-sols/{id}")
    @Timed
    public ResponseEntity<Void> deleteRichesseSol(@PathVariable Long id) {
        log.debug("REST request to delete RichesseSol : {}", id);
        richesseSolService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
