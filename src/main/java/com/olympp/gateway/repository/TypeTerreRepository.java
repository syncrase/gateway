package com.olympp.gateway.repository;

import com.olympp.gateway.domain.TypeTerre;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeTerre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeTerreRepository extends JpaRepository<TypeTerre, Long> {

}
