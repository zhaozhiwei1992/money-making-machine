package com.example.web.rest;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.example.domain.DataPermissionsRel;
import com.example.domain.RoleMenuToolButton;
import com.example.domain.UiToolButton;
import com.example.repository.RoleMenuToolButtonRepository;
import com.example.repository.UiToolButtonRepository;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.example.domain.RoleMenuToolButton}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RoleMenuToolButtonResource {

    private final Logger log = LoggerFactory.getLogger(RoleMenuToolButtonResource.class);

    private static final String ENTITY_NAME = "roleMenuToolButton";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoleMenuToolButtonRepository roleMenuToolButtonRepository;

    private final UiToolButtonRepository uiToolButtonRepository;

    public RoleMenuToolButtonResource(
        RoleMenuToolButtonRepository roleMenuToolButtonRepository,
        UiToolButtonRepository uiToolButtonRepository
    ) {
        this.roleMenuToolButtonRepository = roleMenuToolButtonRepository;
        this.uiToolButtonRepository = uiToolButtonRepository;
    }

    /**
     * {@code POST  /role-menu-tool-buttons} : Create a new roleMenuToolButton.
     *
     * @param roleMenuToolButton the roleMenuToolButton to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleMenuToolButton, or with status {@code 400 (Bad Request)} if the roleMenuToolButton has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/role-menu-tool-buttons")
    public ResponseEntity<RoleMenuToolButton> createRoleMenuToolButton(@RequestBody RoleMenuToolButton roleMenuToolButton)
        throws URISyntaxException {
        log.debug("REST request to save RoleMenuToolButton : {}", roleMenuToolButton);
        if (roleMenuToolButton.getId() != null) {
            throw new BadRequestAlertException("A new roleMenuToolButton cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleMenuToolButton result = roleMenuToolButtonRepository.save(roleMenuToolButton);
        return ResponseEntity
            .created(new URI("/api/role-menu-tool-buttons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /role-menu-tool-buttons/:id} : Updates an existing roleMenuToolButton.
     *
     * @param id the id of the roleMenuToolButton to save.
     * @param roleMenuToolButton the roleMenuToolButton to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleMenuToolButton,
     * or with status {@code 400 (Bad Request)} if the roleMenuToolButton is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roleMenuToolButton couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/role-menu-tool-buttons/{id}")
    public ResponseEntity<RoleMenuToolButton> updateRoleMenuToolButton(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RoleMenuToolButton roleMenuToolButton
    ) throws URISyntaxException {
        log.debug("REST request to update RoleMenuToolButton : {}, {}", id, roleMenuToolButton);
        if (roleMenuToolButton.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roleMenuToolButton.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleMenuToolButtonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RoleMenuToolButton result = roleMenuToolButtonRepository.save(roleMenuToolButton);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roleMenuToolButton.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /role-menu-tool-buttons/:id} : Partial updates given fields of an existing roleMenuToolButton, field will ignore if it is null
     *
     * @param id the id of the roleMenuToolButton to save.
     * @param roleMenuToolButton the roleMenuToolButton to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleMenuToolButton,
     * or with status {@code 400 (Bad Request)} if the roleMenuToolButton is not valid,
     * or with status {@code 404 (Not Found)} if the roleMenuToolButton is not found,
     * or with status {@code 500 (Internal Server Error)} if the roleMenuToolButton couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/role-menu-tool-buttons/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RoleMenuToolButton> partialUpdateRoleMenuToolButton(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RoleMenuToolButton roleMenuToolButton
    ) throws URISyntaxException {
        log.debug("REST request to partial update RoleMenuToolButton partially : {}, {}", id, roleMenuToolButton);
        if (roleMenuToolButton.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roleMenuToolButton.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleMenuToolButtonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RoleMenuToolButton> result = roleMenuToolButtonRepository
            .findById(roleMenuToolButton.getId())
            .map(existingRoleMenuToolButton -> {
                if (roleMenuToolButton.getRoleId() != null) {
                    existingRoleMenuToolButton.setRoleId(roleMenuToolButton.getRoleId());
                }
                if (roleMenuToolButton.getMenuId() != null) {
                    existingRoleMenuToolButton.setMenuId(roleMenuToolButton.getMenuId());
                }
                if (roleMenuToolButton.getToolButtonId() != null) {
                    existingRoleMenuToolButton.setToolButtonId(roleMenuToolButton.getToolButtonId());
                }

                return existingRoleMenuToolButton;
            })
            .map(roleMenuToolButtonRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roleMenuToolButton.getId().toString())
        );
    }

    /**
     * {@code GET  /role-menu-tool-buttons} : get all the roleMenuToolButtons.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roleMenuToolButtons in body.
     */
    @GetMapping("/role-menu-tool-buttons")
    public List<RoleMenuToolButton> getAllRoleMenuToolButtons() {
        log.debug("REST request to get all RoleMenuToolButtons");
        return roleMenuToolButtonRepository.findAll();
    }

    /**
     * {@code GET  /role-menu-tool-buttons/:id} : get the "id" roleMenuToolButton.
     *
     * @param id the id of the roleMenuToolButton to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleMenuToolButton, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/role-menu-tool-buttons/{id}")
    public ResponseEntity<RoleMenuToolButton> getRoleMenuToolButton(@PathVariable Long id) {
        log.debug("REST request to get RoleMenuToolButton : {}", id);
        Optional<RoleMenuToolButton> roleMenuToolButton = roleMenuToolButtonRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(roleMenuToolButton);
    }

    /**
     * {@code DELETE  /role-menu-tool-buttons/:id} : delete the "id" roleMenuToolButton.
     *
     * @param id the id of the roleMenuToolButton to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/role-menu-tool-buttons/{id}")
    public ResponseEntity<Void> deleteRoleMenuToolButton(@PathVariable Long id) {
        log.debug("REST request to delete RoleMenuToolButton : {}", id);
        roleMenuToolButtonRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/role-menu-tool-buttons/menu/by/role/{roleId}")
    public List<String> getRoleMenuToolMenuSelect(@PathVariable String roleId) {
        log.debug("REST request to get DataPermissionRels Tree");
        List<RoleMenuToolButton> roleMenuList = roleMenuToolButtonRepository.findByRoleId(roleId);
        return roleMenuList.stream().map(RoleMenuToolButton::getMenuId).collect(Collectors.toList());
    }

    /**
     * @data: 2022/5/21-下午4:41
     * @User: zhaozhiwei
     * @method: getRoleMenuToolButtonTreeWithSelect
     * @param roleId :
     * @param menuId :
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Description: 获取按钮信息并选中
     */
    @GetMapping("/role-menu-tool-buttons/toolbutton/by/role/{roleId}/menu/{menuId}")
    public Map<String, Object> getRoleMenuToolButtonTreeWithSelect(@PathVariable String roleId, @PathVariable String menuId) {
        log.debug("REST request to get DataPermissionRels Tree");
        List<RoleMenuToolButton> dataPermissionsRelList = roleMenuToolButtonRepository.findByRoleIdAndMenuId(roleId, menuId);
        final List<Long> selectButtonId = dataPermissionsRelList
            .stream()
            .map(RoleMenuToolButton::getToolButtonId)
            .collect(Collectors.toList());

        //        菜单下所有的按钮
        final List<UiToolButton> buttonList = uiToolButtonRepository.findByMenuidOrderByOrdernumAsc(Long.parseLong(menuId));

        //树形结构一些特殊配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("ordernum");
        treeNodeConfig.setDeep(3);

        //转换器
        List<Tree<Long>> treeNodes = TreeUtil.build(
            buttonList,
            0L,
            treeNodeConfig,
            (uiToolButton, tree) -> {
                tree.setId(uiToolButton.getId());
                tree.setParentId(0L);
                tree.setName(uiToolButton.getName());
                tree.putExtra("label", uiToolButton.getName());
                tree.putExtra("ordernum", uiToolButton.getOrdernum());
            }
        );

        // children默认给空, 防止前端解析报错
        for (Tree<Long> treeNode : treeNodes) {
            if (Objects.isNull(treeNode.getChildren())) {
                treeNode.setChildren(Collections.emptyList());
            }
        }

        final Map<String, Object> result = new HashMap<>();
        result.put("buttonTree", treeNodes);
        result.put("buttonSelect", selectButtonId);
        return result;
    }

    @PostMapping("/role-menu-tool-buttons/save")
    public ResponseEntity<Void> save(
        @RequestParam List<String> roleIdList,
        @RequestParam List<String> menuIdList,
        @RequestParam List<String> toolButtonIdList
    ) {
        log.debug("REST request to save role menus toolButtons");
        //        每个角色都对应所有的menuidList, 如果需要每个角色分别配置，则每次单选角色
        final List<RoleMenuToolButton> roleMenuToolButtonList = new ArrayList<>();
        for (String roleId : roleIdList) {
            for (String menuId : menuIdList) {
                for (String toolButtonId : toolButtonIdList) {
                    if ("全部".equals(roleId) || "0".equals(menuId) || "0".equals(toolButtonId)) {
                        continue;
                    }
                    final RoleMenuToolButton roleMenuToolButton = new RoleMenuToolButton();
                    roleMenuToolButton.setRoleId(roleId);
                    roleMenuToolButton.setMenuId(menuId);
                    roleMenuToolButton.setToolButtonId(Long.parseLong(toolButtonId));
                    roleMenuToolButtonList.add(roleMenuToolButton);
                }
            }
        }
        //        删除原角色删除配置信息
        this.roleMenuToolButtonRepository.deleteAllByRoleIdInAndMenuIdIn(roleIdList, menuIdList);
        this.roleMenuToolButtonRepository.saveAll(roleMenuToolButtonList);
        return ResponseEntity.ok().build();
    }
}
