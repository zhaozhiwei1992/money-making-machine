package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.UiTable;
import com.example.repository.UiTableRepository;
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
 * Integration tests for the {@link UiTableResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UiTableResourceIT {

    private static final Long DEFAULT_MENUID = 1L;
    private static final Long UPDATED_MENUID = 2L;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERNUM = 1;
    private static final Integer UPDATED_ORDERNUM = 2;

    private static final Boolean DEFAULT_ISSOURCE = false;
    private static final Boolean UPDATED_ISSOURCE = true;

    private static final Boolean DEFAULT_ISEDIT = false;
    private static final Boolean UPDATED_ISEDIT = true;

    private static final Boolean DEFAULT_REQUIREMENT = false;
    private static final Boolean UPDATED_REQUIREMENT = true;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ui-tables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UiTableRepository uiTableRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUiTableMockMvc;

    private UiTable uiTable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiTable createEntity(EntityManager em) {
        UiTable uiTable = new UiTable()
            .menuid(DEFAULT_MENUID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .ordernum(DEFAULT_ORDERNUM)
            .issource(DEFAULT_ISSOURCE)
            .isedit(DEFAULT_ISEDIT)
            .requirement(DEFAULT_REQUIREMENT)
            .type(DEFAULT_TYPE)
            .config(DEFAULT_CONFIG);
        return uiTable;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiTable createUpdatedEntity(EntityManager em) {
        UiTable uiTable = new UiTable()
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .issource(UPDATED_ISSOURCE)
            .isedit(UPDATED_ISEDIT)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .config(UPDATED_CONFIG);
        return uiTable;
    }

    @BeforeEach
    public void initTest() {
        uiTable = createEntity(em);
    }

    @Test
    @Transactional
    void createUiTable() throws Exception {
        int databaseSizeBeforeCreate = uiTableRepository.findAll().size();
        // Create the UiTable
        restUiTableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiTable)))
            .andExpect(status().isCreated());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeCreate + 1);
        UiTable testUiTable = uiTableList.get(uiTableList.size() - 1);
        assertThat(testUiTable.getMenuid()).isEqualTo(DEFAULT_MENUID);
        assertThat(testUiTable.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUiTable.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUiTable.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
        assertThat(testUiTable.getIssource()).isEqualTo(DEFAULT_ISSOURCE);
        assertThat(testUiTable.getIsedit()).isEqualTo(DEFAULT_ISEDIT);
        assertThat(testUiTable.getRequirement()).isEqualTo(DEFAULT_REQUIREMENT);
        assertThat(testUiTable.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testUiTable.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void createUiTableWithExistingId() throws Exception {
        // Create the UiTable with an existing ID
        uiTable.setId(1L);

        int databaseSizeBeforeCreate = uiTableRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUiTableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiTable)))
            .andExpect(status().isBadRequest());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUiTables() throws Exception {
        // Initialize the database
        uiTableRepository.saveAndFlush(uiTable);

        // Get all the uiTableList
        restUiTableMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uiTable.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuid").value(hasItem(DEFAULT_MENUID.intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].ordernum").value(hasItem(DEFAULT_ORDERNUM)))
            .andExpect(jsonPath("$.[*].issource").value(hasItem(DEFAULT_ISSOURCE.booleanValue())))
            .andExpect(jsonPath("$.[*].isedit").value(hasItem(DEFAULT_ISEDIT.booleanValue())))
            .andExpect(jsonPath("$.[*].requirement").value(hasItem(DEFAULT_REQUIREMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].config").value(hasItem(DEFAULT_CONFIG)));
    }

    @Test
    @Transactional
    void getUiTable() throws Exception {
        // Initialize the database
        uiTableRepository.saveAndFlush(uiTable);

        // Get the uiTable
        restUiTableMockMvc
            .perform(get(ENTITY_API_URL_ID, uiTable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uiTable.getId().intValue()))
            .andExpect(jsonPath("$.menuid").value(DEFAULT_MENUID.intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.ordernum").value(DEFAULT_ORDERNUM))
            .andExpect(jsonPath("$.issource").value(DEFAULT_ISSOURCE.booleanValue()))
            .andExpect(jsonPath("$.isedit").value(DEFAULT_ISEDIT.booleanValue()))
            .andExpect(jsonPath("$.requirement").value(DEFAULT_REQUIREMENT.booleanValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.config").value(DEFAULT_CONFIG));
    }

    @Test
    @Transactional
    void getNonExistingUiTable() throws Exception {
        // Get the uiTable
        restUiTableMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUiTable() throws Exception {
        // Initialize the database
        uiTableRepository.saveAndFlush(uiTable);

        int databaseSizeBeforeUpdate = uiTableRepository.findAll().size();

        // Update the uiTable
        UiTable updatedUiTable = uiTableRepository.findById(uiTable.getId()).get();
        // Disconnect from session so that the updates on updatedUiTable are not directly saved in db
        em.detach(updatedUiTable);
        updatedUiTable
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .issource(UPDATED_ISSOURCE)
            .isedit(UPDATED_ISEDIT)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .config(UPDATED_CONFIG);

        restUiTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUiTable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUiTable))
            )
            .andExpect(status().isOk());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeUpdate);
        UiTable testUiTable = uiTableList.get(uiTableList.size() - 1);
        assertThat(testUiTable.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiTable.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiTable.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiTable.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiTable.getIssource()).isEqualTo(UPDATED_ISSOURCE);
        assertThat(testUiTable.getIsedit()).isEqualTo(UPDATED_ISEDIT);
        assertThat(testUiTable.getRequirement()).isEqualTo(UPDATED_REQUIREMENT);
        assertThat(testUiTable.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUiTable.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void putNonExistingUiTable() throws Exception {
        int databaseSizeBeforeUpdate = uiTableRepository.findAll().size();
        uiTable.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uiTable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUiTable() throws Exception {
        int databaseSizeBeforeUpdate = uiTableRepository.findAll().size();
        uiTable.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUiTable() throws Exception {
        int databaseSizeBeforeUpdate = uiTableRepository.findAll().size();
        uiTable.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiTableMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiTable)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUiTableWithPatch() throws Exception {
        // Initialize the database
        uiTableRepository.saveAndFlush(uiTable);

        int databaseSizeBeforeUpdate = uiTableRepository.findAll().size();

        // Update the uiTable using partial update
        UiTable partialUpdatedUiTable = new UiTable();
        partialUpdatedUiTable.setId(uiTable.getId());

        partialUpdatedUiTable.issource(UPDATED_ISSOURCE).isedit(UPDATED_ISEDIT).requirement(UPDATED_REQUIREMENT);

        restUiTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiTable))
            )
            .andExpect(status().isOk());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeUpdate);
        UiTable testUiTable = uiTableList.get(uiTableList.size() - 1);
        assertThat(testUiTable.getMenuid()).isEqualTo(DEFAULT_MENUID);
        assertThat(testUiTable.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUiTable.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUiTable.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
        assertThat(testUiTable.getIssource()).isEqualTo(UPDATED_ISSOURCE);
        assertThat(testUiTable.getIsedit()).isEqualTo(UPDATED_ISEDIT);
        assertThat(testUiTable.getRequirement()).isEqualTo(UPDATED_REQUIREMENT);
        assertThat(testUiTable.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testUiTable.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void fullUpdateUiTableWithPatch() throws Exception {
        // Initialize the database
        uiTableRepository.saveAndFlush(uiTable);

        int databaseSizeBeforeUpdate = uiTableRepository.findAll().size();

        // Update the uiTable using partial update
        UiTable partialUpdatedUiTable = new UiTable();
        partialUpdatedUiTable.setId(uiTable.getId());

        partialUpdatedUiTable
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .issource(UPDATED_ISSOURCE)
            .isedit(UPDATED_ISEDIT)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .config(UPDATED_CONFIG);

        restUiTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiTable))
            )
            .andExpect(status().isOk());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeUpdate);
        UiTable testUiTable = uiTableList.get(uiTableList.size() - 1);
        assertThat(testUiTable.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiTable.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiTable.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiTable.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiTable.getIssource()).isEqualTo(UPDATED_ISSOURCE);
        assertThat(testUiTable.getIsedit()).isEqualTo(UPDATED_ISEDIT);
        assertThat(testUiTable.getRequirement()).isEqualTo(UPDATED_REQUIREMENT);
        assertThat(testUiTable.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUiTable.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void patchNonExistingUiTable() throws Exception {
        int databaseSizeBeforeUpdate = uiTableRepository.findAll().size();
        uiTable.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uiTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUiTable() throws Exception {
        int databaseSizeBeforeUpdate = uiTableRepository.findAll().size();
        uiTable.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUiTable() throws Exception {
        int databaseSizeBeforeUpdate = uiTableRepository.findAll().size();
        uiTable.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiTableMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(uiTable)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiTable in the database
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUiTable() throws Exception {
        // Initialize the database
        uiTableRepository.saveAndFlush(uiTable);

        int databaseSizeBeforeDelete = uiTableRepository.findAll().size();

        // Delete the uiTable
        restUiTableMockMvc
            .perform(delete(ENTITY_API_URL_ID, uiTable.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UiTable> uiTableList = uiTableRepository.findAll();
        assertThat(uiTableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
