package com.example.web.rest;

import com.example.domain.SysCollectCol;
import com.example.repository.SysCollectColRepository;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    public SysCollectColResource(SysCollectColRepository sysCollectColRepository) {
        this.sysCollectColRepository = sysCollectColRepository;
    }

    /**
     * {@code POST  /sys-collect-cols} : Create a new sysCollectCol.
     *
     * @param sysCollectCol the sysCollectCol to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysCollectCol, or with status {@code 400 (Bad Request)} if the sysCollectCol has already an ID.
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
     * @param id the id of the sysCollectCol to save.
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
     * {@code PATCH  /sys-collect-cols/:id} : Partial updates given fields of an existing sysCollectCol, field will ignore if it is null
     *
     * @param id the id of the sysCollectCol to save.
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
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysCollectCol, or with status {@code 404 (Not Found)}.
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
}
