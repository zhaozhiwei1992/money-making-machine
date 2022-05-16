package com.example.web.rest;

import com.example.domain.DataPermissionsRel;
import com.example.repository.DataPermissionsRelRepository;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
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
 * REST controller for managing {@link com.example.domain.DataPermissionsRel}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DataPermissionsRelResource {

    private final Logger log = LoggerFactory.getLogger(DataPermissionsRelResource.class);

    private static final String ENTITY_NAME = "dataPermissionsRel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataPermissionsRelRepository dataPermissionsRelRepository;

    public DataPermissionsRelResource(DataPermissionsRelRepository dataPermissionsRelRepository) {
        this.dataPermissionsRelRepository = dataPermissionsRelRepository;
    }

    /**
     * {@code POST  /data-permissions-rels} : Create a new dataPermissionsRel.
     *
     * @param dataPermissionsRel the dataPermissionsRel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataPermissionsRel, or with status {@code 400 (Bad Request)} if the dataPermissionsRel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-permissions-rels")
    public ResponseEntity<DataPermissionsRel> createDataPermissionsRel(@RequestBody DataPermissionsRel dataPermissionsRel)
        throws URISyntaxException {
        log.debug("REST request to save DataPermissionsRel : {}", dataPermissionsRel);
        if (dataPermissionsRel.getId() != null) {
            throw new BadRequestAlertException("A new dataPermissionsRel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataPermissionsRel result = dataPermissionsRelRepository.save(dataPermissionsRel);
        return ResponseEntity
            .created(new URI("/api/data-permissions-rels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-permissions-rels/:id} : Updates an existing dataPermissionsRel.
     *
     * @param id the id of the dataPermissionsRel to save.
     * @param dataPermissionsRel the dataPermissionsRel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataPermissionsRel,
     * or with status {@code 400 (Bad Request)} if the dataPermissionsRel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataPermissionsRel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-permissions-rels/{id}")
    public ResponseEntity<DataPermissionsRel> updateDataPermissionsRel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DataPermissionsRel dataPermissionsRel
    ) throws URISyntaxException {
        log.debug("REST request to update DataPermissionsRel : {}, {}", id, dataPermissionsRel);
        if (dataPermissionsRel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dataPermissionsRel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dataPermissionsRelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DataPermissionsRel result = dataPermissionsRelRepository.save(dataPermissionsRel);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataPermissionsRel.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /data-permissions-rels/:id} : Partial updates given fields of an existing dataPermissionsRel, field will ignore if it is null
     *
     * @param id the id of the dataPermissionsRel to save.
     * @param dataPermissionsRel the dataPermissionsRel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataPermissionsRel,
     * or with status {@code 400 (Bad Request)} if the dataPermissionsRel is not valid,
     * or with status {@code 404 (Not Found)} if the dataPermissionsRel is not found,
     * or with status {@code 500 (Internal Server Error)} if the dataPermissionsRel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/data-permissions-rels/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DataPermissionsRel> partialUpdateDataPermissionsRel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DataPermissionsRel dataPermissionsRel
    ) throws URISyntaxException {
        log.debug("REST request to partial update DataPermissionsRel partially : {}, {}", id, dataPermissionsRel);
        if (dataPermissionsRel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dataPermissionsRel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dataPermissionsRelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DataPermissionsRel> result = dataPermissionsRelRepository
            .findById(dataPermissionsRel.getId())
            .map(existingDataPermissionsRel -> {
                if (dataPermissionsRel.getRuleId() != null) {
                    existingDataPermissionsRel.setRuleId(dataPermissionsRel.getRuleId());
                }
                if (dataPermissionsRel.getRoleId() != null) {
                    existingDataPermissionsRel.setRoleId(dataPermissionsRel.getRoleId());
                }
                if (dataPermissionsRel.getMenuId() != null) {
                    existingDataPermissionsRel.setMenuId(dataPermissionsRel.getMenuId());
                }

                return existingDataPermissionsRel;
            })
            .map(dataPermissionsRelRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataPermissionsRel.getId().toString())
        );
    }

    /**
     * {@code GET  /data-permissions-rels} : get all the dataPermissionsRels.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataPermissionsRels in body.
     */
    @GetMapping("/data-permissions-rels")
    public ResponseEntity<List<DataPermissionsRel>> getAllDataPermissionsRels(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DataPermissionsRels");
        Page<DataPermissionsRel> page = dataPermissionsRelRepository.findAll(pageable);
        final List<DataPermissionsRel> allByMenuId = dataPermissionsRelRepository.findAllByMenuIdAndRoleIdIn("1", Arrays.asList("1"));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-permissions-rels/:id} : get the "id" dataPermissionsRel.
     *
     * @param id the id of the dataPermissionsRel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataPermissionsRel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-permissions-rels/{id}")
    public ResponseEntity<DataPermissionsRel> getDataPermissionsRel(@PathVariable Long id) {
        log.debug("REST request to get DataPermissionsRel : {}", id);
        Optional<DataPermissionsRel> dataPermissionsRel = dataPermissionsRelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dataPermissionsRel);
    }

    /**
     * {@code DELETE  /data-permissions-rels/:id} : delete the "id" dataPermissionsRel.
     *
     * @param id the id of the dataPermissionsRel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-permissions-rels/{id}")
    public ResponseEntity<Void> deleteDataPermissionsRel(@PathVariable Long id) {
        log.debug("REST request to delete DataPermissionsRel : {}", id);
        dataPermissionsRelRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
