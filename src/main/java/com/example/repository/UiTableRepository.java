package com.example.repository;

import com.example.domain.UiTable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiTableRepository extends JpaRepository<UiTable, Long> {
    List<UiTable> findByMenuidOrderByOrdernumAsc(Long menuid);
}
