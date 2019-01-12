package com.olympp.gateway.repository;

import com.olympp.gateway.domain.Recolte;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Recolte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {

}
