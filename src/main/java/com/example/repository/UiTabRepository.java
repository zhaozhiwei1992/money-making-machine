package com.example.repository;

import com.example.domain.UiTab;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiTab entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiTabRepository extends JpaRepository<UiTab, Long> {
    List<UiTab> findByMenuid(Long menuid);
}
