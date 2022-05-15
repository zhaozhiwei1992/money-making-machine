package com.example.repository;

import com.example.domain.DataPermissionsRel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DataPermissionsRel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataPermissionsRelRepository extends JpaRepository<DataPermissionsRel, Long> {}
