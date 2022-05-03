package com.example.repository;

import com.example.domain.UiComponent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiComponent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiComponentRepository extends JpaRepository<UiComponent, Long> {}
