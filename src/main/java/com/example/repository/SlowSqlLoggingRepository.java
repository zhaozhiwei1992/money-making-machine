package com.example.repository;

import com.example.domain.SlowSqlLogging;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SlowSqlLogging entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SlowSqlLoggingRepository extends JpaRepository<SlowSqlLogging, Long> {}
