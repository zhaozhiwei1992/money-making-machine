package com.example.repository;

import com.example.domain.SysCollectTab;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SysCollectTab entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCollectTabRepository extends JpaRepository<SysCollectTab, Long> {}
