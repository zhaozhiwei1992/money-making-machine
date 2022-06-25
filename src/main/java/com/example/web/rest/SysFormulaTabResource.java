package com.example.web.rest;

import com.example.domain.SysFormulaTab;
import com.example.repository.SysFormulaTabRepository;
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
 * REST controller for managing {@link com.example.domain.SysFormulaTab}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SysFormulaTabResource {

    private final Logger log = LoggerFactory.getLogger(SysFormulaTabResource.class);

    private static final String ENTITY_NAME = "sysFormulaTab";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysFormulaTabRepository sysFormulaTabRepository;

    public SysFormulaTabResource(SysFormulaTabRepository sysFormulaTabRepository) {
        this.sysFormulaTabRepository = sysFormulaTabRepository;
    }

    /**
     * {@code POST  /sys-formula-tabs} : Create a new sysFormulaTab.
     *
     * @param sysFormulaTab the sysFormulaTab to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysFormulaTab, or
     * with status {@code 400 (Bad Request)} if the sysFormulaTab has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-formula-tabs")
    public ResponseEntity<SysFormulaTab> createSysFormulaTab(@RequestBody SysFormulaTab sysFormulaTab) throws URISyntaxException {
        log.debug("REST request to save SysFormulaTab : {}", sysFormulaTab);
        //        if (sysFormulaTab.getId() != null) {
        //            throw new BadRequestAlertException("A new sysFormulaTab cannot already have an ID", ENTITY_NAME,
        //                "idexists");
        //        }
        SysFormulaTab result = sysFormulaTabRepository.save(sysFormulaTab);
        return ResponseEntity
            .created(new URI("/api/sys-formula-tabs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-formula-tabs/:id} : Updates an existing sysFormulaTab.
     *
     * @param id            the id of the sysFormulaTab to save.
     * @param sysFormulaTab the sysFormulaTab to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysFormulaTab,
     * or with status {@code 400 (Bad Request)} if the sysFormulaTab is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysFormulaTab couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-formula-tabs/{id}")
    public ResponseEntity<SysFormulaTab> updateSysFormulaTab(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysFormulaTab sysFormulaTab
    ) throws URISyntaxException {
        log.debug("REST request to update SysFormulaTab : {}, {}", id, sysFormulaTab);
        if (sysFormulaTab.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysFormulaTab.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysFormulaTabRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysFormulaTab result = sysFormulaTabRepository.save(sysFormulaTab);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysFormulaTab.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-formula-tabs/:id} : Partial updates given fields of an existing sysFormulaTab, field will
     * ignore if it is null
     *
     * @param id            the id of the sysFormulaTab to save.
     * @param sysFormulaTab the sysFormulaTab to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysFormulaTab,
     * or with status {@code 400 (Bad Request)} if the sysFormulaTab is not valid,
     * or with status {@code 404 (Not Found)} if the sysFormulaTab is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysFormulaTab couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-formula-tabs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysFormulaTab> partialUpdateSysFormulaTab(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysFormulaTab sysFormulaTab
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysFormulaTab partially : {}, {}", id, sysFormulaTab);
        if (sysFormulaTab.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysFormulaTab.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysFormulaTabRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysFormulaTab> result = sysFormulaTabRepository
            .findById(sysFormulaTab.getId())
            .map(existingSysFormulaTab -> {
                if (sysFormulaTab.getTabEname() != null) {
                    existingSysFormulaTab.setTabEname(sysFormulaTab.getTabEname());
                }
                if (sysFormulaTab.getColEname() != null) {
                    existingSysFormulaTab.setColEname(sysFormulaTab.getColEname());
                }
                if (sysFormulaTab.getCalFormula() != null) {
                    existingSysFormulaTab.setCalFormula(sysFormulaTab.getCalFormula());
                }
                if (sysFormulaTab.getCalFormulaDes() != null) {
                    existingSysFormulaTab.setCalFormulaDes(sysFormulaTab.getCalFormulaDes());
                }
                if (sysFormulaTab.getRoundType() != null) {
                    existingSysFormulaTab.setRoundType(sysFormulaTab.getRoundType());
                }
                if (sysFormulaTab.getWeight() != null) {
                    existingSysFormulaTab.setWeight(sysFormulaTab.getWeight());
                }
                if (sysFormulaTab.getEnable() != null) {
                    existingSysFormulaTab.setEnable(sysFormulaTab.getEnable());
                }

                return existingSysFormulaTab;
            })
            .map(sysFormulaTabRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysFormulaTab.getId().toString())
        );
    }

    /**
     * {@code GET  /sys-formula-tabs} : get all the sysFormulaTabs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysFormulaTabs in body.
     */
    @GetMapping("/sys-formula-tabs")
    public List<SysFormulaTab> getAllSysFormulaTabs() {
        log.debug("REST request to get all SysFormulaTabs");
        return sysFormulaTabRepository.findAll();
    }

    /**
     * {@code GET  /sys-formula-tabs/:id} : get the "id" sysFormulaTab.
     *
     * @param id the id of the sysFormulaTab to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysFormulaTab, or with
     * status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-formula-tabs/{id}")
    public ResponseEntity<SysFormulaTab> getSysFormulaTab(@PathVariable Long id) {
        log.debug("REST request to get SysFormulaTab : {}", id);
        Optional<SysFormulaTab> sysFormulaTab = sysFormulaTabRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysFormulaTab);
    }

    @GetMapping("/sys-formula-tabs/tab/{tabEname}/col/{colEname}")
    public SysFormulaTab getSysFormulaTabByTabAndCol(@PathVariable String tabEname, @PathVariable String colEname) {
        log.debug("REST request to get SysFormulaTab : {}, {}", tabEname, colEname);
        Optional<SysFormulaTab> sysFormulaTab = sysFormulaTabRepository.findByTabEnameAndColEname(tabEname, colEname);
        final SysFormulaTab sysFormulaTab1 = new SysFormulaTab();
        sysFormulaTab1.setCalFormulaDes("");
        sysFormulaTab1.setCalFormula("");
        return sysFormulaTab.orElse(sysFormulaTab1);
    }

    /**
     * {@code DELETE  /sys-formula-tabs/:id} : delete the "id" sysFormulaTab.
     *
     * @param id the id of the sysFormulaTab to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-formula-tabs/{id}")
    public ResponseEntity<Void> deleteSysFormulaTab(@PathVariable Long id) {
        log.debug("REST request to delete SysFormulaTab : {}", id);
        sysFormulaTabRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
