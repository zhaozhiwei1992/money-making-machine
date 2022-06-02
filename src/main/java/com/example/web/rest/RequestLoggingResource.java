package com.example.web.rest;

import com.example.domain.RequestLogging;
import com.example.repository.RequestLoggingRepository;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.example.domain.RequestLogging}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RequestLoggingResource {

    private final Logger log = LoggerFactory.getLogger(RequestLoggingResource.class);

    private static final String ENTITY_NAME = "requestLogging";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestLoggingRepository requestLoggingRepository;

    public RequestLoggingResource(RequestLoggingRepository requestLoggingRepository) {
        this.requestLoggingRepository = requestLoggingRepository;
    }

    /**
     * {@code POST  /request-loggings} : Create a new requestLogging.
     *
     * @param requestLogging the requestLogging to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestLogging, or with status {@code 400 (Bad Request)} if the requestLogging has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/request-loggings")
    public ResponseEntity<RequestLogging> createRequestLogging(@RequestBody RequestLogging requestLogging) throws URISyntaxException {
        log.debug("REST request to save RequestLogging : {}", requestLogging);
        if (requestLogging.getId() != null) {
            throw new BadRequestAlertException("A new requestLogging cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequestLogging result = requestLoggingRepository.save(requestLogging);
        return ResponseEntity
            .created(new URI("/api/request-loggings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /request-loggings/:id} : Updates an existing requestLogging.
     *
     * @param id the id of the requestLogging to save.
     * @param requestLogging the requestLogging to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestLogging,
     * or with status {@code 400 (Bad Request)} if the requestLogging is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestLogging couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/request-loggings/{id}")
    public ResponseEntity<RequestLogging> updateRequestLogging(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RequestLogging requestLogging
    ) throws URISyntaxException {
        log.debug("REST request to update RequestLogging : {}, {}", id, requestLogging);
        if (requestLogging.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestLogging.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!requestLoggingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RequestLogging result = requestLoggingRepository.save(requestLogging);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestLogging.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /request-loggings/:id} : Partial updates given fields of an existing requestLogging, field will ignore if it is null
     *
     * @param id the id of the requestLogging to save.
     * @param requestLogging the requestLogging to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestLogging,
     * or with status {@code 400 (Bad Request)} if the requestLogging is not valid,
     * or with status {@code 404 (Not Found)} if the requestLogging is not found,
     * or with status {@code 500 (Internal Server Error)} if the requestLogging couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/request-loggings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RequestLogging> partialUpdateRequestLogging(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RequestLogging requestLogging
    ) throws URISyntaxException {
        log.debug("REST request to partial update RequestLogging partially : {}, {}", id, requestLogging);
        if (requestLogging.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestLogging.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!requestLoggingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RequestLogging> result = requestLoggingRepository
            .findById(requestLogging.getId())
            .map(existingRequestLogging -> {
                if (requestLogging.getTraceId() != null) {
                    existingRequestLogging.setTraceId(requestLogging.getTraceId());
                }
                if (requestLogging.getLoginName() != null) {
                    existingRequestLogging.setLoginName(requestLogging.getLoginName());
                }
                if (requestLogging.getRequestURI() != null) {
                    existingRequestLogging.setRequestURI(requestLogging.getRequestURI());
                }
                if (requestLogging.getClientIP() != null) {
                    existingRequestLogging.setClientIP(requestLogging.getClientIP());
                }
                if (requestLogging.getCurrentTime() != null) {
                    existingRequestLogging.setCurrentTime(requestLogging.getCurrentTime());
                }

                return existingRequestLogging;
            })
            .map(requestLoggingRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestLogging.getId().toString())
        );
    }

    /**
     * {@code GET  /request-loggings} : get all the requestLoggings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestLoggings in body.
     */
    @GetMapping("/request-loggings")
    public ResponseEntity<List<RequestLogging>> getAllRequestLoggings(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String condition
    ) {
        log.debug("REST request to get a page of RequestLoggings");

        // 根据条件模糊匹配
        ExampleMatcher matcher = ExampleMatcher
            .matchingAny()
            //改变默认字符串匹配方式：模糊查询
            .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
            //改变默认大小写忽略方式：忽略大小写
            .withIgnoreCase(true)
            //忽略属性：是否关注。因为是基本类型，需要忽略掉
            .withIgnorePaths("id");

        final RequestLogging requestLogging = new RequestLogging();
        requestLogging.setTraceId(condition);
        requestLogging.setLoginName(condition);
        requestLogging.setRequestURI(condition);
        requestLogging.setCurrentTime(condition);

        final Example<RequestLogging> of = Example.of(requestLogging, matcher);
        Page<RequestLogging> page = requestLoggingRepository.findAll(of, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /request-loggings/:id} : get the "id" requestLogging.
     *
     * @param id the id of the requestLogging to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestLogging, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/request-loggings/{id}")
    public ResponseEntity<RequestLogging> getRequestLogging(@PathVariable Long id) {
        log.debug("REST request to get RequestLogging : {}", id);
        Optional<RequestLogging> requestLogging = requestLoggingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(requestLogging);
    }

    /**
     * {@code DELETE  /request-loggings/:id} : delete the "id" requestLogging.
     *
     * @param id the id of the requestLogging to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/request-loggings/{id}")
    public ResponseEntity<Void> deleteRequestLogging(@PathVariable Long id) {
        log.debug("REST request to delete RequestLogging : {}", id);
        requestLoggingRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
