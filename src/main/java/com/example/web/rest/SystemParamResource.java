package com.example.web.rest;

import com.example.domain.SystemParam;
import com.example.repository.SystemParamRepository;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.example.domain.SystemParam}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SystemParamResource {

    private final Logger log = LoggerFactory.getLogger(SystemParamResource.class);

    private static final String ENTITY_NAME = "systemParam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SystemParamRepository systemParamRepository;

    public SystemParamResource(SystemParamRepository systemParamRepository) {
        this.systemParamRepository = systemParamRepository;
    }

    /**
     * {@code POST  /system-params} : Create a new systemParam.
     *
     * @param systemParam the systemParam to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new systemParam, or with status {@code 400 (Bad Request)} if the systemParam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/system-params")
    public ResponseEntity<SystemParam> createSystemParam(@RequestBody SystemParam systemParam) throws URISyntaxException {
        log.debug("REST request to save SystemParam : {}", systemParam);
        if (systemParam.getId() != null) {
            throw new BadRequestAlertException("A new systemParam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SystemParam result = systemParamRepository.save(systemParam);
        return ResponseEntity
            .created(new URI("/api/system-params/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /system-params/:id} : Updates an existing systemParam.
     *
     * @param id the id of the systemParam to save.
     * @param systemParam the systemParam to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated systemParam,
     * or with status {@code 400 (Bad Request)} if the systemParam is not valid,
     * or with status {@code 500 (Internal Server Error)} if the systemParam couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/system-params/{id}")
    public ResponseEntity<SystemParam> updateSystemParam(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SystemParam systemParam
    ) throws URISyntaxException {
        log.debug("REST request to update SystemParam : {}, {}", id, systemParam);
        if (systemParam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, systemParam.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!systemParamRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SystemParam result = systemParamRepository.save(systemParam);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, systemParam.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /system-params/:id} : Partial updates given fields of an existing systemParam, field will ignore if it is null
     *
     * @param id the id of the systemParam to save.
     * @param systemParam the systemParam to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated systemParam,
     * or with status {@code 400 (Bad Request)} if the systemParam is not valid,
     * or with status {@code 404 (Not Found)} if the systemParam is not found,
     * or with status {@code 500 (Internal Server Error)} if the systemParam couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/system-params/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SystemParam> partialUpdateSystemParam(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SystemParam systemParam
    ) throws URISyntaxException {
        log.debug("REST request to partial update SystemParam partially : {}, {}", id, systemParam);
        if (systemParam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, systemParam.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!systemParamRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SystemParam> result = systemParamRepository
            .findById(systemParam.getId())
            .map(existingSystemParam -> {
                if (systemParam.getCode() != null) {
                    existingSystemParam.setCode(systemParam.getCode());
                }
                if (systemParam.getName() != null) {
                    existingSystemParam.setName(systemParam.getName());
                }
                if (systemParam.getValue() != null) {
                    existingSystemParam.setValue(systemParam.getValue());
                }
                if (systemParam.getRemark() != null) {
                    existingSystemParam.setRemark(systemParam.getRemark());
                }
                if (systemParam.getEnable() != null) {
                    existingSystemParam.setEnable(systemParam.getEnable());
                }

                return existingSystemParam;
            })
            .map(systemParamRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, systemParam.getId().toString())
        );
    }

    /**
     * {@code GET  /system-params} : get all the systemParams.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of systemParams in body.
     */
    @GetMapping("/system-params")
    public ResponseEntity<List<SystemParam>> getAllSystemParams(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SystemParams");
        Page<SystemParam> page = systemParamRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /system-params/:id} : get the "id" systemParam.
     *
     * @param id the id of the systemParam to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the systemParam, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/system-params/{id}")
    public ResponseEntity<SystemParam> getSystemParam(@PathVariable Long id) {
        log.debug("REST request to get SystemParam : {}", id);
        Optional<SystemParam> systemParam = systemParamRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(systemParam);
    }

    /**
     * {@code DELETE  /system-params/:id} : delete the "id" systemParam.
     *
     * @param id the id of the systemParam to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/system-params/{id}")
    public ResponseEntity<Void> deleteSystemParam(@PathVariable Long id) {
        log.debug("REST request to delete SystemParam : {}", id);
        systemParamRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
