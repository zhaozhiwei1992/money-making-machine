package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.RoleMenu;
import com.example.repository.RoleMenuRepository;
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
 * Integration tests for the {@link RoleMenuResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class RoleMenuResourceIT {

    private static final String DEFAULT_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MENU_ID = "AAAAAAAAAA";
    private static final String UPDATED_MENU_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/role-menus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRoleMenuMockMvc;

    private RoleMenu roleMenu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleMenu createEntity(EntityManager em) {
        RoleMenu roleMenu = new RoleMenu().roleId(DEFAULT_ROLE_ID).menuId(DEFAULT_MENU_ID);
        return roleMenu;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleMenu createUpdatedEntity(EntityManager em) {
        RoleMenu roleMenu = new RoleMenu().roleId(UPDATED_ROLE_ID).menuId(UPDATED_MENU_ID);
        return roleMenu;
    }

    @BeforeEach
    public void initTest() {
        roleMenu = createEntity(em);
    }

    @Test
    @Transactional
    void createRoleMenu() throws Exception {
        int databaseSizeBeforeCreate = roleMenuRepository.findAll().size();
        // Create the RoleMenu
        restRoleMenuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(roleMenu)))
            .andExpect(status().isCreated());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeCreate + 1);
        RoleMenu testRoleMenu = roleMenuList.get(roleMenuList.size() - 1);
        assertThat(testRoleMenu.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testRoleMenu.getMenuId()).isEqualTo(DEFAULT_MENU_ID);
    }

    @Test
    @Transactional
    void createRoleMenuWithExistingId() throws Exception {
        // Create the RoleMenu with an existing ID
        roleMenu.setId(1L);

        int databaseSizeBeforeCreate = roleMenuRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoleMenuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(roleMenu)))
            .andExpect(status().isBadRequest());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRoleMenus() throws Exception {
        // Initialize the database
        roleMenuRepository.saveAndFlush(roleMenu);

        // Get all the roleMenuList
        restRoleMenuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(roleMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID)))
            .andExpect(jsonPath("$.[*].menuId").value(hasItem(DEFAULT_MENU_ID)));
    }

    @Test
    @Transactional
    void getRoleMenu() throws Exception {
        // Initialize the database
        roleMenuRepository.saveAndFlush(roleMenu);

        // Get the roleMenu
        restRoleMenuMockMvc
            .perform(get(ENTITY_API_URL_ID, roleMenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(roleMenu.getId().intValue()))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID))
            .andExpect(jsonPath("$.menuId").value(DEFAULT_MENU_ID));
    }

    @Test
    @Transactional
    void getNonExistingRoleMenu() throws Exception {
        // Get the roleMenu
        restRoleMenuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRoleMenu() throws Exception {
        // Initialize the database
        roleMenuRepository.saveAndFlush(roleMenu);

        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();

        // Update the roleMenu
        RoleMenu updatedRoleMenu = roleMenuRepository.findById(roleMenu.getId()).get();
        // Disconnect from session so that the updates on updatedRoleMenu are not directly saved in db
        em.detach(updatedRoleMenu);
        updatedRoleMenu.roleId(UPDATED_ROLE_ID).menuId(UPDATED_MENU_ID);

        restRoleMenuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRoleMenu.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRoleMenu))
            )
            .andExpect(status().isOk());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate);
        RoleMenu testRoleMenu = roleMenuList.get(roleMenuList.size() - 1);
        assertThat(testRoleMenu.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleMenu.getMenuId()).isEqualTo(UPDATED_MENU_ID);
    }

    @Test
    @Transactional
    void putNonExistingRoleMenu() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();
        roleMenu.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleMenuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, roleMenu.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleMenu))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRoleMenu() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();
        roleMenu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMenuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(roleMenu))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRoleMenu() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();
        roleMenu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMenuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(roleMenu)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRoleMenuWithPatch() throws Exception {
        // Initialize the database
        roleMenuRepository.saveAndFlush(roleMenu);

        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();

        // Update the roleMenu using partial update
        RoleMenu partialUpdatedRoleMenu = new RoleMenu();
        partialUpdatedRoleMenu.setId(roleMenu.getId());

        partialUpdatedRoleMenu.roleId(UPDATED_ROLE_ID).menuId(UPDATED_MENU_ID);

        restRoleMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoleMenu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoleMenu))
            )
            .andExpect(status().isOk());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate);
        RoleMenu testRoleMenu = roleMenuList.get(roleMenuList.size() - 1);
        assertThat(testRoleMenu.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleMenu.getMenuId()).isEqualTo(UPDATED_MENU_ID);
    }

    @Test
    @Transactional
    void fullUpdateRoleMenuWithPatch() throws Exception {
        // Initialize the database
        roleMenuRepository.saveAndFlush(roleMenu);

        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();

        // Update the roleMenu using partial update
        RoleMenu partialUpdatedRoleMenu = new RoleMenu();
        partialUpdatedRoleMenu.setId(roleMenu.getId());

        partialUpdatedRoleMenu.roleId(UPDATED_ROLE_ID).menuId(UPDATED_MENU_ID);

        restRoleMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoleMenu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoleMenu))
            )
            .andExpect(status().isOk());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate);
        RoleMenu testRoleMenu = roleMenuList.get(roleMenuList.size() - 1);
        assertThat(testRoleMenu.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testRoleMenu.getMenuId()).isEqualTo(UPDATED_MENU_ID);
    }

    @Test
    @Transactional
    void patchNonExistingRoleMenu() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();
        roleMenu.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, roleMenu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleMenu))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRoleMenu() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();
        roleMenu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(roleMenu))
            )
            .andExpect(status().isBadRequest());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRoleMenu() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();
        roleMenu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRoleMenuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(roleMenu)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRoleMenu() throws Exception {
        // Initialize the database
        roleMenuRepository.saveAndFlush(roleMenu);

        int databaseSizeBeforeDelete = roleMenuRepository.findAll().size();

        // Delete the roleMenu
        restRoleMenuMockMvc
            .perform(delete(ENTITY_API_URL_ID, roleMenu.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
