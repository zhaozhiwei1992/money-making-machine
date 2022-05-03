package com.example.web.rest;

import com.example.domain.UiTable;
import com.example.repository.UiTableRepository;
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
 * REST controller for managing {@link com.example.domain.UiTable}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UiTableResource {

    private final Logger log = LoggerFactory.getLogger(UiTableResource.class);

    private static final String ENTITY_NAME = "uiTable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UiTableRepository uiTableRepository;

    public UiTableResource(UiTableRepository uiTableRepository) {
        this.uiTableRepository = uiTableRepository;
    }

    /**
     * {@code POST  /ui-tables} : Create a new uiTable.
     *
     * @param uiTable the uiTable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uiTable, or with status {@code 400 (Bad Request)} if the uiTable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ui-tables")
    public ResponseEntity<UiTable> createUiTable(@RequestBody UiTable uiTable) throws URISyntaxException {
        log.debug("REST request to save UiTable : {}", uiTable);
        if (uiTable.getId() != null) {
            throw new BadRequestAlertException("A new uiTable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UiTable result = uiTableRepository.save(uiTable);
        return ResponseEntity
            .created(new URI("/api/ui-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ui-tables/:id} : Updates an existing uiTable.
     *
     * @param id the id of the uiTable to save.
     * @param uiTable the uiTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uiTable,
     * or with status {@code 400 (Bad Request)} if the uiTable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uiTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ui-tables/{id}")
    public ResponseEntity<UiTable> updateUiTable(@PathVariable(value = "id", required = false) final Long id, @RequestBody UiTable uiTable)
        throws URISyntaxException {
        log.debug("REST request to update UiTable : {}, {}", id, uiTable);
        if (uiTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, uiTable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uiTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UiTable result = uiTableRepository.save(uiTable);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uiTable.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ui-tables/:id} : Partial updates given fields of an existing uiTable, field will ignore if it is null
     *
     * @param id the id of the uiTable to save.
     * @param uiTable the uiTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uiTable,
     * or with status {@code 400 (Bad Request)} if the uiTable is not valid,
     * or with status {@code 404 (Not Found)} if the uiTable is not found,
     * or with status {@code 500 (Internal Server Error)} if the uiTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ui-tables/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UiTable> partialUpdateUiTable(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UiTable uiTable
    ) throws URISyntaxException {
        log.debug("REST request to partial update UiTable partially : {}, {}", id, uiTable);
        if (uiTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, uiTable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uiTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UiTable> result = uiTableRepository
            .findById(uiTable.getId())
            .map(existingUiTable -> {
                if (uiTable.getMenuid() != null) {
                    existingUiTable.setMenuid(uiTable.getMenuid());
                }
                if (uiTable.getCode() != null) {
                    existingUiTable.setCode(uiTable.getCode());
                }
                if (uiTable.getName() != null) {
                    existingUiTable.setName(uiTable.getName());
                }
                if (uiTable.getOrdernum() != null) {
                    existingUiTable.setOrdernum(uiTable.getOrdernum());
                }
                if (uiTable.getIssource() != null) {
                    existingUiTable.setIssource(uiTable.getIssource());
                }
                if (uiTable.getIsedit() != null) {
                    existingUiTable.setIsedit(uiTable.getIsedit());
                }
                if (uiTable.getRequirement() != null) {
                    existingUiTable.setRequirement(uiTable.getRequirement());
                }
                if (uiTable.getType() != null) {
                    existingUiTable.setType(uiTable.getType());
                }
                if (uiTable.getConfig() != null) {
                    existingUiTable.setConfig(uiTable.getConfig());
                }

                return existingUiTable;
            })
            .map(uiTableRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uiTable.getId().toString())
        );
    }

    /**
     * {@code GET  /ui-tables} : get all the uiTables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uiTables in body.
     */
    @GetMapping("/ui-tables")
    public List<UiTable> getAllUiTables() {
        log.debug("REST request to get all UiTables");
        return uiTableRepository.findAll();
    }

    /**
     * {@code GET  /ui-tables/:id} : get the "id" uiTable.
     *
     * @param id the id of the uiTable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uiTable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ui-tables/{id}")
    public ResponseEntity<UiTable> getUiTable(@PathVariable Long id) {
        log.debug("REST request to get UiTable : {}", id);
        Optional<UiTable> uiTable = uiTableRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(uiTable);
    }

    /**
     * {@code DELETE  /ui-tables/:id} : delete the "id" uiTable.
     *
     * @param id the id of the uiTable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ui-tables/{id}")
    public ResponseEntity<Void> deleteUiTable(@PathVariable Long id) {
        log.debug("REST request to delete UiTable : {}", id);
        uiTableRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
