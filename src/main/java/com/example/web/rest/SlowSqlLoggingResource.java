package com.example.web.rest;

import cn.hutool.core.bean.BeanUtil;
import com.example.domain.Menu;
import com.example.domain.SlowSqlLogging;
import com.example.repository.SlowSqlLoggingRepository;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
 * REST controller for managing {@link com.example.domain.SlowSqlLogging}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SlowSqlLoggingResource {

    private final Logger log = LoggerFactory.getLogger(SlowSqlLoggingResource.class);

    private static final String ENTITY_NAME = "slowSqlLogging";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlowSqlLoggingRepository slowSqlLoggingRepository;

    public SlowSqlLoggingResource(SlowSqlLoggingRepository slowSqlLoggingRepository) {
        this.slowSqlLoggingRepository = slowSqlLoggingRepository;
    }

    /**
     * {@code POST  /slow-sql-loggings} : Create a new slowSqlLogging.
     *
     * @param slowSqlLogging the slowSqlLogging to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slowSqlLogging, or with status {@code 400 (Bad Request)} if the slowSqlLogging has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slow-sql-loggings")
    public ResponseEntity<SlowSqlLogging> createSlowSqlLogging(@RequestBody SlowSqlLogging slowSqlLogging) throws URISyntaxException {
        log.debug("REST request to save SlowSqlLogging : {}", slowSqlLogging);
        if (slowSqlLogging.getId() != null) {
            throw new BadRequestAlertException("A new slowSqlLogging cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlowSqlLogging result = slowSqlLoggingRepository.save(slowSqlLogging);
        return ResponseEntity
            .created(new URI("/api/slow-sql-loggings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slow-sql-loggings/:id} : Updates an existing slowSqlLogging.
     *
     * @param id the id of the slowSqlLogging to save.
     * @param slowSqlLogging the slowSqlLogging to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slowSqlLogging,
     * or with status {@code 400 (Bad Request)} if the slowSqlLogging is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slowSqlLogging couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slow-sql-loggings/{id}")
    public ResponseEntity<SlowSqlLogging> updateSlowSqlLogging(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SlowSqlLogging slowSqlLogging
    ) throws URISyntaxException {
        log.debug("REST request to update SlowSqlLogging : {}, {}", id, slowSqlLogging);
        if (slowSqlLogging.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, slowSqlLogging.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!slowSqlLoggingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SlowSqlLogging result = slowSqlLoggingRepository.save(slowSqlLogging);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slowSqlLogging.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /slow-sql-loggings/:id} : Partial updates given fields of an existing slowSqlLogging, field will ignore if it is null
     *
     * @param id the id of the slowSqlLogging to save.
     * @param slowSqlLogging the slowSqlLogging to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slowSqlLogging,
     * or with status {@code 400 (Bad Request)} if the slowSqlLogging is not valid,
     * or with status {@code 404 (Not Found)} if the slowSqlLogging is not found,
     * or with status {@code 500 (Internal Server Error)} if the slowSqlLogging couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/slow-sql-loggings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SlowSqlLogging> partialUpdateSlowSqlLogging(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SlowSqlLogging slowSqlLogging
    ) throws URISyntaxException {
        log.debug("REST request to partial update SlowSqlLogging partially : {}, {}", id, slowSqlLogging);
        if (slowSqlLogging.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, slowSqlLogging.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!slowSqlLoggingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SlowSqlLogging> result = slowSqlLoggingRepository
            .findById(slowSqlLogging.getId())
            .map(existingSlowSqlLogging -> {
                if (slowSqlLogging.getTraceId() != null) {
                    existingSlowSqlLogging.setTraceId(slowSqlLogging.getTraceId());
                }
                if (slowSqlLogging.getCurrentTime() != null) {
                    existingSlowSqlLogging.setCurrentTime(slowSqlLogging.getCurrentTime());
                }
                if (slowSqlLogging.getSql() != null) {
                    existingSlowSqlLogging.setSql(slowSqlLogging.getSql());
                }
                if (slowSqlLogging.getExecuteMillis() != null) {
                    existingSlowSqlLogging.setExecuteMillis(slowSqlLogging.getExecuteMillis());
                }
                if (slowSqlLogging.getExecuteParams() != null) {
                    existingSlowSqlLogging.setExecuteParams(slowSqlLogging.getExecuteParams());
                }

                return existingSlowSqlLogging;
            })
            .map(slowSqlLoggingRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slowSqlLogging.getId().toString())
        );
    }

    /**
     * {@code GET  /slow-sql-loggings} : get all the slowSqlLoggings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slowSqlLoggings in body.
     */
    @GetMapping("/slow-sql-loggings")
    public ResponseEntity<List<SlowSqlLogging>> getAllSlowSqlLoggings(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String condition
    ) {
        log.debug("REST request to get a page of SlowSqlLoggings");

        // 根据条件模糊匹配
        ExampleMatcher matcher = ExampleMatcher
            .matchingAny()
            //改变默认字符串匹配方式：模糊查询
            .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
            //改变默认大小写忽略方式：忽略大小写
            .withIgnoreCase(true)
            //忽略属性：是否关注。因为是基本类型，需要忽略掉
            .withIgnorePaths("id");

        final SlowSqlLogging slowSqlLogging = new SlowSqlLogging();
        slowSqlLogging.setTraceId(condition);
        slowSqlLogging.setCurrentTime(condition);
        slowSqlLogging.setSql(condition);
        final Example<SlowSqlLogging> of = Example.of(slowSqlLogging, matcher);

        Page<SlowSqlLogging> page = slowSqlLoggingRepository.findAll(of, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /slow-sql-loggings/:id} : get the "id" slowSqlLogging.
     *
     * @param id the id of the slowSqlLogging to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slowSqlLogging, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slow-sql-loggings/{id}")
    public ResponseEntity<SlowSqlLogging> getSlowSqlLogging(@PathVariable Long id) {
        log.debug("REST request to get SlowSqlLogging : {}", id);
        Optional<SlowSqlLogging> slowSqlLogging = slowSqlLoggingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(slowSqlLogging);
    }

    /**
     * {@code DELETE  /slow-sql-loggings/:id} : delete the "id" slowSqlLogging.
     *
     * @param id the id of the slowSqlLogging to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slow-sql-loggings/{id}")
    public ResponseEntity<Void> deleteSlowSqlLogging(@PathVariable Long id) {
        log.debug("REST request to delete SlowSqlLogging : {}", id);
        slowSqlLoggingRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
