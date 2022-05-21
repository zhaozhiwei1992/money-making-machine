package com.example.repository;

import com.example.domain.DataPermissionsRel;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DataPermissionsRel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataPermissionsRelRepository extends JpaRepository<DataPermissionsRel, Long> {
    List<DataPermissionsRel> findAllByMenuIdAndRoleIdIn(String s, List<String> asList);

    void deleteAllByRoleIdInAndMenuIdIn(List<String> roleIdList, List<String> menuIdList);

    List<DataPermissionsRel> findByRoleId(String roleId);

    List<DataPermissionsRel> findByRoleIdAndMenuId(String roleId, String menuId);
}
