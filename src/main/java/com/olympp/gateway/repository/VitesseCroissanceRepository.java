package com.olympp.gateway.repository;

import com.olympp.gateway.domain.VitesseCroissance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VitesseCroissance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VitesseCroissanceRepository extends JpaRepository<VitesseCroissance, Long> {

}
