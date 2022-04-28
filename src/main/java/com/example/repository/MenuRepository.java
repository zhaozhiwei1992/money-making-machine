package com.example.repository;

import com.example.domain.Menu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Menu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {}
