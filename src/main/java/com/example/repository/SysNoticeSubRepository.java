package com.example.repository;

import com.example.domain.SysNoticeSub;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SysNoticeSub entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysNoticeSubRepository extends JpaRepository<SysNoticeSub, Long> {}
