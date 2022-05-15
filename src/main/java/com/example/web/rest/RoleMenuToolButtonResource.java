package com.example.web.rest;

import com.example.domain.RoleMenuToolButton;
import com.example.repository.RoleMenuToolButtonRepository;
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
 * REST controller for managing {@link com.example.domain.RoleMenuToolButton}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RoleMenuToolButtonResource {

    private final Logger log = LoggerFactory.getLogger(RoleMenuToolButtonResource.class);

    private static final String ENTITY_NAME = "roleMenuToolButton";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoleMenuToolButtonRepository roleMenuToolButtonRepository;

    public RoleMenuToolButtonResource(RoleMenuToolButtonRepository roleMenuToolButtonRepository) {
        this.roleMenuToolButtonRepository = roleMenuToolButtonRepository;
    }

    /**
     * {@code POST  /role-menu-tool-buttons} : Create a new roleMenuToolButton.
     *
     * @param roleMenuToolButton the roleMenuToolButton to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleMenuToolButton, or with status {@code 400 (Bad Request)} if the roleMenuToolButton has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/role-menu-tool-buttons")
    public ResponseEntity<RoleMenuToolButton> createRoleMenuToolButton(@RequestBody RoleMenuToolButton roleMenuToolButton)
        throws URISyntaxException {
        log.debug("REST request to save RoleMenuToolButton : {}", roleMenuToolButton);
        if (roleMenuToolButton.getId() != null) {
            throw new BadRequestAlertException("A new roleMenuToolButton cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleMenuToolButton result = roleMenuToolButtonRepository.save(roleMenuToolButton);
        return ResponseEntity
            .created(new URI("/api/role-menu-tool-buttons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /role-menu-tool-buttons/:id} : Updates an existing roleMenuToolButton.
     *
     * @param id the id of the roleMenuToolButton to save.
     * @param roleMenuToolButton the roleMenuToolButton to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleMenuToolButton,
     * or with status {@code 400 (Bad Request)} if the roleMenuToolButton is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roleMenuToolButton couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/role-menu-tool-buttons/{id}")
    public ResponseEntity<RoleMenuToolButton> updateRoleMenuToolButton(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RoleMenuToolButton roleMenuToolButton
    ) throws URISyntaxException {
        log.debug("REST request to update RoleMenuToolButton : {}, {}", id, roleMenuToolButton);
        if (roleMenuToolButton.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roleMenuToolButton.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleMenuToolButtonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RoleMenuToolButton result = roleMenuToolButtonRepository.save(roleMenuToolButton);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roleMenuToolButton.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /role-menu-tool-buttons/:id} : Partial updates given fields of an existing roleMenuToolButton, field will ignore if it is null
     *
     * @param id the id of the roleMenuToolButton to save.
     * @param roleMenuToolButton the roleMenuToolButton to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleMenuToolButton,
     * or with status {@code 400 (Bad Request)} if the roleMenuToolButton is not valid,
     * or with status {@code 404 (Not Found)} if the roleMenuToolButton is not found,
     * or with status {@code 500 (Internal Server Error)} if the roleMenuToolButton couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/role-menu-tool-buttons/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RoleMenuToolButton> partialUpdateRoleMenuToolButton(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RoleMenuToolButton roleMenuToolButton
    ) throws URISyntaxException {
        log.debug("REST request to partial update RoleMenuToolButton partially : {}, {}", id, roleMenuToolButton);
        if (roleMenuToolButton.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roleMenuToolButton.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roleMenuToolButtonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RoleMenuToolButton> result = roleMenuToolButtonRepository
            .findById(roleMenuToolButton.getId())
            .map(existingRoleMenuToolButton -> {
                if (roleMenuToolButton.getRoleId() != null) {
                    existingRoleMenuToolButton.setRoleId(roleMenuToolButton.getRoleId());
                }
                if (roleMenuToolButton.getMenuId() != null) {
                    existingRoleMenuToolButton.setMenuId(roleMenuToolButton.getMenuId());
                }
                if (roleMenuToolButton.getToolButtonId() != null) {
                    existingRoleMenuToolButton.setToolButtonId(roleMenuToolButton.getToolButtonId());
                }

                return existingRoleMenuToolButton;
            })
            .map(roleMenuToolButtonRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roleMenuToolButton.getId().toString())
        );
    }

    /**
     * {@code GET  /role-menu-tool-buttons} : get all the roleMenuToolButtons.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roleMenuToolButtons in body.
     */
    @GetMapping("/role-menu-tool-buttons")
    public List<RoleMenuToolButton> getAllRoleMenuToolButtons() {
        log.debug("REST request to get all RoleMenuToolButtons");
        return roleMenuToolButtonRepository.findAll();
    }

    /**
     * {@code GET  /role-menu-tool-buttons/:id} : get the "id" roleMenuToolButton.
     *
     * @param id the id of the roleMenuToolButton to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleMenuToolButton, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/role-menu-tool-buttons/{id}")
    public ResponseEntity<RoleMenuToolButton> getRoleMenuToolButton(@PathVariable Long id) {
        log.debug("REST request to get RoleMenuToolButton : {}", id);
        Optional<RoleMenuToolButton> roleMenuToolButton = roleMenuToolButtonRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(roleMenuToolButton);
    }

    /**
     * {@code DELETE  /role-menu-tool-buttons/:id} : delete the "id" roleMenuToolButton.
     *
     * @param id the id of the roleMenuToolButton to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/role-menu-tool-buttons/{id}")
    public ResponseEntity<Void> deleteRoleMenuToolButton(@PathVariable Long id) {
        log.debug("REST request to delete RoleMenuToolButton : {}", id);
        roleMenuToolButtonRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
