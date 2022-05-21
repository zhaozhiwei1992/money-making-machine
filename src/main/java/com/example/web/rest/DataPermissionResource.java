package com.example.web.rest;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.example.domain.Authority;
import com.example.domain.DataPermission;
import com.example.repository.DataPermissionRepository;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.example.domain.DataPermission}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DataPermissionResource {

    private final Logger log = LoggerFactory.getLogger(DataPermissionResource.class);

    private static final String ENTITY_NAME = "dataPermission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataPermissionRepository dataPermissionRepository;

    public DataPermissionResource(DataPermissionRepository dataPermissionRepository) {
        this.dataPermissionRepository = dataPermissionRepository;
    }

    /**
     * {@code POST  /data-permissions} : Create a new dataPermission.
     *
     * @param dataPermission the dataPermission to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataPermission, or with status {@code 400 (Bad Request)} if the dataPermission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-permissions")
    public ResponseEntity<DataPermission> createDataPermission(@RequestBody DataPermission dataPermission) throws URISyntaxException {
        log.debug("REST request to save DataPermission : {}", dataPermission);
        if (dataPermission.getId() != null) {
            throw new BadRequestAlertException("A new dataPermission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataPermission result = dataPermissionRepository.save(dataPermission);
        return ResponseEntity
            .created(new URI("/api/data-permissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-permissions/:id} : Updates an existing dataPermission.
     *
     * @param id the id of the dataPermission to save.
     * @param dataPermission the dataPermission to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataPermission,
     * or with status {@code 400 (Bad Request)} if the dataPermission is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataPermission couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-permissions/{id}")
    public ResponseEntity<DataPermission> updateDataPermission(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DataPermission dataPermission
    ) throws URISyntaxException {
        log.debug("REST request to update DataPermission : {}, {}", id, dataPermission);
        if (dataPermission.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dataPermission.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dataPermissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DataPermission result = dataPermissionRepository.save(dataPermission);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataPermission.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /data-permissions/:id} : Partial updates given fields of an existing dataPermission, field will ignore if it is null
     *
     * @param id the id of the dataPermission to save.
     * @param dataPermission the dataPermission to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataPermission,
     * or with status {@code 400 (Bad Request)} if the dataPermission is not valid,
     * or with status {@code 404 (Not Found)} if the dataPermission is not found,
     * or with status {@code 500 (Internal Server Error)} if the dataPermission couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/data-permissions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DataPermission> partialUpdateDataPermission(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DataPermission dataPermission
    ) throws URISyntaxException {
        log.debug("REST request to partial update DataPermission partially : {}, {}", id, dataPermission);
        if (dataPermission.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dataPermission.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dataPermissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DataPermission> result = dataPermissionRepository
            .findById(dataPermission.getId())
            .map(existingDataPermission -> {
                if (dataPermission.getCode() != null) {
                    existingDataPermission.setCode(dataPermission.getCode());
                }
                if (dataPermission.getName() != null) {
                    existingDataPermission.setName(dataPermission.getName());
                }
                if (dataPermission.getRuleSql() != null) {
                    existingDataPermission.setRuleSql(dataPermission.getRuleSql());
                }

                return existingDataPermission;
            })
            .map(dataPermissionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataPermission.getId().toString())
        );
    }

    /**
     * {@code GET  /data-permissions} : get all the dataPermissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataPermissions in body.
     */
    @GetMapping("/data-permissions")
    public ResponseEntity<List<DataPermission>> getAllDataPermissions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of DataPermissions");
        Page<DataPermission> page = dataPermissionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-permissions/:id} : get the "id" dataPermission.
     *
     * @param id the id of the dataPermission to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataPermission, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-permissions/{id}")
    public ResponseEntity<DataPermission> getDataPermission(@PathVariable Long id) {
        log.debug("REST request to get DataPermission : {}", id);
        Optional<DataPermission> dataPermission = dataPermissionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dataPermission);
    }

    /**
     * {@code DELETE  /data-permissions/:id} : delete the "id" dataPermission.
     *
     * @param id the id of the dataPermission to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-permissions/{id}")
    public ResponseEntity<Void> deleteDataPermission(@PathVariable Long id) {
        log.debug("REST request to delete DataPermission : {}", id);
        dataPermissionRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/data-permissions/tree")
    public List<Tree<Long>> getDataPermissionTree() {
        log.debug("REST request to get Permission Tree");

        final List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();

        //树形结构一些特殊配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("name");
        //        数据库设计如果和默认值一致，就不设置了
        //        treeNodeConfig.setIdKey("id");
        //        treeNodeConfig.setParentIdKey("parentId");
        //        treeNodeConfig.setChildrenKey("children");
        //        最大递归深度
        treeNodeConfig.setDeep(3);

        //转换器
        List<Tree<Long>> treeNodes = TreeUtil.build(
            dataPermissionList,
            0L,
            treeNodeConfig,
            (dataPermission, tree) -> {
                tree.setId(dataPermission.getId());
                tree.setParentId(0L);
                tree.setName(dataPermission.getName());
                tree.putExtra("label", dataPermission.getName());
            }
        );

        // children默认给空, 防止前端解析报错
        for (Tree<Long> treeNode : treeNodes) {
            if (Objects.isNull(treeNode.getChildren())) {
                treeNode.setChildren(Collections.emptyList());
            }
        }

        log.info("权限树构建: {}", treeNodes);
        return treeNodes;
    }
}
