package com.example.repository;

import com.example.domain.DataPermissionDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DataPermissionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataPermissionDetailsRepository extends JpaRepository<DataPermissionDetails, Long> {}
