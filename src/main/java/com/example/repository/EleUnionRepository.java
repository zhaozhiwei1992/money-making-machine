package com.example.repository;

import com.example.domain.EleUnion;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EleUnion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EleUnionRepository extends JpaRepository<EleUnion, Long> {
    List<EleUnion> findByEleCatCode(String eleCatCode);
}
