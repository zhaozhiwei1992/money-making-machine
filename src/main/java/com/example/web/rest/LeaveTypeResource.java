package com.example.web.rest;

import com.example.domain.LeaveType;
import com.example.repository.LeaveTypeRepository;
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
 * REST controller for managing {@link com.example.domain.LeaveType}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LeaveTypeResource {

    private final Logger log = LoggerFactory.getLogger(LeaveTypeResource.class);

    private static final String ENTITY_NAME = "leaveType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveTypeResource(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    /**
     * {@code POST  /leave-types} : Create a new leaveType.
     *
     * @param leaveType the leaveType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leaveType, or with status {@code 400 (Bad Request)} if the leaveType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/leave-types")
    public ResponseEntity<LeaveType> createLeaveType(@RequestBody LeaveType leaveType) throws URISyntaxException {
        log.debug("REST request to save LeaveType : {}", leaveType);
        if (leaveType.getId() != null) {
            throw new BadRequestAlertException("A new leaveType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LeaveType result = leaveTypeRepository.save(leaveType);
        return ResponseEntity
            .created(new URI("/api/leave-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /leave-types/:id} : Updates an existing leaveType.
     *
     * @param id the id of the leaveType to save.
     * @param leaveType the leaveType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaveType,
     * or with status {@code 400 (Bad Request)} if the leaveType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leaveType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/leave-types/{id}")
    public ResponseEntity<LeaveType> updateLeaveType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LeaveType leaveType
    ) throws URISyntaxException {
        log.debug("REST request to update LeaveType : {}, {}", id, leaveType);
        if (leaveType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leaveType.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leaveTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LeaveType result = leaveTypeRepository.save(leaveType);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaveType.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /leave-types/:id} : Partial updates given fields of an existing leaveType, field will ignore if it is null
     *
     * @param id the id of the leaveType to save.
     * @param leaveType the leaveType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaveType,
     * or with status {@code 400 (Bad Request)} if the leaveType is not valid,
     * or with status {@code 404 (Not Found)} if the leaveType is not found,
     * or with status {@code 500 (Internal Server Error)} if the leaveType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/leave-types/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LeaveType> partialUpdateLeaveType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LeaveType leaveType
    ) throws URISyntaxException {
        log.debug("REST request to partial update LeaveType partially : {}, {}", id, leaveType);
        if (leaveType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leaveType.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leaveTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LeaveType> result = leaveTypeRepository
            .findById(leaveType.getId())
            .map(existingLeaveType -> {
                if (leaveType.getCode() != null) {
                    existingLeaveType.setCode(leaveType.getCode());
                }
                if (leaveType.getName() != null) {
                    existingLeaveType.setName(leaveType.getName());
                }
                if (leaveType.getParentid() != null) {
                    existingLeaveType.setParentid(leaveType.getParentid());
                }
                if (leaveType.getEnabled() != null) {
                    existingLeaveType.setEnabled(leaveType.getEnabled());
                }

                return existingLeaveType;
            })
            .map(leaveTypeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaveType.getId().toString())
        );
    }

    /**
     * {@code GET  /leave-types} : get all the leaveTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leaveTypes in body.
     */
    @GetMapping("/leave-types")
    public List<LeaveType> getAllLeaveTypes() {
        log.debug("REST request to get all LeaveTypes");
        return leaveTypeRepository.findAll();
    }

    /**
     * {@code GET  /leave-types/:id} : get the "id" leaveType.
     *
     * @param id the id of the leaveType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leaveType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/leave-types/{id}")
    public ResponseEntity<LeaveType> getLeaveType(@PathVariable Long id) {
        log.debug("REST request to get LeaveType : {}", id);
        Optional<LeaveType> leaveType = leaveTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(leaveType);
    }

    /**
     * {@code DELETE  /leave-types/:id} : delete the "id" leaveType.
     *
     * @param id the id of the leaveType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/leave-types/{id}")
    public ResponseEntity<Void> deleteLeaveType(@PathVariable Long id) {
        log.debug("REST request to delete LeaveType : {}", id);
        leaveTypeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
