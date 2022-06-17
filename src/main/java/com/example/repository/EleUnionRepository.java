package com.example.repository;

import com.example.domain.EleUnion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EleUnion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EleUnionRepository extends JpaRepository<EleUnion, Long> {}
