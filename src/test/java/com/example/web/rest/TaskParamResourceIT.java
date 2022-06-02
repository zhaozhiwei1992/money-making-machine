package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.TaskParam;
import com.example.repository.TaskParamRepository;
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
 * Integration tests for the {@link TaskParamResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class TaskParamResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CRON_EXPRESSION = "AAAAAAAAAA";
    private static final String UPDATED_CRON_EXPRESSION = "BBBBBBBBBB";

    private static final String DEFAULT_START_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_START_CLASS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLE = false;
    private static final Boolean UPDATED_ENABLE = true;

    private static final String ENTITY_API_URL = "/api/task-params";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaskParamRepository taskParamRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaskParamMockMvc;

    private TaskParam taskParam;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskParam createEntity(EntityManager em) {
        TaskParam taskParam = new TaskParam()
            .name(DEFAULT_NAME)
            .cronExpression(DEFAULT_CRON_EXPRESSION)
            .startClass(DEFAULT_START_CLASS)
            .enable(DEFAULT_ENABLE);
        return taskParam;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskParam createUpdatedEntity(EntityManager em) {
        TaskParam taskParam = new TaskParam()
            .name(UPDATED_NAME)
            .cronExpression(UPDATED_CRON_EXPRESSION)
            .startClass(UPDATED_START_CLASS)
            .enable(UPDATED_ENABLE);
        return taskParam;
    }

    @BeforeEach
    public void initTest() {
        taskParam = createEntity(em);
    }

    @Test
    @Transactional
    void createTaskParam() throws Exception {
        int databaseSizeBeforeCreate = taskParamRepository.findAll().size();
        // Create the TaskParam
        restTaskParamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskParam)))
            .andExpect(status().isCreated());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeCreate + 1);
        TaskParam testTaskParam = taskParamList.get(taskParamList.size() - 1);
        assertThat(testTaskParam.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTaskParam.getCronExpression()).isEqualTo(DEFAULT_CRON_EXPRESSION);
        assertThat(testTaskParam.getStartClass()).isEqualTo(DEFAULT_START_CLASS);
        assertThat(testTaskParam.getEnable()).isEqualTo(DEFAULT_ENABLE);
    }

    @Test
    @Transactional
    void createTaskParamWithExistingId() throws Exception {
        // Create the TaskParam with an existing ID
        taskParam.setId(1L);

        int databaseSizeBeforeCreate = taskParamRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskParamMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskParam)))
            .andExpect(status().isBadRequest());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaskParams() throws Exception {
        // Initialize the database
        taskParamRepository.saveAndFlush(taskParam);

        // Get all the taskParamList
        restTaskParamMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskParam.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].cronExpression").value(hasItem(DEFAULT_CRON_EXPRESSION)))
            .andExpect(jsonPath("$.[*].startClass").value(hasItem(DEFAULT_START_CLASS)))
            .andExpect(jsonPath("$.[*].enable").value(hasItem(DEFAULT_ENABLE.booleanValue())));
    }

    @Test
    @Transactional
    void getTaskParam() throws Exception {
        // Initialize the database
        taskParamRepository.saveAndFlush(taskParam);

        // Get the taskParam
        restTaskParamMockMvc
            .perform(get(ENTITY_API_URL_ID, taskParam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taskParam.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.cronExpression").value(DEFAULT_CRON_EXPRESSION))
            .andExpect(jsonPath("$.startClass").value(DEFAULT_START_CLASS))
            .andExpect(jsonPath("$.enable").value(DEFAULT_ENABLE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTaskParam() throws Exception {
        // Get the taskParam
        restTaskParamMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTaskParam() throws Exception {
        // Initialize the database
        taskParamRepository.saveAndFlush(taskParam);

        int databaseSizeBeforeUpdate = taskParamRepository.findAll().size();

        // Update the taskParam
        TaskParam updatedTaskParam = taskParamRepository.findById(taskParam.getId()).get();
        // Disconnect from session so that the updates on updatedTaskParam are not directly saved in db
        em.detach(updatedTaskParam);
        updatedTaskParam.name(UPDATED_NAME).cronExpression(UPDATED_CRON_EXPRESSION).startClass(UPDATED_START_CLASS).enable(UPDATED_ENABLE);

        restTaskParamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTaskParam.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTaskParam))
            )
            .andExpect(status().isOk());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeUpdate);
        TaskParam testTaskParam = taskParamList.get(taskParamList.size() - 1);
        assertThat(testTaskParam.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTaskParam.getCronExpression()).isEqualTo(UPDATED_CRON_EXPRESSION);
        assertThat(testTaskParam.getStartClass()).isEqualTo(UPDATED_START_CLASS);
        assertThat(testTaskParam.getEnable()).isEqualTo(UPDATED_ENABLE);
    }

    @Test
    @Transactional
    void putNonExistingTaskParam() throws Exception {
        int databaseSizeBeforeUpdate = taskParamRepository.findAll().size();
        taskParam.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskParamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskParam.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaskParam() throws Exception {
        int databaseSizeBeforeUpdate = taskParamRepository.findAll().size();
        taskParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskParamMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaskParam() throws Exception {
        int databaseSizeBeforeUpdate = taskParamRepository.findAll().size();
        taskParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskParamMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(taskParam)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaskParamWithPatch() throws Exception {
        // Initialize the database
        taskParamRepository.saveAndFlush(taskParam);

        int databaseSizeBeforeUpdate = taskParamRepository.findAll().size();

        // Update the taskParam using partial update
        TaskParam partialUpdatedTaskParam = new TaskParam();
        partialUpdatedTaskParam.setId(taskParam.getId());

        partialUpdatedTaskParam.cronExpression(UPDATED_CRON_EXPRESSION).startClass(UPDATED_START_CLASS);

        restTaskParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskParam.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskParam))
            )
            .andExpect(status().isOk());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeUpdate);
        TaskParam testTaskParam = taskParamList.get(taskParamList.size() - 1);
        assertThat(testTaskParam.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTaskParam.getCronExpression()).isEqualTo(UPDATED_CRON_EXPRESSION);
        assertThat(testTaskParam.getStartClass()).isEqualTo(UPDATED_START_CLASS);
        assertThat(testTaskParam.getEnable()).isEqualTo(DEFAULT_ENABLE);
    }

    @Test
    @Transactional
    void fullUpdateTaskParamWithPatch() throws Exception {
        // Initialize the database
        taskParamRepository.saveAndFlush(taskParam);

        int databaseSizeBeforeUpdate = taskParamRepository.findAll().size();

        // Update the taskParam using partial update
        TaskParam partialUpdatedTaskParam = new TaskParam();
        partialUpdatedTaskParam.setId(taskParam.getId());

        partialUpdatedTaskParam
            .name(UPDATED_NAME)
            .cronExpression(UPDATED_CRON_EXPRESSION)
            .startClass(UPDATED_START_CLASS)
            .enable(UPDATED_ENABLE);

        restTaskParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskParam.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskParam))
            )
            .andExpect(status().isOk());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeUpdate);
        TaskParam testTaskParam = taskParamList.get(taskParamList.size() - 1);
        assertThat(testTaskParam.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTaskParam.getCronExpression()).isEqualTo(UPDATED_CRON_EXPRESSION);
        assertThat(testTaskParam.getStartClass()).isEqualTo(UPDATED_START_CLASS);
        assertThat(testTaskParam.getEnable()).isEqualTo(UPDATED_ENABLE);
    }

    @Test
    @Transactional
    void patchNonExistingTaskParam() throws Exception {
        int databaseSizeBeforeUpdate = taskParamRepository.findAll().size();
        taskParam.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taskParam.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaskParam() throws Exception {
        int databaseSizeBeforeUpdate = taskParamRepository.findAll().size();
        taskParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskParamMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskParam))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaskParam() throws Exception {
        int databaseSizeBeforeUpdate = taskParamRepository.findAll().size();
        taskParam.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskParamMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(taskParam))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskParam in the database
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaskParam() throws Exception {
        // Initialize the database
        taskParamRepository.saveAndFlush(taskParam);

        int databaseSizeBeforeDelete = taskParamRepository.findAll().size();

        // Delete the taskParam
        restTaskParamMockMvc
            .perform(delete(ENTITY_API_URL_ID, taskParam.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaskParam> taskParamList = taskParamRepository.findAll();
        assertThat(taskParamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
