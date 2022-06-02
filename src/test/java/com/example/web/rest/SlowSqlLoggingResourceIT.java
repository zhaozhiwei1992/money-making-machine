package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.SlowSqlLogging;
import com.example.repository.SlowSqlLoggingRepository;
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
 * Integration tests for the {@link SlowSqlLoggingResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class SlowSqlLoggingResourceIT {

    private static final String DEFAULT_TRACE_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRACE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_SQL = "AAAAAAAAAA";
    private static final String UPDATED_SQL = "BBBBBBBBBB";

    private static final String DEFAULT_EXECUTE_MILLIS = "AAAAAAAAAA";
    private static final String UPDATED_EXECUTE_MILLIS = "BBBBBBBBBB";

    private static final String DEFAULT_EXECUTE_PARAMS = "AAAAAAAAAA";
    private static final String UPDATED_EXECUTE_PARAMS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/slow-sql-loggings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SlowSqlLoggingRepository slowSqlLoggingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSlowSqlLoggingMockMvc;

    private SlowSqlLogging slowSqlLogging;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SlowSqlLogging createEntity(EntityManager em) {
        SlowSqlLogging slowSqlLogging = new SlowSqlLogging()
            .traceId(DEFAULT_TRACE_ID)
            .currentTime(DEFAULT_CURRENT_TIME)
            .sql(DEFAULT_SQL)
            .executeMillis(DEFAULT_EXECUTE_MILLIS)
            .executeParams(DEFAULT_EXECUTE_PARAMS);
        return slowSqlLogging;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SlowSqlLogging createUpdatedEntity(EntityManager em) {
        SlowSqlLogging slowSqlLogging = new SlowSqlLogging()
            .traceId(UPDATED_TRACE_ID)
            .currentTime(UPDATED_CURRENT_TIME)
            .sql(UPDATED_SQL)
            .executeMillis(UPDATED_EXECUTE_MILLIS)
            .executeParams(UPDATED_EXECUTE_PARAMS);
        return slowSqlLogging;
    }

    @BeforeEach
    public void initTest() {
        slowSqlLogging = createEntity(em);
    }

    @Test
    @Transactional
    void createSlowSqlLogging() throws Exception {
        int databaseSizeBeforeCreate = slowSqlLoggingRepository.findAll().size();
        // Create the SlowSqlLogging
        restSlowSqlLoggingMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(slowSqlLogging))
            )
            .andExpect(status().isCreated());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeCreate + 1);
        SlowSqlLogging testSlowSqlLogging = slowSqlLoggingList.get(slowSqlLoggingList.size() - 1);
        assertThat(testSlowSqlLogging.getTraceId()).isEqualTo(DEFAULT_TRACE_ID);
        assertThat(testSlowSqlLogging.getCurrentTime()).isEqualTo(DEFAULT_CURRENT_TIME);
        assertThat(testSlowSqlLogging.getSql()).isEqualTo(DEFAULT_SQL);
        assertThat(testSlowSqlLogging.getExecuteMillis()).isEqualTo(DEFAULT_EXECUTE_MILLIS);
        assertThat(testSlowSqlLogging.getExecuteParams()).isEqualTo(DEFAULT_EXECUTE_PARAMS);
    }

    @Test
    @Transactional
    void createSlowSqlLoggingWithExistingId() throws Exception {
        // Create the SlowSqlLogging with an existing ID
        slowSqlLogging.setId(1L);

        int databaseSizeBeforeCreate = slowSqlLoggingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlowSqlLoggingMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(slowSqlLogging))
            )
            .andExpect(status().isBadRequest());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSlowSqlLoggings() throws Exception {
        // Initialize the database
        slowSqlLoggingRepository.saveAndFlush(slowSqlLogging);

        // Get all the slowSqlLoggingList
        restSlowSqlLoggingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(slowSqlLogging.getId().intValue())))
            .andExpect(jsonPath("$.[*].traceId").value(hasItem(DEFAULT_TRACE_ID)))
            .andExpect(jsonPath("$.[*].currentTime").value(hasItem(DEFAULT_CURRENT_TIME)))
            .andExpect(jsonPath("$.[*].sql").value(hasItem(DEFAULT_SQL)))
            .andExpect(jsonPath("$.[*].executeMillis").value(hasItem(DEFAULT_EXECUTE_MILLIS)))
            .andExpect(jsonPath("$.[*].executeParams").value(hasItem(DEFAULT_EXECUTE_PARAMS)));
    }

    @Test
    @Transactional
    void getSlowSqlLogging() throws Exception {
        // Initialize the database
        slowSqlLoggingRepository.saveAndFlush(slowSqlLogging);

        // Get the slowSqlLogging
        restSlowSqlLoggingMockMvc
            .perform(get(ENTITY_API_URL_ID, slowSqlLogging.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(slowSqlLogging.getId().intValue()))
            .andExpect(jsonPath("$.traceId").value(DEFAULT_TRACE_ID))
            .andExpect(jsonPath("$.currentTime").value(DEFAULT_CURRENT_TIME))
            .andExpect(jsonPath("$.sql").value(DEFAULT_SQL))
            .andExpect(jsonPath("$.executeMillis").value(DEFAULT_EXECUTE_MILLIS))
            .andExpect(jsonPath("$.executeParams").value(DEFAULT_EXECUTE_PARAMS));
    }

    @Test
    @Transactional
    void getNonExistingSlowSqlLogging() throws Exception {
        // Get the slowSqlLogging
        restSlowSqlLoggingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSlowSqlLogging() throws Exception {
        // Initialize the database
        slowSqlLoggingRepository.saveAndFlush(slowSqlLogging);

        int databaseSizeBeforeUpdate = slowSqlLoggingRepository.findAll().size();

        // Update the slowSqlLogging
        SlowSqlLogging updatedSlowSqlLogging = slowSqlLoggingRepository.findById(slowSqlLogging.getId()).get();
        // Disconnect from session so that the updates on updatedSlowSqlLogging are not directly saved in db
        em.detach(updatedSlowSqlLogging);
        updatedSlowSqlLogging
            .traceId(UPDATED_TRACE_ID)
            .currentTime(UPDATED_CURRENT_TIME)
            .sql(UPDATED_SQL)
            .executeMillis(UPDATED_EXECUTE_MILLIS)
            .executeParams(UPDATED_EXECUTE_PARAMS);

        restSlowSqlLoggingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSlowSqlLogging.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSlowSqlLogging))
            )
            .andExpect(status().isOk());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeUpdate);
        SlowSqlLogging testSlowSqlLogging = slowSqlLoggingList.get(slowSqlLoggingList.size() - 1);
        assertThat(testSlowSqlLogging.getTraceId()).isEqualTo(UPDATED_TRACE_ID);
        assertThat(testSlowSqlLogging.getCurrentTime()).isEqualTo(UPDATED_CURRENT_TIME);
        assertThat(testSlowSqlLogging.getSql()).isEqualTo(UPDATED_SQL);
        assertThat(testSlowSqlLogging.getExecuteMillis()).isEqualTo(UPDATED_EXECUTE_MILLIS);
        assertThat(testSlowSqlLogging.getExecuteParams()).isEqualTo(UPDATED_EXECUTE_PARAMS);
    }

    @Test
    @Transactional
    void putNonExistingSlowSqlLogging() throws Exception {
        int databaseSizeBeforeUpdate = slowSqlLoggingRepository.findAll().size();
        slowSqlLogging.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlowSqlLoggingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, slowSqlLogging.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(slowSqlLogging))
            )
            .andExpect(status().isBadRequest());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSlowSqlLogging() throws Exception {
        int databaseSizeBeforeUpdate = slowSqlLoggingRepository.findAll().size();
        slowSqlLogging.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSlowSqlLoggingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(slowSqlLogging))
            )
            .andExpect(status().isBadRequest());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSlowSqlLogging() throws Exception {
        int databaseSizeBeforeUpdate = slowSqlLoggingRepository.findAll().size();
        slowSqlLogging.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSlowSqlLoggingMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(slowSqlLogging)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSlowSqlLoggingWithPatch() throws Exception {
        // Initialize the database
        slowSqlLoggingRepository.saveAndFlush(slowSqlLogging);

        int databaseSizeBeforeUpdate = slowSqlLoggingRepository.findAll().size();

        // Update the slowSqlLogging using partial update
        SlowSqlLogging partialUpdatedSlowSqlLogging = new SlowSqlLogging();
        partialUpdatedSlowSqlLogging.setId(slowSqlLogging.getId());

        partialUpdatedSlowSqlLogging.traceId(UPDATED_TRACE_ID).currentTime(UPDATED_CURRENT_TIME).executeMillis(UPDATED_EXECUTE_MILLIS);

        restSlowSqlLoggingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSlowSqlLogging.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSlowSqlLogging))
            )
            .andExpect(status().isOk());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeUpdate);
        SlowSqlLogging testSlowSqlLogging = slowSqlLoggingList.get(slowSqlLoggingList.size() - 1);
        assertThat(testSlowSqlLogging.getTraceId()).isEqualTo(UPDATED_TRACE_ID);
        assertThat(testSlowSqlLogging.getCurrentTime()).isEqualTo(UPDATED_CURRENT_TIME);
        assertThat(testSlowSqlLogging.getSql()).isEqualTo(DEFAULT_SQL);
        assertThat(testSlowSqlLogging.getExecuteMillis()).isEqualTo(UPDATED_EXECUTE_MILLIS);
        assertThat(testSlowSqlLogging.getExecuteParams()).isEqualTo(DEFAULT_EXECUTE_PARAMS);
    }

    @Test
    @Transactional
    void fullUpdateSlowSqlLoggingWithPatch() throws Exception {
        // Initialize the database
        slowSqlLoggingRepository.saveAndFlush(slowSqlLogging);

        int databaseSizeBeforeUpdate = slowSqlLoggingRepository.findAll().size();

        // Update the slowSqlLogging using partial update
        SlowSqlLogging partialUpdatedSlowSqlLogging = new SlowSqlLogging();
        partialUpdatedSlowSqlLogging.setId(slowSqlLogging.getId());

        partialUpdatedSlowSqlLogging
            .traceId(UPDATED_TRACE_ID)
            .currentTime(UPDATED_CURRENT_TIME)
            .sql(UPDATED_SQL)
            .executeMillis(UPDATED_EXECUTE_MILLIS)
            .executeParams(UPDATED_EXECUTE_PARAMS);

        restSlowSqlLoggingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSlowSqlLogging.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSlowSqlLogging))
            )
            .andExpect(status().isOk());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeUpdate);
        SlowSqlLogging testSlowSqlLogging = slowSqlLoggingList.get(slowSqlLoggingList.size() - 1);
        assertThat(testSlowSqlLogging.getTraceId()).isEqualTo(UPDATED_TRACE_ID);
        assertThat(testSlowSqlLogging.getCurrentTime()).isEqualTo(UPDATED_CURRENT_TIME);
        assertThat(testSlowSqlLogging.getSql()).isEqualTo(UPDATED_SQL);
        assertThat(testSlowSqlLogging.getExecuteMillis()).isEqualTo(UPDATED_EXECUTE_MILLIS);
        assertThat(testSlowSqlLogging.getExecuteParams()).isEqualTo(UPDATED_EXECUTE_PARAMS);
    }

    @Test
    @Transactional
    void patchNonExistingSlowSqlLogging() throws Exception {
        int databaseSizeBeforeUpdate = slowSqlLoggingRepository.findAll().size();
        slowSqlLogging.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlowSqlLoggingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, slowSqlLogging.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(slowSqlLogging))
            )
            .andExpect(status().isBadRequest());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSlowSqlLogging() throws Exception {
        int databaseSizeBeforeUpdate = slowSqlLoggingRepository.findAll().size();
        slowSqlLogging.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSlowSqlLoggingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(slowSqlLogging))
            )
            .andExpect(status().isBadRequest());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSlowSqlLogging() throws Exception {
        int databaseSizeBeforeUpdate = slowSqlLoggingRepository.findAll().size();
        slowSqlLogging.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSlowSqlLoggingMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(slowSqlLogging))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SlowSqlLogging in the database
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSlowSqlLogging() throws Exception {
        // Initialize the database
        slowSqlLoggingRepository.saveAndFlush(slowSqlLogging);

        int databaseSizeBeforeDelete = slowSqlLoggingRepository.findAll().size();

        // Delete the slowSqlLogging
        restSlowSqlLoggingMockMvc
            .perform(delete(ENTITY_API_URL_ID, slowSqlLogging.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SlowSqlLogging> slowSqlLoggingList = slowSqlLoggingRepository.findAll();
        assertThat(slowSqlLoggingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
