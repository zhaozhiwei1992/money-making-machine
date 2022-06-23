package com.example.web.rest;

import cn.hutool.core.bean.BeanUtil;
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
import java.time.LocalDateTime;
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
 * REST controller for managing {@link com.example.domain.SysCollectTab}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SysCollectTabResource {

    private final Logger log = LoggerFactory.getLogger(SysCollectTabResource.class);

    private static final String ENTITY_NAME = "sysCollectTab";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysCollectTabRepository sysCollectTabRepository;

    private final SysCollectColRepository sysCollectColRepository;

    private final CommonSqlRepository commonSqlRepository;

    public SysCollectTabResource(
        SysCollectTabRepository sysCollectTabRepository,
        SysCollectColRepository sysCollectColRepository,
        CommonSqlRepository commonSqlRepository
    ) {
        this.sysCollectTabRepository = sysCollectTabRepository;
        this.sysCollectColRepository = sysCollectColRepository;
        this.commonSqlRepository = commonSqlRepository;
    }

    /**
     * {@code POST  /sys-collect-tabs} : Create a new sysCollectTab.
     *
     * @param sysCollectTab the sysCollectTab to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysCollectTab, or
     * with status {@code 400 (Bad Request)} if the sysCollectTab has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-collect-tabs")
    public ResponseEntity<SysCollectTab> createSysCollectTab(@RequestBody SysCollectTab sysCollectTab) throws URISyntaxException {
        log.debug("REST request to save SysCollectTab : {}", sysCollectTab);
        if (sysCollectTab.getId() != null) {
            throw new BadRequestAlertException("A new sysCollectTab cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCollectTab result = sysCollectTabRepository.save(sysCollectTab);
        return ResponseEntity
            .created(new URI("/api/sys-collect-tabs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * @param tabCols : 存储采集表和列信息
     * @data: 2022/6/17-上午9:27
     * @User: zhaozhiwei
     * @method: createSysCollectTabAndCols
     * @return: org.springframework.http.ResponseEntity<com.example.domain.SysCollectTab>
     * @Description: 采集表和列信息同时保存
     */
    @PostMapping("/sys-collect-tabs-cols")
    public ResponseEntity<SysCollectTab> createSysCollectTabAndCols(@RequestBody Map<String, Object> tabCols) throws URISyntaxException {
        log.debug("REST request List<SysCollectTab> : {}", tabCols.get("collectTabs"));
        log.debug("REST request List<SysCollectCol> : {}", tabCols.get("collectCols"));
        //        可能存在多组表和列信息
        final List<SysCollectTab> sysCollectTabsCreated = new ArrayList<>();
        final List<SysCollectTab> sysCollectTabsUpdated = new ArrayList<>();

        //        如果id存在为修改, 否则为新增(新增的, tabid为表明, 通过tabid进行关联, 保存后转换真实id)
        ((List) tabCols.get("collectTabs")).forEach(o -> {
                final SysCollectTab sysCollectTab = BeanUtil.toBean(o, SysCollectTab.class);
                if (Objects.isNull(sysCollectTab.getId())) {
                    sysCollectTabsCreated.add(BeanUtil.toBean(o, SysCollectTab.class));
                } else {
                    sysCollectTabsUpdated.add(BeanUtil.toBean(o, SysCollectTab.class));
                }
            });

        final ArrayList<SysCollectCol> sysCollectColsCreated = new ArrayList<>();
        final ArrayList<SysCollectCol> sysCollectColsUpdated = new ArrayList<>();
        for (Object o : ((List) tabCols.get("collectCols"))) {
            final SysCollectCol sysCollectCol = BeanUtil.toBean(o, SysCollectCol.class);
            if (Objects.isNull(sysCollectCol.getId())) {
                sysCollectColsCreated.add(BeanUtil.toBean(o, SysCollectCol.class));
            } else {
                sysCollectColsUpdated.add(BeanUtil.toBean(o, SysCollectCol.class));
            }
        }

        //        新增保存开始

        //        新增的需要先保存表信息, 根据返回值修改列中tabid在保存列信息, 初始tabid为表英文名称
        final List<SysCollectTab> sysCollectTabs = sysCollectTabRepository.saveAll(sysCollectTabsCreated);
        for (SysCollectTab sysCollectTab : sysCollectTabs) {
            for (SysCollectCol sysCollectCol : sysCollectColsCreated) {
                //                采集表列config中记录下表英文名
                if (sysCollectCol.getConfig().contains(sysCollectTab.getTabEname())) {
                    sysCollectCol.setTabId(sysCollectTab.getId());
                }
            }
        }
        sysCollectColRepository.saveAll(sysCollectColsCreated);
        //        新增保存结束

        //        修改的表直接保存即可, 列可能是新增需填充tabid即可
        for (SysCollectTab sysCollectTab : sysCollectTabsUpdated) {
            for (SysCollectCol sysCollectCol : sysCollectColsUpdated) {
                if (sysCollectCol.getConfig().contains(sysCollectTab.getTabEname())) {
                    sysCollectCol.setTabId(sysCollectTab.getId());
                }
            }
        }
        sysCollectTabRepository.saveAll(sysCollectTabsUpdated);
        sysCollectColRepository.saveAll(sysCollectColsUpdated);

        //        创建表, 先删后插
        sysCollectColsCreated.addAll(sysCollectColsUpdated);
        sysCollectTabsCreated.addAll(sysCollectTabsUpdated);
        createTable(sysCollectTabsCreated, sysCollectColsCreated);

        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, "")).build();
    }

    /**
     * 根据表和列信息生成表
     * 正常录入先删后插
     *
     * @param sysCollectTabs 采集表信息
     * @param sysCollectCols 采集表列信息
     */
    public void createTable(List<SysCollectTab> sysCollectTabs, List<SysCollectCol> sysCollectCols) {
        final List<String> commonCols = Arrays.asList("create_time", "update_time", "create_user", "is_delete");

        final List<String> dropTable = sysCollectTabs
            .stream()
            .map(m -> "" + "drop table if exists " + m.getTabEname() + ";")
            .collect(Collectors.toList());

        //生成建表语句
        final List<String> createTable = sysCollectTabs
            .stream()
            .map(m -> {
                //创建sql语句
                final StringBuffer stringBuffer = new StringBuffer("");
                stringBuffer.append("create table  IF NOT EXISTS ");
                stringBuffer.append(m.getTabEname());
                stringBuffer.append("(");

                //主键字段
                // 增加一卡通标识(0), 默认0
                stringBuffer.append(" id VARCHAR(64) primary key, ");

                //通用字段
                commonCols.forEach(s -> {
                    stringBuffer.append(s);
                    stringBuffer.append(" VARCHAR(50),");
                });

                //采集表字段
                sysCollectCols.forEach(col -> {
                    if (
                        (!commonCols.contains(col.getColEname().toLowerCase()) && !commonCols.contains(col.getColEname().toUpperCase())) &&
                        m.getId().equals(col.getTabId())
                    ) {
                        stringBuffer.append(col.getColEname());
                        stringBuffer
                            .append(" VARCHAR(100)")
                            .append(" default '0'")
                            .append(" COMMENT '")
                            .append(col.getColCname())
                            .append("',");
                    }
                });
                //采集表结束
                stringBuffer.append(")");
                return stringBuffer.toString();
            })
            .collect(Collectors.toList());
        final String createSql = String.join(";", createTable).replaceAll(",\\)", ")");
        log.debug("采集表创建建表语句: {}", createSql);

        //执行, 这里不能执行多个，循环执行吧
        //        formulaTabDao.executeUpdateSql(createSql);

        try {
            //正常录入先删后插
            dropTable.forEach(commonSqlRepository::execute);

            createTable.forEach(sql -> {
                commonSqlRepository.execute(sql.replaceAll(",\\)", ")"));
            });
        } catch (Exception e) {
            log.error("创建或者更新表失败", e);
            throw new RuntimeException("建表失败", e);
        }
    }

    /**
     * {@code PUT  /sys-collect-tabs/:id} : Updates an existing sysCollectTab.
     *
     * @param id            the id of the sysCollectTab to save.
     * @param sysCollectTab the sysCollectTab to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysCollectTab,
     * or with status {@code 400 (Bad Request)} if the sysCollectTab is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysCollectTab couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-collect-tabs/{id}")
    public ResponseEntity<SysCollectTab> updateSysCollectTab(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysCollectTab sysCollectTab
    ) throws URISyntaxException {
        log.debug("REST request to update SysCollectTab : {}, {}", id, sysCollectTab);
        if (sysCollectTab.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysCollectTab.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysCollectTabRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysCollectTab result = sysCollectTabRepository.save(sysCollectTab);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysCollectTab.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-collect-tabs/:id} : Partial updates given fields of an existing sysCollectTab, field will
     * ignore if it is null
     *
     * @param id            the id of the sysCollectTab to save.
     * @param sysCollectTab the sysCollectTab to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysCollectTab,
     * or with status {@code 400 (Bad Request)} if the sysCollectTab is not valid,
     * or with status {@code 404 (Not Found)} if the sysCollectTab is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysCollectTab couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-collect-tabs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysCollectTab> partialUpdateSysCollectTab(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysCollectTab sysCollectTab
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysCollectTab partially : {}, {}", id, sysCollectTab);
        if (sysCollectTab.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysCollectTab.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysCollectTabRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysCollectTab> result = sysCollectTabRepository
            .findById(sysCollectTab.getId())
            .map(existingSysCollectTab -> {
                if (sysCollectTab.getTabCname() != null) {
                    existingSysCollectTab.setTabCname(sysCollectTab.getTabCname());
                }
                if (sysCollectTab.getTabEname() != null) {
                    existingSysCollectTab.setTabEname(sysCollectTab.getTabEname());
                }
                if (sysCollectTab.getConfig() != null) {
                    existingSysCollectTab.setConfig(sysCollectTab.getConfig());
                }
                if (sysCollectTab.getEnable() != null) {
                    existingSysCollectTab.setEnable(sysCollectTab.getEnable());
                }

                return existingSysCollectTab;
            })
            .map(sysCollectTabRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysCollectTab.getId().toString())
        );
    }

    /**
     * {@code GET  /sys-collect-tabs} : get all the sysCollectTabs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysCollectTabs in body.
     */
    @GetMapping("/sys-collect-tabs")
    public List<SysCollectTab> getAllSysCollectTabs() {
        log.debug("REST request to get all SysCollectTabs");
        return sysCollectTabRepository.findAll();
    }

    /**
     * {@code GET  /sys-collect-tabs/:id} : get the "id" sysCollectTab.
     *
     * @param id the id of the sysCollectTab to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysCollectTab, or with
     * status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-collect-tabs/{id}")
    public ResponseEntity<SysCollectTab> getSysCollectTab(@PathVariable Long id) {
        log.debug("REST request to get SysCollectTab : {}", id);
        Optional<SysCollectTab> sysCollectTab = sysCollectTabRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysCollectTab);
    }

    /**
     * {@code DELETE  /sys-collect-tabs/:id} : delete the "id" sysCollectTab.
     *
     * @param id the id of the sysCollectTab to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-collect-tabs/{id}")
    public ResponseEntity<Void> deleteSysCollectTab(@PathVariable Long id) {
        log.debug("REST request to delete SysCollectTab : {}", id);
        sysCollectTabRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * @data: 2022/6/23-下午9:54
     * @User: zhaozhiwei
     * @method: saveOrUpdate
     * @param sysCollectTabList :
     * @return: java.util.List<com.example.domain.SysCollectTab>
     * @Description: 批量保存或更新采集表 表信息
     */
    @PostMapping("/sys-collect-tabs/save/update")
    public List<SysCollectTab> saveOrUpdate(@RequestBody List<SysCollectTab> sysCollectTabList) {
        log.debug("REST request to save SysCollectTabList : {}", sysCollectTabList);
        //1. 清理掉已经删除的数据, id不在范围内的就是
        final List<SysCollectTab> sysCollectTabs = sysCollectTabRepository.findAll();
        final List<Long> idList = sysCollectTabList.stream().map(SysCollectTab::getId).collect(Collectors.toList());
        final List<Long> deleteIds = new ArrayList<>();
        for (SysCollectTab sysCollectTab : sysCollectTabs) {
            if (!idList.contains(sysCollectTab.getId())) {
                deleteIds.add(sysCollectTab.getId());
            }
        }
        sysCollectTabRepository.deleteAllById(deleteIds);

        //2. 如果表英文名为null, 则进行中文转换
        for (SysCollectTab sysCollectTab : sysCollectTabList) {
            if (StrUtil.isEmpty(sysCollectTab.getTabCname())) {
                throw new RuntimeException("表中文名不能为空");
            } else if (StrUtil.isEmpty(sysCollectTab.getTabEname())) {
                sysCollectTab.setTabEname("coll_t_" + PinyinUtil.getFirstLetter(sysCollectTab.getTabCname(), ""));
            }
        }

        //2. 保存数据
        sysCollectTabRepository.saveAll(sysCollectTabList);
        return sysCollectTabList;
    }

    /**
     * @data: 2022/6/23-下午10:22
     * @User: zhaozhiwei
     * @method: getEleUnionsLeftTree

     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Description: 左侧树显示采集表信息
     */
    @GetMapping("/sys-collect-tabs/left-tree")
    public List<Map<String, Object>> getSysCollectTabsLeftTree() {
        log.debug("REST request to get all getSysCollectTabsLeftTree");

        //1. 获取所有ele_的信息
        final List<Map<String, Object>> allEleCategory = sysCollectTabRepository
            .findAllByEnable(true)
            .stream()
            .map(BeanUtil::beanToMap)
            .collect(Collectors.toList());
        //2. 构建成树信息
        for (Map<String, Object> map : allEleCategory) {
            map.put("id", map.get("id"));
            map.put("label", map.get("tabEname") + "-" + map.get("tabCname"));
        }
        return allEleCategory;
    }
}
