package com.olympp.gateway.service.impl;

import com.olympp.gateway.service.GenreService;
import com.olympp.gateway.domain.Genre;
import com.olympp.gateway.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Genre.
 */
@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    private final Logger log = LoggerFactory.getLogger(GenreServiceImpl.class);

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Save a genre.
     *
     * @param genre the entity to save
     * @return the persisted entity
     */
    @Override
    public Genre save(Genre genre) {
        log.debug("Request to save Genre : {}", genre);
        return genreRepository.save(genre);
    }

    /**
     * Get all the genres.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        log.debug("Request to get all Genres");
        return genreRepository.findAll();
    }


    /**
     * Get one genre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findOne(Long id) {
        log.debug("Request to get Genre : {}", id);
        return genreRepository.findById(id);
    }

    /**
     * Delete the genre by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Genre : {}", id);
        genreRepository.deleteById(id);
    }
}
