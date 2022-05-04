package com.example.repository;

import com.example.domain.UiToolButton;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UiToolButton entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UiToolButtonRepository extends JpaRepository<UiToolButton, Long> {
    List<UiToolButton> findByMenuid(Long menuid);
}
