package com.example.repository;

import com.example.domain.UiComponent;
import java.util.List;
import liquibase.pro.packaged.T;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiComponent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiComponentRepository extends JpaRepository<UiComponent, Long> {
    List<UiComponent> findByMenuid(Long menuid);
}
