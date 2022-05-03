package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.UiToolButton;
import com.example.repository.UiToolButtonRepository;
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
 * Integration tests for the {@link UiToolButtonResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UiToolButtonResourceIT {

    private static final Long DEFAULT_MENUID = 1L;
    private static final Long UPDATED_MENUID = 2L;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDERNUM = 1;
    private static final Integer UPDATED_ORDERNUM = 2;

    private static final String DEFAULT_ACTION = "AAAAAAAAAA";
    private static final String UPDATED_ACTION = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ui-tool-buttons";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UiToolButtonRepository uiToolButtonRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUiToolButtonMockMvc;

    private UiToolButton uiToolButton;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiToolButton createEntity(EntityManager em) {
        UiToolButton uiToolButton = new UiToolButton()
            .menuid(DEFAULT_MENUID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .ordernum(DEFAULT_ORDERNUM)
            .action(DEFAULT_ACTION)
            .config(DEFAULT_CONFIG);
        return uiToolButton;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UiToolButton createUpdatedEntity(EntityManager em) {
        UiToolButton uiToolButton = new UiToolButton()
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .action(UPDATED_ACTION)
            .config(UPDATED_CONFIG);
        return uiToolButton;
    }

    @BeforeEach
    public void initTest() {
        uiToolButton = createEntity(em);
    }

    @Test
    @Transactional
    void createUiToolButton() throws Exception {
        int databaseSizeBeforeCreate = uiToolButtonRepository.findAll().size();
        // Create the UiToolButton
        restUiToolButtonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiToolButton)))
            .andExpect(status().isCreated());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeCreate + 1);
        UiToolButton testUiToolButton = uiToolButtonList.get(uiToolButtonList.size() - 1);
        assertThat(testUiToolButton.getMenuid()).isEqualTo(DEFAULT_MENUID);
        assertThat(testUiToolButton.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUiToolButton.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUiToolButton.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
        assertThat(testUiToolButton.getAction()).isEqualTo(DEFAULT_ACTION);
        assertThat(testUiToolButton.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void createUiToolButtonWithExistingId() throws Exception {
        // Create the UiToolButton with an existing ID
        uiToolButton.setId(1L);

        int databaseSizeBeforeCreate = uiToolButtonRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUiToolButtonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiToolButton)))
            .andExpect(status().isBadRequest());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUiToolButtons() throws Exception {
        // Initialize the database
        uiToolButtonRepository.saveAndFlush(uiToolButton);

        // Get all the uiToolButtonList
        restUiToolButtonMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uiToolButton.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuid").value(hasItem(DEFAULT_MENUID.intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].ordernum").value(hasItem(DEFAULT_ORDERNUM)))
            .andExpect(jsonPath("$.[*].action").value(hasItem(DEFAULT_ACTION)))
            .andExpect(jsonPath("$.[*].config").value(hasItem(DEFAULT_CONFIG)));
    }

    @Test
    @Transactional
    void getUiToolButton() throws Exception {
        // Initialize the database
        uiToolButtonRepository.saveAndFlush(uiToolButton);

        // Get the uiToolButton
        restUiToolButtonMockMvc
            .perform(get(ENTITY_API_URL_ID, uiToolButton.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uiToolButton.getId().intValue()))
            .andExpect(jsonPath("$.menuid").value(DEFAULT_MENUID.intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.ordernum").value(DEFAULT_ORDERNUM))
            .andExpect(jsonPath("$.action").value(DEFAULT_ACTION))
            .andExpect(jsonPath("$.config").value(DEFAULT_CONFIG));
    }

    @Test
    @Transactional
    void getNonExistingUiToolButton() throws Exception {
        // Get the uiToolButton
        restUiToolButtonMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUiToolButton() throws Exception {
        // Initialize the database
        uiToolButtonRepository.saveAndFlush(uiToolButton);

        int databaseSizeBeforeUpdate = uiToolButtonRepository.findAll().size();

        // Update the uiToolButton
        UiToolButton updatedUiToolButton = uiToolButtonRepository.findById(uiToolButton.getId()).get();
        // Disconnect from session so that the updates on updatedUiToolButton are not directly saved in db
        em.detach(updatedUiToolButton);
        updatedUiToolButton
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .action(UPDATED_ACTION)
            .config(UPDATED_CONFIG);

        restUiToolButtonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUiToolButton.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUiToolButton))
            )
            .andExpect(status().isOk());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeUpdate);
        UiToolButton testUiToolButton = uiToolButtonList.get(uiToolButtonList.size() - 1);
        assertThat(testUiToolButton.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiToolButton.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiToolButton.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiToolButton.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiToolButton.getAction()).isEqualTo(UPDATED_ACTION);
        assertThat(testUiToolButton.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void putNonExistingUiToolButton() throws Exception {
        int databaseSizeBeforeUpdate = uiToolButtonRepository.findAll().size();
        uiToolButton.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiToolButtonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uiToolButton.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiToolButton))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUiToolButton() throws Exception {
        int databaseSizeBeforeUpdate = uiToolButtonRepository.findAll().size();
        uiToolButton.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiToolButtonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uiToolButton))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUiToolButton() throws Exception {
        int databaseSizeBeforeUpdate = uiToolButtonRepository.findAll().size();
        uiToolButton.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiToolButtonMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uiToolButton)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUiToolButtonWithPatch() throws Exception {
        // Initialize the database
        uiToolButtonRepository.saveAndFlush(uiToolButton);

        int databaseSizeBeforeUpdate = uiToolButtonRepository.findAll().size();

        // Update the uiToolButton using partial update
        UiToolButton partialUpdatedUiToolButton = new UiToolButton();
        partialUpdatedUiToolButton.setId(uiToolButton.getId());

        partialUpdatedUiToolButton.menuid(UPDATED_MENUID).code(UPDATED_CODE).action(UPDATED_ACTION);

        restUiToolButtonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiToolButton.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiToolButton))
            )
            .andExpect(status().isOk());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeUpdate);
        UiToolButton testUiToolButton = uiToolButtonList.get(uiToolButtonList.size() - 1);
        assertThat(testUiToolButton.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiToolButton.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiToolButton.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUiToolButton.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
        assertThat(testUiToolButton.getAction()).isEqualTo(UPDATED_ACTION);
        assertThat(testUiToolButton.getConfig()).isEqualTo(DEFAULT_CONFIG);
    }

    @Test
    @Transactional
    void fullUpdateUiToolButtonWithPatch() throws Exception {
        // Initialize the database
        uiToolButtonRepository.saveAndFlush(uiToolButton);

        int databaseSizeBeforeUpdate = uiToolButtonRepository.findAll().size();

        // Update the uiToolButton using partial update
        UiToolButton partialUpdatedUiToolButton = new UiToolButton();
        partialUpdatedUiToolButton.setId(uiToolButton.getId());

        partialUpdatedUiToolButton
            .menuid(UPDATED_MENUID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .ordernum(UPDATED_ORDERNUM)
            .action(UPDATED_ACTION)
            .config(UPDATED_CONFIG);

        restUiToolButtonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUiToolButton.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUiToolButton))
            )
            .andExpect(status().isOk());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeUpdate);
        UiToolButton testUiToolButton = uiToolButtonList.get(uiToolButtonList.size() - 1);
        assertThat(testUiToolButton.getMenuid()).isEqualTo(UPDATED_MENUID);
        assertThat(testUiToolButton.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUiToolButton.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUiToolButton.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
        assertThat(testUiToolButton.getAction()).isEqualTo(UPDATED_ACTION);
        assertThat(testUiToolButton.getConfig()).isEqualTo(UPDATED_CONFIG);
    }

    @Test
    @Transactional
    void patchNonExistingUiToolButton() throws Exception {
        int databaseSizeBeforeUpdate = uiToolButtonRepository.findAll().size();
        uiToolButton.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUiToolButtonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uiToolButton.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiToolButton))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUiToolButton() throws Exception {
        int databaseSizeBeforeUpdate = uiToolButtonRepository.findAll().size();
        uiToolButton.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiToolButtonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uiToolButton))
            )
            .andExpect(status().isBadRequest());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUiToolButton() throws Exception {
        int databaseSizeBeforeUpdate = uiToolButtonRepository.findAll().size();
        uiToolButton.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUiToolButtonMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(uiToolButton))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UiToolButton in the database
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUiToolButton() throws Exception {
        // Initialize the database
        uiToolButtonRepository.saveAndFlush(uiToolButton);

        int databaseSizeBeforeDelete = uiToolButtonRepository.findAll().size();

        // Delete the uiToolButton
        restUiToolButtonMockMvc
            .perform(delete(ENTITY_API_URL_ID, uiToolButton.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UiToolButton> uiToolButtonList = uiToolButtonRepository.findAll();
        assertThat(uiToolButtonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
