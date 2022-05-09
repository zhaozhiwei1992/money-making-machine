package com.example.web.rest;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.example.domain.Example;
import com.example.domain.UiTable;
import com.example.repository.ExampleRepository;
import com.example.repository.UiTableRepository;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.example.domain.Example}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ExampleResource {

    private final Logger log = LoggerFactory.getLogger(ExampleResource.class);

    private static final String ENTITY_NAME = "example";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExampleRepository exampleRepository;

    public ExampleResource(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    /**
     * {@code POST  /examples} : Create a new example.
     *
     * @param example the example to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new example, or with
     * status {@code 400 (Bad Request)} if the example has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/examples")
    public ResponseEntity<Example> createExample(@RequestBody Example example) throws URISyntaxException {
        log.debug("REST request to save Example : {}", example);
        if (example.getId() != null) {
            throw new BadRequestAlertException("A new example cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Example result = exampleRepository.save(example);
        return ResponseEntity
            .created(new URI("/api/examples/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /examples/:id} : Updates an existing example.
     *
     * @param id      the id of the example to save.
     * @param example the example to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated example,
     * or with status {@code 400 (Bad Request)} if the example is not valid,
     * or with status {@code 500 (Internal Server Error)} if the example couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/examples/{id}")
    public ResponseEntity<Example> updateExample(@PathVariable(value = "id", required = false) final Long id, @RequestBody Example example)
        throws URISyntaxException {
        log.debug("REST request to update Example : {}, {}", id, example);
        if (example.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, example.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exampleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Example result = exampleRepository.save(example);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, example.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /examples/:id} : Partial updates given fields of an existing example, field will ignore if it is
     * null
     *
     * @param id      the id of the example to save.
     * @param example the example to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated example,
     * or with status {@code 400 (Bad Request)} if the example is not valid,
     * or with status {@code 404 (Not Found)} if the example is not found,
     * or with status {@code 500 (Internal Server Error)} if the example couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/examples/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Example> partialUpdateExample(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Example example
    ) throws URISyntaxException {
        log.debug("REST request to partial update Example partially : {}, {}", id, example);
        if (example.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, example.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exampleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Example> result = exampleRepository
            .findById(example.getId())
            .map(existingExample -> {
                if (example.getName() != null) {
                    existingExample.setName(example.getName());
                }
                if (example.getAge() != null) {
                    existingExample.setAge(example.getAge());
                }
                if (example.getSex() != null) {
                    existingExample.setSex(example.getSex());
                }

                return existingExample;
            })
            .map(exampleRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, example.getId().toString())
        );
    }

    /**
     * {@code GET  /examples} : get all the examples.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examples in body.
     */
    @GetMapping("/examples")
    public List<Example> getAllExamples() {
        log.debug("REST request to get all Examples");
        return exampleRepository.findAll();
    }

    @Autowired
    private UiTableRepository uiTableRepository;

    @GetMapping("/examples/search/{key}")
    public List<Example> searchExamples(@PathVariable String key) {
        log.debug("REST request to search Examples with key {}", key);
        if (StrUtil.isEmpty(key)) {
            return exampleRepository.findAll();
        }
        //      1. 根据菜单id, 获取显示列, 这里演示效果写死菜单id
        final List<UiTable> uiTableList = uiTableRepository.findByMenuid(0L);
        //      2. 将传入属性, 填充给界面显示字段
        final Map<String, String> collect = uiTableList.stream().collect(Collectors.toMap(UiTable::getCode, key2 -> key));
        //      3. 动态构建查询条件
        final Example example = new Example();
        BeanUtil.fillBeanWithMap(collect, example, true);
        log.info("填充后对象信息 {}", example);
        //创建匹配器，即如何使用查询条件
        //构建对象
        ExampleMatcher matcher = ExampleMatcher
            .matchingAny()
            //改变默认字符串匹配方式：模糊查询
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            //改变默认大小写忽略方式：忽略大小写
            .withIgnoreCase(true)
            //地址采用“开始匹配”的方式查询
            .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.startsWith())
            //忽略属性：是否关注。因为是基本类型，需要忽略掉
            .withIgnorePaths("id");

        //创建实例
        org.springframework.data.domain.Example<Example> ex = org.springframework.data.domain.Example.of(example, matcher);
        return exampleRepository.findAll(ex);
    }

    /**
     * {@code GET  /examples/:id} : get the "id" example.
     *
     * @param id the id of the example to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the example, or with status
     * {@code 404 (Not Found)}.
     */
    @GetMapping("/examples/{id}")
    public ResponseEntity<Example> getExample(@PathVariable Long id) {
        log.debug("REST request to get Example : {}", id);
        Optional<Example> example = exampleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(example);
    }

    /**
     * {@code DELETE  /examples/:id} : delete the "id" example.
     *
     * @param id the id of the example to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/examples/{id}")
    public ResponseEntity<Void> deleteExample(@PathVariable Long id) {
        log.debug("REST request to delete Example : {}", id);
        exampleRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
