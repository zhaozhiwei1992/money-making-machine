package com.example.repository;

import com.example.domain.DataPermission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DataPermission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataPermissionRepository extends JpaRepository<DataPermission, Long> {}
