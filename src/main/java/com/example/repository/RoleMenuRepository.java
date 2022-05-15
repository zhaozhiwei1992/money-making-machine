package com.example.repository;

import com.example.domain.RoleMenu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RoleMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {}
