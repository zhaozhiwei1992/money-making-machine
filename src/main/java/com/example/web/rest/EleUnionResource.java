package com.example.web.rest;

import cn.hutool.core.util.StrUtil;
import com.example.domain.EleUnion;
import com.example.repository.EleUnionRepository;
import com.example.service.CommonEleService;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
 * REST controller for managing {@link com.example.domain.EleUnion}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EleUnionResource {

    private final Logger log = LoggerFactory.getLogger(EleUnionResource.class);

    private static final String ENTITY_NAME = "eleUnion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EleUnionRepository eleUnionRepository;

    private final CommonEleService commonEleService;

    public EleUnionResource(EleUnionRepository eleUnionRepository, CommonEleService commonEleService) {
        this.eleUnionRepository = eleUnionRepository;
        this.commonEleService = commonEleService;
    }

    /**
     * {@code POST  /ele-unions} : Create a new eleUnion.
     *
     * @param eleUnion the eleUnion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eleUnion, or with
     * status {@code 400 (Bad Request)} if the eleUnion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ele-unions")
    public ResponseEntity<EleUnion> createEleUnion(@RequestBody EleUnion eleUnion) throws URISyntaxException {
        log.debug("REST request to save EleUnion : {}", eleUnion);
        if (eleUnion.getId() != null) {
            throw new BadRequestAlertException("A new eleUnion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EleUnion result = eleUnionRepository.save(eleUnion);
        return ResponseEntity
            .created(new URI("/api/ele-unions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ele-unions/:id} : Updates an existing eleUnion.
     *
     * @param id       the id of the eleUnion to save.
     * @param eleUnion the eleUnion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eleUnion,
     * or with status {@code 400 (Bad Request)} if the eleUnion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eleUnion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ele-unions/{id}")
    public ResponseEntity<EleUnion> updateEleUnion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EleUnion eleUnion
    ) throws URISyntaxException {
        log.debug("REST request to update EleUnion : {}, {}", id, eleUnion);
        if (eleUnion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, eleUnion.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!eleUnionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EleUnion result = eleUnionRepository.save(eleUnion);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eleUnion.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ele-unions/:id} : Partial updates given fields of an existing eleUnion, field will ignore if it
     * is null
     *
     * @param id       the id of the eleUnion to save.
     * @param eleUnion the eleUnion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eleUnion,
     * or with status {@code 400 (Bad Request)} if the eleUnion is not valid,
     * or with status {@code 404 (Not Found)} if the eleUnion is not found,
     * or with status {@code 500 (Internal Server Error)} if the eleUnion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ele-unions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EleUnion> partialUpdateEleUnion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EleUnion eleUnion
    ) throws URISyntaxException {
        log.debug("REST request to partial update EleUnion partially : {}, {}", id, eleUnion);
        if (eleUnion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, eleUnion.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!eleUnionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EleUnion> result = eleUnionRepository
            .findById(eleUnion.getId())
            .map(existingEleUnion -> {
                if (eleUnion.getEleCatCode() != null) {
                    existingEleUnion.setEleCatCode(eleUnion.getEleCatCode());
                }
                if (eleUnion.getEleCatName() != null) {
                    existingEleUnion.setEleCatName(eleUnion.getEleCatName());
                }
                if (eleUnion.getEleCode() != null) {
                    existingEleUnion.setEleCode(eleUnion.getEleCode());
                }
                if (eleUnion.getEleName() != null) {
                    existingEleUnion.setEleName(eleUnion.getEleName());
                }
                if (eleUnion.getParentId() != null) {
                    existingEleUnion.setParentId(eleUnion.getParentId());
                }
                if (eleUnion.getLevelNo() != null) {
                    existingEleUnion.setLevelNo(eleUnion.getLevelNo());
                }
                if (eleUnion.getIsLeaf() != null) {
                    existingEleUnion.setIsLeaf(eleUnion.getIsLeaf());
                }
                if (eleUnion.getIsEnabled() != null) {
                    existingEleUnion.setIsEnabled(eleUnion.getIsEnabled());
                }
                if (eleUnion.getCreateTime() != null) {
                    existingEleUnion.setCreateTime(eleUnion.getCreateTime());
                }
                if (eleUnion.getUpdateTime() != null) {
                    existingEleUnion.setUpdateTime(eleUnion.getUpdateTime());
                }

                return existingEleUnion;
            })
            .map(eleUnionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eleUnion.getId().toString())
        );
    }

    /**
     * {@code GET  /ele-unions} : get all the eleUnions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eleUnions in body.
     */
    @GetMapping("/ele-unions")
    public List<EleUnion> getAllEleUnions() {
        log.debug("REST request to get all EleUnions");
        return eleUnionRepository.findAll();
    }

    /**
     * @data: 2022/6/20-下午10:32
     * @User: zhaozhiwei
     * @method: getEleUnionsLeftTree
     * @return: java.util.List<com.example.domain.EleUnion>
     * @Description: 获取基础信息左侧要素列表
     */
    @GetMapping("/ele-unions/left-tree")
    public List<Map<String, Object>> getEleUnionsLeftTree() {
        log.debug("REST request to get all getEleUnionsLeftTree");

        //1. 获取所有ele_的信息
        final List<Map<String, Object>> allEleCategory = commonEleService.findAllEleCategory();
        //2. 构建成树信息
        for (Map<String, Object> map : allEleCategory) {
            map.put("id", map.get("eleCatCode"));
            map.put("label", map.get("eleCatCode") + "-" + map.get("eleCatName"));
        }
        return allEleCategory;
    }

    /**
     * @param eleUnionList :
     * @data: 2022/6/21-下午9:32
     * @User: zhaozhiwei
     * @method: saveOrUpdate
     * @return: org.springframework.http.ResponseEntity<com.example.domain.EleUnion>
     * @Description: 保存提交的数据
     */
    @PostMapping("/ele-unions/save/update")
    public List<EleUnion> saveOrUpdate(@RequestBody List<EleUnion> eleUnionList) throws URISyntaxException {
        log.debug("REST request to save EleUnionList : {}", eleUnionList);
        //1. 清理掉已经删除的数据, id不在范围内的就是
        final String eleCatCode = eleUnionList.get(0).getEleCatCode();
        final List<EleUnion> byEleCatCode = eleUnionRepository.findByEleCatCode(eleCatCode);
        final List<Long> idList = eleUnionList.stream().map(m -> m.getId()).collect(Collectors.toList());
        final List<Long> deleteIds = new ArrayList<>();
        for (EleUnion eleUnion : byEleCatCode) {
            if (!idList.contains(eleUnion.getId())) {
                deleteIds.add(eleUnion.getId());
            }
        }
        eleUnionRepository.deleteAllById(deleteIds);

        //2. 保存数据
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final String format = dateTimeFormatter.format(LocalDateTime.now());
        for (EleUnion eleUnion : eleUnionList) {
            if (eleUnion.getId() == null) {
                eleUnion.setCreateTime(format);
            } else {
                eleUnion.setUpdateTime(format);
            }
        }
        eleUnionRepository.saveAll(eleUnionList);
        return eleUnionList;
    }

    /**
     * @data: 2022/6/20-下午11:01
     * @User: zhaozhiwei
     * @method: getElementInfoByNodeId
     * @return: java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Description: 根据选中的左侧节点id查询基础信息
     */
    @GetMapping("/ele-unions/element-info/{id}")
    public List<EleUnion> getElementInfoByNodeId(@PathVariable String id) {
        log.debug("REST request to get all getElementInfoByNodeId");
        return commonEleService.findElementInfoByEleCatCode(id);
    }

    /**
     * {@code GET  /ele-unions/:id} : get the "id" eleUnion.
     *
     * @param id the id of the eleUnion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eleUnion, or with status
     * {@code 404 (Not Found)}.
     */
    @GetMapping("/ele-unions/{id}")
    public ResponseEntity<EleUnion> getEleUnion(@PathVariable Long id) {
        log.debug("REST request to get EleUnion : {}", id);
        Optional<EleUnion> eleUnion = eleUnionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(eleUnion);
    }

    /**
     * {@code DELETE  /ele-unions/:id} : delete the "id" eleUnion.
     *
     * @param id the id of the eleUnion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ele-unions/{id}")
    public ResponseEntity<Void> deleteEleUnion(@PathVariable Long id) {
        log.debug("REST request to delete EleUnion : {}", id);
        eleUnionRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
