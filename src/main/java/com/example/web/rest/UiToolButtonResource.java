package com.example.web.rest;

import com.example.domain.UiToolButton;
import com.example.repository.UiToolButtonRepository;
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
 * REST controller for managing {@link com.example.domain.UiToolButton}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UiToolButtonResource {

    private final Logger log = LoggerFactory.getLogger(UiToolButtonResource.class);

    private static final String ENTITY_NAME = "uiToolButton";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UiToolButtonRepository uiToolButtonRepository;

    public UiToolButtonResource(UiToolButtonRepository uiToolButtonRepository) {
        this.uiToolButtonRepository = uiToolButtonRepository;
    }

    /**
     * {@code POST  /ui-tool-buttons} : Create a new uiToolButton.
     *
     * @param uiToolButton the uiToolButton to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uiToolButton, or with status {@code 400 (Bad Request)} if the uiToolButton has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ui-tool-buttons")
    public ResponseEntity<UiToolButton> createUiToolButton(@RequestBody UiToolButton uiToolButton) throws URISyntaxException {
        log.debug("REST request to save UiToolButton : {}", uiToolButton);
        if (uiToolButton.getId() != null) {
            throw new BadRequestAlertException("A new uiToolButton cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UiToolButton result = uiToolButtonRepository.save(uiToolButton);
        return ResponseEntity
            .created(new URI("/api/ui-tool-buttons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ui-tool-buttons/:id} : Updates an existing uiToolButton.
     *
     * @param id the id of the uiToolButton to save.
     * @param uiToolButton the uiToolButton to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uiToolButton,
     * or with status {@code 400 (Bad Request)} if the uiToolButton is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uiToolButton couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ui-tool-buttons/{id}")
    public ResponseEntity<UiToolButton> updateUiToolButton(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UiToolButton uiToolButton
    ) throws URISyntaxException {
        log.debug("REST request to update UiToolButton : {}, {}", id, uiToolButton);
        if (uiToolButton.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, uiToolButton.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uiToolButtonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UiToolButton result = uiToolButtonRepository.save(uiToolButton);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uiToolButton.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ui-tool-buttons/:id} : Partial updates given fields of an existing uiToolButton, field will ignore if it is null
     *
     * @param id the id of the uiToolButton to save.
     * @param uiToolButton the uiToolButton to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uiToolButton,
     * or with status {@code 400 (Bad Request)} if the uiToolButton is not valid,
     * or with status {@code 404 (Not Found)} if the uiToolButton is not found,
     * or with status {@code 500 (Internal Server Error)} if the uiToolButton couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ui-tool-buttons/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UiToolButton> partialUpdateUiToolButton(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UiToolButton uiToolButton
    ) throws URISyntaxException {
        log.debug("REST request to partial update UiToolButton partially : {}, {}", id, uiToolButton);
        if (uiToolButton.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, uiToolButton.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uiToolButtonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UiToolButton> result = uiToolButtonRepository
            .findById(uiToolButton.getId())
            .map(existingUiToolButton -> {
                if (uiToolButton.getMenuid() != null) {
                    existingUiToolButton.setMenuid(uiToolButton.getMenuid());
                }
                if (uiToolButton.getCode() != null) {
                    existingUiToolButton.setCode(uiToolButton.getCode());
                }
                if (uiToolButton.getName() != null) {
                    existingUiToolButton.setName(uiToolButton.getName());
                }
                if (uiToolButton.getOrdernum() != null) {
                    existingUiToolButton.setOrdernum(uiToolButton.getOrdernum());
                }
                if (uiToolButton.getAction() != null) {
                    existingUiToolButton.setAction(uiToolButton.getAction());
                }
                if (uiToolButton.getConfig() != null) {
                    existingUiToolButton.setConfig(uiToolButton.getConfig());
                }

                return existingUiToolButton;
            })
            .map(uiToolButtonRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uiToolButton.getId().toString())
        );
    }

    /**
     * {@code GET  /ui-tool-buttons} : get all the uiToolButtons.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uiToolButtons in body.
     */
    @GetMapping("/ui-tool-buttons")
    public List<UiToolButton> getAllUiToolButtons() {
        log.debug("REST request to get all UiToolButtons");
        return uiToolButtonRepository.findAll();
    }

    /**
     * {@code GET  /ui-tool-buttons/:id} : get the "id" uiToolButton.
     *
     * @param id the id of the uiToolButton to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uiToolButton, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ui-tool-buttons/{id}")
    public ResponseEntity<UiToolButton> getUiToolButton(@PathVariable Long id) {
        log.debug("REST request to get UiToolButton : {}", id);
        Optional<UiToolButton> uiToolButton = uiToolButtonRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(uiToolButton);
    }

    @GetMapping("/ui-tool-buttons/menu/{menuid}")
    public List<UiToolButton> getUiTableByMenuid(@PathVariable Long menuid) {
        log.debug("REST request to get UiToolButton by menu : {}", menuid);
        return uiToolButtonRepository.findByMenuid(menuid);
    }

    /**
     * {@code DELETE  /ui-tool-buttons/:id} : delete the "id" uiToolButton.
     *
     * @param id the id of the uiToolButton to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ui-tool-buttons/{id}")
    public ResponseEntity<Void> deleteUiToolButton(@PathVariable Long id) {
        log.debug("REST request to delete UiToolButton : {}", id);
        uiToolButtonRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
