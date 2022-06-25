package com.example.repository;

import com.example.domain.SysFormulaTab;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SysFormulaTab entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysFormulaTabRepository extends JpaRepository<SysFormulaTab, Long> {
    Optional<SysFormulaTab> findByTabEnameAndColEname(String tabEname, String colEname);
}
