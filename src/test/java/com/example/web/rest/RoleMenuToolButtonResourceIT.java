package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.RoleMenuToolButton;
import com.example.repository.RoleMenuToolButtonRepository;
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
 * Integration tests for the {@link RoleMenuToolButtonResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class RoleMenuToolButtonResourceIT {

    private static final String DEFAULT_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MENU_ID = "AAAAAAAAAA";
    private static final String UPDATED_MENU_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_TOOL_BUTTON_ID = 1L;
    private static final Long UPDATED_TOOL_BUTTON_ID = 2L;

    private static final String ENTITY_API_URL = "/api/role-menu-tool-buttons";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RoleMenuToolButtonRepository roleMenuToolButtonRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRoleMenuToolButtonMockMvc;

    private RoleMenuToolButton roleMenuToolButton;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleMenuToolButton createEntity(EntityManager em) {
        RoleMenuToolButton roleMenuToolButton = new RoleMenuToolButton()
            .roleId(DEFAULT_ROLE_ID)
            .menuId(DEFAULT_MENU_ID)
            .toolButtonId(DEFAULT_TOOL_BUTTON_ID);
        return roleMenuToolButton;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleMenuToolButton createUpdatedEntity(EntityManager em) {
        RoleMenuToolButton roleMenuToolButton = new RoleMenuToolButton()
            .roleId(UPDATED_ROLE_ID)
            .menuId(UPDATED_MENU_ID)
            .toolButtonId(UPDATED_TOOL_BUTTON_ID);
        return roleMenuToolButton;
    }

    @BeforeEach
    public void initTest() {
        roleMenuToolButton = createEntity(em);
    }

    @Test
    @Transactional
    void createRoleMenuToolButton() throws Exception {
        int databaseSizeBeforeCreate = roleMenuToolButtonRepository.findAll().size();
        // Create the RoleMenuToolButton
        restRoleMenuToolButtonMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(roleMenuToolButton))
            )
            .andExpect(status().isCreated());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeCreate + 1);
        RoleMenuToolButton testRoleMenuToolButton = roleMenuToolButtonList.get(roleMenuToolButtonList.size() - 1);
        assertThat(testRoleMenuToolButton.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testRoleMenuToolButton.getMenuId()).isEqualTo(DEFAULT_MENU_ID);
        assertThat(testRoleMenuToolButton.getToolButtonId()).isEqualTo(DEFAULT_TOOL_BUTTON_ID);
    }

    @Test
    @Transactional
    void createRoleMenuToolButtonWithExistingId() throws Exception {
        // Create the RoleMenuToolButton with an existing ID
        roleMenuToolButton.setId(1L);

        int databaseSizeBeforeCreate = roleMenuToolButtonRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoleMenuToolButtonMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(roleMenuToolButton))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRoleMenuToolButtons() throws Exception {
        // Initialize the database
        roleMenuToolButtonRepository.saveAndFlush(roleMenuToolButton);

        // Get all the roleMenuToolButtonList
        restRoleMenuToolButtonMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(roleMenuToolButton.getId().intValue())))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID)))
            .andExpect(jsonPath("$.[*].menuId").value(hasItem(DEFAULT_MENU_ID)))
            .andExpect(jsonPath("$.[*].toolButtonId").value(hasItem(DEFAULT_TOOL_BUTTON_ID.intValue())));
    }

    @Test
    @Transactional
    void getRoleMenuToolButton() throws Exception {
        // Initialize the database
        roleMenuToolButtonRepository.saveAndFlush(roleMenuToolButton);

        // Get the roleMenuToolButton
        restRoleMenuToolButtonMockMvc
            .perform(get(ENTITY_API_URL_ID, roleMenuToolButton.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(roleMenuToolButton.getId().intValue()))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID))
            .andExpect(jsonPath("$.menuId").value(DEFAULT_MENU_ID))
            .andExpect(jsonPath("$.toolButtonId").value(DEFAULT_TOOL_BUTTON_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingRoleMenuToolButton() throws Exception {
        // Get the roleMenuToolButton
        restRoleMenuToolButtonMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRoleMenuToolButton() throws Exception {
        // Initialize the database
        roleMenuToolButtonRepository.saveAndFlush(roleMenuToolButton);

        int databaseSizeBeforeUpdate = roleMenuToolButtonRepository.findAll().size();

        // Update the roleMenuToolButton
        RoleMenuToolButton updatedRoleMenuToolButton = roleMenuToolButtonRepository.findById(roleMenuToolButton.getId()).get();
        // Disconnect from session so that the updates on updatedRoleMenuToolButton are not directly saved in db
        em.detach(updatedRoleMenuToolButton);
        updatedRoleMenuToolButton.roleId(UPDATED_ROLE_ID).menuId(UPDATED_MENU_ID).toolButtonId(UPDATED_TOOL_BUTTON_ID);

        restRoleMenuToolButtonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRoleMenuToolButton.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRoleMenuToolButton))
            )
            .andExpect(status().isOk());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeUpdate);
        RoleMenuToolButton testRoleMenuToolButton = roleMenuToolButtonList.get(roleMenuToolButtonList.size() - 1);
        assertThat(testRoleMenuToolButton.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleMenuToolButton.getMenuId()).isEqualTo(UPDATED_MENU_ID);
        assertThat(testRoleMenuToolButton.getToolButtonId()).isEqualTo(UPDATED_TOOL_BUTTON_ID);
    }

    @Test
    @Transactional
    void putNonExistingRoleMenuToolButton() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuToolButtonRepository.findAll().size();
        roleMenuToolButton.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleMenuToolButtonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, roleMenuToolButton.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleMenuToolButton))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRoleMenuToolButton() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuToolButtonRepository.findAll().size();
        roleMenuToolButton.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMenuToolButtonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleMenuToolButton))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRoleMenuToolButton() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuToolButtonRepository.findAll().size();
        roleMenuToolButton.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMenuToolButtonMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(roleMenuToolButton))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRoleMenuToolButtonWithPatch() throws Exception {
        // Initialize the database
        roleMenuToolButtonRepository.saveAndFlush(roleMenuToolButton);

        int databaseSizeBeforeUpdate = roleMenuToolButtonRepository.findAll().size();

        // Update the roleMenuToolButton using partial update
        RoleMenuToolButton partialUpdatedRoleMenuToolButton = new RoleMenuToolButton();
        partialUpdatedRoleMenuToolButton.setId(roleMenuToolButton.getId());

        partialUpdatedRoleMenuToolButton.roleId(UPDATED_ROLE_ID).menuId(UPDATED_MENU_ID).toolButtonId(UPDATED_TOOL_BUTTON_ID);

        restRoleMenuToolButtonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoleMenuToolButton.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoleMenuToolButton))
            )
            .andExpect(status().isOk());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeUpdate);
        RoleMenuToolButton testRoleMenuToolButton = roleMenuToolButtonList.get(roleMenuToolButtonList.size() - 1);
        assertThat(testRoleMenuToolButton.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleMenuToolButton.getMenuId()).isEqualTo(UPDATED_MENU_ID);
        assertThat(testRoleMenuToolButton.getToolButtonId()).isEqualTo(UPDATED_TOOL_BUTTON_ID);
    }

    @Test
    @Transactional
    void fullUpdateRoleMenuToolButtonWithPatch() throws Exception {
        // Initialize the database
        roleMenuToolButtonRepository.saveAndFlush(roleMenuToolButton);

        int databaseSizeBeforeUpdate = roleMenuToolButtonRepository.findAll().size();

        // Update the roleMenuToolButton using partial update
        RoleMenuToolButton partialUpdatedRoleMenuToolButton = new RoleMenuToolButton();
        partialUpdatedRoleMenuToolButton.setId(roleMenuToolButton.getId());

        partialUpdatedRoleMenuToolButton.roleId(UPDATED_ROLE_ID).menuId(UPDATED_MENU_ID).toolButtonId(UPDATED_TOOL_BUTTON_ID);

        restRoleMenuToolButtonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoleMenuToolButton.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoleMenuToolButton))
            )
            .andExpect(status().isOk());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeUpdate);
        RoleMenuToolButton testRoleMenuToolButton = roleMenuToolButtonList.get(roleMenuToolButtonList.size() - 1);
        assertThat(testRoleMenuToolButton.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleMenuToolButton.getMenuId()).isEqualTo(UPDATED_MENU_ID);
        assertThat(testRoleMenuToolButton.getToolButtonId()).isEqualTo(UPDATED_TOOL_BUTTON_ID);
    }

    @Test
    @Transactional
    void patchNonExistingRoleMenuToolButton() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuToolButtonRepository.findAll().size();
        roleMenuToolButton.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleMenuToolButtonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, roleMenuToolButton.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleMenuToolButton))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRoleMenuToolButton() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuToolButtonRepository.findAll().size();
        roleMenuToolButton.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMenuToolButtonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleMenuToolButton))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRoleMenuToolButton() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuToolButtonRepository.findAll().size();
        roleMenuToolButton.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMenuToolButtonMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleMenuToolButton))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RoleMenuToolButton in the database
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRoleMenuToolButton() throws Exception {
        // Initialize the database
        roleMenuToolButtonRepository.saveAndFlush(roleMenuToolButton);

        int databaseSizeBeforeDelete = roleMenuToolButtonRepository.findAll().size();

        // Delete the roleMenuToolButton
        restRoleMenuToolButtonMockMvc
            .perform(delete(ENTITY_API_URL_ID, roleMenuToolButton.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RoleMenuToolButton> roleMenuToolButtonList = roleMenuToolButtonRepository.findAll();
        assertThat(roleMenuToolButtonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
