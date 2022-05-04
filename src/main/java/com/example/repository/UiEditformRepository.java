package com.example.repository;

import com.example.domain.UiEditform;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiEditform entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiEditformRepository extends JpaRepository<UiEditform, Long> {
    List<UiEditform> findByMenuid(Long menuid);
}
