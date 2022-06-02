package com.example.repository;

import com.example.domain.SystemParam;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SystemParam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemParamRepository extends JpaRepository<SystemParam, Long> {}
