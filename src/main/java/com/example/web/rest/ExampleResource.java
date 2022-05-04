package com.example.web.rest;

import com.example.domain.Example;
import com.example.repository.ExampleRepository;
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
 * REST controller for managing {@link com.example.domain.Example}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ExampleResource {

    private final Logger log = LoggerFactory.getLogger(ExampleResource.class);

    private static final String ENTITY_NAME = "example";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExampleRepository exampleRepository;

    public ExampleResource(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    /**
     * {@code POST  /examples} : Create a new example.
     *
     * @param example the example to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new example, or with status {@code 400 (Bad Request)} if the example has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/examples")
    public ResponseEntity<Example> createExample(@RequestBody Example example) throws URISyntaxException {
        log.debug("REST request to save Example : {}", example);
        if (example.getId() != null) {
            throw new BadRequestAlertException("A new example cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Example result = exampleRepository.save(example);
        return ResponseEntity
            .created(new URI("/api/examples/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /examples/:id} : Updates an existing example.
     *
     * @param id the id of the example to save.
     * @param example the example to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated example,
     * or with status {@code 400 (Bad Request)} if the example is not valid,
     * or with status {@code 500 (Internal Server Error)} if the example couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/examples/{id}")
    public ResponseEntity<Example> updateExample(@PathVariable(value = "id", required = false) final Long id, @RequestBody Example example)
        throws URISyntaxException {
        log.debug("REST request to update Example : {}, {}", id, example);
        if (example.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, example.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exampleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Example result = exampleRepository.save(example);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, example.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /examples/:id} : Partial updates given fields of an existing example, field will ignore if it is null
     *
     * @param id the id of the example to save.
     * @param example the example to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated example,
     * or with status {@code 400 (Bad Request)} if the example is not valid,
     * or with status {@code 404 (Not Found)} if the example is not found,
     * or with status {@code 500 (Internal Server Error)} if the example couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/examples/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Example> partialUpdateExample(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Example example
    ) throws URISyntaxException {
        log.debug("REST request to partial update Example partially : {}, {}", id, example);
        if (example.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, example.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exampleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Example> result = exampleRepository
            .findById(example.getId())
            .map(existingExample -> {
                if (example.getName() != null) {
                    existingExample.setName(example.getName());
                }
                if (example.getAge() != null) {
                    existingExample.setAge(example.getAge());
                }
                if (example.getSex() != null) {
                    existingExample.setSex(example.getSex());
                }

                return existingExample;
            })
            .map(exampleRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, example.getId().toString())
        );
    }

    /**
     * {@code GET  /examples} : get all the examples.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examples in body.
     */
    @GetMapping("/examples")
    public List<Example> getAllExamples() {
        log.debug("REST request to get all Examples");
        return exampleRepository.findAll();
    }

    /**
     * {@code GET  /examples/:id} : get the "id" example.
     *
     * @param id the id of the example to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the example, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/examples/{id}")
    public ResponseEntity<Example> getExample(@PathVariable Long id) {
        log.debug("REST request to get Example : {}", id);
        Optional<Example> example = exampleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(example);
    }

    /**
     * {@code DELETE  /examples/:id} : delete the "id" example.
     *
     * @param id the id of the example to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/examples/{id}")
    public ResponseEntity<Void> deleteExample(@PathVariable Long id) {
        log.debug("REST request to delete Example : {}", id);
        exampleRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
