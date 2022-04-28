package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.Menu;
import com.example.repository.MenuRepository;
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
 * Integration tests for the {@link MenuResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class MenuResourceIT {

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_COMPONENT = "AAAAAAAAAA";
    private static final String UPDATED_COMPONENT = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ICON_CLS = "AAAAAAAAAA";
    private static final String UPDATED_ICON_CLS = "BBBBBBBBBB";

    private static final Integer DEFAULT_KEEP_ALIVE = 1;
    private static final Integer UPDATED_KEEP_ALIVE = 2;

    private static final Integer DEFAULT_REQUIRE_AUTH = 1;
    private static final Integer UPDATED_REQUIRE_AUTH = 2;

    private static final Integer DEFAULT_PARENT_ID = 1;
    private static final Integer UPDATED_PARENT_ID = 2;

    private static final Integer DEFAULT_ENABLED = 1;
    private static final Integer UPDATED_ENABLED = 2;

    private static final String ENTITY_API_URL = "/api/menus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMenuMockMvc;

    private Menu menu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Menu createEntity(EntityManager em) {
        Menu menu = new Menu()
            .url(DEFAULT_URL)
            .path(DEFAULT_PATH)
            .component(DEFAULT_COMPONENT)
            .name(DEFAULT_NAME)
            .iconCls(DEFAULT_ICON_CLS)
            .keepAlive(DEFAULT_KEEP_ALIVE)
            .requireAuth(DEFAULT_REQUIRE_AUTH)
            .parentId(DEFAULT_PARENT_ID)
            .enabled(DEFAULT_ENABLED);
        return menu;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Menu createUpdatedEntity(EntityManager em) {
        Menu menu = new Menu()
            .url(UPDATED_URL)
            .path(UPDATED_PATH)
            .component(UPDATED_COMPONENT)
            .name(UPDATED_NAME)
            .iconCls(UPDATED_ICON_CLS)
            .keepAlive(UPDATED_KEEP_ALIVE)
            .requireAuth(UPDATED_REQUIRE_AUTH)
            .parentId(UPDATED_PARENT_ID)
            .enabled(UPDATED_ENABLED);
        return menu;
    }

    @BeforeEach
    public void initTest() {
        menu = createEntity(em);
    }

    @Test
    @Transactional
    void createMenu() throws Exception {
        int databaseSizeBeforeCreate = menuRepository.findAll().size();
        // Create the Menu
        restMenuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(menu)))
            .andExpect(status().isCreated());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeCreate + 1);
        Menu testMenu = menuList.get(menuList.size() - 1);
        assertThat(testMenu.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testMenu.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testMenu.getComponent()).isEqualTo(DEFAULT_COMPONENT);
        assertThat(testMenu.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMenu.getIconCls()).isEqualTo(DEFAULT_ICON_CLS);
        assertThat(testMenu.getKeepAlive()).isEqualTo(DEFAULT_KEEP_ALIVE);
        assertThat(testMenu.getRequireAuth()).isEqualTo(DEFAULT_REQUIRE_AUTH);
        assertThat(testMenu.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testMenu.getEnabled()).isEqualTo(DEFAULT_ENABLED);
    }

    @Test
    @Transactional
    void createMenuWithExistingId() throws Exception {
        // Create the Menu with an existing ID
        menu.setId(1L);

        int databaseSizeBeforeCreate = menuRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(menu)))
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMenus() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        // Get all the menuList
        restMenuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menu.getId().intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].component").value(hasItem(DEFAULT_COMPONENT)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].iconCls").value(hasItem(DEFAULT_ICON_CLS)))
            .andExpect(jsonPath("$.[*].keepAlive").value(hasItem(DEFAULT_KEEP_ALIVE)))
            .andExpect(jsonPath("$.[*].requireAuth").value(hasItem(DEFAULT_REQUIRE_AUTH)))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID)))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED)));
    }

    @Test
    @Transactional
    void getMenu() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        // Get the menu
        restMenuMockMvc
            .perform(get(ENTITY_API_URL_ID, menu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(menu.getId().intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH))
            .andExpect(jsonPath("$.component").value(DEFAULT_COMPONENT))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.iconCls").value(DEFAULT_ICON_CLS))
            .andExpect(jsonPath("$.keepAlive").value(DEFAULT_KEEP_ALIVE))
            .andExpect(jsonPath("$.requireAuth").value(DEFAULT_REQUIRE_AUTH))
            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED));
    }

    @Test
    @Transactional
    void getNonExistingMenu() throws Exception {
        // Get the menu
        restMenuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewMenu() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        int databaseSizeBeforeUpdate = menuRepository.findAll().size();

        // Update the menu
        Menu updatedMenu = menuRepository.findById(menu.getId()).get();
        // Disconnect from session so that the updates on updatedMenu are not directly saved in db
        em.detach(updatedMenu);
        updatedMenu
            .url(UPDATED_URL)
            .path(UPDATED_PATH)
            .component(UPDATED_COMPONENT)
            .name(UPDATED_NAME)
            .iconCls(UPDATED_ICON_CLS)
            .keepAlive(UPDATED_KEEP_ALIVE)
            .requireAuth(UPDATED_REQUIRE_AUTH)
            .parentId(UPDATED_PARENT_ID)
            .enabled(UPDATED_ENABLED);

        restMenuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMenu.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMenu))
            )
            .andExpect(status().isOk());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
        Menu testMenu = menuList.get(menuList.size() - 1);
        assertThat(testMenu.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testMenu.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testMenu.getComponent()).isEqualTo(UPDATED_COMPONENT);
        assertThat(testMenu.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMenu.getIconCls()).isEqualTo(UPDATED_ICON_CLS);
        assertThat(testMenu.getKeepAlive()).isEqualTo(UPDATED_KEEP_ALIVE);
        assertThat(testMenu.getRequireAuth()).isEqualTo(UPDATED_REQUIRE_AUTH);
        assertThat(testMenu.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testMenu.getEnabled()).isEqualTo(UPDATED_ENABLED);
    }

    @Test
    @Transactional
    void putNonExistingMenu() throws Exception {
        int databaseSizeBeforeUpdate = menuRepository.findAll().size();
        menu.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, menu.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(menu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMenu() throws Exception {
        int databaseSizeBeforeUpdate = menuRepository.findAll().size();
        menu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(menu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMenu() throws Exception {
        int databaseSizeBeforeUpdate = menuRepository.findAll().size();
        menu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(menu)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMenuWithPatch() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        int databaseSizeBeforeUpdate = menuRepository.findAll().size();

        // Update the menu using partial update
        Menu partialUpdatedMenu = new Menu();
        partialUpdatedMenu.setId(menu.getId());

        partialUpdatedMenu.name(UPDATED_NAME).enabled(UPDATED_ENABLED);

        restMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMenu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMenu))
            )
            .andExpect(status().isOk());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
        Menu testMenu = menuList.get(menuList.size() - 1);
        assertThat(testMenu.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testMenu.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testMenu.getComponent()).isEqualTo(DEFAULT_COMPONENT);
        assertThat(testMenu.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMenu.getIconCls()).isEqualTo(DEFAULT_ICON_CLS);
        assertThat(testMenu.getKeepAlive()).isEqualTo(DEFAULT_KEEP_ALIVE);
        assertThat(testMenu.getRequireAuth()).isEqualTo(DEFAULT_REQUIRE_AUTH);
        assertThat(testMenu.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testMenu.getEnabled()).isEqualTo(UPDATED_ENABLED);
    }

    @Test
    @Transactional
    void fullUpdateMenuWithPatch() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        int databaseSizeBeforeUpdate = menuRepository.findAll().size();

        // Update the menu using partial update
        Menu partialUpdatedMenu = new Menu();
        partialUpdatedMenu.setId(menu.getId());

        partialUpdatedMenu
            .url(UPDATED_URL)
            .path(UPDATED_PATH)
            .component(UPDATED_COMPONENT)
            .name(UPDATED_NAME)
            .iconCls(UPDATED_ICON_CLS)
            .keepAlive(UPDATED_KEEP_ALIVE)
            .requireAuth(UPDATED_REQUIRE_AUTH)
            .parentId(UPDATED_PARENT_ID)
            .enabled(UPDATED_ENABLED);

        restMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMenu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMenu))
            )
            .andExpect(status().isOk());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
        Menu testMenu = menuList.get(menuList.size() - 1);
        assertThat(testMenu.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testMenu.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testMenu.getComponent()).isEqualTo(UPDATED_COMPONENT);
        assertThat(testMenu.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMenu.getIconCls()).isEqualTo(UPDATED_ICON_CLS);
        assertThat(testMenu.getKeepAlive()).isEqualTo(UPDATED_KEEP_ALIVE);
        assertThat(testMenu.getRequireAuth()).isEqualTo(UPDATED_REQUIRE_AUTH);
        assertThat(testMenu.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testMenu.getEnabled()).isEqualTo(UPDATED_ENABLED);
    }

    @Test
    @Transactional
    void patchNonExistingMenu() throws Exception {
        int databaseSizeBeforeUpdate = menuRepository.findAll().size();
        menu.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, menu.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(menu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMenu() throws Exception {
        int databaseSizeBeforeUpdate = menuRepository.findAll().size();
        menu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(menu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMenu() throws Exception {
        int databaseSizeBeforeUpdate = menuRepository.findAll().size();
        menu.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(menu)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMenu() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        int databaseSizeBeforeDelete = menuRepository.findAll().size();

        // Delete the menu
        restMenuMockMvc
            .perform(delete(ENTITY_API_URL_ID, menu.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
