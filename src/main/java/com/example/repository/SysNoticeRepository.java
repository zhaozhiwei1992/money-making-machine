package com.example.repository;

import com.example.domain.SysNotice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SysNotice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysNoticeRepository extends JpaRepository<SysNotice, Long> {}
