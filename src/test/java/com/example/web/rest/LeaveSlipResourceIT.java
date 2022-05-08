package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.LeaveSlip;
import com.example.repository.LeaveSlipRepository;
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
 * Integration tests for the {@link LeaveSlipResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class LeaveSlipResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_START_TIME = "AAAAAAAAAA";
    private static final String UPDATED_START_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_END_TIME = "AAAAAAAAAA";
    private static final String UPDATED_END_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_FILE = "AAAAAAAAAA";
    private static final String UPDATED_FILE = "BBBBBBBBBB";

    private static final Long DEFAULT_SUPERIOR = 1L;
    private static final Long UPDATED_SUPERIOR = 2L;

    private static final String ENTITY_API_URL = "/api/leave-slips";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LeaveSlipRepository leaveSlipRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLeaveSlipMockMvc;

    private LeaveSlip leaveSlip;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaveSlip createEntity(EntityManager em) {
        LeaveSlip leaveSlip = new LeaveSlip()
            .type(DEFAULT_TYPE)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME)
            .reason(DEFAULT_REASON)
            .file(DEFAULT_FILE)
            .superior(DEFAULT_SUPERIOR);
        return leaveSlip;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaveSlip createUpdatedEntity(EntityManager em) {
        LeaveSlip leaveSlip = new LeaveSlip()
            .type(UPDATED_TYPE)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .reason(UPDATED_REASON)
            .file(UPDATED_FILE)
            .superior(UPDATED_SUPERIOR);
        return leaveSlip;
    }

    @BeforeEach
    public void initTest() {
        leaveSlip = createEntity(em);
    }

    @Test
    @Transactional
    void createLeaveSlip() throws Exception {
        int databaseSizeBeforeCreate = leaveSlipRepository.findAll().size();
        // Create the LeaveSlip
        restLeaveSlipMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leaveSlip)))
            .andExpect(status().isCreated());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeCreate + 1);
        LeaveSlip testLeaveSlip = leaveSlipList.get(leaveSlipList.size() - 1);
        assertThat(testLeaveSlip.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testLeaveSlip.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testLeaveSlip.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testLeaveSlip.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testLeaveSlip.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testLeaveSlip.getSuperior()).isEqualTo(DEFAULT_SUPERIOR);
    }

    @Test
    @Transactional
    void createLeaveSlipWithExistingId() throws Exception {
        // Create the LeaveSlip with an existing ID
        leaveSlip.setId(1L);

        int databaseSizeBeforeCreate = leaveSlipRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeaveSlipMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leaveSlip)))
            .andExpect(status().isBadRequest());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLeaveSlips() throws Exception {
        // Initialize the database
        leaveSlipRepository.saveAndFlush(leaveSlip);

        // Get all the leaveSlipList
        restLeaveSlipMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leaveSlip.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME)))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(DEFAULT_FILE)))
            .andExpect(jsonPath("$.[*].superior").value(hasItem(DEFAULT_SUPERIOR.intValue())));
    }

    @Test
    @Transactional
    void getLeaveSlip() throws Exception {
        // Initialize the database
        leaveSlipRepository.saveAndFlush(leaveSlip);

        // Get the leaveSlip
        restLeaveSlipMockMvc
            .perform(get(ENTITY_API_URL_ID, leaveSlip.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(leaveSlip.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.file").value(DEFAULT_FILE))
            .andExpect(jsonPath("$.superior").value(DEFAULT_SUPERIOR.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingLeaveSlip() throws Exception {
        // Get the leaveSlip
        restLeaveSlipMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLeaveSlip() throws Exception {
        // Initialize the database
        leaveSlipRepository.saveAndFlush(leaveSlip);

        int databaseSizeBeforeUpdate = leaveSlipRepository.findAll().size();

        // Update the leaveSlip
        LeaveSlip updatedLeaveSlip = leaveSlipRepository.findById(leaveSlip.getId()).get();
        // Disconnect from session so that the updates on updatedLeaveSlip are not directly saved in db
        em.detach(updatedLeaveSlip);
        updatedLeaveSlip
            .type(UPDATED_TYPE)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .reason(UPDATED_REASON)
            .file(UPDATED_FILE)
            .superior(UPDATED_SUPERIOR);

        restLeaveSlipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLeaveSlip.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLeaveSlip))
            )
            .andExpect(status().isOk());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeUpdate);
        LeaveSlip testLeaveSlip = leaveSlipList.get(leaveSlipList.size() - 1);
        assertThat(testLeaveSlip.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testLeaveSlip.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testLeaveSlip.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testLeaveSlip.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testLeaveSlip.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testLeaveSlip.getSuperior()).isEqualTo(UPDATED_SUPERIOR);
    }

    @Test
    @Transactional
    void putNonExistingLeaveSlip() throws Exception {
        int databaseSizeBeforeUpdate = leaveSlipRepository.findAll().size();
        leaveSlip.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeaveSlipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, leaveSlip.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(leaveSlip))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLeaveSlip() throws Exception {
        int databaseSizeBeforeUpdate = leaveSlipRepository.findAll().size();
        leaveSlip.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaveSlipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(leaveSlip))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLeaveSlip() throws Exception {
        int databaseSizeBeforeUpdate = leaveSlipRepository.findAll().size();
        leaveSlip.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaveSlipMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(leaveSlip)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLeaveSlipWithPatch() throws Exception {
        // Initialize the database
        leaveSlipRepository.saveAndFlush(leaveSlip);

        int databaseSizeBeforeUpdate = leaveSlipRepository.findAll().size();

        // Update the leaveSlip using partial update
        LeaveSlip partialUpdatedLeaveSlip = new LeaveSlip();
        partialUpdatedLeaveSlip.setId(leaveSlip.getId());

        partialUpdatedLeaveSlip.type(UPDATED_TYPE).reason(UPDATED_REASON).superior(UPDATED_SUPERIOR);

        restLeaveSlipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeaveSlip.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLeaveSlip))
            )
            .andExpect(status().isOk());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeUpdate);
        LeaveSlip testLeaveSlip = leaveSlipList.get(leaveSlipList.size() - 1);
        assertThat(testLeaveSlip.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testLeaveSlip.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testLeaveSlip.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testLeaveSlip.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testLeaveSlip.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testLeaveSlip.getSuperior()).isEqualTo(UPDATED_SUPERIOR);
    }

    @Test
    @Transactional
    void fullUpdateLeaveSlipWithPatch() throws Exception {
        // Initialize the database
        leaveSlipRepository.saveAndFlush(leaveSlip);

        int databaseSizeBeforeUpdate = leaveSlipRepository.findAll().size();

        // Update the leaveSlip using partial update
        LeaveSlip partialUpdatedLeaveSlip = new LeaveSlip();
        partialUpdatedLeaveSlip.setId(leaveSlip.getId());

        partialUpdatedLeaveSlip
            .type(UPDATED_TYPE)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .reason(UPDATED_REASON)
            .file(UPDATED_FILE)
            .superior(UPDATED_SUPERIOR);

        restLeaveSlipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeaveSlip.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLeaveSlip))
            )
            .andExpect(status().isOk());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeUpdate);
        LeaveSlip testLeaveSlip = leaveSlipList.get(leaveSlipList.size() - 1);
        assertThat(testLeaveSlip.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testLeaveSlip.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testLeaveSlip.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testLeaveSlip.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testLeaveSlip.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testLeaveSlip.getSuperior()).isEqualTo(UPDATED_SUPERIOR);
    }

    @Test
    @Transactional
    void patchNonExistingLeaveSlip() throws Exception {
        int databaseSizeBeforeUpdate = leaveSlipRepository.findAll().size();
        leaveSlip.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeaveSlipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, leaveSlip.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(leaveSlip))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLeaveSlip() throws Exception {
        int databaseSizeBeforeUpdate = leaveSlipRepository.findAll().size();
        leaveSlip.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaveSlipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(leaveSlip))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLeaveSlip() throws Exception {
        int databaseSizeBeforeUpdate = leaveSlipRepository.findAll().size();
        leaveSlip.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaveSlipMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(leaveSlip))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LeaveSlip in the database
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLeaveSlip() throws Exception {
        // Initialize the database
        leaveSlipRepository.saveAndFlush(leaveSlip);

        int databaseSizeBeforeDelete = leaveSlipRepository.findAll().size();

        // Delete the leaveSlip
        restLeaveSlipMockMvc
            .perform(delete(ENTITY_API_URL_ID, leaveSlip.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LeaveSlip> leaveSlipList = leaveSlipRepository.findAll();
        assertThat(leaveSlipList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
