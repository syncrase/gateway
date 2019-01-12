package com.olympp.gateway.repository;

import com.olympp.gateway.domain.Strate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Strate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StrateRepository extends JpaRepository<Strate, Long> {

}
