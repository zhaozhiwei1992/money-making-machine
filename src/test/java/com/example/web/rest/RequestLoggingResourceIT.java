package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.RequestLogging;
import com.example.repository.RequestLoggingRepository;
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
 * Integration tests for the {@link RequestLoggingResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class RequestLoggingResourceIT {

    private static final String DEFAULT_TRACE_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRACE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LOGIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_URI = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_URI = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_IP = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_IP = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_TIME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/request-loggings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RequestLoggingRepository requestLoggingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestLoggingMockMvc;

    private RequestLogging requestLogging;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestLogging createEntity(EntityManager em) {
        RequestLogging requestLogging = new RequestLogging()
            .traceId(DEFAULT_TRACE_ID)
            .loginName(DEFAULT_LOGIN_NAME)
            .requestURI(DEFAULT_REQUEST_URI)
            .clientIP(DEFAULT_CLIENT_IP)
            .currentTime(DEFAULT_CURRENT_TIME);
        return requestLogging;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestLogging createUpdatedEntity(EntityManager em) {
        RequestLogging requestLogging = new RequestLogging()
            .traceId(UPDATED_TRACE_ID)
            .loginName(UPDATED_LOGIN_NAME)
            .requestURI(UPDATED_REQUEST_URI)
            .clientIP(UPDATED_CLIENT_IP)
            .currentTime(UPDATED_CURRENT_TIME);
        return requestLogging;
    }

    @BeforeEach
    public void initTest() {
        requestLogging = createEntity(em);
    }

    @Test
    @Transactional
    void createRequestLogging() throws Exception {
        int databaseSizeBeforeCreate = requestLoggingRepository.findAll().size();
        // Create the RequestLogging
        restRequestLoggingMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requestLogging))
            )
            .andExpect(status().isCreated());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeCreate + 1);
        RequestLogging testRequestLogging = requestLoggingList.get(requestLoggingList.size() - 1);
        assertThat(testRequestLogging.getTraceId()).isEqualTo(DEFAULT_TRACE_ID);
        assertThat(testRequestLogging.getLoginName()).isEqualTo(DEFAULT_LOGIN_NAME);
        assertThat(testRequestLogging.getRequestURI()).isEqualTo(DEFAULT_REQUEST_URI);
        assertThat(testRequestLogging.getClientIP()).isEqualTo(DEFAULT_CLIENT_IP);
        assertThat(testRequestLogging.getCurrentTime()).isEqualTo(DEFAULT_CURRENT_TIME);
    }

    @Test
    @Transactional
    void createRequestLoggingWithExistingId() throws Exception {
        // Create the RequestLogging with an existing ID
        requestLogging.setId(1L);

        int databaseSizeBeforeCreate = requestLoggingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestLoggingMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requestLogging))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRequestLoggings() throws Exception {
        // Initialize the database
        requestLoggingRepository.saveAndFlush(requestLogging);

        // Get all the requestLoggingList
        restRequestLoggingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requestLogging.getId().intValue())))
            .andExpect(jsonPath("$.[*].traceId").value(hasItem(DEFAULT_TRACE_ID)))
            .andExpect(jsonPath("$.[*].loginName").value(hasItem(DEFAULT_LOGIN_NAME)))
            .andExpect(jsonPath("$.[*].requestURI").value(hasItem(DEFAULT_REQUEST_URI)))
            .andExpect(jsonPath("$.[*].clientIP").value(hasItem(DEFAULT_CLIENT_IP)))
            .andExpect(jsonPath("$.[*].currentTime").value(hasItem(DEFAULT_CURRENT_TIME)));
    }

    @Test
    @Transactional
    void getRequestLogging() throws Exception {
        // Initialize the database
        requestLoggingRepository.saveAndFlush(requestLogging);

        // Get the requestLogging
        restRequestLoggingMockMvc
            .perform(get(ENTITY_API_URL_ID, requestLogging.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requestLogging.getId().intValue()))
            .andExpect(jsonPath("$.traceId").value(DEFAULT_TRACE_ID))
            .andExpect(jsonPath("$.loginName").value(DEFAULT_LOGIN_NAME))
            .andExpect(jsonPath("$.requestURI").value(DEFAULT_REQUEST_URI))
            .andExpect(jsonPath("$.clientIP").value(DEFAULT_CLIENT_IP))
            .andExpect(jsonPath("$.currentTime").value(DEFAULT_CURRENT_TIME));
    }

    @Test
    @Transactional
    void getNonExistingRequestLogging() throws Exception {
        // Get the requestLogging
        restRequestLoggingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRequestLogging() throws Exception {
        // Initialize the database
        requestLoggingRepository.saveAndFlush(requestLogging);

        int databaseSizeBeforeUpdate = requestLoggingRepository.findAll().size();

        // Update the requestLogging
        RequestLogging updatedRequestLogging = requestLoggingRepository.findById(requestLogging.getId()).get();
        // Disconnect from session so that the updates on updatedRequestLogging are not directly saved in db
        em.detach(updatedRequestLogging);
        updatedRequestLogging
            .traceId(UPDATED_TRACE_ID)
            .loginName(UPDATED_LOGIN_NAME)
            .requestURI(UPDATED_REQUEST_URI)
            .clientIP(UPDATED_CLIENT_IP)
            .currentTime(UPDATED_CURRENT_TIME);

        restRequestLoggingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRequestLogging.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRequestLogging))
            )
            .andExpect(status().isOk());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeUpdate);
        RequestLogging testRequestLogging = requestLoggingList.get(requestLoggingList.size() - 1);
        assertThat(testRequestLogging.getTraceId()).isEqualTo(UPDATED_TRACE_ID);
        assertThat(testRequestLogging.getLoginName()).isEqualTo(UPDATED_LOGIN_NAME);
        assertThat(testRequestLogging.getRequestURI()).isEqualTo(UPDATED_REQUEST_URI);
        assertThat(testRequestLogging.getClientIP()).isEqualTo(UPDATED_CLIENT_IP);
        assertThat(testRequestLogging.getCurrentTime()).isEqualTo(UPDATED_CURRENT_TIME);
    }

    @Test
    @Transactional
    void putNonExistingRequestLogging() throws Exception {
        int databaseSizeBeforeUpdate = requestLoggingRepository.findAll().size();
        requestLogging.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestLoggingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, requestLogging.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestLogging))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRequestLogging() throws Exception {
        int databaseSizeBeforeUpdate = requestLoggingRepository.findAll().size();
        requestLogging.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestLoggingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestLogging))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRequestLogging() throws Exception {
        int databaseSizeBeforeUpdate = requestLoggingRepository.findAll().size();
        requestLogging.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestLoggingMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requestLogging)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRequestLoggingWithPatch() throws Exception {
        // Initialize the database
        requestLoggingRepository.saveAndFlush(requestLogging);

        int databaseSizeBeforeUpdate = requestLoggingRepository.findAll().size();

        // Update the requestLogging using partial update
        RequestLogging partialUpdatedRequestLogging = new RequestLogging();
        partialUpdatedRequestLogging.setId(requestLogging.getId());

        partialUpdatedRequestLogging.requestURI(UPDATED_REQUEST_URI).clientIP(UPDATED_CLIENT_IP);

        restRequestLoggingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequestLogging.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestLogging))
            )
            .andExpect(status().isOk());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeUpdate);
        RequestLogging testRequestLogging = requestLoggingList.get(requestLoggingList.size() - 1);
        assertThat(testRequestLogging.getTraceId()).isEqualTo(DEFAULT_TRACE_ID);
        assertThat(testRequestLogging.getLoginName()).isEqualTo(DEFAULT_LOGIN_NAME);
        assertThat(testRequestLogging.getRequestURI()).isEqualTo(UPDATED_REQUEST_URI);
        assertThat(testRequestLogging.getClientIP()).isEqualTo(UPDATED_CLIENT_IP);
        assertThat(testRequestLogging.getCurrentTime()).isEqualTo(DEFAULT_CURRENT_TIME);
    }

    @Test
    @Transactional
    void fullUpdateRequestLoggingWithPatch() throws Exception {
        // Initialize the database
        requestLoggingRepository.saveAndFlush(requestLogging);

        int databaseSizeBeforeUpdate = requestLoggingRepository.findAll().size();

        // Update the requestLogging using partial update
        RequestLogging partialUpdatedRequestLogging = new RequestLogging();
        partialUpdatedRequestLogging.setId(requestLogging.getId());

        partialUpdatedRequestLogging
            .traceId(UPDATED_TRACE_ID)
            .loginName(UPDATED_LOGIN_NAME)
            .requestURI(UPDATED_REQUEST_URI)
            .clientIP(UPDATED_CLIENT_IP)
            .currentTime(UPDATED_CURRENT_TIME);

        restRequestLoggingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequestLogging.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestLogging))
            )
            .andExpect(status().isOk());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeUpdate);
        RequestLogging testRequestLogging = requestLoggingList.get(requestLoggingList.size() - 1);
        assertThat(testRequestLogging.getTraceId()).isEqualTo(UPDATED_TRACE_ID);
        assertThat(testRequestLogging.getLoginName()).isEqualTo(UPDATED_LOGIN_NAME);
        assertThat(testRequestLogging.getRequestURI()).isEqualTo(UPDATED_REQUEST_URI);
        assertThat(testRequestLogging.getClientIP()).isEqualTo(UPDATED_CLIENT_IP);
        assertThat(testRequestLogging.getCurrentTime()).isEqualTo(UPDATED_CURRENT_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingRequestLogging() throws Exception {
        int databaseSizeBeforeUpdate = requestLoggingRepository.findAll().size();
        requestLogging.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestLoggingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, requestLogging.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestLogging))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRequestLogging() throws Exception {
        int databaseSizeBeforeUpdate = requestLoggingRepository.findAll().size();
        requestLogging.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestLoggingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestLogging))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRequestLogging() throws Exception {
        int databaseSizeBeforeUpdate = requestLoggingRepository.findAll().size();
        requestLogging.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestLoggingMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(requestLogging))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RequestLogging in the database
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRequestLogging() throws Exception {
        // Initialize the database
        requestLoggingRepository.saveAndFlush(requestLogging);

        int databaseSizeBeforeDelete = requestLoggingRepository.findAll().size();

        // Delete the requestLogging
        restRequestLoggingMockMvc
            .perform(delete(ENTITY_API_URL_ID, requestLogging.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RequestLogging> requestLoggingList = requestLoggingRepository.findAll();
        assertThat(requestLoggingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
