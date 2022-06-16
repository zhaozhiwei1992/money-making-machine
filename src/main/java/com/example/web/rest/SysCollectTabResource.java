package com.example.web.rest;

import com.example.domain.SysCollectTab;
import com.example.repository.SysCollectTabRepository;
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

    public SysCollectTabResource(SysCollectTabRepository sysCollectTabRepository) {
        this.sysCollectTabRepository = sysCollectTabRepository;
    }

    /**
     * {@code POST  /sys-collect-tabs} : Create a new sysCollectTab.
     *
     * @param sysCollectTab the sysCollectTab to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysCollectTab, or with status {@code 400 (Bad Request)} if the sysCollectTab has already an ID.
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
     * {@code PUT  /sys-collect-tabs/:id} : Updates an existing sysCollectTab.
     *
     * @param id the id of the sysCollectTab to save.
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
     * {@code PATCH  /sys-collect-tabs/:id} : Partial updates given fields of an existing sysCollectTab, field will ignore if it is null
     *
     * @param id the id of the sysCollectTab to save.
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
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysCollectTab, or with status {@code 404 (Not Found)}.
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
}
