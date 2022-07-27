package com.example.repository;

import com.example.domain.RequestLogging;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RequestLogging entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestLoggingRepository extends JpaRepository<RequestLogging, Long> {
    Optional<RequestLogging> findOneByTraceId(String traceId);
}
