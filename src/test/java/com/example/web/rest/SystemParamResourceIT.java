package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.SystemParam;
import com.example.repository.SystemParamRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SystemParamResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class SystemParamResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLE = false;
    private static final Boolean UPDATED_ENABLE = true;

    private static final String ENTITY_API_URL = "/api/system-params";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SystemParamRepository systemParamRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSystemParamMockMvc;

    private SystemParam systemParam;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemParam createEntity(EntityManager em) {
        SystemParam systemParam = new SystemParam()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE)
            .remark(DEFAULT_REMARK)
            .enable(DEFAULT_ENABLE);
        return systemParam;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemParam createUpdatedEntity(EntityManager em) {
        SystemParam systemParam = new SystemParam()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .remark(UPDATED_REMARK)
            .enable(UPDATED_ENABLE);
        return systemParam;
    }

    @BeforeEach
    public void initTest() {
        systemParam = createEntity(em);
    }

    @Test
    @Transactional
    void createSystemParam() throws Exception {
        int databaseSizeBeforeCreate = systemParamRepository.findAll().size();
        // Create the SystemParam
        restSystemParamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(systemParam)))
            .andExpect(status().isCreated());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeCreate + 1);
        SystemParam testSystemParam = systemParamList.get(systemParamList.size() - 1);
        assertThat(testSystemParam.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSystemParam.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSystemParam.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testSystemParam.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testSystemParam.getEnable()).isEqualTo(DEFAULT_ENABLE);
    }

    @Test
    @Transactional
    void createSystemParamWithExistingId() throws Exception {
        // Create the SystemParam with an existing ID
        systemParam.setId(1L);

        int databaseSizeBeforeCreate = systemParamRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemParamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(systemParam)))
            .andExpect(status().isBadRequest());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSystemParams() throws Exception {
        // Initialize the database
        systemParamRepository.saveAndFlush(systemParam);

        // Get all the systemParamList
        restSystemParamMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemParam.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].enable").value(hasItem(DEFAULT_ENABLE.booleanValue())));
    }

    @Test
    @Transactional
    void getSystemParam() throws Exception {
        // Initialize the database
        systemParamRepository.saveAndFlush(systemParam);

        // Get the systemParam
        restSystemParamMockMvc
            .perform(get(ENTITY_API_URL_ID, systemParam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(systemParam.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.enable").value(DEFAULT_ENABLE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingSystemParam() throws Exception {
        // Get the systemParam
        restSystemParamMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSystemParam() throws Exception {
        // Initialize the database
        systemParamRepository.saveAndFlush(systemParam);

        int databaseSizeBeforeUpdate = systemParamRepository.findAll().size();

        // Update the systemParam
        SystemParam updatedSystemParam = systemParamRepository.findById(systemParam.getId()).get();
        // Disconnect from session so that the updates on updatedSystemParam are not directly saved in db
        em.detach(updatedSystemParam);
        updatedSystemParam.code(UPDATED_CODE).name(UPDATED_NAME).value(UPDATED_VALUE).remark(UPDATED_REMARK).enable(UPDATED_ENABLE);

        restSystemParamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSystemParam.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSystemParam))
            )
            .andExpect(status().isOk());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeUpdate);
        SystemParam testSystemParam = systemParamList.get(systemParamList.size() - 1);
        assertThat(testSystemParam.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSystemParam.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSystemParam.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSystemParam.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSystemParam.getEnable()).isEqualTo(UPDATED_ENABLE);
    }

    @Test
    @Transactional
    void putNonExistingSystemParam() throws Exception {
        int databaseSizeBeforeUpdate = systemParamRepository.findAll().size();
        systemParam.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemParamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, systemParam.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(systemParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSystemParam() throws Exception {
        int databaseSizeBeforeUpdate = systemParamRepository.findAll().size();
        systemParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSystemParamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(systemParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSystemParam() throws Exception {
        int databaseSizeBeforeUpdate = systemParamRepository.findAll().size();
        systemParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSystemParamMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(systemParam)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSystemParamWithPatch() throws Exception {
        // Initialize the database
        systemParamRepository.saveAndFlush(systemParam);

        int databaseSizeBeforeUpdate = systemParamRepository.findAll().size();

        // Update the systemParam using partial update
        SystemParam partialUpdatedSystemParam = new SystemParam();
        partialUpdatedSystemParam.setId(systemParam.getId());

        partialUpdatedSystemParam.code(UPDATED_CODE).remark(UPDATED_REMARK);

        restSystemParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSystemParam.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSystemParam))
            )
            .andExpect(status().isOk());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeUpdate);
        SystemParam testSystemParam = systemParamList.get(systemParamList.size() - 1);
        assertThat(testSystemParam.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSystemParam.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSystemParam.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testSystemParam.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSystemParam.getEnable()).isEqualTo(DEFAULT_ENABLE);
    }

    @Test
    @Transactional
    void fullUpdateSystemParamWithPatch() throws Exception {
        // Initialize the database
        systemParamRepository.saveAndFlush(systemParam);

        int databaseSizeBeforeUpdate = systemParamRepository.findAll().size();

        // Update the systemParam using partial update
        SystemParam partialUpdatedSystemParam = new SystemParam();
        partialUpdatedSystemParam.setId(systemParam.getId());

        partialUpdatedSystemParam.code(UPDATED_CODE).name(UPDATED_NAME).value(UPDATED_VALUE).remark(UPDATED_REMARK).enable(UPDATED_ENABLE);

        restSystemParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSystemParam.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSystemParam))
            )
            .andExpect(status().isOk());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeUpdate);
        SystemParam testSystemParam = systemParamList.get(systemParamList.size() - 1);
        assertThat(testSystemParam.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSystemParam.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSystemParam.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSystemParam.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSystemParam.getEnable()).isEqualTo(UPDATED_ENABLE);
    }

    @Test
    @Transactional
    void patchNonExistingSystemParam() throws Exception {
        int databaseSizeBeforeUpdate = systemParamRepository.findAll().size();
        systemParam.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, systemParam.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(systemParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSystemParam() throws Exception {
        int databaseSizeBeforeUpdate = systemParamRepository.findAll().size();
        systemParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSystemParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(systemParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSystemParam() throws Exception {
        int databaseSizeBeforeUpdate = systemParamRepository.findAll().size();
        systemParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSystemParamMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(systemParam))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SystemParam in the database
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSystemParam() throws Exception {
        // Initialize the database
        systemParamRepository.saveAndFlush(systemParam);

        int databaseSizeBeforeDelete = systemParamRepository.findAll().size();

        // Delete the systemParam
        restSystemParamMockMvc
            .perform(delete(ENTITY_API_URL_ID, systemParam.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SystemParam> systemParamList = systemParamRepository.findAll();
        assertThat(systemParamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
