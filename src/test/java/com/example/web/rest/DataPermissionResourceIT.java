package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.DataPermission;
import com.example.repository.DataPermissionRepository;
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
 * Integration tests for the {@link DataPermissionResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class DataPermissionResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RULE_SQL = "AAAAAAAAAA";
    private static final String UPDATED_RULE_SQL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/data-permissions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DataPermissionRepository dataPermissionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDataPermissionMockMvc;

    private DataPermission dataPermission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataPermission createEntity(EntityManager em) {
        DataPermission dataPermission = new DataPermission().code(DEFAULT_CODE).name(DEFAULT_NAME).ruleSql(DEFAULT_RULE_SQL);
        return dataPermission;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataPermission createUpdatedEntity(EntityManager em) {
        DataPermission dataPermission = new DataPermission().code(UPDATED_CODE).name(UPDATED_NAME).ruleSql(UPDATED_RULE_SQL);
        return dataPermission;
    }

    @BeforeEach
    public void initTest() {
        dataPermission = createEntity(em);
    }

    @Test
    @Transactional
    void createDataPermission() throws Exception {
        int databaseSizeBeforeCreate = dataPermissionRepository.findAll().size();
        // Create the DataPermission
        restDataPermissionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dataPermission))
            )
            .andExpect(status().isCreated());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeCreate + 1);
        DataPermission testDataPermission = dataPermissionList.get(dataPermissionList.size() - 1);
        assertThat(testDataPermission.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDataPermission.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDataPermission.getRuleSql()).isEqualTo(DEFAULT_RULE_SQL);
    }

    @Test
    @Transactional
    void createDataPermissionWithExistingId() throws Exception {
        // Create the DataPermission with an existing ID
        dataPermission.setId(1L);

        int databaseSizeBeforeCreate = dataPermissionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataPermissionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dataPermission))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDataPermissions() throws Exception {
        // Initialize the database
        dataPermissionRepository.saveAndFlush(dataPermission);

        // Get all the dataPermissionList
        restDataPermissionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataPermission.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].ruleSql").value(hasItem(DEFAULT_RULE_SQL)));
    }

    @Test
    @Transactional
    void getDataPermission() throws Exception {
        // Initialize the database
        dataPermissionRepository.saveAndFlush(dataPermission);

        // Get the dataPermission
        restDataPermissionMockMvc
            .perform(get(ENTITY_API_URL_ID, dataPermission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataPermission.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.ruleSql").value(DEFAULT_RULE_SQL));
    }

    @Test
    @Transactional
    void getNonExistingDataPermission() throws Exception {
        // Get the dataPermission
        restDataPermissionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDataPermission() throws Exception {
        // Initialize the database
        dataPermissionRepository.saveAndFlush(dataPermission);

        int databaseSizeBeforeUpdate = dataPermissionRepository.findAll().size();

        // Update the dataPermission
        DataPermission updatedDataPermission = dataPermissionRepository.findById(dataPermission.getId()).get();
        // Disconnect from session so that the updates on updatedDataPermission are not directly saved in db
        em.detach(updatedDataPermission);
        updatedDataPermission.code(UPDATED_CODE).name(UPDATED_NAME).ruleSql(UPDATED_RULE_SQL);

        restDataPermissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDataPermission.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDataPermission))
            )
            .andExpect(status().isOk());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeUpdate);
        DataPermission testDataPermission = dataPermissionList.get(dataPermissionList.size() - 1);
        assertThat(testDataPermission.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDataPermission.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDataPermission.getRuleSql()).isEqualTo(UPDATED_RULE_SQL);
    }

    @Test
    @Transactional
    void putNonExistingDataPermission() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionRepository.findAll().size();
        dataPermission.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataPermissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dataPermission.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dataPermission))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDataPermission() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionRepository.findAll().size();
        dataPermission.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dataPermission))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDataPermission() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionRepository.findAll().size();
        dataPermission.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dataPermission)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDataPermissionWithPatch() throws Exception {
        // Initialize the database
        dataPermissionRepository.saveAndFlush(dataPermission);

        int databaseSizeBeforeUpdate = dataPermissionRepository.findAll().size();

        // Update the dataPermission using partial update
        DataPermission partialUpdatedDataPermission = new DataPermission();
        partialUpdatedDataPermission.setId(dataPermission.getId());

        partialUpdatedDataPermission.code(UPDATED_CODE);

        restDataPermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDataPermission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDataPermission))
            )
            .andExpect(status().isOk());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeUpdate);
        DataPermission testDataPermission = dataPermissionList.get(dataPermissionList.size() - 1);
        assertThat(testDataPermission.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDataPermission.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDataPermission.getRuleSql()).isEqualTo(DEFAULT_RULE_SQL);
    }

    @Test
    @Transactional
    void fullUpdateDataPermissionWithPatch() throws Exception {
        // Initialize the database
        dataPermissionRepository.saveAndFlush(dataPermission);

        int databaseSizeBeforeUpdate = dataPermissionRepository.findAll().size();

        // Update the dataPermission using partial update
        DataPermission partialUpdatedDataPermission = new DataPermission();
        partialUpdatedDataPermission.setId(dataPermission.getId());

        partialUpdatedDataPermission.code(UPDATED_CODE).name(UPDATED_NAME).ruleSql(UPDATED_RULE_SQL);

        restDataPermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDataPermission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDataPermission))
            )
            .andExpect(status().isOk());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeUpdate);
        DataPermission testDataPermission = dataPermissionList.get(dataPermissionList.size() - 1);
        assertThat(testDataPermission.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDataPermission.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDataPermission.getRuleSql()).isEqualTo(UPDATED_RULE_SQL);
    }

    @Test
    @Transactional
    void patchNonExistingDataPermission() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionRepository.findAll().size();
        dataPermission.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataPermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dataPermission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dataPermission))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDataPermission() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionRepository.findAll().size();
        dataPermission.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dataPermission))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDataPermission() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionRepository.findAll().size();
        dataPermission.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(dataPermission))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DataPermission in the database
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDataPermission() throws Exception {
        // Initialize the database
        dataPermissionRepository.saveAndFlush(dataPermission);

        int databaseSizeBeforeDelete = dataPermissionRepository.findAll().size();

        // Delete the dataPermission
        restDataPermissionMockMvc
            .perform(delete(ENTITY_API_URL_ID, dataPermission.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataPermission> dataPermissionList = dataPermissionRepository.findAll();
        assertThat(dataPermissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
