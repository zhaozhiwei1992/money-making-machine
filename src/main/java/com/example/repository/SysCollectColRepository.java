package com.example.repository;

import com.example.domain.SysCollectCol;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SysCollectCol entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCollectColRepository extends JpaRepository<SysCollectCol, Long> {
    List<SysCollectCol> findAllByTabId(Long tabId);

    List<SysCollectCol> findAllByTabIdOrderByOrderNum(Long tabId);
}
