package com.example.web.rest;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.domain.SysCollectCol;
import com.example.repository.CommonSqlRepository;
import com.example.repository.SysCollectColRepository;
import com.example.repository.UiTableRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采集表使用 测试demo
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DataCollectResource {

    private final Logger log = LoggerFactory.getLogger(DataCollectResource.class);

    private final CommonSqlRepository commonSqlRepository;

    public DataCollectResource(CommonSqlRepository exampleRepository, SysCollectColRepository sysCollectColRepository) {
        this.commonSqlRepository = exampleRepository;
        this.sysCollectColRepository = sysCollectColRepository;
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

    @PostMapping("/data-collect/audit")
    public List<Map> dataCollectAudit(@RequestBody List<Map> datas) {
        // 修改业务字段
        for (Map data : datas) {
            data.put("wfstatus", "011");
        }
        commonSqlRepository.updateAllByPK("coll_t_cs", "id", datas, Arrays.asList("wfstatus"));
        return datas;
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

    @PostMapping("/data-collect/import")
    public ResponseEntity<Object> imp(MultipartFile file) throws IOException {
        //获取来自浏览器发送的文件内容
        InputStream inputStream = file.getInputStream();

        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Map<String, Object>> importDatas = reader.readAll();

        final List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAllByTabId(11L);
        // 字段中英文对照
        final Map<String, String> enCnameMap = new HashMap<>();
        for (SysCollectCol sysCollectCol : sysCollectColList) {
            enCnameMap.put(sysCollectCol.getColEname(), sysCollectCol.getColCname());
        }

        // 数据转换为字段, 因为动态构建，只能保留英文字段, 并增加id
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final List<Map> saveDataList = importDatas
            .stream()
            .map(m -> {
                final Map<String, Object> map = new HashMap<>();
                for (Map.Entry<String, String> enCnNameEntry : enCnameMap.entrySet()) {
                    final String colEnameName = enCnNameEntry.getKey();
                    final String colCnameName = enCnNameEntry.getValue();
                    map.put(colEnameName, m.get(colCnameName));
                }
                map.put("id", UUID.fastUUID().toString());
                map.put("create_time", dateTimeFormatter.format(LocalDateTime.now()));
                return map;
            })
            .collect(Collectors.toList());
        commonSqlRepository.insertDatas("coll_t_cs", saveDataList);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/data-collect/export")
    public void export(HttpServletResponse response) {
        try {
            String fileName = "demo采集表数据导出.xlsx";
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.addHeader("filename", URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            final ServletOutputStream out = response.getOutputStream();

            //https://hutool.cn/docs/#/poi/Excel%E7%94%9F%E6%88%90-ExcelWriter
            ExcelWriter writer = ExcelUtil.getWriter(true);
            // 每行的记录
            final List<Map> rows = new ArrayList<>();

            final List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAllByTabId(11L);
            // 字段中英文对照
            final Map<String, String> enCnameMap = new HashMap<>();
            final Map<String, Object> header = new HashMap<>();
            for (SysCollectCol sysCollectCol : sysCollectColList) {
                header.put(sysCollectCol.getColCname(), "");
                enCnameMap.put(sysCollectCol.getColEname(), sysCollectCol.getColCname());
            }

            // 增加脑袋
            rows.add(header);
            // 增加数据, 导出全部先
            final List<Map> list = commonSqlRepository.queryForList("select * from coll_t_cs");
            for (Map map : list) {
                // 将数据根据字段转换为 hutool需要的填充格式
                for (Map.Entry<String, String> enCnNameEntry : enCnameMap.entrySet()) {
                    final String colEnameName = enCnNameEntry.getKey();
                    final String colCnameName = enCnNameEntry.getValue();
                    map.put(colCnameName, map.get(colEnameName));
                }
                rows.add(map);
            }

            // 数据格式list<map>
            writer.write(rows, true);

            writer.flush(out, true);
            writer.close();
            IoUtil.close(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final SysCollectColRepository sysCollectColRepository;

    /**
     * @data: 2022/6/26-下午10:09
     * @User: zhaozhiwei
     * @method: exportTemplate
     * @return: boolean
     * @Description: 导出模板
     */
    @GetMapping("/data-collect/exportTemplate")
    public void exportTemplate(HttpServletResponse response) {
        try {
            String fileName = "demo采集表导入模板.xlsx";
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.addHeader("filename", URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setCharacterEncoding("UTF-8");
            //            response.setContentType("application/x-download");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            final ServletOutputStream out = response.getOutputStream();

            //https://hutool.cn/docs/#/poi/Excel%E7%94%9F%E6%88%90-ExcelWriter
            ExcelWriter writer = ExcelUtil.getWriter(true);

            final List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAllByTabId(11L);
            final HashMap<String, Object> header = new HashMap<>();
            for (SysCollectCol sysCollectCol : sysCollectColList) {
                header.put(sysCollectCol.getColCname(), "");
            }

            final List<Map> rows = new ArrayList<>();
            rows.add(header);

            // 数据格式list<map>
            writer.write(rows, true);

            writer.flush(out, true);
            writer.close();
            IoUtil.close(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
