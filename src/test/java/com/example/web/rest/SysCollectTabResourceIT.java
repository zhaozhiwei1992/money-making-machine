package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.SysCollectTab;
import com.example.repository.SysCollectTabRepository;
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
 * Integration tests for the {@link SysCollectTabResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class SysCollectTabResourceIT {

    private static final String DEFAULT_TAB_CNAME = "AAAAAAAAAA";
    private static final String UPDATED_TAB_CNAME = "BBBBBBBBBB";

    private static final String DEFAULT_TAB_ENAME = "AAAAAAAAAA";
    private static final String UPDATED_TAB_ENAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLE = false;
    private static final Boolean UPDATED_ENABLE = true;

    private static final String ENTITY_API_URL = "/api/sys-collect-tabs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SysCollectTabRepository sysCollectTabRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysCollectTabMockMvc;

    private SysCollectTab sysCollectTab;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysCollectTab createEntity(EntityManager em) {
        SysCollectTab sysCollectTab = new SysCollectTab()
            .tabCname(DEFAULT_TAB_CNAME)
            .tabEname(DEFAULT_TAB_ENAME)
            .config(DEFAULT_CONFIG)
            .enable(DEFAULT_ENABLE);
        return sysCollectTab;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysCollectTab createUpdatedEntity(EntityManager em) {
        SysCollectTab sysCollectTab = new SysCollectTab()
            .tabCname(UPDATED_TAB_CNAME)
            .tabEname(UPDATED_TAB_ENAME)
            .config(UPDATED_CONFIG)
            .enable(UPDATED_ENABLE);
        return sysCollectTab;
    }

    @BeforeEach
    public void initTest() {
        sysCollectTab = createEntity(em);
    }

    @Test
    @Transactional
    void createSysCollectTab() throws Exception {
        int databaseSizeBeforeCreate = sysCollectTabRepository.findAll().size();
        // Create the SysCollectTab
        restSysCollectTabMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysCollectTab)))
            .andExpect(status().isCreated());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeCreate + 1);
        SysCollectTab testSysCollectTab = sysCollectTabList.get(sysCollectTabList.size() - 1);
        assertThat(testSysCollectTab.getTabCname()).isEqualTo(DEFAULT_TAB_CNAME);
        assertThat(testSysCollectTab.getTabEname()).isEqualTo(DEFAULT_TAB_ENAME);
        assertThat(testSysCollectTab.getConfig()).isEqualTo(DEFAULT_CONFIG);
        assertThat(testSysCollectTab.getEnable()).isEqualTo(DEFAULT_ENABLE);
    }

    @Test
    @Transactional
    void createSysCollectTabWithExistingId() throws Exception {
        // Create the SysCollectTab with an existing ID
        sysCollectTab.setId(1L);

        int databaseSizeBeforeCreate = sysCollectTabRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysCollectTabMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysCollectTab)))
            .andExpect(status().isBadRequest());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysCollectTabs() throws Exception {
        // Initialize the database
        sysCollectTabRepository.saveAndFlush(sysCollectTab);

        // Get all the sysCollectTabList
        restSysCollectTabMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCollectTab.getId().intValue())))
            .andExpect(jsonPath("$.[*].tabCname").value(hasItem(DEFAULT_TAB_CNAME)))
            .andExpect(jsonPath("$.[*].tabEname").value(hasItem(DEFAULT_TAB_ENAME)))
            .andExpect(jsonPath("$.[*].config").value(hasItem(DEFAULT_CONFIG)))
            .andExpect(jsonPath("$.[*].enable").value(hasItem(DEFAULT_ENABLE.booleanValue())));
    }

    @Test
    @Transactional
    void getSysCollectTab() throws Exception {
        // Initialize the database
        sysCollectTabRepository.saveAndFlush(sysCollectTab);

        // Get the sysCollectTab
        restSysCollectTabMockMvc
            .perform(get(ENTITY_API_URL_ID, sysCollectTab.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysCollectTab.getId().intValue()))
            .andExpect(jsonPath("$.tabCname").value(DEFAULT_TAB_CNAME))
            .andExpect(jsonPath("$.tabEname").value(DEFAULT_TAB_ENAME))
            .andExpect(jsonPath("$.config").value(DEFAULT_CONFIG))
            .andExpect(jsonPath("$.enable").value(DEFAULT_ENABLE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingSysCollectTab() throws Exception {
        // Get the sysCollectTab
        restSysCollectTabMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSysCollectTab() throws Exception {
        // Initialize the database
        sysCollectTabRepository.saveAndFlush(sysCollectTab);

        int databaseSizeBeforeUpdate = sysCollectTabRepository.findAll().size();

        // Update the sysCollectTab
        SysCollectTab updatedSysCollectTab = sysCollectTabRepository.findById(sysCollectTab.getId()).get();
        // Disconnect from session so that the updates on updatedSysCollectTab are not directly saved in db
        em.detach(updatedSysCollectTab);
        updatedSysCollectTab.tabCname(UPDATED_TAB_CNAME).tabEname(UPDATED_TAB_ENAME).config(UPDATED_CONFIG).enable(UPDATED_ENABLE);

        restSysCollectTabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSysCollectTab.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSysCollectTab))
            )
            .andExpect(status().isOk());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeUpdate);
        SysCollectTab testSysCollectTab = sysCollectTabList.get(sysCollectTabList.size() - 1);
        assertThat(testSysCollectTab.getTabCname()).isEqualTo(UPDATED_TAB_CNAME);
        assertThat(testSysCollectTab.getTabEname()).isEqualTo(UPDATED_TAB_ENAME);
        assertThat(testSysCollectTab.getConfig()).isEqualTo(UPDATED_CONFIG);
        assertThat(testSysCollectTab.getEnable()).isEqualTo(UPDATED_ENABLE);
    }

    @Test
    @Transactional
    void putNonExistingSysCollectTab() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectTabRepository.findAll().size();
        sysCollectTab.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCollectTabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysCollectTab.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysCollectTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysCollectTab() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectTabRepository.findAll().size();
        sysCollectTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysCollectTabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysCollectTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysCollectTab() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectTabRepository.findAll().size();
        sysCollectTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysCollectTabMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysCollectTab)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysCollectTabWithPatch() throws Exception {
        // Initialize the database
        sysCollectTabRepository.saveAndFlush(sysCollectTab);

        int databaseSizeBeforeUpdate = sysCollectTabRepository.findAll().size();

        // Update the sysCollectTab using partial update
        SysCollectTab partialUpdatedSysCollectTab = new SysCollectTab();
        partialUpdatedSysCollectTab.setId(sysCollectTab.getId());

        partialUpdatedSysCollectTab.tabCname(UPDATED_TAB_CNAME);

        restSysCollectTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysCollectTab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysCollectTab))
            )
            .andExpect(status().isOk());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeUpdate);
        SysCollectTab testSysCollectTab = sysCollectTabList.get(sysCollectTabList.size() - 1);
        assertThat(testSysCollectTab.getTabCname()).isEqualTo(UPDATED_TAB_CNAME);
        assertThat(testSysCollectTab.getTabEname()).isEqualTo(DEFAULT_TAB_ENAME);
        assertThat(testSysCollectTab.getConfig()).isEqualTo(DEFAULT_CONFIG);
        assertThat(testSysCollectTab.getEnable()).isEqualTo(DEFAULT_ENABLE);
    }

    @Test
    @Transactional
    void fullUpdateSysCollectTabWithPatch() throws Exception {
        // Initialize the database
        sysCollectTabRepository.saveAndFlush(sysCollectTab);

        int databaseSizeBeforeUpdate = sysCollectTabRepository.findAll().size();

        // Update the sysCollectTab using partial update
        SysCollectTab partialUpdatedSysCollectTab = new SysCollectTab();
        partialUpdatedSysCollectTab.setId(sysCollectTab.getId());

        partialUpdatedSysCollectTab.tabCname(UPDATED_TAB_CNAME).tabEname(UPDATED_TAB_ENAME).config(UPDATED_CONFIG).enable(UPDATED_ENABLE);

        restSysCollectTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysCollectTab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysCollectTab))
            )
            .andExpect(status().isOk());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeUpdate);
        SysCollectTab testSysCollectTab = sysCollectTabList.get(sysCollectTabList.size() - 1);
        assertThat(testSysCollectTab.getTabCname()).isEqualTo(UPDATED_TAB_CNAME);
        assertThat(testSysCollectTab.getTabEname()).isEqualTo(UPDATED_TAB_ENAME);
        assertThat(testSysCollectTab.getConfig()).isEqualTo(UPDATED_CONFIG);
        assertThat(testSysCollectTab.getEnable()).isEqualTo(UPDATED_ENABLE);
    }

    @Test
    @Transactional
    void patchNonExistingSysCollectTab() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectTabRepository.findAll().size();
        sysCollectTab.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCollectTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysCollectTab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysCollectTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysCollectTab() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectTabRepository.findAll().size();
        sysCollectTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysCollectTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysCollectTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysCollectTab() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectTabRepository.findAll().size();
        sysCollectTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysCollectTabMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysCollectTab))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysCollectTab in the database
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysCollectTab() throws Exception {
        // Initialize the database
        sysCollectTabRepository.saveAndFlush(sysCollectTab);

        int databaseSizeBeforeDelete = sysCollectTabRepository.findAll().size();

        // Delete the sysCollectTab
        restSysCollectTabMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysCollectTab.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysCollectTab> sysCollectTabList = sysCollectTabRepository.findAll();
        assertThat(sysCollectTabList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
