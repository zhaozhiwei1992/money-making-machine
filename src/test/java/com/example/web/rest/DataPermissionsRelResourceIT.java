package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.DataPermissionsRel;
import com.example.repository.DataPermissionsRelRepository;
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
 * Integration tests for the {@link DataPermissionsRelResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class DataPermissionsRelResourceIT {

    private static final String DEFAULT_RULE_ID = "AAAAAAAAAA";
    private static final String UPDATED_RULE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MENU_ID = "AAAAAAAAAA";
    private static final String UPDATED_MENU_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/data-permissions-rels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DataPermissionsRelRepository dataPermissionsRelRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDataPermissionsRelMockMvc;

    private DataPermissionsRel dataPermissionsRel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataPermissionsRel createEntity(EntityManager em) {
        DataPermissionsRel dataPermissionsRel = new DataPermissionsRel()
            .ruleId(DEFAULT_RULE_ID)
            .roleId(DEFAULT_ROLE_ID)
            .menuId(DEFAULT_MENU_ID);
        return dataPermissionsRel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataPermissionsRel createUpdatedEntity(EntityManager em) {
        DataPermissionsRel dataPermissionsRel = new DataPermissionsRel()
            .ruleId(UPDATED_RULE_ID)
            .roleId(UPDATED_ROLE_ID)
            .menuId(UPDATED_MENU_ID);
        return dataPermissionsRel;
    }

    @BeforeEach
    public void initTest() {
        dataPermissionsRel = createEntity(em);
    }

    @Test
    @Transactional
    void createDataPermissionsRel() throws Exception {
        int databaseSizeBeforeCreate = dataPermissionsRelRepository.findAll().size();
        // Create the DataPermissionsRel
        restDataPermissionsRelMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dataPermissionsRel))
            )
            .andExpect(status().isCreated());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeCreate + 1);
        DataPermissionsRel testDataPermissionsRel = dataPermissionsRelList.get(dataPermissionsRelList.size() - 1);
        assertThat(testDataPermissionsRel.getRuleId()).isEqualTo(DEFAULT_RULE_ID);
        assertThat(testDataPermissionsRel.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testDataPermissionsRel.getMenuId()).isEqualTo(DEFAULT_MENU_ID);
    }

    @Test
    @Transactional
    void createDataPermissionsRelWithExistingId() throws Exception {
        // Create the DataPermissionsRel with an existing ID
        dataPermissionsRel.setId(1L);

        int databaseSizeBeforeCreate = dataPermissionsRelRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataPermissionsRelMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dataPermissionsRel))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDataPermissionsRels() throws Exception {
        // Initialize the database
        dataPermissionsRelRepository.saveAndFlush(dataPermissionsRel);

        // Get all the dataPermissionsRelList
        restDataPermissionsRelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataPermissionsRel.getId().intValue())))
            .andExpect(jsonPath("$.[*].ruleId").value(hasItem(DEFAULT_RULE_ID)))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID)))
            .andExpect(jsonPath("$.[*].menuId").value(hasItem(DEFAULT_MENU_ID)));
    }

    @Test
    @Transactional
    void getDataPermissionsRel() throws Exception {
        // Initialize the database
        dataPermissionsRelRepository.saveAndFlush(dataPermissionsRel);

        // Get the dataPermissionsRel
        restDataPermissionsRelMockMvc
            .perform(get(ENTITY_API_URL_ID, dataPermissionsRel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataPermissionsRel.getId().intValue()))
            .andExpect(jsonPath("$.ruleId").value(DEFAULT_RULE_ID))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID))
            .andExpect(jsonPath("$.menuId").value(DEFAULT_MENU_ID));
    }

    @Test
    @Transactional
    void getNonExistingDataPermissionsRel() throws Exception {
        // Get the dataPermissionsRel
        restDataPermissionsRelMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDataPermissionsRel() throws Exception {
        // Initialize the database
        dataPermissionsRelRepository.saveAndFlush(dataPermissionsRel);

        int databaseSizeBeforeUpdate = dataPermissionsRelRepository.findAll().size();

        // Update the dataPermissionsRel
        DataPermissionsRel updatedDataPermissionsRel = dataPermissionsRelRepository.findById(dataPermissionsRel.getId()).get();
        // Disconnect from session so that the updates on updatedDataPermissionsRel are not directly saved in db
        em.detach(updatedDataPermissionsRel);
        updatedDataPermissionsRel.ruleId(UPDATED_RULE_ID).roleId(UPDATED_ROLE_ID).menuId(UPDATED_MENU_ID);

        restDataPermissionsRelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDataPermissionsRel.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDataPermissionsRel))
            )
            .andExpect(status().isOk());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeUpdate);
        DataPermissionsRel testDataPermissionsRel = dataPermissionsRelList.get(dataPermissionsRelList.size() - 1);
        assertThat(testDataPermissionsRel.getRuleId()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testDataPermissionsRel.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testDataPermissionsRel.getMenuId()).isEqualTo(UPDATED_MENU_ID);
    }

    @Test
    @Transactional
    void putNonExistingDataPermissionsRel() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionsRelRepository.findAll().size();
        dataPermissionsRel.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataPermissionsRelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dataPermissionsRel.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionsRel))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDataPermissionsRel() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionsRelRepository.findAll().size();
        dataPermissionsRel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionsRelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionsRel))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDataPermissionsRel() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionsRelRepository.findAll().size();
        dataPermissionsRel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionsRelMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dataPermissionsRel))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDataPermissionsRelWithPatch() throws Exception {
        // Initialize the database
        dataPermissionsRelRepository.saveAndFlush(dataPermissionsRel);

        int databaseSizeBeforeUpdate = dataPermissionsRelRepository.findAll().size();

        // Update the dataPermissionsRel using partial update
        DataPermissionsRel partialUpdatedDataPermissionsRel = new DataPermissionsRel();
        partialUpdatedDataPermissionsRel.setId(dataPermissionsRel.getId());

        partialUpdatedDataPermissionsRel.ruleId(UPDATED_RULE_ID).menuId(UPDATED_MENU_ID);

        restDataPermissionsRelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDataPermissionsRel.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDataPermissionsRel))
            )
            .andExpect(status().isOk());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeUpdate);
        DataPermissionsRel testDataPermissionsRel = dataPermissionsRelList.get(dataPermissionsRelList.size() - 1);
        assertThat(testDataPermissionsRel.getRuleId()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testDataPermissionsRel.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testDataPermissionsRel.getMenuId()).isEqualTo(UPDATED_MENU_ID);
    }

    @Test
    @Transactional
    void fullUpdateDataPermissionsRelWithPatch() throws Exception {
        // Initialize the database
        dataPermissionsRelRepository.saveAndFlush(dataPermissionsRel);

        int databaseSizeBeforeUpdate = dataPermissionsRelRepository.findAll().size();

        // Update the dataPermissionsRel using partial update
        DataPermissionsRel partialUpdatedDataPermissionsRel = new DataPermissionsRel();
        partialUpdatedDataPermissionsRel.setId(dataPermissionsRel.getId());

        partialUpdatedDataPermissionsRel.ruleId(UPDATED_RULE_ID).roleId(UPDATED_ROLE_ID).menuId(UPDATED_MENU_ID);

        restDataPermissionsRelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDataPermissionsRel.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDataPermissionsRel))
            )
            .andExpect(status().isOk());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeUpdate);
        DataPermissionsRel testDataPermissionsRel = dataPermissionsRelList.get(dataPermissionsRelList.size() - 1);
        assertThat(testDataPermissionsRel.getRuleId()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testDataPermissionsRel.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testDataPermissionsRel.getMenuId()).isEqualTo(UPDATED_MENU_ID);
    }

    @Test
    @Transactional
    void patchNonExistingDataPermissionsRel() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionsRelRepository.findAll().size();
        dataPermissionsRel.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataPermissionsRelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dataPermissionsRel.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionsRel))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDataPermissionsRel() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionsRelRepository.findAll().size();
        dataPermissionsRel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionsRelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionsRel))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDataPermissionsRel() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionsRelRepository.findAll().size();
        dataPermissionsRel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionsRelMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionsRel))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DataPermissionsRel in the database
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDataPermissionsRel() throws Exception {
        // Initialize the database
        dataPermissionsRelRepository.saveAndFlush(dataPermissionsRel);

        int databaseSizeBeforeDelete = dataPermissionsRelRepository.findAll().size();

        // Delete the dataPermissionsRel
        restDataPermissionsRelMockMvc
            .perform(delete(ENTITY_API_URL_ID, dataPermissionsRel.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataPermissionsRel> dataPermissionsRelList = dataPermissionsRelRepository.findAll();
        assertThat(dataPermissionsRelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
