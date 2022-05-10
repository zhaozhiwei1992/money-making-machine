package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.UiTab;
import com.example.repository.UiTabRepository;
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
 * Integration tests for the {@link UiTabResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UiTabResourceIT {

    private static final Long DEFAULT_MENUID = 1L;
    private static final Long UPDATED_MENUID = 2L;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERNUM = 1;
    private static final Integer UPDATED_ORDERNUM = 2;

    private static final String DEFAULT_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ui-tabs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UiTabRepository uiTabRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUiTabMockMvc;

    private UiTab uiTab;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiTab createEntity(EntityManager em) {
        UiTab uiTab = new UiTab()
            .menuid(DEFAULT_MENUID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .ordernum(DEFAULT_ORDERNUM)
            .config(DEFAULT_CONFIG);
        return uiTab;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiTab createUpdatedEntity(EntityManager em) {
        UiTab uiTab = new UiTab()
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .config(UPDATED_CONFIG);
        return uiTab;
    }

    @BeforeEach
    public void initTest() {
        uiTab = createEntity(em);
    }

    @Test
    @Transactional
    void createUiTab() throws Exception {
        int databaseSizeBeforeCreate = uiTabRepository.findAll().size();
        // Create the UiTab
        restUiTabMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiTab)))
            .andExpect(status().isCreated());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeCreate + 1);
        UiTab testUiTab = uiTabList.get(uiTabList.size() - 1);
        assertThat(testUiTab.getMenuid()).isEqualTo(DEFAULT_MENUID);
        assertThat(testUiTab.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUiTab.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUiTab.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
        assertThat(testUiTab.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void createUiTabWithExistingId() throws Exception {
        // Create the UiTab with an existing ID
        uiTab.setId(1L);

        int databaseSizeBeforeCreate = uiTabRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUiTabMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiTab)))
            .andExpect(status().isBadRequest());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUiTabs() throws Exception {
        // Initialize the database
        uiTabRepository.saveAndFlush(uiTab);

        // Get all the uiTabList
        restUiTabMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uiTab.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuid").value(hasItem(DEFAULT_MENUID.intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].ordernum").value(hasItem(DEFAULT_ORDERNUM)))
            .andExpect(jsonPath("$.[*].config").value(hasItem(DEFAULT_CONFIG)));
    }

    @Test
    @Transactional
    void getUiTab() throws Exception {
        // Initialize the database
        uiTabRepository.saveAndFlush(uiTab);

        // Get the uiTab
        restUiTabMockMvc
            .perform(get(ENTITY_API_URL_ID, uiTab.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uiTab.getId().intValue()))
            .andExpect(jsonPath("$.menuid").value(DEFAULT_MENUID.intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.ordernum").value(DEFAULT_ORDERNUM))
            .andExpect(jsonPath("$.config").value(DEFAULT_CONFIG));
    }

    @Test
    @Transactional
    void getNonExistingUiTab() throws Exception {
        // Get the uiTab
        restUiTabMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUiTab() throws Exception {
        // Initialize the database
        uiTabRepository.saveAndFlush(uiTab);

        int databaseSizeBeforeUpdate = uiTabRepository.findAll().size();

        // Update the uiTab
        UiTab updatedUiTab = uiTabRepository.findById(uiTab.getId()).get();
        // Disconnect from session so that the updates on updatedUiTab are not directly saved in db
        em.detach(updatedUiTab);
        updatedUiTab.menuid(UPDATED_MENUID).code(UPDATED_CODE).name(UPDATED_NAME).ordernum(UPDATED_ORDERNUM).config(UPDATED_CONFIG);

        restUiTabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUiTab.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUiTab))
            )
            .andExpect(status().isOk());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeUpdate);
        UiTab testUiTab = uiTabList.get(uiTabList.size() - 1);
        assertThat(testUiTab.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiTab.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiTab.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiTab.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiTab.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void putNonExistingUiTab() throws Exception {
        int databaseSizeBeforeUpdate = uiTabRepository.findAll().size();
        uiTab.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiTabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uiTab.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUiTab() throws Exception {
        int databaseSizeBeforeUpdate = uiTabRepository.findAll().size();
        uiTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiTabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUiTab() throws Exception {
        int databaseSizeBeforeUpdate = uiTabRepository.findAll().size();
        uiTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiTabMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiTab)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUiTabWithPatch() throws Exception {
        // Initialize the database
        uiTabRepository.saveAndFlush(uiTab);

        int databaseSizeBeforeUpdate = uiTabRepository.findAll().size();

        // Update the uiTab using partial update
        UiTab partialUpdatedUiTab = new UiTab();
        partialUpdatedUiTab.setId(uiTab.getId());

        partialUpdatedUiTab.ordernum(UPDATED_ORDERNUM);

        restUiTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiTab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiTab))
            )
            .andExpect(status().isOk());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeUpdate);
        UiTab testUiTab = uiTabList.get(uiTabList.size() - 1);
        assertThat(testUiTab.getMenuid()).isEqualTo(DEFAULT_MENUID);
        assertThat(testUiTab.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUiTab.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUiTab.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiTab.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void fullUpdateUiTabWithPatch() throws Exception {
        // Initialize the database
        uiTabRepository.saveAndFlush(uiTab);

        int databaseSizeBeforeUpdate = uiTabRepository.findAll().size();

        // Update the uiTab using partial update
        UiTab partialUpdatedUiTab = new UiTab();
        partialUpdatedUiTab.setId(uiTab.getId());

        partialUpdatedUiTab.menuid(UPDATED_MENUID).code(UPDATED_CODE).name(UPDATED_NAME).ordernum(UPDATED_ORDERNUM).config(UPDATED_CONFIG);

        restUiTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiTab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiTab))
            )
            .andExpect(status().isOk());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeUpdate);
        UiTab testUiTab = uiTabList.get(uiTabList.size() - 1);
        assertThat(testUiTab.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiTab.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiTab.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiTab.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiTab.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void patchNonExistingUiTab() throws Exception {
        int databaseSizeBeforeUpdate = uiTabRepository.findAll().size();
        uiTab.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uiTab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUiTab() throws Exception {
        int databaseSizeBeforeUpdate = uiTabRepository.findAll().size();
        uiTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiTabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiTab))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUiTab() throws Exception {
        int databaseSizeBeforeUpdate = uiTabRepository.findAll().size();
        uiTab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiTabMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(uiTab)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiTab in the database
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUiTab() throws Exception {
        // Initialize the database
        uiTabRepository.saveAndFlush(uiTab);

        int databaseSizeBeforeDelete = uiTabRepository.findAll().size();

        // Delete the uiTab
        restUiTabMockMvc
            .perform(delete(ENTITY_API_URL_ID, uiTab.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UiTab> uiTabList = uiTabRepository.findAll();
        assertThat(uiTabList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
