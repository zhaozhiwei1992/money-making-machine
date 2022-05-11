package com.example.web.rest;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.domain.Menu;
import com.example.repository.MenuRepository;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.example.domain.Menu}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MenuResource {

    private final Logger log = LoggerFactory.getLogger(MenuResource.class);

    private static final String ENTITY_NAME = "menu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MenuRepository menuRepository;

    public MenuResource(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * {@code POST  /menus} : Create a new menu.
     *
     * @param menu the menu to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new menu, or with
     * status {@code 400 (Bad Request)} if the menu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/menus")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) throws URISyntaxException {
        log.debug("REST request to save Menu : {}", menu);
        if (menu.getId() != null) {
            throw new BadRequestAlertException("A new menu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Menu result = menuRepository.save(menu);
        return ResponseEntity
            .created(new URI("/api/menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /menus/:id} : Updates an existing menu.
     *
     * @param id   the id of the menu to save.
     * @param menu the menu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated menu,
     * or with status {@code 400 (Bad Request)} if the menu is not valid,
     * or with status {@code 500 (Internal Server Error)} if the menu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/menus/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable(value = "id", required = false) final Long id, @RequestBody Menu menu)
        throws URISyntaxException {
        log.debug("REST request to update Menu : {}, {}", id, menu);
        if (menu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, menu.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!menuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Menu result = menuRepository.save(menu);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, menu.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /menus/:id} : Partial updates given fields of an existing menu, field will ignore if it is null
     *
     * @param id   the id of the menu to save.
     * @param menu the menu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated menu,
     * or with status {@code 400 (Bad Request)} if the menu is not valid,
     * or with status {@code 404 (Not Found)} if the menu is not found,
     * or with status {@code 500 (Internal Server Error)} if the menu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/menus/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Menu> partialUpdateMenu(@PathVariable(value = "id", required = false) final Long id, @RequestBody Menu menu)
        throws URISyntaxException {
        log.debug("REST request to partial update Menu partially : {}, {}", id, menu);
        if (menu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, menu.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!menuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Menu> result = menuRepository
            .findById(menu.getId())
            .map(existingMenu -> {
                if (menu.getUrl() != null) {
                    existingMenu.setUrl(menu.getUrl());
                }
                if (menu.getName() != null) {
                    existingMenu.setName(menu.getName());
                }
                if (menu.getIconCls() != null) {
                    existingMenu.setIconCls(menu.getIconCls());
                }
                if (menu.getOrdernum() != null) {
                    existingMenu.setOrdernum(menu.getOrdernum());
                }
                if (menu.getKeepAlive() != null) {
                    existingMenu.setKeepAlive(menu.getKeepAlive());
                }
                if (menu.getRequireAuth() != null) {
                    existingMenu.setRequireAuth(menu.getRequireAuth());
                }
                if (menu.getParentId() != null) {
                    existingMenu.setParentId(menu.getParentId());
                }
                if (menu.getEnabled() != null) {
                    existingMenu.setEnabled(menu.getEnabled());
                }
                if (menu.getConfig() != null) {
                    existingMenu.setConfig(menu.getConfig());
                }

                return existingMenu;
            })
            .map(menuRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, menu.getId().toString())
        );
    }

    /**
     * {@code GET  /menus} : get all the menus.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of menus in body.
     */
    //    @GetMapping("/menus")
    //    public List<Menu> getAllMenus() {
    //        log.debug("REST request to get all Menus");
    //        return menuRepository.findAll();
    //    }

    @GetMapping("/menus")
    public ResponseEntity<List<Menu>> getAllMenus(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String name
    ) {
        log.debug("REST request to get a page of UiComponents");

        // 菜单精确匹配
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        final Menu menu = new Menu();
        menu.setName(name);
        final Example<Menu> of = Example.of(menu, matcher);

        Page<Menu> page = menuRepository.findAll(of, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * @data: 2022/5/6-上午10:11
     * @User: zhaozhiwei
     * @method: getMenusTree
     * @return: java.util.List<com.example.domain.Menu>
     * @Description: 需返回如下结果
     * public homeMenu = [
     * { index: '/', title: '导航1', children: [] },
     * {
     * index: '/example/uiexample',
     * title: '导航2',
     * children: [
     * { index: '/xx', title: '导航2-1', children: [] },
     * { index: '/xx2', title: '导航2-2', children: [] },
     * ],
     * },
     * ];
     */
    @GetMapping("/menus/tree")
    public List<Tree<Long>> getMenusTree() {
        log.debug("REST request to get Menus Tree");
        final List<Menu> allMenusOrderByOrdernumAsc = menuRepository.findAllByOrderByOrdernumAsc();

        //树形结构一些特殊配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("ordernum");
        //        数据库设计如果和默认值一致，就不设置了
        //        treeNodeConfig.setIdKey("id");
        //        treeNodeConfig.setParentIdKey("parentId");
        //        treeNodeConfig.setChildrenKey("children");
        //        最大递归深度
        treeNodeConfig.setDeep(3);

        //转换器
        List<Tree<Long>> treeNodes = TreeUtil.build(
            allMenusOrderByOrdernumAsc,
            0L,
            treeNodeConfig,
            (menuObj, tree) -> {
                tree.setId(menuObj.getId());
                tree.setParentId(menuObj.getParentId());
                //              tree.setWeight(treeNode.getWeight());
                tree.setName(menuObj.getName());
                // 属性扩展, 只显示界面展示需要的属性即可
                tree.putExtra("icons", menuObj.getIconCls());
                tree.putExtra("url", menuObj.getUrl());
                tree.putExtra("index", menuObj.getUrl());
                tree.putExtra("ordernum", menuObj.getOrdernum());
            }
        );

        //      children默认给空, 防止前端解析报错
        for (Tree<Long> treeNode : treeNodes) {
            if (Objects.isNull(treeNode.getChildren())) {
                treeNode.setChildren(Collections.emptyList());
            }
        }

        log.info("左侧树构建: {}", treeNodes);
        return treeNodes;
    }

    /**
     * @data: 2022/5/8-下午7:44
     * @User: zhaozhiwei
     * @method: getMenusRoute
     * @return: java.util.List<cn.hutool.core.lang.tree.Tree < java.lang.Long>>
     * @Description: 根据菜单动态产生路由, 菜单表自己加的菜单就不用手动加路由了
     * url 非#号的都作为路由
     * <p>
     * 路由数据结构
     * export default [
     * {
     * path: '/example/uiexample',
     * name: 'DynamicUiExample',
     * component: UiExample,
     * meta: { authorities: [Authority.USER] },
     * },
     * {
     * path: '/example/helloworld',
     * name: 'HelloWorld',
     * component: HelloWorld,
     * meta: { authorities: [Authority.USER] },
     * },
     * ]
     */
    @GetMapping("/menus/route")
    public List<Map<String, Object>> getMenusRoute() {
        log.debug("REST request to get Menus Tree");
        final List<Menu> allMenusOrderByOrdernumAsc = menuRepository.findAllByOrderByOrdernumAsc();

        final List<Map<String, Object>> collect = allMenusOrderByOrdernumAsc
            .stream()
            //            保留url不是#的, 并且config里配置了组件的
            .filter(menu -> !"#".equals(menu.getUrl()) && menu.getConfig().contains("component"))
            //            构建成router需要的形式
            .map(menu -> {
                final Map<String, Object> map = new HashMap<>();
                map.put("path", menu.getUrl());
                final JSONObject jsonObject = JSONUtil.parseObj(menu.getConfig());
                final String componentid = jsonObject.getStr("component");
                map.put("name", componentid);
                //                component字段是字符串，前端需要把这个字符串转化为前端定义的组件
                map.put("component", componentid);
                return map;
            })
            .collect(Collectors.toList());

        return collect;
    }

    /**
     * {@code GET  /menus/:id} : get the "id" menu.
     *
     * @param id the id of the menu to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the menu, or with status {@code
     * 404 (Not Found)}.
     */
    @GetMapping("/menus/{id}")
    public ResponseEntity<Menu> getMenu(@PathVariable Long id) {
        log.debug("REST request to get Menu : {}", id);
        Optional<Menu> menu = menuRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(menu);
    }

    /**
     * {@code DELETE  /menus/:id} : delete the "id" menu.
     *
     * @param id the id of the menu to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/menus/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        log.debug("REST request to delete Menu : {}", id);
        menuRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
