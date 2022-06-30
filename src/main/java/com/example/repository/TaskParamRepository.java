package com.example.repository;

import com.example.domain.TaskParam;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TaskParam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskParamRepository extends JpaRepository<TaskParam, Long> {
    List<TaskParam> findAllByEnable(boolean enable);
}
