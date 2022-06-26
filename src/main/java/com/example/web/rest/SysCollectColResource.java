package com.example.web.rest;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.example.domain.SysCollectCol;
import com.example.domain.SysCollectTab;
import com.example.repository.CommonSqlRepository;
import com.example.repository.SysCollectColRepository;
import com.example.repository.SysCollectTabRepository;
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
 * REST controller for managing {@link com.example.domain.SysCollectCol}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SysCollectColResource {

    private final Logger log = LoggerFactory.getLogger(SysCollectColResource.class);

    private static final String ENTITY_NAME = "sysCollectCol";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysCollectColRepository sysCollectColRepository;

    private final SysCollectTabRepository sysCollectTabRepository;

    public SysCollectColResource(
        SysCollectColRepository sysCollectColRepository,
        SysCollectTabRepository sysCollectTabRepository,
        CommonSqlRepository commonSqlRepository
    ) {
        this.sysCollectColRepository = sysCollectColRepository;
        this.sysCollectTabRepository = sysCollectTabRepository;
        this.commonSqlRepository = commonSqlRepository;
    }

    /**
     * {@code POST  /sys-collect-cols} : Create a new sysCollectCol.
     *
     * @param sysCollectCol the sysCollectCol to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysCollectCol, or
     * with status {@code 400 (Bad Request)} if the sysCollectCol has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-collect-cols")
    public ResponseEntity<SysCollectCol> createSysCollectCol(@RequestBody SysCollectCol sysCollectCol) throws URISyntaxException {
        log.debug("REST request to save SysCollectCol : {}", sysCollectCol);
        if (sysCollectCol.getId() != null) {
            throw new BadRequestAlertException("A new sysCollectCol cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCollectCol result = sysCollectColRepository.save(sysCollectCol);
        return ResponseEntity
            .created(new URI("/api/sys-collect-cols/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-collect-cols/:id} : Updates an existing sysCollectCol.
     *
     * @param id            the id of the sysCollectCol to save.
     * @param sysCollectCol the sysCollectCol to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysCollectCol,
     * or with status {@code 400 (Bad Request)} if the sysCollectCol is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysCollectCol couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-collect-cols/{id}")
    public ResponseEntity<SysCollectCol> updateSysCollectCol(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysCollectCol sysCollectCol
    ) throws URISyntaxException {
        log.debug("REST request to update SysCollectCol : {}, {}", id, sysCollectCol);
        if (sysCollectCol.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysCollectCol.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysCollectColRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysCollectCol result = sysCollectColRepository.save(sysCollectCol);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysCollectCol.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-collect-cols/:id} : Partial updates given fields of an existing sysCollectCol, field will
     * ignore if it is null
     *
     * @param id            the id of the sysCollectCol to save.
     * @param sysCollectCol the sysCollectCol to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysCollectCol,
     * or with status {@code 400 (Bad Request)} if the sysCollectCol is not valid,
     * or with status {@code 404 (Not Found)} if the sysCollectCol is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysCollectCol couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-collect-cols/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysCollectCol> partialUpdateSysCollectCol(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysCollectCol sysCollectCol
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysCollectCol partially : {}, {}", id, sysCollectCol);
        if (sysCollectCol.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysCollectCol.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysCollectColRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysCollectCol> result = sysCollectColRepository
            .findById(sysCollectCol.getId())
            .map(existingSysCollectCol -> {
                if (sysCollectCol.getColCname() != null) {
                    existingSysCollectCol.setColCname(sysCollectCol.getColCname());
                }
                if (sysCollectCol.getColEname() != null) {
                    existingSysCollectCol.setColEname(sysCollectCol.getColEname());
                }
                if (sysCollectCol.getTabId() != null) {
                    existingSysCollectCol.setTabId(sysCollectCol.getTabId());
                }
                if (sysCollectCol.getOrderNum() != null) {
                    existingSysCollectCol.setOrderNum(sysCollectCol.getOrderNum());
                }
                if (sysCollectCol.getSource() != null) {
                    existingSysCollectCol.setSource(sysCollectCol.getSource());
                }
                if (sysCollectCol.getIsEdit() != null) {
                    existingSysCollectCol.setIsEdit(sysCollectCol.getIsEdit());
                }
                if (sysCollectCol.getRequirement() != null) {
                    existingSysCollectCol.setRequirement(sysCollectCol.getRequirement());
                }
                if (sysCollectCol.getType() != null) {
                    existingSysCollectCol.setType(sysCollectCol.getType());
                }
                if (sysCollectCol.getConfig() != null) {
                    existingSysCollectCol.setConfig(sysCollectCol.getConfig());
                }

                return existingSysCollectCol;
            })
            .map(sysCollectColRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysCollectCol.getId().toString())
        );
    }

    /**
     * {@code GET  /sys-collect-cols} : get all the sysCollectCols.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysCollectCols in body.
     */
    @GetMapping("/sys-collect-cols")
    public List<SysCollectCol> getAllSysCollectCols() {
        log.debug("REST request to get all SysCollectCols");
        return sysCollectColRepository.findAll();
    }

    /**
     * {@code GET  /sys-collect-cols/:id} : get the "id" sysCollectCol.
     *
     * @param id the id of the sysCollectCol to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysCollectCol, or with
     * status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-collect-cols/{id}")
    public ResponseEntity<SysCollectCol> getSysCollectCol(@PathVariable Long id) {
        log.debug("REST request to get SysCollectCol : {}", id);
        Optional<SysCollectCol> sysCollectCol = sysCollectColRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysCollectCol);
    }

    /**
     * {@code DELETE  /sys-collect-cols/:id} : delete the "id" sysCollectCol.
     *
     * @param id the id of the sysCollectCol to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-collect-cols/{id}")
    public ResponseEntity<Void> deleteSysCollectCol(@PathVariable Long id) {
        log.debug("REST request to delete SysCollectCol : {}", id);
        sysCollectColRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * @param sysCollectColList :
     * @data: 2022/6/23-下午9:54
     * @User: zhaozhiwei
     * @method: saveOrUpdate
     * @return: java.util.List<com.example.domain.SysCollectCol>
     * @Description: 批量保存或更新采集表 表信息
     */
    @PostMapping("/sys-collect-cols/save/update")
    public List<SysCollectCol> saveOrUpdate(@RequestBody List<SysCollectCol> sysCollectColList) {
        log.debug("REST request to save SysCollectColList : {}", sysCollectColList);
        //1. 根据第一条数据获取到采集表tab_id, 每次只能处理一个表的数据
        final Long tabId = sysCollectColList.get(0).getTabId();
        //2. 清理掉已经删除的数据, id不在范围内的就是
        final List<SysCollectCol> sysCollectCols = sysCollectColRepository.findAllByTabId(tabId);
        final List<Long> idList = sysCollectColList.stream().map(SysCollectCol::getId).collect(Collectors.toList());
        final List<Long> deleteIds = new ArrayList<>();
        for (SysCollectCol sysCollectCol : sysCollectCols) {
            if (!idList.contains(sysCollectCol.getId())) {
                deleteIds.add(sysCollectCol.getId());
            }
        }
        sysCollectColRepository.deleteAllById(deleteIds);

        //3. 根据传入顺序更新ordernum
        for (int i = 0; i < sysCollectColList.size(); i++) {
            final SysCollectCol sysCollectCol = sysCollectColList.get(i);
            sysCollectCol.setOrderNum(i + 1);

            // 汉字转拼音
            if (StrUtil.isEmpty(sysCollectCol.getColCname())) {
                throw new RuntimeException("表中文名不能为空");
            } else if (StrUtil.isEmpty(sysCollectCol.getColEname())) {
                sysCollectCol.setColEname(PinyinUtil.getFirstLetter(sysCollectCol.getColCname(), ""));
            }
        }
        //4. 保存数据
        sysCollectColRepository.saveAll(sysCollectColList);

        //5. 生成表结构
        final Optional<SysCollectTab> sysCollectTab = sysCollectTabRepository.findById(tabId);
        sysCollectTab.ifPresent(collectTab -> createTable(List.of(collectTab), sysCollectCols));

        return sysCollectColList;
    }

    private final CommonSqlRepository commonSqlRepository;

    /**
     * @param sysCollectTabs :
     * @param sysCollectCols :
     * @data: 2022/6/26-下午12:53
     * @User: zhaozhiwei
     * @method: createTable
     * @return: void
     * @Description: 生成表结构
     */
    public void createTable(List<SysCollectTab> sysCollectTabs, List<SysCollectCol> sysCollectCols) {
        final List<String> commonCols = Arrays.asList("create_time", "update_time", "wfstatus", "create_user");

        final List<String> dropTableSql = sysCollectTabs
            .stream()
            .map(m -> "drop table if exists " + m.getTabEname() + ";")
            .collect(Collectors.toList());

        //生成建表语句
        final List<String> createTableSql = sysCollectTabs
            .stream()
            .map(m -> {
                //创建sql语句
                final StringBuffer stringBuffer = new StringBuffer("");
                stringBuffer.append("create table  IF NOT EXISTS ");
                stringBuffer.append(m.getTabEname());
                stringBuffer.append("(");

                // 主键字段
                stringBuffer.append(" id VARCHAR(64) primary key, ");

                //通用字段
                commonCols.forEach(s -> {
                    stringBuffer.append(s);
                    stringBuffer.append(" VARCHAR(50),");
                });

                //采集表字段
                sysCollectCols.forEach(col -> {
                    final String colEname = col.getColEname();
                    if (
                        (!commonCols.contains(colEname.toLowerCase()) && !commonCols.contains(colEname.toUpperCase())) &&
                        m.getId().equals(col.getTabId())
                    ) {
                        stringBuffer.append(colEname);
                        stringBuffer
                            .append(" VARCHAR(100)")
                            .append(" default '")
                            .append("0")
                            .append("'")
                            .append(" COMMENT '")
                            .append(col.getColCname())
                            .append("',");
                    }
                });
                stringBuffer.append(")");
                //采集表结束
                return stringBuffer.toString();
            })
            .collect(Collectors.toList());
        final String createSql = String.join(";", createTableSql).replaceAll(",\\)", ")");
        log.debug("采集表创建建表语句: {}", createSql);

        try {
            // 先删后插表
            dropTableSql.forEach(commonSqlRepository::execute);

            createTableSql.forEach(sql -> {
                commonSqlRepository.execute(sql.replaceAll(",\\)", ")"));
            });
        } catch (Exception e) {
            log.error("创建或者更新表失败, msg{}", e.getMessage());
            throw new RuntimeException("建表失败", e);
        }
    }

    /**
     * @param tabId :
     * @data: 2022/6/23-下午10:45
     * @User: zhaozhiwei
     * @method: getSysCollectColByTabId
     * @return: java.util.List<com.example.domain.SysCollectCol>
     * @Description: 根据选中采集表查询所有对应列信息
     */
    @GetMapping("/sys-collect-cols/tab-id/{tabId}")
    public List<SysCollectCol> getSysCollectColByTabId(@PathVariable Long tabId) {
        log.debug("REST request to get SysCollectCol : {}", tabId);
        final List<SysCollectCol> allByTabId = sysCollectColRepository.findAllByTabIdOrderByOrderNum(tabId);
        return allByTabId;
    }
}
