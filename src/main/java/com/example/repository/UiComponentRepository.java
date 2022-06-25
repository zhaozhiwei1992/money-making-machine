package com.example.repository;

import com.example.domain.UiComponent;
import java.util.List;
import java.util.Optional;
import liquibase.pro.packaged.T;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiComponent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiComponentRepository extends JpaRepository<UiComponent, Long> {
    List<UiComponent> findByMenuidOrderByOrdernumAsc(Long menuid);

    Optional<UiComponent> findByMenuidAndComponentid(Long menuid, String uitable);
}
