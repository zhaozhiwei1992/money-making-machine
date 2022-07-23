package com.example.web.rest;

import com.example.domain.SysNoticeSub;
import com.example.repository.SysNoticeSubRepository;
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
 * REST controller for managing {@link com.example.domain.SysNoticeSub}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SysNoticeSubResource {

    private final Logger log = LoggerFactory.getLogger(SysNoticeSubResource.class);

    private static final String ENTITY_NAME = "sysNoticeSub";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysNoticeSubRepository sysNoticeSubRepository;

    public SysNoticeSubResource(SysNoticeSubRepository sysNoticeSubRepository) {
        this.sysNoticeSubRepository = sysNoticeSubRepository;
    }

    /**
     * {@code POST  /sys-notice-subs} : Create a new sysNoticeSub.
     *
     * @param sysNoticeSub the sysNoticeSub to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysNoticeSub, or with status {@code 400 (Bad Request)} if the sysNoticeSub has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-notice-subs")
    public ResponseEntity<SysNoticeSub> createSysNoticeSub(@RequestBody SysNoticeSub sysNoticeSub) throws URISyntaxException {
        log.debug("REST request to save SysNoticeSub : {}", sysNoticeSub);
        if (sysNoticeSub.getId() != null) {
            throw new BadRequestAlertException("A new sysNoticeSub cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysNoticeSub result = sysNoticeSubRepository.save(sysNoticeSub);
        return ResponseEntity
            .created(new URI("/api/sys-notice-subs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-notice-subs/:id} : Updates an existing sysNoticeSub.
     *
     * @param id the id of the sysNoticeSub to save.
     * @param sysNoticeSub the sysNoticeSub to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysNoticeSub,
     * or with status {@code 400 (Bad Request)} if the sysNoticeSub is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysNoticeSub couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-notice-subs/{id}")
    public ResponseEntity<SysNoticeSub> updateSysNoticeSub(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysNoticeSub sysNoticeSub
    ) throws URISyntaxException {
        log.debug("REST request to update SysNoticeSub : {}, {}", id, sysNoticeSub);
        if (sysNoticeSub.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysNoticeSub.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysNoticeSubRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysNoticeSub result = sysNoticeSubRepository.save(sysNoticeSub);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysNoticeSub.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-notice-subs/:id} : Partial updates given fields of an existing sysNoticeSub, field will ignore if it is null
     *
     * @param id the id of the sysNoticeSub to save.
     * @param sysNoticeSub the sysNoticeSub to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysNoticeSub,
     * or with status {@code 400 (Bad Request)} if the sysNoticeSub is not valid,
     * or with status {@code 404 (Not Found)} if the sysNoticeSub is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysNoticeSub couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-notice-subs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysNoticeSub> partialUpdateSysNoticeSub(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysNoticeSub sysNoticeSub
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysNoticeSub partially : {}, {}", id, sysNoticeSub);
        if (sysNoticeSub.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysNoticeSub.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysNoticeSubRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysNoticeSub> result = sysNoticeSubRepository
            .findById(sysNoticeSub.getId())
            .map(existingSysNoticeSub -> {
                if (sysNoticeSub.getSysNoticeId() != null) {
                    existingSysNoticeSub.setSysNoticeId(sysNoticeSub.getSysNoticeId());
                }
                if (sysNoticeSub.getRecipientId() != null) {
                    existingSysNoticeSub.setRecipientId(sysNoticeSub.getRecipientId());
                }
                if (sysNoticeSub.getUpdateTime() != null) {
                    existingSysNoticeSub.setUpdateTime(sysNoticeSub.getUpdateTime());
                }
                if (sysNoticeSub.getStatus() != null) {
                    existingSysNoticeSub.setStatus(sysNoticeSub.getStatus());
                }

                return existingSysNoticeSub;
            })
            .map(sysNoticeSubRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysNoticeSub.getId().toString())
        );
    }

    /**
     * {@code GET  /sys-notice-subs} : get all the sysNoticeSubs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysNoticeSubs in body.
     */
    @GetMapping("/sys-notice-subs")
    public List<SysNoticeSub> getAllSysNoticeSubs() {
        log.debug("REST request to get all SysNoticeSubs");
        return sysNoticeSubRepository.findAll();
    }

    /**
     * {@code GET  /sys-notice-subs/:id} : get the "id" sysNoticeSub.
     *
     * @param id the id of the sysNoticeSub to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysNoticeSub, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-notice-subs/{id}")
    public ResponseEntity<SysNoticeSub> getSysNoticeSub(@PathVariable Long id) {
        log.debug("REST request to get SysNoticeSub : {}", id);
        Optional<SysNoticeSub> sysNoticeSub = sysNoticeSubRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysNoticeSub);
    }

    /**
     * {@code DELETE  /sys-notice-subs/:id} : delete the "id" sysNoticeSub.
     *
     * @param id the id of the sysNoticeSub to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-notice-subs/{id}")
    public ResponseEntity<Void> deleteSysNoticeSub(@PathVariable Long id) {
        log.debug("REST request to delete SysNoticeSub : {}", id);
        sysNoticeSubRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
