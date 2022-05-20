package com.example.web.rest;

import com.example.domain.RoleMenu;
import com.example.repository.MenuRepository;
import com.example.repository.RoleMenuRepository;
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
 * REST controller for managing {@link com.example.domain.RoleMenu}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RoleMenuResource {

    private final Logger log = LoggerFactory.getLogger(RoleMenuResource.class);

    private static final String ENTITY_NAME = "roleMenu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoleMenuRepository roleMenuRepository;

    private final MenuRepository menuRepository;

    public RoleMenuResource(RoleMenuRepository roleMenuRepository, MenuRepository menuRepository) {
        this.roleMenuRepository = roleMenuRepository;
        this.menuRepository = menuRepository;
    }

    /**
     * {@code POST  /role-menus} : Create a new roleMenu.
     *
     * @param roleMenu the roleMenu to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleMenu, or with
     * status {@code 400 (Bad Request)} if the roleMenu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/role-menus")
    public ResponseEntity<RoleMenu> createRoleMenu(@RequestBody RoleMenu roleMenu) throws URISyntaxException {
        log.debug("REST request to save RoleMenu : {}", roleMenu);
        if (roleMenu.getId() != null) {
            throw new BadRequestAlertException("A new roleMenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleMenu result = roleMenuRepository.save(roleMenu);
        return ResponseEntity
            .created(new URI("/api/role-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /role-menus/:id} : Updates an existing roleMenu.
     *
     * @param id       the id of the roleMenu to save.
     * @param roleMenu the roleMenu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleMenu,
     * or with status {@code 400 (Bad Request)} if the roleMenu is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roleMenu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/role-menus/{id}")
    public ResponseEntity<RoleMenu> updateRoleMenu(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RoleMenu roleMenu
    ) throws URISyntaxException {
        log.debug("REST request to update RoleMenu : {}, {}", id, roleMenu);
        if (roleMenu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roleMenu.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleMenuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RoleMenu result = roleMenuRepository.save(roleMenu);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roleMenu.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /role-menus/:id} : Partial updates given fields of an existing roleMenu, field will ignore if it
     * is null
     *
     * @param id       the id of the roleMenu to save.
     * @param roleMenu the roleMenu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleMenu,
     * or with status {@code 400 (Bad Request)} if the roleMenu is not valid,
     * or with status {@code 404 (Not Found)} if the roleMenu is not found,
     * or with status {@code 500 (Internal Server Error)} if the roleMenu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/role-menus/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RoleMenu> partialUpdateRoleMenu(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RoleMenu roleMenu
    ) throws URISyntaxException {
        log.debug("REST request to partial update RoleMenu partially : {}, {}", id, roleMenu);
        if (roleMenu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roleMenu.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleMenuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RoleMenu> result = roleMenuRepository
            .findById(roleMenu.getId())
            .map(existingRoleMenu -> {
                if (roleMenu.getRoleId() != null) {
                    existingRoleMenu.setRoleId(roleMenu.getRoleId());
                }
                if (roleMenu.getMenuId() != null) {
                    existingRoleMenu.setMenuId(roleMenu.getMenuId());
                }

                return existingRoleMenu;
            })
            .map(roleMenuRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roleMenu.getId().toString())
        );
    }

    /**
     * {@code GET  /role-menus} : get all the roleMenus.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roleMenus in body.
     */
    @GetMapping("/role-menus")
    public List<RoleMenu> getAllRoleMenus() {
        log.debug("REST request to get all RoleMenus");
        return roleMenuRepository.findAll();
    }

    /**
     * {@code GET  /role-menus/:id} : get the "id" roleMenu.
     *
     * @param id the id of the roleMenu to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleMenu, or with status
     * {@code 404 (Not Found)}.
     */
    @GetMapping("/role-menus/{id}")
    public ResponseEntity<RoleMenu> getRoleMenu(@PathVariable Long id) {
        log.debug("REST request to get RoleMenu : {}", id);
        Optional<RoleMenu> roleMenu = roleMenuRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(roleMenu);
    }

    /**
     * {@code DELETE  /role-menus/:id} : delete the "id" roleMenu.
     *
     * @param id the id of the roleMenu to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/role-menus/{id}")
    public ResponseEntity<Void> deleteRoleMenu(@PathVariable Long id) {
        log.debug("REST request to delete RoleMenu : {}", id);
        roleMenuRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/role-menus/save")
    public ResponseEntity<Void> deleteExample(@RequestParam List<String> roleIdList, @RequestParam List<String> menuIdList) {
        log.debug("REST request to save role menus ");
        //        每个角色都对应所有的menuidList, 如果需要每个角色分别配置，则每次单选角色
        final List<RoleMenu> roleMenus = new ArrayList<>();
        for (String roleId : roleIdList) {
            for (String menuId : menuIdList) {
                final RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                if ("全部".equals(roleId) || "0".equals(menuId)) {
                    continue;
                }
                roleMenus.add(roleMenu);
            }
        }
        //        删除原角色删除配置信息
        this.roleMenuRepository.deleteAllByRoleIdIn(roleIdList);
        this.roleMenuRepository.saveAll(roleMenus);
        return ResponseEntity.ok().build();
    }

    /**
     * @data: 2022/5/20-下午9:02
     * @User: zhaozhiwei
     * @method: getMenusTree
     * @param roleId :
     * @return: java.util.List<java.lang.String>
     * @Description: 返回选中的菜单id
     */
    @GetMapping("/role-menus/menu/by/role/{roleId}")
    public List<String> getMenusTree(@PathVariable String roleId) {
        log.debug("REST request to get Menus Tree");
        List<RoleMenu> roleMenuList = roleMenuRepository.findByRoleId(roleId);
        final List<String> menuIdList = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        return menuIdList;
    }
}
