package com.example.repository;

import com.example.domain.UiTable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiTableRepository extends JpaRepository<UiTable, Long> {}
