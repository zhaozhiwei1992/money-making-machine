package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.UiEditform;
import com.example.repository.UiEditformRepository;
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
 * Integration tests for the {@link UiEditformResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UiEditformResourceIT {

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

    private static final String DEFAULT_PLACEHOLDER = "AAAAAAAAAA";
    private static final String UPDATED_PLACEHOLDER = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ui-editforms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UiEditformRepository uiEditformRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUiEditformMockMvc;

    private UiEditform uiEditform;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiEditform createEntity(EntityManager em) {
        UiEditform uiEditform = new UiEditform()
            .menuid(DEFAULT_MENUID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .ordernum(DEFAULT_ORDERNUM)
            .issource(DEFAULT_ISSOURCE)
            .isedit(DEFAULT_ISEDIT)
            .requirement(DEFAULT_REQUIREMENT)
            .type(DEFAULT_TYPE)
            .placeholder(DEFAULT_PLACEHOLDER)
            .config(DEFAULT_CONFIG);
        return uiEditform;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiEditform createUpdatedEntity(EntityManager em) {
        UiEditform uiEditform = new UiEditform()
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .issource(UPDATED_ISSOURCE)
            .isedit(UPDATED_ISEDIT)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .placeholder(UPDATED_PLACEHOLDER)
            .config(UPDATED_CONFIG);
        return uiEditform;
    }

    @BeforeEach
    public void initTest() {
        uiEditform = createEntity(em);
    }

    @Test
    @Transactional
    void createUiEditform() throws Exception {
        int databaseSizeBeforeCreate = uiEditformRepository.findAll().size();
        // Create the UiEditform
        restUiEditformMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiEditform)))
            .andExpect(status().isCreated());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeCreate + 1);
        UiEditform testUiEditform = uiEditformList.get(uiEditformList.size() - 1);
        assertThat(testUiEditform.getMenuid()).isEqualTo(DEFAULT_MENUID);
        assertThat(testUiEditform.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUiEditform.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUiEditform.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
        assertThat(testUiEditform.getIssource()).isEqualTo(DEFAULT_ISSOURCE);
        assertThat(testUiEditform.getIsedit()).isEqualTo(DEFAULT_ISEDIT);
        assertThat(testUiEditform.getRequirement()).isEqualTo(DEFAULT_REQUIREMENT);
        assertThat(testUiEditform.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testUiEditform.getPlaceholder()).isEqualTo(DEFAULT_PLACEHOLDER);
        assertThat(testUiEditform.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void createUiEditformWithExistingId() throws Exception {
        // Create the UiEditform with an existing ID
        uiEditform.setId(1L);

        int databaseSizeBeforeCreate = uiEditformRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUiEditformMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiEditform)))
            .andExpect(status().isBadRequest());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUiEditforms() throws Exception {
        // Initialize the database
        uiEditformRepository.saveAndFlush(uiEditform);

        // Get all the uiEditformList
        restUiEditformMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uiEditform.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuid").value(hasItem(DEFAULT_MENUID.intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].ordernum").value(hasItem(DEFAULT_ORDERNUM)))
            .andExpect(jsonPath("$.[*].issource").value(hasItem(DEFAULT_ISSOURCE.booleanValue())))
            .andExpect(jsonPath("$.[*].isedit").value(hasItem(DEFAULT_ISEDIT.booleanValue())))
            .andExpect(jsonPath("$.[*].requirement").value(hasItem(DEFAULT_REQUIREMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].placeholder").value(hasItem(DEFAULT_PLACEHOLDER)))
            .andExpect(jsonPath("$.[*].config").value(hasItem(DEFAULT_CONFIG)));
    }

    @Test
    @Transactional
    void getUiEditform() throws Exception {
        // Initialize the database
        uiEditformRepository.saveAndFlush(uiEditform);

        // Get the uiEditform
        restUiEditformMockMvc
            .perform(get(ENTITY_API_URL_ID, uiEditform.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uiEditform.getId().intValue()))
            .andExpect(jsonPath("$.menuid").value(DEFAULT_MENUID.intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.ordernum").value(DEFAULT_ORDERNUM))
            .andExpect(jsonPath("$.issource").value(DEFAULT_ISSOURCE.booleanValue()))
            .andExpect(jsonPath("$.isedit").value(DEFAULT_ISEDIT.booleanValue()))
            .andExpect(jsonPath("$.requirement").value(DEFAULT_REQUIREMENT.booleanValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.placeholder").value(DEFAULT_PLACEHOLDER))
            .andExpect(jsonPath("$.config").value(DEFAULT_CONFIG));
    }

    @Test
    @Transactional
    void getNonExistingUiEditform() throws Exception {
        // Get the uiEditform
        restUiEditformMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUiEditform() throws Exception {
        // Initialize the database
        uiEditformRepository.saveAndFlush(uiEditform);

        int databaseSizeBeforeUpdate = uiEditformRepository.findAll().size();

        // Update the uiEditform
        UiEditform updatedUiEditform = uiEditformRepository.findById(uiEditform.getId()).get();
        // Disconnect from session so that the updates on updatedUiEditform are not directly saved in db
        em.detach(updatedUiEditform);
        updatedUiEditform
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .issource(UPDATED_ISSOURCE)
            .isedit(UPDATED_ISEDIT)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .placeholder(UPDATED_PLACEHOLDER)
            .config(UPDATED_CONFIG);

        restUiEditformMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUiEditform.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUiEditform))
            )
            .andExpect(status().isOk());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeUpdate);
        UiEditform testUiEditform = uiEditformList.get(uiEditformList.size() - 1);
        assertThat(testUiEditform.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiEditform.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiEditform.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiEditform.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiEditform.getIssource()).isEqualTo(UPDATED_ISSOURCE);
        assertThat(testUiEditform.getIsedit()).isEqualTo(UPDATED_ISEDIT);
        assertThat(testUiEditform.getRequirement()).isEqualTo(UPDATED_REQUIREMENT);
        assertThat(testUiEditform.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUiEditform.getPlaceholder()).isEqualTo(UPDATED_PLACEHOLDER);
        assertThat(testUiEditform.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void putNonExistingUiEditform() throws Exception {
        int databaseSizeBeforeUpdate = uiEditformRepository.findAll().size();
        uiEditform.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiEditformMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uiEditform.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiEditform))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUiEditform() throws Exception {
        int databaseSizeBeforeUpdate = uiEditformRepository.findAll().size();
        uiEditform.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiEditformMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiEditform))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUiEditform() throws Exception {
        int databaseSizeBeforeUpdate = uiEditformRepository.findAll().size();
        uiEditform.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiEditformMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiEditform)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUiEditformWithPatch() throws Exception {
        // Initialize the database
        uiEditformRepository.saveAndFlush(uiEditform);

        int databaseSizeBeforeUpdate = uiEditformRepository.findAll().size();

        // Update the uiEditform using partial update
        UiEditform partialUpdatedUiEditform = new UiEditform();
        partialUpdatedUiEditform.setId(uiEditform.getId());

        partialUpdatedUiEditform.code(UPDATED_CODE).ordernum(UPDATED_ORDERNUM).issource(UPDATED_ISSOURCE).placeholder(UPDATED_PLACEHOLDER);

        restUiEditformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiEditform.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiEditform))
            )
            .andExpect(status().isOk());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeUpdate);
        UiEditform testUiEditform = uiEditformList.get(uiEditformList.size() - 1);
        assertThat(testUiEditform.getMenuid()).isEqualTo(DEFAULT_MENUID);
        assertThat(testUiEditform.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiEditform.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUiEditform.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiEditform.getIssource()).isEqualTo(UPDATED_ISSOURCE);
        assertThat(testUiEditform.getIsedit()).isEqualTo(DEFAULT_ISEDIT);
        assertThat(testUiEditform.getRequirement()).isEqualTo(DEFAULT_REQUIREMENT);
        assertThat(testUiEditform.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testUiEditform.getPlaceholder()).isEqualTo(UPDATED_PLACEHOLDER);
        assertThat(testUiEditform.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void fullUpdateUiEditformWithPatch() throws Exception {
        // Initialize the database
        uiEditformRepository.saveAndFlush(uiEditform);

        int databaseSizeBeforeUpdate = uiEditformRepository.findAll().size();

        // Update the uiEditform using partial update
        UiEditform partialUpdatedUiEditform = new UiEditform();
        partialUpdatedUiEditform.setId(uiEditform.getId());

        partialUpdatedUiEditform
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .issource(UPDATED_ISSOURCE)
            .isedit(UPDATED_ISEDIT)
            .requirement(UPDATED_REQUIREMENT)
            .type(UPDATED_TYPE)
            .placeholder(UPDATED_PLACEHOLDER)
            .config(UPDATED_CONFIG);

        restUiEditformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiEditform.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiEditform))
            )
            .andExpect(status().isOk());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeUpdate);
        UiEditform testUiEditform = uiEditformList.get(uiEditformList.size() - 1);
        assertThat(testUiEditform.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiEditform.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiEditform.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiEditform.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiEditform.getIssource()).isEqualTo(UPDATED_ISSOURCE);
        assertThat(testUiEditform.getIsedit()).isEqualTo(UPDATED_ISEDIT);
        assertThat(testUiEditform.getRequirement()).isEqualTo(UPDATED_REQUIREMENT);
        assertThat(testUiEditform.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUiEditform.getPlaceholder()).isEqualTo(UPDATED_PLACEHOLDER);
        assertThat(testUiEditform.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void patchNonExistingUiEditform() throws Exception {
        int databaseSizeBeforeUpdate = uiEditformRepository.findAll().size();
        uiEditform.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiEditformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uiEditform.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiEditform))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUiEditform() throws Exception {
        int databaseSizeBeforeUpdate = uiEditformRepository.findAll().size();
        uiEditform.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiEditformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiEditform))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUiEditform() throws Exception {
        int databaseSizeBeforeUpdate = uiEditformRepository.findAll().size();
        uiEditform.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiEditformMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(uiEditform))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiEditform in the database
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUiEditform() throws Exception {
        // Initialize the database
        uiEditformRepository.saveAndFlush(uiEditform);

        int databaseSizeBeforeDelete = uiEditformRepository.findAll().size();

        // Delete the uiEditform
        restUiEditformMockMvc
            .perform(delete(ENTITY_API_URL_ID, uiEditform.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UiEditform> uiEditformList = uiEditformRepository.findAll();
        assertThat(uiEditformList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
