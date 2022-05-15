package com.example.repository;

import com.example.domain.RoleMenuToolButton;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RoleMenuToolButton entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleMenuToolButtonRepository extends JpaRepository<RoleMenuToolButton, Long> {}
