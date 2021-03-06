package com.olympp.gateway.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.gateway.domain.Plante;
import com.olympp.gateway.service.PlanteService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Plante.
 */
@RestController
@RequestMapping("/api")
public class PlanteResource {

    private final Logger log = LoggerFactory.getLogger(PlanteResource.class);

    private static final String ENTITY_NAME = "plante";

    private final PlanteService planteService;

    public PlanteResource(PlanteService planteService) {
        this.planteService = planteService;
    }

    /**
     * POST  /plantes : Create a new plante.
     *
     * @param plante the plante to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plante, or with status 400 (Bad Request) if the plante has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plantes")
    @Timed
    public ResponseEntity<Plante> createPlante(@Valid @RequestBody Plante plante) throws URISyntaxException {
        log.debug("REST request to save Plante : {}", plante);
        if (plante.getId() != null) {
            throw new BadRequestAlertException("A new plante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Plante result = planteService.save(plante);
        return ResponseEntity.created(new URI("/api/plantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plantes : Updates an existing plante.
     *
     * @param plante the plante to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plante,
     * or with status 400 (Bad Request) if the plante is not valid,
     * or with status 500 (Internal Server Error) if the plante couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plantes")
    @Timed
    public ResponseEntity<Plante> updatePlante(@Valid @RequestBody Plante plante) throws URISyntaxException {
        log.debug("REST request to update Plante : {}", plante);
        if (plante.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Plante result = planteService.save(plante);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plante.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plantes : get all the plantes.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of plantes in body
     */
    @GetMapping("/plantes")
    @Timed
    public List<Plante> getAllPlantes(@RequestParam(required = false) String filter) {
        if ("recolte-is-null".equals(filter)) {
            log.debug("REST request to get all Plantes where recolte is null");
            return planteService.findAllWhereRecolteIsNull();
        }
        if ("floraison-is-null".equals(filter)) {
            log.debug("REST request to get all Plantes where floraison is null");
            return planteService.findAllWhereFloraisonIsNull();
        }
        log.debug("REST request to get all Plantes");
        return planteService.findAll();
    }

    /**
     * GET  /plantes/:id : get the "id" plante.
     *
     * @param id the id of the plante to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plante, or with status 404 (Not Found)
     */
    @GetMapping("/plantes/{id}")
    @Timed
    public ResponseEntity<Plante> getPlante(@PathVariable Long id) {
        log.debug("REST request to get Plante : {}", id);
        Optional<Plante> plante = planteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(plante);
    }

    /**
     * DELETE  /plantes/:id : delete the "id" plante.
     *
     * @param id the id of the plante to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plantes/{id}")
    @Timed
    public ResponseEntity<Void> deletePlante(@PathVariable Long id) {
        log.debug("REST request to delete Plante : {}", id);
        planteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
