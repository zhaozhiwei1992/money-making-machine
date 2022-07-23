package com.example.repository;

import com.example.domain.SysNoticeSub;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SysNoticeSub entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysNoticeSubRepository extends JpaRepository<SysNoticeSub, Long> {
    List<SysNoticeSub> findAllByRecipientIdAndStatus(String login, Integer status);
}
