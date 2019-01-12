package com.olympp.gateway.repository;

import com.olympp.gateway.domain.Floraison;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Floraison entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FloraisonRepository extends JpaRepository<Floraison, Long> {

}
