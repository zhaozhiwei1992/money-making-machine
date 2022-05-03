package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.UiQueryform;
import com.example.repository.UiQueryformRepository;
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
 * Integration tests for the {@link UiQueryformResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UiQueryformResourceIT {

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

    private static final Boolean DEFAULT_REQUIREMENT = false;
    private static final Boolean UPDATED_REQUIREMENT = true;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PLACEHOLDER = "AAAAAAAAAA";
    private static final String UPDATED_PLACEHOLDER = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ui-queryforms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UiQueryformRepository uiQueryformRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUiQueryformMockMvc;

    private UiQueryform uiQueryform;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiQueryform createEntity(EntityManager em) {
        UiQueryform uiQueryform = new UiQueryform()
            .menuid(DEFAULT_MENUID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .ordernum(DEFAULT_ORDERNUM)
            .issource(DEFAULT_ISSOURCE)
            .requirement(DEFAULT_REQUIREMENT)
            .type(DEFAULT_TYPE)
            .placeholder(DEFAULT_PLACEHOLDER)
            .config(DEFAULT_CONFIG);
        return uiQueryform;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiQueryform createUpdatedEntity(EntityManager em) {
        UiQueryform uiQueryform = new UiQueryform()
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .issource(UPDATED_ISSOURCE)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .placeholder(UPDATED_PLACEHOLDER)
            .config(UPDATED_CONFIG);
        return uiQueryform;
    }

    @BeforeEach
    public void initTest() {
        uiQueryform = createEntity(em);
    }

    @Test
    @Transactional
    void createUiQueryform() throws Exception {
        int databaseSizeBeforeCreate = uiQueryformRepository.findAll().size();
        // Create the UiQueryform
        restUiQueryformMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiQueryform)))
            .andExpect(status().isCreated());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeCreate + 1);
        UiQueryform testUiQueryform = uiQueryformList.get(uiQueryformList.size() - 1);
        assertThat(testUiQueryform.getMenuid()).isEqualTo(DEFAULT_MENUID);
        assertThat(testUiQueryform.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUiQueryform.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUiQueryform.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
        assertThat(testUiQueryform.getIssource()).isEqualTo(DEFAULT_ISSOURCE);
        assertThat(testUiQueryform.getRequirement()).isEqualTo(DEFAULT_REQUIREMENT);
        assertThat(testUiQueryform.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testUiQueryform.getPlaceholder()).isEqualTo(DEFAULT_PLACEHOLDER);
        assertThat(testUiQueryform.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void createUiQueryformWithExistingId() throws Exception {
        // Create the UiQueryform with an existing ID
        uiQueryform.setId(1L);

        int databaseSizeBeforeCreate = uiQueryformRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUiQueryformMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiQueryform)))
            .andExpect(status().isBadRequest());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUiQueryforms() throws Exception {
        // Initialize the database
        uiQueryformRepository.saveAndFlush(uiQueryform);

        // Get all the uiQueryformList
        restUiQueryformMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uiQueryform.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuid").value(hasItem(DEFAULT_MENUID.intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].ordernum").value(hasItem(DEFAULT_ORDERNUM)))
            .andExpect(jsonPath("$.[*].issource").value(hasItem(DEFAULT_ISSOURCE.booleanValue())))
            .andExpect(jsonPath("$.[*].requirement").value(hasItem(DEFAULT_REQUIREMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].placeholder").value(hasItem(DEFAULT_PLACEHOLDER)))
            .andExpect(jsonPath("$.[*].config").value(hasItem(DEFAULT_CONFIG)));
    }

    @Test
    @Transactional
    void getUiQueryform() throws Exception {
        // Initialize the database
        uiQueryformRepository.saveAndFlush(uiQueryform);

        // Get the uiQueryform
        restUiQueryformMockMvc
            .perform(get(ENTITY_API_URL_ID, uiQueryform.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uiQueryform.getId().intValue()))
            .andExpect(jsonPath("$.menuid").value(DEFAULT_MENUID.intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.ordernum").value(DEFAULT_ORDERNUM))
            .andExpect(jsonPath("$.issource").value(DEFAULT_ISSOURCE.booleanValue()))
            .andExpect(jsonPath("$.requirement").value(DEFAULT_REQUIREMENT.booleanValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.placeholder").value(DEFAULT_PLACEHOLDER))
            .andExpect(jsonPath("$.config").value(DEFAULT_CONFIG));
    }

    @Test
    @Transactional
    void getNonExistingUiQueryform() throws Exception {
        // Get the uiQueryform
        restUiQueryformMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUiQueryform() throws Exception {
        // Initialize the database
        uiQueryformRepository.saveAndFlush(uiQueryform);

        int databaseSizeBeforeUpdate = uiQueryformRepository.findAll().size();

        // Update the uiQueryform
        UiQueryform updatedUiQueryform = uiQueryformRepository.findById(uiQueryform.getId()).get();
        // Disconnect from session so that the updates on updatedUiQueryform are not directly saved in db
        em.detach(updatedUiQueryform);
        updatedUiQueryform
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .issource(UPDATED_ISSOURCE)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .placeholder(UPDATED_PLACEHOLDER)
            .config(UPDATED_CONFIG);

        restUiQueryformMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUiQueryform.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUiQueryform))
            )
            .andExpect(status().isOk());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeUpdate);
        UiQueryform testUiQueryform = uiQueryformList.get(uiQueryformList.size() - 1);
        assertThat(testUiQueryform.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiQueryform.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiQueryform.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiQueryform.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiQueryform.getIssource()).isEqualTo(UPDATED_ISSOURCE);
        assertThat(testUiQueryform.getRequirement()).isEqualTo(UPDATED_REQUIREMENT);
        assertThat(testUiQueryform.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUiQueryform.getPlaceholder()).isEqualTo(UPDATED_PLACEHOLDER);
        assertThat(testUiQueryform.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void putNonExistingUiQueryform() throws Exception {
        int databaseSizeBeforeUpdate = uiQueryformRepository.findAll().size();
        uiQueryform.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiQueryformMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uiQueryform.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiQueryform))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUiQueryform() throws Exception {
        int databaseSizeBeforeUpdate = uiQueryformRepository.findAll().size();
        uiQueryform.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiQueryformMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiQueryform))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUiQueryform() throws Exception {
        int databaseSizeBeforeUpdate = uiQueryformRepository.findAll().size();
        uiQueryform.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiQueryformMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiQueryform)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUiQueryformWithPatch() throws Exception {
        // Initialize the database
        uiQueryformRepository.saveAndFlush(uiQueryform);

        int databaseSizeBeforeUpdate = uiQueryformRepository.findAll().size();

        // Update the uiQueryform using partial update
        UiQueryform partialUpdatedUiQueryform = new UiQueryform();
        partialUpdatedUiQueryform.setId(uiQueryform.getId());

        partialUpdatedUiQueryform
            .menuid(UPDATED_MENUID)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .issource(UPDATED_ISSOURCE)
            .type(UPDATED_TYPE)
            .config(UPDATED_CONFIG);

        restUiQueryformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiQueryform.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiQueryform))
            )
            .andExpect(status().isOk());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeUpdate);
        UiQueryform testUiQueryform = uiQueryformList.get(uiQueryformList.size() - 1);
        assertThat(testUiQueryform.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiQueryform.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUiQueryform.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiQueryform.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiQueryform.getIssource()).isEqualTo(UPDATED_ISSOURCE);
        assertThat(testUiQueryform.getRequirement()).isEqualTo(DEFAULT_REQUIREMENT);
        assertThat(testUiQueryform.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUiQueryform.getPlaceholder()).isEqualTo(DEFAULT_PLACEHOLDER);
        assertThat(testUiQueryform.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void fullUpdateUiQueryformWithPatch() throws Exception {
        // Initialize the database
        uiQueryformRepository.saveAndFlush(uiQueryform);

        int databaseSizeBeforeUpdate = uiQueryformRepository.findAll().size();

        // Update the uiQueryform using partial update
        UiQueryform partialUpdatedUiQueryform = new UiQueryform();
        partialUpdatedUiQueryform.setId(uiQueryform.getId());

        partialUpdatedUiQueryform
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .issource(UPDATED_ISSOURCE)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .placeholder(UPDATED_PLACEHOLDER)
            .config(UPDATED_CONFIG);

        restUiQueryformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiQueryform.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiQueryform))
            )
            .andExpect(status().isOk());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeUpdate);
        UiQueryform testUiQueryform = uiQueryformList.get(uiQueryformList.size() - 1);
        assertThat(testUiQueryform.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiQueryform.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiQueryform.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiQueryform.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiQueryform.getIssource()).isEqualTo(UPDATED_ISSOURCE);
        assertThat(testUiQueryform.getRequirement()).isEqualTo(UPDATED_REQUIREMENT);
        assertThat(testUiQueryform.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUiQueryform.getPlaceholder()).isEqualTo(UPDATED_PLACEHOLDER);
        assertThat(testUiQueryform.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void patchNonExistingUiQueryform() throws Exception {
        int databaseSizeBeforeUpdate = uiQueryformRepository.findAll().size();
        uiQueryform.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiQueryformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uiQueryform.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiQueryform))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUiQueryform() throws Exception {
        int databaseSizeBeforeUpdate = uiQueryformRepository.findAll().size();
        uiQueryform.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiQueryformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiQueryform))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUiQueryform() throws Exception {
        int databaseSizeBeforeUpdate = uiQueryformRepository.findAll().size();
        uiQueryform.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiQueryformMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(uiQueryform))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiQueryform in the database
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUiQueryform() throws Exception {
        // Initialize the database
        uiQueryformRepository.saveAndFlush(uiQueryform);

        int databaseSizeBeforeDelete = uiQueryformRepository.findAll().size();

        // Delete the uiQueryform
        restUiQueryformMockMvc
            .perform(delete(ENTITY_API_URL_ID, uiQueryform.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UiQueryform> uiQueryformList = uiQueryformRepository.findAll();
        assertThat(uiQueryformList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
