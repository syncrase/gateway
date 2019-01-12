package com.olympp.gateway.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.gateway.domain.Recolte;
import com.olympp.gateway.service.RecolteService;
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

/**
 * REST controller for managing Recolte.
 */
@RestController
@RequestMapping("/api")
public class RecolteResource {

    private final Logger log = LoggerFactory.getLogger(RecolteResource.class);

    private static final String ENTITY_NAME = "recolte";

    private final RecolteService recolteService;

    public RecolteResource(RecolteService recolteService) {
        this.recolteService = recolteService;
    }

    /**
     * POST  /recoltes : Create a new recolte.
     *
     * @param recolte the recolte to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recolte, or with status 400 (Bad Request) if the recolte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recoltes")
    @Timed
    public ResponseEntity<Recolte> createRecolte(@RequestBody Recolte recolte) throws URISyntaxException {
        log.debug("REST request to save Recolte : {}", recolte);
        if (recolte.getId() != null) {
            throw new BadRequestAlertException("A new recolte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Recolte result = recolteService.save(recolte);
        return ResponseEntity.created(new URI("/api/recoltes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recoltes : Updates an existing recolte.
     *
     * @param recolte the recolte to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recolte,
     * or with status 400 (Bad Request) if the recolte is not valid,
     * or with status 500 (Internal Server Error) if the recolte couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recoltes")
    @Timed
    public ResponseEntity<Recolte> updateRecolte(@RequestBody Recolte recolte) throws URISyntaxException {
        log.debug("REST request to update Recolte : {}", recolte);
        if (recolte.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Recolte result = recolteService.save(recolte);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recolte.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recoltes : get all the recoltes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of recoltes in body
     */
    @GetMapping("/recoltes")
    @Timed
    public List<Recolte> getAllRecoltes() {
        log.debug("REST request to get all Recoltes");
        return recolteService.findAll();
    }

    /**
     * GET  /recoltes/:id : get the "id" recolte.
     *
     * @param id the id of the recolte to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recolte, or with status 404 (Not Found)
     */
    @GetMapping("/recoltes/{id}")
    @Timed
    public ResponseEntity<Recolte> getRecolte(@PathVariable Long id) {
        log.debug("REST request to get Recolte : {}", id);
        Optional<Recolte> recolte = recolteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recolte);
    }

    /**
     * DELETE  /recoltes/:id : delete the "id" recolte.
     *
     * @param id the id of the recolte to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recoltes/{id}")
    @Timed
    public ResponseEntity<Void> deleteRecolte(@PathVariable Long id) {
        log.debug("REST request to delete Recolte : {}", id);
        recolteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
