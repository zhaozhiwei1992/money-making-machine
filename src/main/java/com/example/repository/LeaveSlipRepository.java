package com.example.repository;

import com.example.domain.LeaveSlip;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LeaveSlip entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveSlipRepository extends JpaRepository<LeaveSlip, Long> {}
