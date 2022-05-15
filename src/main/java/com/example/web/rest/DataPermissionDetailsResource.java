package com.example.web.rest;

import com.example.domain.DataPermissionDetails;
import com.example.repository.DataPermissionDetailsRepository;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.example.domain.DataPermissionDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DataPermissionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(DataPermissionDetailsResource.class);

    private static final String ENTITY_NAME = "dataPermissionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataPermissionDetailsRepository dataPermissionDetailsRepository;

    public DataPermissionDetailsResource(DataPermissionDetailsRepository dataPermissionDetailsRepository) {
        this.dataPermissionDetailsRepository = dataPermissionDetailsRepository;
    }

    /**
     * {@code POST  /data-permission-details} : Create a new dataPermissionDetails.
     *
     * @param dataPermissionDetails the dataPermissionDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataPermissionDetails, or with status {@code 400 (Bad Request)} if the dataPermissionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-permission-details")
    public ResponseEntity<DataPermissionDetails> createDataPermissionDetails(
        @Valid @RequestBody DataPermissionDetails dataPermissionDetails
    ) throws URISyntaxException {
        log.debug("REST request to save DataPermissionDetails : {}", dataPermissionDetails);
        if (dataPermissionDetails.getId() != null) {
            throw new BadRequestAlertException("A new dataPermissionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataPermissionDetails result = dataPermissionDetailsRepository.save(dataPermissionDetails);
        return ResponseEntity
            .created(new URI("/api/data-permission-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-permission-details/:id} : Updates an existing dataPermissionDetails.
     *
     * @param id the id of the dataPermissionDetails to save.
     * @param dataPermissionDetails the dataPermissionDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataPermissionDetails,
     * or with status {@code 400 (Bad Request)} if the dataPermissionDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataPermissionDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-permission-details/{id}")
    public ResponseEntity<DataPermissionDetails> updateDataPermissionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DataPermissionDetails dataPermissionDetails
    ) throws URISyntaxException {
        log.debug("REST request to update DataPermissionDetails : {}, {}", id, dataPermissionDetails);
        if (dataPermissionDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dataPermissionDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dataPermissionDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DataPermissionDetails result = dataPermissionDetailsRepository.save(dataPermissionDetails);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataPermissionDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /data-permission-details/:id} : Partial updates given fields of an existing dataPermissionDetails, field will ignore if it is null
     *
     * @param id the id of the dataPermissionDetails to save.
     * @param dataPermissionDetails the dataPermissionDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataPermissionDetails,
     * or with status {@code 400 (Bad Request)} if the dataPermissionDetails is not valid,
     * or with status {@code 404 (Not Found)} if the dataPermissionDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the dataPermissionDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/data-permission-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DataPermissionDetails> partialUpdateDataPermissionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DataPermissionDetails dataPermissionDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update DataPermissionDetails partially : {}, {}", id, dataPermissionDetails);
        if (dataPermissionDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dataPermissionDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dataPermissionDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DataPermissionDetails> result = dataPermissionDetailsRepository
            .findById(dataPermissionDetails.getId())
            .map(existingDataPermissionDetails -> {
                if (dataPermissionDetails.getRuleId() != null) {
                    existingDataPermissionDetails.setRuleId(dataPermissionDetails.getRuleId());
                }
                if (dataPermissionDetails.getLeftBracket() != null) {
                    existingDataPermissionDetails.setLeftBracket(dataPermissionDetails.getLeftBracket());
                }
                if (dataPermissionDetails.getColumn() != null) {
                    existingDataPermissionDetails.setColumn(dataPermissionDetails.getColumn());
                }
                if (dataPermissionDetails.getOp() != null) {
                    existingDataPermissionDetails.setOp(dataPermissionDetails.getOp());
                }
                if (dataPermissionDetails.getValue() != null) {
                    existingDataPermissionDetails.setValue(dataPermissionDetails.getValue());
                }
                if (dataPermissionDetails.getRightBracket() != null) {
                    existingDataPermissionDetails.setRightBracket(dataPermissionDetails.getRightBracket());
                }
                if (dataPermissionDetails.getOrdernum() != null) {
                    existingDataPermissionDetails.setOrdernum(dataPermissionDetails.getOrdernum());
                }
                if (dataPermissionDetails.getLogicalRel() != null) {
                    existingDataPermissionDetails.setLogicalRel(dataPermissionDetails.getLogicalRel());
                }

                return existingDataPermissionDetails;
            })
            .map(dataPermissionDetailsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataPermissionDetails.getId().toString())
        );
    }

    /**
     * {@code GET  /data-permission-details} : get all the dataPermissionDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataPermissionDetails in body.
     */
    @GetMapping("/data-permission-details")
    public ResponseEntity<List<DataPermissionDetails>> getAllDataPermissionDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DataPermissionDetails");
        Page<DataPermissionDetails> page = dataPermissionDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-permission-details/:id} : get the "id" dataPermissionDetails.
     *
     * @param id the id of the dataPermissionDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataPermissionDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-permission-details/{id}")
    public ResponseEntity<DataPermissionDetails> getDataPermissionDetails(@PathVariable Long id) {
        log.debug("REST request to get DataPermissionDetails : {}", id);
        Optional<DataPermissionDetails> dataPermissionDetails = dataPermissionDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dataPermissionDetails);
    }

    /**
     * {@code DELETE  /data-permission-details/:id} : delete the "id" dataPermissionDetails.
     *
     * @param id the id of the dataPermissionDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-permission-details/{id}")
    public ResponseEntity<Void> deleteDataPermissionDetails(@PathVariable Long id) {
        log.debug("REST request to delete DataPermissionDetails : {}", id);
        dataPermissionDetailsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
