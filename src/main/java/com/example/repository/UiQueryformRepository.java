package com.example.repository;

import com.example.domain.UiQueryform;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiQueryform entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiQueryformRepository extends JpaRepository<UiQueryform, Long> {}
