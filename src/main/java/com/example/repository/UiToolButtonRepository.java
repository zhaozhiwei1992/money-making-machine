package com.example.repository;

import com.example.domain.UiToolButton;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiToolButton entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiToolButtonRepository extends JpaRepository<UiToolButton, Long> {}
