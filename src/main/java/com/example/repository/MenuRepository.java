package com.example.repository;

import com.example.domain.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Menu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByOrderByOrdernumAsc();

    List<Menu> findByIdInOrderByOrdernumAsc(List<Long> menuIdList);

    List<Menu> findAllByIdInOrderByOrdernumAsc(List<Long> menuIdList);
}
