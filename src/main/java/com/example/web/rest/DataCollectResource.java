package com.example.web.rest;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.example.repository.CommonSqlRepository;
import com.example.repository.UiTableRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 采集表使用 测试demo
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DataCollectResource {

    private final Logger log = LoggerFactory.getLogger(DataCollectResource.class);

    private final CommonSqlRepository commonSqlRepository;

    public DataCollectResource(CommonSqlRepository exampleRepository) {
        this.commonSqlRepository = exampleRepository;
    }

    /**
     * @param datas :
     * @data: 2022/6/26-下午12:42
     * @User: zhaozhiwei
     * @method: createDataCollect
     * @return: java.util.List<java.util.Map>
     * @Description: 批量保存数据, 并返回结果
     * 数据可能存在删除, 变更, 新增的情况
     */
    @PostMapping("/data-collect")
    public List<Map> createDataCollect(@RequestBody List<Map> datas) {
        log.debug("REST request to save DataCollect : {}", datas);
        //1. 清理掉已经删除的数据, id不在范围内的就是
        final List<Map> curAllDataList = commonSqlRepository.queryForList("select * from coll_t_cs");
        final List<String> idList = datas.stream().map(m -> String.valueOf(m.get("id"))).collect(Collectors.toList());
        final List<String> deleteIds = new ArrayList<>();
        for (Map map : curAllDataList) {
            if (!idList.contains(map.get("id"))) {
                deleteIds.add(String.valueOf(map.get("id")));
            }
        }

        final List updateList = new ArrayList();

        final List createList = new ArrayList();

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Map data : datas) {
            if (Objects.isNull(data.get("id")) || StrUtil.isEmpty(String.valueOf(data.get("id")))) {
                data.put("id", UUID.fastUUID().toString());
                data.put("create_time", dateTimeFormatter.format(LocalDateTime.now()));
                createList.add(data);
            } else {
                data.put("update_time", dateTimeFormatter.format(LocalDateTime.now()));
                updateList.add(data);
            }
        }

        // 删除数据
        if (deleteIds.size() > 0) {
            commonSqlRepository.deleteBySql("coll_t_cs", " where " + commonSqlRepository.parseInSql("id", deleteIds));
        }

        if (updateList.size() > 0) {
            // 修改
            commonSqlRepository.updateAllByPK("coll_t_cs", "id", updateList, null);
        }

        if (createList.size() > 0) {
            // 保存
            commonSqlRepository.insertDatas("coll_t_cs", createList);
        }

        final List<Map> list = commonSqlRepository.queryForList("select * from coll_t_cs");
        return list;
    }

    /**
     * {@code GET  /data-collect} : get all the data-collect.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of data-collect in body.
     */
    @GetMapping("/data-collect")
    public List<Map> getAllDataCollects() {
        log.debug("REST request to get all DataCollects");
        return commonSqlRepository.queryForList("select * from coll_t_cs");
    }

    @GetMapping("/data-collect/page/{page}/size/{size}")
    public Page<Map> getPageDataCollects(@PathVariable int page, @PathVariable int size) {
        log.debug("REST request to get Page DataCollects");
        final PageRequest of = PageRequest.of(page - 1, size);
        //        final Page<Map> examplePage = commonSqlRepository.findAll(of);
        //        todo 构建分页查询
        return null;
    }

    @Autowired
    private UiTableRepository uiTableRepository;

    @GetMapping("/data-collect/search/{key}")
    public List<Map> searchDataCollects(@PathVariable String key) {
        log.debug("REST request to search DataCollects with key {}", key);
        if (StrUtil.isEmpty(key)) {
            return commonSqlRepository.queryForList("select * from coll_t_cs");
        }
        return null;
    }
}
