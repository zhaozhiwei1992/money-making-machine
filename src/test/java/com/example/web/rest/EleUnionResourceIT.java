package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.EleUnion;
import com.example.repository.EleUnionRepository;
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
 * Integration tests for the {@link EleUnionResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class EleUnionResourceIT {

    private static final String DEFAULT_ELE_CAT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ELE_CAT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ELE_CAT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ELE_CAT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ELE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ELE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ELE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ELE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL_NO = 1;
    private static final Integer UPDATED_LEVEL_NO = 2;

    private static final Boolean DEFAULT_IS_LEAF = false;
    private static final Boolean UPDATED_IS_LEAF = true;

    private static final Boolean DEFAULT_IS_ENABLED = false;
    private static final Boolean UPDATED_IS_ENABLED = true;

    private static final String DEFAULT_CREATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_TIME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ele-unions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EleUnionRepository eleUnionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEleUnionMockMvc;

    private EleUnion eleUnion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EleUnion createEntity(EntityManager em) {
        EleUnion eleUnion = new EleUnion()
            .eleCatCode(DEFAULT_ELE_CAT_CODE)
            .eleCatName(DEFAULT_ELE_CAT_NAME)
            .eleCode(DEFAULT_ELE_CODE)
            .eleName(DEFAULT_ELE_NAME)
            .parentId(DEFAULT_PARENT_ID)
            .levelNo(DEFAULT_LEVEL_NO)
            .isLeaf(DEFAULT_IS_LEAF)
            .isEnabled(DEFAULT_IS_ENABLED)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return eleUnion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EleUnion createUpdatedEntity(EntityManager em) {
        EleUnion eleUnion = new EleUnion()
            .eleCatCode(UPDATED_ELE_CAT_CODE)
            .eleCatName(UPDATED_ELE_CAT_NAME)
            .eleCode(UPDATED_ELE_CODE)
            .eleName(UPDATED_ELE_NAME)
            .parentId(UPDATED_PARENT_ID)
            .levelNo(UPDATED_LEVEL_NO)
            .isLeaf(UPDATED_IS_LEAF)
            .isEnabled(UPDATED_IS_ENABLED)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);
        return eleUnion;
    }

    @BeforeEach
    public void initTest() {
        eleUnion = createEntity(em);
    }

    @Test
    @Transactional
    void createEleUnion() throws Exception {
        int databaseSizeBeforeCreate = eleUnionRepository.findAll().size();
        // Create the EleUnion
        restEleUnionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(eleUnion)))
            .andExpect(status().isCreated());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeCreate + 1);
        EleUnion testEleUnion = eleUnionList.get(eleUnionList.size() - 1);
        assertThat(testEleUnion.getEleCatCode()).isEqualTo(DEFAULT_ELE_CAT_CODE);
        assertThat(testEleUnion.getEleCatName()).isEqualTo(DEFAULT_ELE_CAT_NAME);
        assertThat(testEleUnion.getEleCode()).isEqualTo(DEFAULT_ELE_CODE);
        assertThat(testEleUnion.getEleName()).isEqualTo(DEFAULT_ELE_NAME);
        assertThat(testEleUnion.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testEleUnion.getLevelNo()).isEqualTo(DEFAULT_LEVEL_NO);
        assertThat(testEleUnion.getIsLeaf()).isEqualTo(DEFAULT_IS_LEAF);
        assertThat(testEleUnion.getIsEnabled()).isEqualTo(DEFAULT_IS_ENABLED);
        assertThat(testEleUnion.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testEleUnion.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createEleUnionWithExistingId() throws Exception {
        // Create the EleUnion with an existing ID
        eleUnion.setId(1L);

        int databaseSizeBeforeCreate = eleUnionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEleUnionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(eleUnion)))
            .andExpect(status().isBadRequest());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEleUnions() throws Exception {
        // Initialize the database
        eleUnionRepository.saveAndFlush(eleUnion);

        // Get all the eleUnionList
        restEleUnionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eleUnion.getId().intValue())))
            .andExpect(jsonPath("$.[*].eleCatCode").value(hasItem(DEFAULT_ELE_CAT_CODE)))
            .andExpect(jsonPath("$.[*].eleCatName").value(hasItem(DEFAULT_ELE_CAT_NAME)))
            .andExpect(jsonPath("$.[*].eleCode").value(hasItem(DEFAULT_ELE_CODE)))
            .andExpect(jsonPath("$.[*].eleName").value(hasItem(DEFAULT_ELE_NAME)))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID)))
            .andExpect(jsonPath("$.[*].levelNo").value(hasItem(DEFAULT_LEVEL_NO)))
            .andExpect(jsonPath("$.[*].isLeaf").value(hasItem(DEFAULT_IS_LEAF.booleanValue())))
            .andExpect(jsonPath("$.[*].isEnabled").value(hasItem(DEFAULT_IS_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    void getEleUnion() throws Exception {
        // Initialize the database
        eleUnionRepository.saveAndFlush(eleUnion);

        // Get the eleUnion
        restEleUnionMockMvc
            .perform(get(ENTITY_API_URL_ID, eleUnion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eleUnion.getId().intValue()))
            .andExpect(jsonPath("$.eleCatCode").value(DEFAULT_ELE_CAT_CODE))
            .andExpect(jsonPath("$.eleCatName").value(DEFAULT_ELE_CAT_NAME))
            .andExpect(jsonPath("$.eleCode").value(DEFAULT_ELE_CODE))
            .andExpect(jsonPath("$.eleName").value(DEFAULT_ELE_NAME))
            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID))
            .andExpect(jsonPath("$.levelNo").value(DEFAULT_LEVEL_NO))
            .andExpect(jsonPath("$.isLeaf").value(DEFAULT_IS_LEAF.booleanValue()))
            .andExpect(jsonPath("$.isEnabled").value(DEFAULT_IS_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME));
    }

    @Test
    @Transactional
    void getNonExistingEleUnion() throws Exception {
        // Get the eleUnion
        restEleUnionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEleUnion() throws Exception {
        // Initialize the database
        eleUnionRepository.saveAndFlush(eleUnion);

        int databaseSizeBeforeUpdate = eleUnionRepository.findAll().size();

        // Update the eleUnion
        EleUnion updatedEleUnion = eleUnionRepository.findById(eleUnion.getId()).get();
        // Disconnect from session so that the updates on updatedEleUnion are not directly saved in db
        em.detach(updatedEleUnion);
        updatedEleUnion
            .eleCatCode(UPDATED_ELE_CAT_CODE)
            .eleCatName(UPDATED_ELE_CAT_NAME)
            .eleCode(UPDATED_ELE_CODE)
            .eleName(UPDATED_ELE_NAME)
            .parentId(UPDATED_PARENT_ID)
            .levelNo(UPDATED_LEVEL_NO)
            .isLeaf(UPDATED_IS_LEAF)
            .isEnabled(UPDATED_IS_ENABLED)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restEleUnionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEleUnion.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEleUnion))
            )
            .andExpect(status().isOk());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeUpdate);
        EleUnion testEleUnion = eleUnionList.get(eleUnionList.size() - 1);
        assertThat(testEleUnion.getEleCatCode()).isEqualTo(UPDATED_ELE_CAT_CODE);
        assertThat(testEleUnion.getEleCatName()).isEqualTo(UPDATED_ELE_CAT_NAME);
        assertThat(testEleUnion.getEleCode()).isEqualTo(UPDATED_ELE_CODE);
        assertThat(testEleUnion.getEleName()).isEqualTo(UPDATED_ELE_NAME);
        assertThat(testEleUnion.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testEleUnion.getLevelNo()).isEqualTo(UPDATED_LEVEL_NO);
        assertThat(testEleUnion.getIsLeaf()).isEqualTo(UPDATED_IS_LEAF);
        assertThat(testEleUnion.getIsEnabled()).isEqualTo(UPDATED_IS_ENABLED);
        assertThat(testEleUnion.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEleUnion.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingEleUnion() throws Exception {
        int databaseSizeBeforeUpdate = eleUnionRepository.findAll().size();
        eleUnion.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEleUnionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, eleUnion.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(eleUnion))
            )
            .andExpect(status().isBadRequest());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEleUnion() throws Exception {
        int databaseSizeBeforeUpdate = eleUnionRepository.findAll().size();
        eleUnion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEleUnionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(eleUnion))
            )
            .andExpect(status().isBadRequest());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEleUnion() throws Exception {
        int databaseSizeBeforeUpdate = eleUnionRepository.findAll().size();
        eleUnion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEleUnionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(eleUnion)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEleUnionWithPatch() throws Exception {
        // Initialize the database
        eleUnionRepository.saveAndFlush(eleUnion);

        int databaseSizeBeforeUpdate = eleUnionRepository.findAll().size();

        // Update the eleUnion using partial update
        EleUnion partialUpdatedEleUnion = new EleUnion();
        partialUpdatedEleUnion.setId(eleUnion.getId());

        partialUpdatedEleUnion.levelNo(UPDATED_LEVEL_NO);

        restEleUnionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEleUnion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEleUnion))
            )
            .andExpect(status().isOk());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeUpdate);
        EleUnion testEleUnion = eleUnionList.get(eleUnionList.size() - 1);
        assertThat(testEleUnion.getEleCatCode()).isEqualTo(DEFAULT_ELE_CAT_CODE);
        assertThat(testEleUnion.getEleCatName()).isEqualTo(DEFAULT_ELE_CAT_NAME);
        assertThat(testEleUnion.getEleCode()).isEqualTo(DEFAULT_ELE_CODE);
        assertThat(testEleUnion.getEleName()).isEqualTo(DEFAULT_ELE_NAME);
        assertThat(testEleUnion.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testEleUnion.getLevelNo()).isEqualTo(UPDATED_LEVEL_NO);
        assertThat(testEleUnion.getIsLeaf()).isEqualTo(DEFAULT_IS_LEAF);
        assertThat(testEleUnion.getIsEnabled()).isEqualTo(DEFAULT_IS_ENABLED);
        assertThat(testEleUnion.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testEleUnion.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateEleUnionWithPatch() throws Exception {
        // Initialize the database
        eleUnionRepository.saveAndFlush(eleUnion);

        int databaseSizeBeforeUpdate = eleUnionRepository.findAll().size();

        // Update the eleUnion using partial update
        EleUnion partialUpdatedEleUnion = new EleUnion();
        partialUpdatedEleUnion.setId(eleUnion.getId());

        partialUpdatedEleUnion
            .eleCatCode(UPDATED_ELE_CAT_CODE)
            .eleCatName(UPDATED_ELE_CAT_NAME)
            .eleCode(UPDATED_ELE_CODE)
            .eleName(UPDATED_ELE_NAME)
            .parentId(UPDATED_PARENT_ID)
            .levelNo(UPDATED_LEVEL_NO)
            .isLeaf(UPDATED_IS_LEAF)
            .isEnabled(UPDATED_IS_ENABLED)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restEleUnionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEleUnion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEleUnion))
            )
            .andExpect(status().isOk());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeUpdate);
        EleUnion testEleUnion = eleUnionList.get(eleUnionList.size() - 1);
        assertThat(testEleUnion.getEleCatCode()).isEqualTo(UPDATED_ELE_CAT_CODE);
        assertThat(testEleUnion.getEleCatName()).isEqualTo(UPDATED_ELE_CAT_NAME);
        assertThat(testEleUnion.getEleCode()).isEqualTo(UPDATED_ELE_CODE);
        assertThat(testEleUnion.getEleName()).isEqualTo(UPDATED_ELE_NAME);
        assertThat(testEleUnion.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testEleUnion.getLevelNo()).isEqualTo(UPDATED_LEVEL_NO);
        assertThat(testEleUnion.getIsLeaf()).isEqualTo(UPDATED_IS_LEAF);
        assertThat(testEleUnion.getIsEnabled()).isEqualTo(UPDATED_IS_ENABLED);
        assertThat(testEleUnion.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEleUnion.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingEleUnion() throws Exception {
        int databaseSizeBeforeUpdate = eleUnionRepository.findAll().size();
        eleUnion.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEleUnionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, eleUnion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(eleUnion))
            )
            .andExpect(status().isBadRequest());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEleUnion() throws Exception {
        int databaseSizeBeforeUpdate = eleUnionRepository.findAll().size();
        eleUnion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEleUnionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(eleUnion))
            )
            .andExpect(status().isBadRequest());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEleUnion() throws Exception {
        int databaseSizeBeforeUpdate = eleUnionRepository.findAll().size();
        eleUnion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEleUnionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(eleUnion)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EleUnion in the database
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEleUnion() throws Exception {
        // Initialize the database
        eleUnionRepository.saveAndFlush(eleUnion);

        int databaseSizeBeforeDelete = eleUnionRepository.findAll().size();

        // Delete the eleUnion
        restEleUnionMockMvc
            .perform(delete(ENTITY_API_URL_ID, eleUnion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EleUnion> eleUnionList = eleUnionRepository.findAll();
        assertThat(eleUnionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
