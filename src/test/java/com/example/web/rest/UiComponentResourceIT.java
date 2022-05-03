package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.UiComponent;
import com.example.repository.UiComponentRepository;
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
 * Integration tests for the {@link UiComponentResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UiComponentResourceIT {

    private static final Long DEFAULT_MENUID = 1L;
    private static final Long UPDATED_MENUID = 2L;

    private static final Integer DEFAULT_ORDERNUM = 1;
    private static final Integer UPDATED_ORDERNUM = 2;

    private static final String DEFAULT_COMPONENTID = "AAAAAAAAAA";
    private static final String UPDATED_COMPONENTID = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ui-components";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UiComponentRepository uiComponentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUiComponentMockMvc;

    private UiComponent uiComponent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiComponent createEntity(EntityManager em) {
        UiComponent uiComponent = new UiComponent()
            .menuid(DEFAULT_MENUID)
            .ordernum(DEFAULT_ORDERNUM)
            .componentid(DEFAULT_COMPONENTID)
            .config(DEFAULT_CONFIG);
        return uiComponent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiComponent createUpdatedEntity(EntityManager em) {
        UiComponent uiComponent = new UiComponent()
            .menuid(UPDATED_MENUID)
            .ordernum(UPDATED_ORDERNUM)
            .componentid(UPDATED_COMPONENTID)
            .config(UPDATED_CONFIG);
        return uiComponent;
    }

    @BeforeEach
    public void initTest() {
        uiComponent = createEntity(em);
    }

    @Test
    @Transactional
    void createUiComponent() throws Exception {
        int databaseSizeBeforeCreate = uiComponentRepository.findAll().size();
        // Create the UiComponent
        restUiComponentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiComponent)))
            .andExpect(status().isCreated());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeCreate + 1);
        UiComponent testUiComponent = uiComponentList.get(uiComponentList.size() - 1);
        assertThat(testUiComponent.getMenuid()).isEqualTo(DEFAULT_MENUID);
        assertThat(testUiComponent.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
        assertThat(testUiComponent.getComponentid()).isEqualTo(DEFAULT_COMPONENTID);
        assertThat(testUiComponent.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void createUiComponentWithExistingId() throws Exception {
        // Create the UiComponent with an existing ID
        uiComponent.setId(1L);

        int databaseSizeBeforeCreate = uiComponentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUiComponentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiComponent)))
            .andExpect(status().isBadRequest());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUiComponents() throws Exception {
        // Initialize the database
        uiComponentRepository.saveAndFlush(uiComponent);

        // Get all the uiComponentList
        restUiComponentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uiComponent.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuid").value(hasItem(DEFAULT_MENUID.intValue())))
            .andExpect(jsonPath("$.[*].ordernum").value(hasItem(DEFAULT_ORDERNUM)))
            .andExpect(jsonPath("$.[*].componentid").value(hasItem(DEFAULT_COMPONENTID)))
            .andExpect(jsonPath("$.[*].config").value(hasItem(DEFAULT_CONFIG)));
    }

    @Test
    @Transactional
    void getUiComponent() throws Exception {
        // Initialize the database
        uiComponentRepository.saveAndFlush(uiComponent);

        // Get the uiComponent
        restUiComponentMockMvc
            .perform(get(ENTITY_API_URL_ID, uiComponent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uiComponent.getId().intValue()))
            .andExpect(jsonPath("$.menuid").value(DEFAULT_MENUID.intValue()))
            .andExpect(jsonPath("$.ordernum").value(DEFAULT_ORDERNUM))
            .andExpect(jsonPath("$.componentid").value(DEFAULT_COMPONENTID))
            .andExpect(jsonPath("$.config").value(DEFAULT_CONFIG));
    }

    @Test
    @Transactional
    void getNonExistingUiComponent() throws Exception {
        // Get the uiComponent
        restUiComponentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUiComponent() throws Exception {
        // Initialize the database
        uiComponentRepository.saveAndFlush(uiComponent);

        int databaseSizeBeforeUpdate = uiComponentRepository.findAll().size();

        // Update the uiComponent
        UiComponent updatedUiComponent = uiComponentRepository.findById(uiComponent.getId()).get();
        // Disconnect from session so that the updates on updatedUiComponent are not directly saved in db
        em.detach(updatedUiComponent);
        updatedUiComponent.menuid(UPDATED_MENUID).ordernum(UPDATED_ORDERNUM).componentid(UPDATED_COMPONENTID).config(UPDATED_CONFIG);

        restUiComponentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUiComponent.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUiComponent))
            )
            .andExpect(status().isOk());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeUpdate);
        UiComponent testUiComponent = uiComponentList.get(uiComponentList.size() - 1);
        assertThat(testUiComponent.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiComponent.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiComponent.getComponentid()).isEqualTo(UPDATED_COMPONENTID);
        assertThat(testUiComponent.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void putNonExistingUiComponent() throws Exception {
        int databaseSizeBeforeUpdate = uiComponentRepository.findAll().size();
        uiComponent.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiComponentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uiComponent.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiComponent))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUiComponent() throws Exception {
        int databaseSizeBeforeUpdate = uiComponentRepository.findAll().size();
        uiComponent.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiComponentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiComponent))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUiComponent() throws Exception {
        int databaseSizeBeforeUpdate = uiComponentRepository.findAll().size();
        uiComponent.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiComponentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiComponent)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUiComponentWithPatch() throws Exception {
        // Initialize the database
        uiComponentRepository.saveAndFlush(uiComponent);

        int databaseSizeBeforeUpdate = uiComponentRepository.findAll().size();

        // Update the uiComponent using partial update
        UiComponent partialUpdatedUiComponent = new UiComponent();
        partialUpdatedUiComponent.setId(uiComponent.getId());

        partialUpdatedUiComponent.config(UPDATED_CONFIG);

        restUiComponentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiComponent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiComponent))
            )
            .andExpect(status().isOk());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeUpdate);
        UiComponent testUiComponent = uiComponentList.get(uiComponentList.size() - 1);
        assertThat(testUiComponent.getMenuid()).isEqualTo(DEFAULT_MENUID);
        assertThat(testUiComponent.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
        assertThat(testUiComponent.getComponentid()).isEqualTo(DEFAULT_COMPONENTID);
        assertThat(testUiComponent.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void fullUpdateUiComponentWithPatch() throws Exception {
        // Initialize the database
        uiComponentRepository.saveAndFlush(uiComponent);

        int databaseSizeBeforeUpdate = uiComponentRepository.findAll().size();

        // Update the uiComponent using partial update
        UiComponent partialUpdatedUiComponent = new UiComponent();
        partialUpdatedUiComponent.setId(uiComponent.getId());

        partialUpdatedUiComponent.menuid(UPDATED_MENUID).ordernum(UPDATED_ORDERNUM).componentid(UPDATED_COMPONENTID).config(UPDATED_CONFIG);

        restUiComponentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiComponent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiComponent))
            )
            .andExpect(status().isOk());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeUpdate);
        UiComponent testUiComponent = uiComponentList.get(uiComponentList.size() - 1);
        assertThat(testUiComponent.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiComponent.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiComponent.getComponentid()).isEqualTo(UPDATED_COMPONENTID);
        assertThat(testUiComponent.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void patchNonExistingUiComponent() throws Exception {
        int databaseSizeBeforeUpdate = uiComponentRepository.findAll().size();
        uiComponent.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiComponentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uiComponent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiComponent))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUiComponent() throws Exception {
        int databaseSizeBeforeUpdate = uiComponentRepository.findAll().size();
        uiComponent.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiComponentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiComponent))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUiComponent() throws Exception {
        int databaseSizeBeforeUpdate = uiComponentRepository.findAll().size();
        uiComponent.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiComponentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(uiComponent))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiComponent in the database
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUiComponent() throws Exception {
        // Initialize the database
        uiComponentRepository.saveAndFlush(uiComponent);

        int databaseSizeBeforeDelete = uiComponentRepository.findAll().size();

        // Delete the uiComponent
        restUiComponentMockMvc
            .perform(delete(ENTITY_API_URL_ID, uiComponent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UiComponent> uiComponentList = uiComponentRepository.findAll();
        assertThat(uiComponentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
