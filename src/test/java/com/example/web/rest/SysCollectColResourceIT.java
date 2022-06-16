package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.SysCollectCol;
import com.example.repository.SysCollectColRepository;
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
 * Integration tests for the {@link SysCollectColResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class SysCollectColResourceIT {

    private static final String DEFAULT_COL_CNAME = "AAAAAAAAAA";
    private static final String UPDATED_COL_CNAME = "BBBBBBBBBB";

    private static final String DEFAULT_COL_ENAME = "AAAAAAAAAA";
    private static final String UPDATED_COL_ENAME = "BBBBBBBBBB";

    private static final Long DEFAULT_TAB_ID = 1L;
    private static final Long UPDATED_TAB_ID = 2L;

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_EDIT = false;
    private static final Boolean UPDATED_IS_EDIT = true;

    private static final Boolean DEFAULT_REQUIREMENT = false;
    private static final Boolean UPDATED_REQUIREMENT = true;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sys-collect-cols";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SysCollectColRepository sysCollectColRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysCollectColMockMvc;

    private SysCollectCol sysCollectCol;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysCollectCol createEntity(EntityManager em) {
        SysCollectCol sysCollectCol = new SysCollectCol()
            .colCname(DEFAULT_COL_CNAME)
            .colEname(DEFAULT_COL_ENAME)
            .tabId(DEFAULT_TAB_ID)
            .orderNum(DEFAULT_ORDER_NUM)
            .source(DEFAULT_SOURCE)
            .isEdit(DEFAULT_IS_EDIT)
            .requirement(DEFAULT_REQUIREMENT)
            .type(DEFAULT_TYPE)
            .config(DEFAULT_CONFIG);
        return sysCollectCol;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysCollectCol createUpdatedEntity(EntityManager em) {
        SysCollectCol sysCollectCol = new SysCollectCol()
            .colCname(UPDATED_COL_CNAME)
            .colEname(UPDATED_COL_ENAME)
            .tabId(UPDATED_TAB_ID)
            .orderNum(UPDATED_ORDER_NUM)
            .source(UPDATED_SOURCE)
            .isEdit(UPDATED_IS_EDIT)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .config(UPDATED_CONFIG);
        return sysCollectCol;
    }

    @BeforeEach
    public void initTest() {
        sysCollectCol = createEntity(em);
    }

    @Test
    @Transactional
    void createSysCollectCol() throws Exception {
        int databaseSizeBeforeCreate = sysCollectColRepository.findAll().size();
        // Create the SysCollectCol
        restSysCollectColMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysCollectCol)))
            .andExpect(status().isCreated());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeCreate + 1);
        SysCollectCol testSysCollectCol = sysCollectColList.get(sysCollectColList.size() - 1);
        assertThat(testSysCollectCol.getColCname()).isEqualTo(DEFAULT_COL_CNAME);
        assertThat(testSysCollectCol.getColEname()).isEqualTo(DEFAULT_COL_ENAME);
        assertThat(testSysCollectCol.getTabId()).isEqualTo(DEFAULT_TAB_ID);
        assertThat(testSysCollectCol.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testSysCollectCol.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testSysCollectCol.getIsEdit()).isEqualTo(DEFAULT_IS_EDIT);
        assertThat(testSysCollectCol.getRequirement()).isEqualTo(DEFAULT_REQUIREMENT);
        assertThat(testSysCollectCol.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSysCollectCol.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void createSysCollectColWithExistingId() throws Exception {
        // Create the SysCollectCol with an existing ID
        sysCollectCol.setId(1L);

        int databaseSizeBeforeCreate = sysCollectColRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysCollectColMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysCollectCol)))
            .andExpect(status().isBadRequest());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysCollectCols() throws Exception {
        // Initialize the database
        sysCollectColRepository.saveAndFlush(sysCollectCol);

        // Get all the sysCollectColList
        restSysCollectColMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCollectCol.getId().intValue())))
            .andExpect(jsonPath("$.[*].colCname").value(hasItem(DEFAULT_COL_CNAME)))
            .andExpect(jsonPath("$.[*].colEname").value(hasItem(DEFAULT_COL_ENAME)))
            .andExpect(jsonPath("$.[*].tabId").value(hasItem(DEFAULT_TAB_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].isEdit").value(hasItem(DEFAULT_IS_EDIT.booleanValue())))
            .andExpect(jsonPath("$.[*].requirement").value(hasItem(DEFAULT_REQUIREMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].config").value(hasItem(DEFAULT_CONFIG)));
    }

    @Test
    @Transactional
    void getSysCollectCol() throws Exception {
        // Initialize the database
        sysCollectColRepository.saveAndFlush(sysCollectCol);

        // Get the sysCollectCol
        restSysCollectColMockMvc
            .perform(get(ENTITY_API_URL_ID, sysCollectCol.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysCollectCol.getId().intValue()))
            .andExpect(jsonPath("$.colCname").value(DEFAULT_COL_CNAME))
            .andExpect(jsonPath("$.colEname").value(DEFAULT_COL_ENAME))
            .andExpect(jsonPath("$.tabId").value(DEFAULT_TAB_ID.intValue()))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE))
            .andExpect(jsonPath("$.isEdit").value(DEFAULT_IS_EDIT.booleanValue()))
            .andExpect(jsonPath("$.requirement").value(DEFAULT_REQUIREMENT.booleanValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.config").value(DEFAULT_CONFIG));
    }

    @Test
    @Transactional
    void getNonExistingSysCollectCol() throws Exception {
        // Get the sysCollectCol
        restSysCollectColMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSysCollectCol() throws Exception {
        // Initialize the database
        sysCollectColRepository.saveAndFlush(sysCollectCol);

        int databaseSizeBeforeUpdate = sysCollectColRepository.findAll().size();

        // Update the sysCollectCol
        SysCollectCol updatedSysCollectCol = sysCollectColRepository.findById(sysCollectCol.getId()).get();
        // Disconnect from session so that the updates on updatedSysCollectCol are not directly saved in db
        em.detach(updatedSysCollectCol);
        updatedSysCollectCol
            .colCname(UPDATED_COL_CNAME)
            .colEname(UPDATED_COL_ENAME)
            .tabId(UPDATED_TAB_ID)
            .orderNum(UPDATED_ORDER_NUM)
            .source(UPDATED_SOURCE)
            .isEdit(UPDATED_IS_EDIT)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .config(UPDATED_CONFIG);

        restSysCollectColMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSysCollectCol.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSysCollectCol))
            )
            .andExpect(status().isOk());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeUpdate);
        SysCollectCol testSysCollectCol = sysCollectColList.get(sysCollectColList.size() - 1);
        assertThat(testSysCollectCol.getColCname()).isEqualTo(UPDATED_COL_CNAME);
        assertThat(testSysCollectCol.getColEname()).isEqualTo(UPDATED_COL_ENAME);
        assertThat(testSysCollectCol.getTabId()).isEqualTo(UPDATED_TAB_ID);
        assertThat(testSysCollectCol.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testSysCollectCol.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testSysCollectCol.getIsEdit()).isEqualTo(UPDATED_IS_EDIT);
        assertThat(testSysCollectCol.getRequirement()).isEqualTo(UPDATED_REQUIREMENT);
        assertThat(testSysCollectCol.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSysCollectCol.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void putNonExistingSysCollectCol() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectColRepository.findAll().size();
        sysCollectCol.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCollectColMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysCollectCol.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysCollectCol))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysCollectCol() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectColRepository.findAll().size();
        sysCollectCol.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysCollectColMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysCollectCol))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysCollectCol() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectColRepository.findAll().size();
        sysCollectCol.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysCollectColMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysCollectCol)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysCollectColWithPatch() throws Exception {
        // Initialize the database
        sysCollectColRepository.saveAndFlush(sysCollectCol);

        int databaseSizeBeforeUpdate = sysCollectColRepository.findAll().size();

        // Update the sysCollectCol using partial update
        SysCollectCol partialUpdatedSysCollectCol = new SysCollectCol();
        partialUpdatedSysCollectCol.setId(sysCollectCol.getId());

        partialUpdatedSysCollectCol.colEname(UPDATED_COL_ENAME).source(UPDATED_SOURCE).requirement(UPDATED_REQUIREMENT).type(UPDATED_TYPE);

        restSysCollectColMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysCollectCol.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysCollectCol))
            )
            .andExpect(status().isOk());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeUpdate);
        SysCollectCol testSysCollectCol = sysCollectColList.get(sysCollectColList.size() - 1);
        assertThat(testSysCollectCol.getColCname()).isEqualTo(DEFAULT_COL_CNAME);
        assertThat(testSysCollectCol.getColEname()).isEqualTo(UPDATED_COL_ENAME);
        assertThat(testSysCollectCol.getTabId()).isEqualTo(DEFAULT_TAB_ID);
        assertThat(testSysCollectCol.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testSysCollectCol.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testSysCollectCol.getIsEdit()).isEqualTo(DEFAULT_IS_EDIT);
        assertThat(testSysCollectCol.getRequirement()).isEqualTo(UPDATED_REQUIREMENT);
        assertThat(testSysCollectCol.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSysCollectCol.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void fullUpdateSysCollectColWithPatch() throws Exception {
        // Initialize the database
        sysCollectColRepository.saveAndFlush(sysCollectCol);

        int databaseSizeBeforeUpdate = sysCollectColRepository.findAll().size();

        // Update the sysCollectCol using partial update
        SysCollectCol partialUpdatedSysCollectCol = new SysCollectCol();
        partialUpdatedSysCollectCol.setId(sysCollectCol.getId());

        partialUpdatedSysCollectCol
            .colCname(UPDATED_COL_CNAME)
            .colEname(UPDATED_COL_ENAME)
            .tabId(UPDATED_TAB_ID)
            .orderNum(UPDATED_ORDER_NUM)
            .source(UPDATED_SOURCE)
            .isEdit(UPDATED_IS_EDIT)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .config(UPDATED_CONFIG);

        restSysCollectColMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysCollectCol.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysCollectCol))
            )
            .andExpect(status().isOk());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeUpdate);
        SysCollectCol testSysCollectCol = sysCollectColList.get(sysCollectColList.size() - 1);
        assertThat(testSysCollectCol.getColCname()).isEqualTo(UPDATED_COL_CNAME);
        assertThat(testSysCollectCol.getColEname()).isEqualTo(UPDATED_COL_ENAME);
        assertThat(testSysCollectCol.getTabId()).isEqualTo(UPDATED_TAB_ID);
        assertThat(testSysCollectCol.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testSysCollectCol.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testSysCollectCol.getIsEdit()).isEqualTo(UPDATED_IS_EDIT);
        assertThat(testSysCollectCol.getRequirement()).isEqualTo(UPDATED_REQUIREMENT);
        assertThat(testSysCollectCol.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSysCollectCol.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void patchNonExistingSysCollectCol() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectColRepository.findAll().size();
        sysCollectCol.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCollectColMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysCollectCol.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysCollectCol))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysCollectCol() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectColRepository.findAll().size();
        sysCollectCol.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysCollectColMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysCollectCol))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysCollectCol() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectColRepository.findAll().size();
        sysCollectCol.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysCollectColMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysCollectCol))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysCollectCol in the database
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysCollectCol() throws Exception {
        // Initialize the database
        sysCollectColRepository.saveAndFlush(sysCollectCol);

        int databaseSizeBeforeDelete = sysCollectColRepository.findAll().size();

        // Delete the sysCollectCol
        restSysCollectColMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysCollectCol.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysCollectCol> sysCollectColList = sysCollectColRepository.findAll();
        assertThat(sysCollectColList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
