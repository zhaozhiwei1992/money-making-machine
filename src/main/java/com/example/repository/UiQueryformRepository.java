package com.example.repository;

import com.example.domain.UiQueryform;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiQueryform entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiQueryformRepository extends JpaRepository<UiQueryform, Long> {
    List<UiQueryform> findByMenuidOrderByOrdernumAsc(Long menuid);
}
