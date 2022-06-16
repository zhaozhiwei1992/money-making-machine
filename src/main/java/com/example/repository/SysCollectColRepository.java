package com.example.repository;

import com.example.domain.SysCollectCol;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SysCollectCol entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCollectColRepository extends JpaRepository<SysCollectCol, Long> {}
