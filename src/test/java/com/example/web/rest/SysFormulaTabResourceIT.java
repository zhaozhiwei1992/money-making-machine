package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.SysFormulaTab;
import com.example.repository.SysFormulaTabRepository;
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
 * Integration tests for the {@link SysFormulaTabResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class SysFormulaTabResourceIT {

    private static final String DEFAULT_TAB_ENAME = "AAAAAAAAAA";
    private static final String UPDATED_TAB_ENAME = "BBBBBBBBBB";

    private static final String DEFAULT_COL_ENAME = "AAAAAAAAAA";
    private static final String UPDATED_COL_ENAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAL_FORMULA = "AAAAAAAAAA";
    private static final String UPDATED_CAL_FORMULA = "BBBBBBBBBB";

    private static final String DEFAULT_CAL_FORMULA_DES = "AAAAAAAAAA";
    private static final String UPDATED_CAL_FORMULA_DES = "BBBBBBBBBB";

    private static final String DEFAULT_ROUND_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ROUND_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Boolean DEFAULT_ENABLE = false;
    private static final Boolean UPDATED_ENABLE = true;

    private static final String ENTITY_API_URL = "/api/sys-formula-tabs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SysFormulaTabRepository sysFormulaTabRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysFormulaTabMockMvc;

    private SysFormulaTab sysFormulaTab;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysFormulaTab createEntity(EntityManager em) {
        SysFormulaTab sysFormulaTab = new SysFormulaTab()
            .tabEname(DEFAULT_TAB_ENAME)
            .colEname(DEFAULT_COL_ENAME)
            .calFormula(DEFAULT_CAL_FORMULA)
            .calFormulaDes(DEFAULT_CAL_FORMULA_DES)
            .roundType(DEFAULT_ROUND_TYPE)
            .weight(DEFAULT_WEIGHT)
            .enable(DEFAULT_ENABLE);
        return sysFormulaTab;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysFormulaTab createUpdatedEntity(EntityManager em) {
        SysFormulaTab sysFormulaTab = new SysFormulaTab()
            .tabEname(UPDATED_TAB_ENAME)
            .colEname(UPDATED_COL_ENAME)
            .calFormula(UPDATED_CAL_FORMULA)
            .calFormulaDes(UPDATED_CAL_FORMULA_DES)
            .roundType(UPDATED_ROUND_TYPE)
            .weight(UPDATED_WEIGHT)
            .enable(UPDATED_ENABLE);
        return sysFormulaTab;
    }

    @BeforeEach
    public void initTest() {
        sysFormulaTab = createEntity(em);
    }

    @Test
    @Transactional
    void createSysFormulaTab() throws Exception {
        int databaseSizeBeforeCreate = sysFormulaTabRepository.findAll().size();
        // Create the SysFormulaTab
        restSysFormulaTabMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysFormulaTab)))
            .andExpect(status().isCreated());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeCreate + 1);
        SysFormulaTab testSysFormulaTab = sysFormulaTabList.get(sysFormulaTabList.size() - 1);
        assertThat(testSysFormulaTab.getTabEname()).isEqualTo(DEFAULT_TAB_ENAME);
        assertThat(testSysFormulaTab.getColEname()).isEqualTo(DEFAULT_COL_ENAME);
        assertThat(testSysFormulaTab.getCalFormula()).isEqualTo(DEFAULT_CAL_FORMULA);
        assertThat(testSysFormulaTab.getCalFormulaDes()).isEqualTo(DEFAULT_CAL_FORMULA_DES);
        assertThat(testSysFormulaTab.getRoundType()).isEqualTo(DEFAULT_ROUND_TYPE);
        assertThat(testSysFormulaTab.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testSysFormulaTab.getEnable()).isEqualTo(DEFAULT_ENABLE);
    }

    @Test
    @Transactional
    void createSysFormulaTabWithExistingId() throws Exception {
        // Create the SysFormulaTab with an existing ID
        sysFormulaTab.setId(1L);

        int databaseSizeBeforeCreate = sysFormulaTabRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysFormulaTabMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysFormulaTab)))
            .andExpect(status().isBadRequest());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysFormulaTabs() throws Exception {
        // Initialize the database
        sysFormulaTabRepository.saveAndFlush(sysFormulaTab);

        // Get all the sysFormulaTabList
        restSysFormulaTabMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysFormulaTab.getId().intValue())))
            .andExpect(jsonPath("$.[*].tabEname").value(hasItem(DEFAULT_TAB_ENAME)))
            .andExpect(jsonPath("$.[*].colEname").value(hasItem(DEFAULT_COL_ENAME)))
            .andExpect(jsonPath("$.[*].calFormula").value(hasItem(DEFAULT_CAL_FORMULA)))
            .andExpect(jsonPath("$.[*].calFormulaDes").value(hasItem(DEFAULT_CAL_FORMULA_DES)))
            .andExpect(jsonPath("$.[*].roundType").value(hasItem(DEFAULT_ROUND_TYPE)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].enable").value(hasItem(DEFAULT_ENABLE.booleanValue())));
    }

    @Test
    @Transactional
    void getSysFormulaTab() throws Exception {
        // Initialize the database
        sysFormulaTabRepository.saveAndFlush(sysFormulaTab);

        // Get the sysFormulaTab
        restSysFormulaTabMockMvc
            .perform(get(ENTITY_API_URL_ID, sysFormulaTab.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysFormulaTab.getId().intValue()))
            .andExpect(jsonPath("$.tabEname").value(DEFAULT_TAB_ENAME))
            .andExpect(jsonPath("$.colEname").value(DEFAULT_COL_ENAME))
            .andExpect(jsonPath("$.calFormula").value(DEFAULT_CAL_FORMULA))
            .andExpect(jsonPath("$.calFormulaDes").value(DEFAULT_CAL_FORMULA_DES))
            .andExpect(jsonPath("$.roundType").value(DEFAULT_ROUND_TYPE))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.enable").value(DEFAULT_ENABLE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingSysFormulaTab() throws Exception {
        // Get the sysFormulaTab
        restSysFormulaTabMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSysFormulaTab() throws Exception {
        // Initialize the database
        sysFormulaTabRepository.saveAndFlush(sysFormulaTab);

        int databaseSizeBeforeUpdate = sysFormulaTabRepository.findAll().size();

        // Update the sysFormulaTab
        SysFormulaTab updatedSysFormulaTab = sysFormulaTabRepository.findById(sysFormulaTab.getId()).get();
        // Disconnect from session so that the updates on updatedSysFormulaTab are not directly saved in db
        em.detach(updatedSysFormulaTab);
        updatedSysFormulaTab
            .tabEname(UPDATED_TAB_ENAME)
            .colEname(UPDATED_COL_ENAME)
            .calFormula(UPDATED_CAL_FORMULA)
            .calFormulaDes(UPDATED_CAL_FORMULA_DES)
            .roundType(UPDATED_ROUND_TYPE)
            .weight(UPDATED_WEIGHT)
            .enable(UPDATED_ENABLE);

        restSysFormulaTabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSysFormulaTab.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSysFormulaTab))
            )
            .andExpect(status().isOk());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeUpdate);
        SysFormulaTab testSysFormulaTab = sysFormulaTabList.get(sysFormulaTabList.size() - 1);
        assertThat(testSysFormulaTab.getTabEname()).isEqualTo(UPDATED_TAB_ENAME);
        assertThat(testSysFormulaTab.getColEname()).isEqualTo(UPDATED_COL_ENAME);
        assertThat(testSysFormulaTab.getCalFormula()).isEqualTo(UPDATED_CAL_FORMULA);
        assertThat(testSysFormulaTab.getCalFormulaDes()).isEqualTo(UPDATED_CAL_FORMULA_DES);
        assertThat(testSysFormulaTab.getRoundType()).isEqualTo(UPDATED_ROUND_TYPE);
        assertThat(testSysFormulaTab.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testSysFormulaTab.getEnable()).isEqualTo(UPDATED_ENABLE);
    }

    @Test
    @Transactional
    void putNonExistingSysFormulaTab() throws Exception {
        int databaseSizeBeforeUpdate = sysFormulaTabRepository.findAll().size();
        sysFormulaTab.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysFormulaTabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysFormulaTab.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysFormulaTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysFormulaTab() throws Exception {
        int databaseSizeBeforeUpdate = sysFormulaTabRepository.findAll().size();
        sysFormulaTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysFormulaTabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysFormulaTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysFormulaTab() throws Exception {
        int databaseSizeBeforeUpdate = sysFormulaTabRepository.findAll().size();
        sysFormulaTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysFormulaTabMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysFormulaTab)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysFormulaTabWithPatch() throws Exception {
        // Initialize the database
        sysFormulaTabRepository.saveAndFlush(sysFormulaTab);

        int databaseSizeBeforeUpdate = sysFormulaTabRepository.findAll().size();

        // Update the sysFormulaTab using partial update
        SysFormulaTab partialUpdatedSysFormulaTab = new SysFormulaTab();
        partialUpdatedSysFormulaTab.setId(sysFormulaTab.getId());

        partialUpdatedSysFormulaTab
            .tabEname(UPDATED_TAB_ENAME)
            .colEname(UPDATED_COL_ENAME)
            .calFormula(UPDATED_CAL_FORMULA)
            .weight(UPDATED_WEIGHT);

        restSysFormulaTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysFormulaTab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysFormulaTab))
            )
            .andExpect(status().isOk());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeUpdate);
        SysFormulaTab testSysFormulaTab = sysFormulaTabList.get(sysFormulaTabList.size() - 1);
        assertThat(testSysFormulaTab.getTabEname()).isEqualTo(UPDATED_TAB_ENAME);
        assertThat(testSysFormulaTab.getColEname()).isEqualTo(UPDATED_COL_ENAME);
        assertThat(testSysFormulaTab.getCalFormula()).isEqualTo(UPDATED_CAL_FORMULA);
        assertThat(testSysFormulaTab.getCalFormulaDes()).isEqualTo(DEFAULT_CAL_FORMULA_DES);
        assertThat(testSysFormulaTab.getRoundType()).isEqualTo(DEFAULT_ROUND_TYPE);
        assertThat(testSysFormulaTab.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testSysFormulaTab.getEnable()).isEqualTo(DEFAULT_ENABLE);
    }

    @Test
    @Transactional
    void fullUpdateSysFormulaTabWithPatch() throws Exception {
        // Initialize the database
        sysFormulaTabRepository.saveAndFlush(sysFormulaTab);

        int databaseSizeBeforeUpdate = sysFormulaTabRepository.findAll().size();

        // Update the sysFormulaTab using partial update
        SysFormulaTab partialUpdatedSysFormulaTab = new SysFormulaTab();
        partialUpdatedSysFormulaTab.setId(sysFormulaTab.getId());

        partialUpdatedSysFormulaTab
            .tabEname(UPDATED_TAB_ENAME)
            .colEname(UPDATED_COL_ENAME)
            .calFormula(UPDATED_CAL_FORMULA)
            .calFormulaDes(UPDATED_CAL_FORMULA_DES)
            .roundType(UPDATED_ROUND_TYPE)
            .weight(UPDATED_WEIGHT)
            .enable(UPDATED_ENABLE);

        restSysFormulaTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysFormulaTab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysFormulaTab))
            )
            .andExpect(status().isOk());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeUpdate);
        SysFormulaTab testSysFormulaTab = sysFormulaTabList.get(sysFormulaTabList.size() - 1);
        assertThat(testSysFormulaTab.getTabEname()).isEqualTo(UPDATED_TAB_ENAME);
        assertThat(testSysFormulaTab.getColEname()).isEqualTo(UPDATED_COL_ENAME);
        assertThat(testSysFormulaTab.getCalFormula()).isEqualTo(UPDATED_CAL_FORMULA);
        assertThat(testSysFormulaTab.getCalFormulaDes()).isEqualTo(UPDATED_CAL_FORMULA_DES);
        assertThat(testSysFormulaTab.getRoundType()).isEqualTo(UPDATED_ROUND_TYPE);
        assertThat(testSysFormulaTab.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testSysFormulaTab.getEnable()).isEqualTo(UPDATED_ENABLE);
    }

    @Test
    @Transactional
    void patchNonExistingSysFormulaTab() throws Exception {
        int databaseSizeBeforeUpdate = sysFormulaTabRepository.findAll().size();
        sysFormulaTab.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysFormulaTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysFormulaTab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysFormulaTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysFormulaTab() throws Exception {
        int databaseSizeBeforeUpdate = sysFormulaTabRepository.findAll().size();
        sysFormulaTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysFormulaTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysFormulaTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysFormulaTab() throws Exception {
        int databaseSizeBeforeUpdate = sysFormulaTabRepository.findAll().size();
        sysFormulaTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysFormulaTabMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysFormulaTab))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysFormulaTab in the database
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysFormulaTab() throws Exception {
        // Initialize the database
        sysFormulaTabRepository.saveAndFlush(sysFormulaTab);

        int databaseSizeBeforeDelete = sysFormulaTabRepository.findAll().size();

        // Delete the sysFormulaTab
        restSysFormulaTabMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysFormulaTab.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysFormulaTab> sysFormulaTabList = sysFormulaTabRepository.findAll();
        assertThat(sysFormulaTabList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
