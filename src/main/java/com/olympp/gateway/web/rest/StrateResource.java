package com.olympp.gateway.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.gateway.domain.Strate;
import com.olympp.gateway.service.StrateService;
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
 * REST controller for managing Strate.
 */
@RestController
@RequestMapping("/api")
public class StrateResource {

    private final Logger log = LoggerFactory.getLogger(StrateResource.class);

    private static final String ENTITY_NAME = "strate";

    private final StrateService strateService;

    public StrateResource(StrateService strateService) {
        this.strateService = strateService;
    }

    /**
     * POST  /strates : Create a new strate.
     *
     * @param strate the strate to create
     * @return the ResponseEntity with status 201 (Created) and with body the new strate, or with status 400 (Bad Request) if the strate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/strates")
    @Timed
    public ResponseEntity<Strate> createStrate(@RequestBody Strate strate) throws URISyntaxException {
        log.debug("REST request to save Strate : {}", strate);
        if (strate.getId() != null) {
            throw new BadRequestAlertException("A new strate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Strate result = strateService.save(strate);
        return ResponseEntity.created(new URI("/api/strates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /strates : Updates an existing strate.
     *
     * @param strate the strate to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated strate,
     * or with status 400 (Bad Request) if the strate is not valid,
     * or with status 500 (Internal Server Error) if the strate couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/strates")
    @Timed
    public ResponseEntity<Strate> updateStrate(@RequestBody Strate strate) throws URISyntaxException {
        log.debug("REST request to update Strate : {}", strate);
        if (strate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Strate result = strateService.save(strate);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, strate.getId().toString()))
            .body(result);
    }

    /**
     * GET  /strates : get all the strates.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of strates in body
     */
    @GetMapping("/strates")
    @Timed
    public List<Strate> getAllStrates(@RequestParam(required = false) String filter) {
        if ("plante-is-null".equals(filter)) {
            log.debug("REST request to get all Strates where plante is null");
            return strateService.findAllWherePlanteIsNull();
        }
        log.debug("REST request to get all Strates");
        return strateService.findAll();
    }

    /**
     * GET  /strates/:id : get the "id" strate.
     *
     * @param id the id of the strate to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the strate, or with status 404 (Not Found)
     */
    @GetMapping("/strates/{id}")
    @Timed
    public ResponseEntity<Strate> getStrate(@PathVariable Long id) {
        log.debug("REST request to get Strate : {}", id);
        Optional<Strate> strate = strateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(strate);
    }

    /**
     * DELETE  /strates/:id : delete the "id" strate.
     *
     * @param id the id of the strate to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/strates/{id}")
    @Timed
    public ResponseEntity<Void> deleteStrate(@PathVariable Long id) {
        log.debug("REST request to delete Strate : {}", id);
        strateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
