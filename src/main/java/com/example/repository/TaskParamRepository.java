package com.example.repository;

import com.example.domain.TaskParam;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TaskParam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskParamRepository extends JpaRepository<TaskParam, Long> {
    List<TaskParam> findAllByEnable(boolean enable);

    Optional<TaskParam> findOneByStartClass(String startClass);
}
