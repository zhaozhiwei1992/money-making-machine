package com.example.repository;

import com.example.domain.UiEditform;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiEditform entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiEditformRepository extends JpaRepository<UiEditform, Long> {}
