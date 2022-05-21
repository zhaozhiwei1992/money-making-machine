package com.example.repository;

import com.example.domain.RoleMenuToolButton;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RoleMenuToolButton entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleMenuToolButtonRepository extends JpaRepository<RoleMenuToolButton, Long> {
    List<RoleMenuToolButton> findByRoleId(String roleId);

    List<RoleMenuToolButton> findByRoleIdAndMenuId(String roleId, String menuId);

    void deleteAllByRoleIdInAndMenuIdIn(List<String> roleIdList, List<String> menuIdList);

    List<RoleMenuToolButton> findByMenuIdAndRoleIdIn(String menuid, List<String> roleIds);
}
