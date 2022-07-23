package com.example.web.rest;

import com.example.domain.SysNotice;
import com.example.repository.SysNoticeRepository;
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
 * REST controller for managing {@link com.example.domain.SysNotice}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SysNoticeResource {

    private final Logger log = LoggerFactory.getLogger(SysNoticeResource.class);

    private static final String ENTITY_NAME = "sysNotice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysNoticeRepository sysNoticeRepository;

    public SysNoticeResource(SysNoticeRepository sysNoticeRepository) {
        this.sysNoticeRepository = sysNoticeRepository;
    }

    /**
     * {@code POST  /sys-notices} : Create a new sysNotice.
     *
     * @param sysNotice the sysNotice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysNotice, or with status {@code 400 (Bad Request)} if the sysNotice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-notices")
    public ResponseEntity<SysNotice> createSysNotice(@RequestBody SysNotice sysNotice) throws URISyntaxException {
        log.debug("REST request to save SysNotice : {}", sysNotice);
        if (sysNotice.getId() != null) {
            throw new BadRequestAlertException("A new sysNotice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysNotice result = sysNoticeRepository.save(sysNotice);
        return ResponseEntity
            .created(new URI("/api/sys-notices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-notices/:id} : Updates an existing sysNotice.
     *
     * @param id the id of the sysNotice to save.
     * @param sysNotice the sysNotice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysNotice,
     * or with status {@code 400 (Bad Request)} if the sysNotice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysNotice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-notices/{id}")
    public ResponseEntity<SysNotice> updateSysNotice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysNotice sysNotice
    ) throws URISyntaxException {
        log.debug("REST request to update SysNotice : {}, {}", id, sysNotice);
        if (sysNotice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysNotice.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysNoticeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysNotice result = sysNoticeRepository.save(sysNotice);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysNotice.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-notices/:id} : Partial updates given fields of an existing sysNotice, field will ignore if it is null
     *
     * @param id the id of the sysNotice to save.
     * @param sysNotice the sysNotice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysNotice,
     * or with status {@code 400 (Bad Request)} if the sysNotice is not valid,
     * or with status {@code 404 (Not Found)} if the sysNotice is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysNotice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-notices/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysNotice> partialUpdateSysNotice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysNotice sysNotice
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysNotice partially : {}, {}", id, sysNotice);
        if (sysNotice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysNotice.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysNoticeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysNotice> result = sysNoticeRepository
            .findById(sysNotice.getId())
            .map(existingSysNotice -> {
                if (sysNotice.getTitle() != null) {
                    existingSysNotice.setTitle(sysNotice.getTitle());
                }
                if (sysNotice.getContent() != null) {
                    existingSysNotice.setContent(sysNotice.getContent());
                }
                if (sysNotice.getCreater() != null) {
                    existingSysNotice.setCreater(sysNotice.getCreater());
                }
                if (sysNotice.getCreateTime() != null) {
                    existingSysNotice.setCreateTime(sysNotice.getCreateTime());
                }
                if (sysNotice.getRecType() != null) {
                    existingSysNotice.setRecType(sysNotice.getRecType());
                }
                if (sysNotice.getReceiver() != null) {
                    existingSysNotice.setReceiver(sysNotice.getReceiver());
                }
                if (sysNotice.getUrgent() != null) {
                    existingSysNotice.setUrgent(sysNotice.getUrgent());
                }
                if (sysNotice.getNotiType() != null) {
                    existingSysNotice.setNotiType(sysNotice.getNotiType());
                }
                if (sysNotice.getStatus() != null) {
                    existingSysNotice.setStatus(sysNotice.getStatus());
                }

                return existingSysNotice;
            })
            .map(sysNoticeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysNotice.getId().toString())
        );
    }

    /**
     * {@code GET  /sys-notices} : get all the sysNotices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysNotices in body.
     */
    @GetMapping("/sys-notices")
    public List<SysNotice> getAllSysNotices() {
        log.debug("REST request to get all SysNotices");
        return sysNoticeRepository.findAll();
    }

    /**
     * {@code GET  /sys-notices/:id} : get the "id" sysNotice.
     *
     * @param id the id of the sysNotice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysNotice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-notices/{id}")
    public ResponseEntity<SysNotice> getSysNotice(@PathVariable Long id) {
        log.debug("REST request to get SysNotice : {}", id);
        Optional<SysNotice> sysNotice = sysNoticeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysNotice);
    }

    /**
     * {@code DELETE  /sys-notices/:id} : delete the "id" sysNotice.
     *
     * @param id the id of the sysNotice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-notices/{id}")
    public ResponseEntity<Void> deleteSysNotice(@PathVariable Long id) {
        log.debug("REST request to delete SysNotice : {}", id);
        sysNoticeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
