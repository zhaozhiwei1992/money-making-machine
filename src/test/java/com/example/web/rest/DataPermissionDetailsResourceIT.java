package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.DataPermissionDetails;
import com.example.repository.DataPermissionDetailsRepository;
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
 * Integration tests for the {@link DataPermissionDetailsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class DataPermissionDetailsResourceIT {

    private static final String DEFAULT_RULE_ID = "AAAAAAAAAA";
    private static final String UPDATED_RULE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LEFT_BRACKET = "(";
    private static final String UPDATED_LEFT_BRACKET = "(B";

    private static final String DEFAULT_COLUMN = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN = "BBBBBBBBBB";

    private static final String DEFAULT_OP = "AAAAAAAAAA";
    private static final String UPDATED_OP = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_RIGHT_BRACKET = ")";
    private static final String UPDATED_RIGHT_BRACKET = ")B";

    private static final Integer DEFAULT_ORDERNUM = 1;
    private static final Integer UPDATED_ORDERNUM = 2;

    private static final String DEFAULT_LOGICAL_REL = "AAAAAAAAAA";
    private static final String UPDATED_LOGICAL_REL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/data-permission-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DataPermissionDetailsRepository dataPermissionDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDataPermissionDetailsMockMvc;

    private DataPermissionDetails dataPermissionDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataPermissionDetails createEntity(EntityManager em) {
        DataPermissionDetails dataPermissionDetails = new DataPermissionDetails()
            .ruleId(DEFAULT_RULE_ID)
            .leftBracket(DEFAULT_LEFT_BRACKET)
            .column(DEFAULT_COLUMN)
            .op(DEFAULT_OP)
            .value(DEFAULT_VALUE)
            .rightBracket(DEFAULT_RIGHT_BRACKET)
            .ordernum(DEFAULT_ORDERNUM)
            .logicalRel(DEFAULT_LOGICAL_REL);
        return dataPermissionDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataPermissionDetails createUpdatedEntity(EntityManager em) {
        DataPermissionDetails dataPermissionDetails = new DataPermissionDetails()
            .ruleId(UPDATED_RULE_ID)
            .leftBracket(UPDATED_LEFT_BRACKET)
            .column(UPDATED_COLUMN)
            .op(UPDATED_OP)
            .value(UPDATED_VALUE)
            .rightBracket(UPDATED_RIGHT_BRACKET)
            .ordernum(UPDATED_ORDERNUM)
            .logicalRel(UPDATED_LOGICAL_REL);
        return dataPermissionDetails;
    }

    @BeforeEach
    public void initTest() {
        dataPermissionDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createDataPermissionDetails() throws Exception {
        int databaseSizeBeforeCreate = dataPermissionDetailsRepository.findAll().size();
        // Create the DataPermissionDetails
        restDataPermissionDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionDetails))
            )
            .andExpect(status().isCreated());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        DataPermissionDetails testDataPermissionDetails = dataPermissionDetailsList.get(dataPermissionDetailsList.size() - 1);
        assertThat(testDataPermissionDetails.getRuleId()).isEqualTo(DEFAULT_RULE_ID);
        assertThat(testDataPermissionDetails.getLeftBracket()).isEqualTo(DEFAULT_LEFT_BRACKET);
        assertThat(testDataPermissionDetails.getColumn()).isEqualTo(DEFAULT_COLUMN);
        assertThat(testDataPermissionDetails.getOp()).isEqualTo(DEFAULT_OP);
        assertThat(testDataPermissionDetails.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testDataPermissionDetails.getRightBracket()).isEqualTo(DEFAULT_RIGHT_BRACKET);
        assertThat(testDataPermissionDetails.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
        assertThat(testDataPermissionDetails.getLogicalRel()).isEqualTo(DEFAULT_LOGICAL_REL);
    }

    @Test
    @Transactional
    void createDataPermissionDetailsWithExistingId() throws Exception {
        // Create the DataPermissionDetails with an existing ID
        dataPermissionDetails.setId(1L);

        int databaseSizeBeforeCreate = dataPermissionDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataPermissionDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDataPermissionDetails() throws Exception {
        // Initialize the database
        dataPermissionDetailsRepository.saveAndFlush(dataPermissionDetails);

        // Get all the dataPermissionDetailsList
        restDataPermissionDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataPermissionDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].ruleId").value(hasItem(DEFAULT_RULE_ID)))
            .andExpect(jsonPath("$.[*].leftBracket").value(hasItem(DEFAULT_LEFT_BRACKET)))
            .andExpect(jsonPath("$.[*].column").value(hasItem(DEFAULT_COLUMN)))
            .andExpect(jsonPath("$.[*].op").value(hasItem(DEFAULT_OP)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].rightBracket").value(hasItem(DEFAULT_RIGHT_BRACKET)))
            .andExpect(jsonPath("$.[*].ordernum").value(hasItem(DEFAULT_ORDERNUM)))
            .andExpect(jsonPath("$.[*].logicalRel").value(hasItem(DEFAULT_LOGICAL_REL)));
    }

    @Test
    @Transactional
    void getDataPermissionDetails() throws Exception {
        // Initialize the database
        dataPermissionDetailsRepository.saveAndFlush(dataPermissionDetails);

        // Get the dataPermissionDetails
        restDataPermissionDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, dataPermissionDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataPermissionDetails.getId().intValue()))
            .andExpect(jsonPath("$.ruleId").value(DEFAULT_RULE_ID))
            .andExpect(jsonPath("$.leftBracket").value(DEFAULT_LEFT_BRACKET))
            .andExpect(jsonPath("$.column").value(DEFAULT_COLUMN))
            .andExpect(jsonPath("$.op").value(DEFAULT_OP))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.rightBracket").value(DEFAULT_RIGHT_BRACKET))
            .andExpect(jsonPath("$.ordernum").value(DEFAULT_ORDERNUM))
            .andExpect(jsonPath("$.logicalRel").value(DEFAULT_LOGICAL_REL));
    }

    @Test
    @Transactional
    void getNonExistingDataPermissionDetails() throws Exception {
        // Get the dataPermissionDetails
        restDataPermissionDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDataPermissionDetails() throws Exception {
        // Initialize the database
        dataPermissionDetailsRepository.saveAndFlush(dataPermissionDetails);

        int databaseSizeBeforeUpdate = dataPermissionDetailsRepository.findAll().size();

        // Update the dataPermissionDetails
        DataPermissionDetails updatedDataPermissionDetails = dataPermissionDetailsRepository.findById(dataPermissionDetails.getId()).get();
        // Disconnect from session so that the updates on updatedDataPermissionDetails are not directly saved in db
        em.detach(updatedDataPermissionDetails);
        updatedDataPermissionDetails
            .ruleId(UPDATED_RULE_ID)
            .leftBracket(UPDATED_LEFT_BRACKET)
            .column(UPDATED_COLUMN)
            .op(UPDATED_OP)
            .value(UPDATED_VALUE)
            .rightBracket(UPDATED_RIGHT_BRACKET)
            .ordernum(UPDATED_ORDERNUM)
            .logicalRel(UPDATED_LOGICAL_REL);

        restDataPermissionDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDataPermissionDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDataPermissionDetails))
            )
            .andExpect(status().isOk());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeUpdate);
        DataPermissionDetails testDataPermissionDetails = dataPermissionDetailsList.get(dataPermissionDetailsList.size() - 1);
        assertThat(testDataPermissionDetails.getRuleId()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testDataPermissionDetails.getLeftBracket()).isEqualTo(UPDATED_LEFT_BRACKET);
        assertThat(testDataPermissionDetails.getColumn()).isEqualTo(UPDATED_COLUMN);
        assertThat(testDataPermissionDetails.getOp()).isEqualTo(UPDATED_OP);
        assertThat(testDataPermissionDetails.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testDataPermissionDetails.getRightBracket()).isEqualTo(UPDATED_RIGHT_BRACKET);
        assertThat(testDataPermissionDetails.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testDataPermissionDetails.getLogicalRel()).isEqualTo(UPDATED_LOGICAL_REL);
    }

    @Test
    @Transactional
    void putNonExistingDataPermissionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionDetailsRepository.findAll().size();
        dataPermissionDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataPermissionDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dataPermissionDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDataPermissionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionDetailsRepository.findAll().size();
        dataPermissionDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDataPermissionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionDetailsRepository.findAll().size();
        dataPermissionDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionDetails))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDataPermissionDetailsWithPatch() throws Exception {
        // Initialize the database
        dataPermissionDetailsRepository.saveAndFlush(dataPermissionDetails);

        int databaseSizeBeforeUpdate = dataPermissionDetailsRepository.findAll().size();

        // Update the dataPermissionDetails using partial update
        DataPermissionDetails partialUpdatedDataPermissionDetails = new DataPermissionDetails();
        partialUpdatedDataPermissionDetails.setId(dataPermissionDetails.getId());

        partialUpdatedDataPermissionDetails
            .ruleId(UPDATED_RULE_ID)
            .leftBracket(UPDATED_LEFT_BRACKET)
            .column(UPDATED_COLUMN)
            .op(UPDATED_OP)
            .rightBracket(UPDATED_RIGHT_BRACKET)
            .ordernum(UPDATED_ORDERNUM);

        restDataPermissionDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDataPermissionDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDataPermissionDetails))
            )
            .andExpect(status().isOk());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeUpdate);
        DataPermissionDetails testDataPermissionDetails = dataPermissionDetailsList.get(dataPermissionDetailsList.size() - 1);
        assertThat(testDataPermissionDetails.getRuleId()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testDataPermissionDetails.getLeftBracket()).isEqualTo(UPDATED_LEFT_BRACKET);
        assertThat(testDataPermissionDetails.getColumn()).isEqualTo(UPDATED_COLUMN);
        assertThat(testDataPermissionDetails.getOp()).isEqualTo(UPDATED_OP);
        assertThat(testDataPermissionDetails.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testDataPermissionDetails.getRightBracket()).isEqualTo(UPDATED_RIGHT_BRACKET);
        assertThat(testDataPermissionDetails.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testDataPermissionDetails.getLogicalRel()).isEqualTo(DEFAULT_LOGICAL_REL);
    }

    @Test
    @Transactional
    void fullUpdateDataPermissionDetailsWithPatch() throws Exception {
        // Initialize the database
        dataPermissionDetailsRepository.saveAndFlush(dataPermissionDetails);

        int databaseSizeBeforeUpdate = dataPermissionDetailsRepository.findAll().size();

        // Update the dataPermissionDetails using partial update
        DataPermissionDetails partialUpdatedDataPermissionDetails = new DataPermissionDetails();
        partialUpdatedDataPermissionDetails.setId(dataPermissionDetails.getId());

        partialUpdatedDataPermissionDetails
            .ruleId(UPDATED_RULE_ID)
            .leftBracket(UPDATED_LEFT_BRACKET)
            .column(UPDATED_COLUMN)
            .op(UPDATED_OP)
            .value(UPDATED_VALUE)
            .rightBracket(UPDATED_RIGHT_BRACKET)
            .ordernum(UPDATED_ORDERNUM)
            .logicalRel(UPDATED_LOGICAL_REL);

        restDataPermissionDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDataPermissionDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDataPermissionDetails))
            )
            .andExpect(status().isOk());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeUpdate);
        DataPermissionDetails testDataPermissionDetails = dataPermissionDetailsList.get(dataPermissionDetailsList.size() - 1);
        assertThat(testDataPermissionDetails.getRuleId()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testDataPermissionDetails.getLeftBracket()).isEqualTo(UPDATED_LEFT_BRACKET);
        assertThat(testDataPermissionDetails.getColumn()).isEqualTo(UPDATED_COLUMN);
        assertThat(testDataPermissionDetails.getOp()).isEqualTo(UPDATED_OP);
        assertThat(testDataPermissionDetails.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testDataPermissionDetails.getRightBracket()).isEqualTo(UPDATED_RIGHT_BRACKET);
        assertThat(testDataPermissionDetails.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testDataPermissionDetails.getLogicalRel()).isEqualTo(UPDATED_LOGICAL_REL);
    }

    @Test
    @Transactional
    void patchNonExistingDataPermissionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionDetailsRepository.findAll().size();
        dataPermissionDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataPermissionDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dataPermissionDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDataPermissionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionDetailsRepository.findAll().size();
        dataPermissionDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDataPermissionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dataPermissionDetailsRepository.findAll().size();
        dataPermissionDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDataPermissionDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dataPermissionDetails))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DataPermissionDetails in the database
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDataPermissionDetails() throws Exception {
        // Initialize the database
        dataPermissionDetailsRepository.saveAndFlush(dataPermissionDetails);

        int databaseSizeBeforeDelete = dataPermissionDetailsRepository.findAll().size();

        // Delete the dataPermissionDetails
        restDataPermissionDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, dataPermissionDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataPermissionDetails> dataPermissionDetailsList = dataPermissionDetailsRepository.findAll();
        assertThat(dataPermissionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
