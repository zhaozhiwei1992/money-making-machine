package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.SysNoticeSub;
import com.example.repository.SysNoticeSubRepository;
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
 * Integration tests for the {@link SysNoticeSubResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class SysNoticeSubResourceIT {

    private static final Long DEFAULT_SYS_NOTICE_ID = 1L;
    private static final Long UPDATED_SYS_NOTICE_ID = 2L;

    private static final String DEFAULT_RECIPIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECIPIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_TIME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String ENTITY_API_URL = "/api/sys-notice-subs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SysNoticeSubRepository sysNoticeSubRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysNoticeSubMockMvc;

    private SysNoticeSub sysNoticeSub;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysNoticeSub createEntity(EntityManager em) {
        SysNoticeSub sysNoticeSub = new SysNoticeSub()
            .sysNoticeId(DEFAULT_SYS_NOTICE_ID)
            .recipientId(DEFAULT_RECIPIENT_ID)
            .updateTime(DEFAULT_UPDATE_TIME)
            .status(DEFAULT_STATUS);
        return sysNoticeSub;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysNoticeSub createUpdatedEntity(EntityManager em) {
        SysNoticeSub sysNoticeSub = new SysNoticeSub()
            .sysNoticeId(UPDATED_SYS_NOTICE_ID)
            .recipientId(UPDATED_RECIPIENT_ID)
            .updateTime(UPDATED_UPDATE_TIME)
            .status(UPDATED_STATUS);
        return sysNoticeSub;
    }

    @BeforeEach
    public void initTest() {
        sysNoticeSub = createEntity(em);
    }

    @Test
    @Transactional
    void createSysNoticeSub() throws Exception {
        int databaseSizeBeforeCreate = sysNoticeSubRepository.findAll().size();
        // Create the SysNoticeSub
        restSysNoticeSubMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysNoticeSub)))
            .andExpect(status().isCreated());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeCreate + 1);
        SysNoticeSub testSysNoticeSub = sysNoticeSubList.get(sysNoticeSubList.size() - 1);
        assertThat(testSysNoticeSub.getSysNoticeId()).isEqualTo(DEFAULT_SYS_NOTICE_ID);
        assertThat(testSysNoticeSub.getRecipientId()).isEqualTo(DEFAULT_RECIPIENT_ID);
        assertThat(testSysNoticeSub.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysNoticeSub.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createSysNoticeSubWithExistingId() throws Exception {
        // Create the SysNoticeSub with an existing ID
        sysNoticeSub.setId(1L);

        int databaseSizeBeforeCreate = sysNoticeSubRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysNoticeSubMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysNoticeSub)))
            .andExpect(status().isBadRequest());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysNoticeSubs() throws Exception {
        // Initialize the database
        sysNoticeSubRepository.saveAndFlush(sysNoticeSub);

        // Get all the sysNoticeSubList
        restSysNoticeSubMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysNoticeSub.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysNoticeId").value(hasItem(DEFAULT_SYS_NOTICE_ID.intValue())))
            .andExpect(jsonPath("$.[*].recipientId").value(hasItem(DEFAULT_RECIPIENT_ID)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    void getSysNoticeSub() throws Exception {
        // Initialize the database
        sysNoticeSubRepository.saveAndFlush(sysNoticeSub);

        // Get the sysNoticeSub
        restSysNoticeSubMockMvc
            .perform(get(ENTITY_API_URL_ID, sysNoticeSub.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysNoticeSub.getId().intValue()))
            .andExpect(jsonPath("$.sysNoticeId").value(DEFAULT_SYS_NOTICE_ID.intValue()))
            .andExpect(jsonPath("$.recipientId").value(DEFAULT_RECIPIENT_ID))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingSysNoticeSub() throws Exception {
        // Get the sysNoticeSub
        restSysNoticeSubMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSysNoticeSub() throws Exception {
        // Initialize the database
        sysNoticeSubRepository.saveAndFlush(sysNoticeSub);

        int databaseSizeBeforeUpdate = sysNoticeSubRepository.findAll().size();

        // Update the sysNoticeSub
        SysNoticeSub updatedSysNoticeSub = sysNoticeSubRepository.findById(sysNoticeSub.getId()).get();
        // Disconnect from session so that the updates on updatedSysNoticeSub are not directly saved in db
        em.detach(updatedSysNoticeSub);
        updatedSysNoticeSub
            .sysNoticeId(UPDATED_SYS_NOTICE_ID)
            .recipientId(UPDATED_RECIPIENT_ID)
            .updateTime(UPDATED_UPDATE_TIME)
            .status(UPDATED_STATUS);

        restSysNoticeSubMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSysNoticeSub.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSysNoticeSub))
            )
            .andExpect(status().isOk());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeUpdate);
        SysNoticeSub testSysNoticeSub = sysNoticeSubList.get(sysNoticeSubList.size() - 1);
        assertThat(testSysNoticeSub.getSysNoticeId()).isEqualTo(UPDATED_SYS_NOTICE_ID);
        assertThat(testSysNoticeSub.getRecipientId()).isEqualTo(UPDATED_RECIPIENT_ID);
        assertThat(testSysNoticeSub.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysNoticeSub.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingSysNoticeSub() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeSubRepository.findAll().size();
        sysNoticeSub.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysNoticeSubMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysNoticeSub.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysNoticeSub))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysNoticeSub() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeSubRepository.findAll().size();
        sysNoticeSub.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysNoticeSubMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysNoticeSub))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysNoticeSub() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeSubRepository.findAll().size();
        sysNoticeSub.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysNoticeSubMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysNoticeSub)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysNoticeSubWithPatch() throws Exception {
        // Initialize the database
        sysNoticeSubRepository.saveAndFlush(sysNoticeSub);

        int databaseSizeBeforeUpdate = sysNoticeSubRepository.findAll().size();

        // Update the sysNoticeSub using partial update
        SysNoticeSub partialUpdatedSysNoticeSub = new SysNoticeSub();
        partialUpdatedSysNoticeSub.setId(sysNoticeSub.getId());

        partialUpdatedSysNoticeSub.sysNoticeId(UPDATED_SYS_NOTICE_ID).updateTime(UPDATED_UPDATE_TIME).status(UPDATED_STATUS);

        restSysNoticeSubMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysNoticeSub.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysNoticeSub))
            )
            .andExpect(status().isOk());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeUpdate);
        SysNoticeSub testSysNoticeSub = sysNoticeSubList.get(sysNoticeSubList.size() - 1);
        assertThat(testSysNoticeSub.getSysNoticeId()).isEqualTo(UPDATED_SYS_NOTICE_ID);
        assertThat(testSysNoticeSub.getRecipientId()).isEqualTo(DEFAULT_RECIPIENT_ID);
        assertThat(testSysNoticeSub.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysNoticeSub.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateSysNoticeSubWithPatch() throws Exception {
        // Initialize the database
        sysNoticeSubRepository.saveAndFlush(sysNoticeSub);

        int databaseSizeBeforeUpdate = sysNoticeSubRepository.findAll().size();

        // Update the sysNoticeSub using partial update
        SysNoticeSub partialUpdatedSysNoticeSub = new SysNoticeSub();
        partialUpdatedSysNoticeSub.setId(sysNoticeSub.getId());

        partialUpdatedSysNoticeSub
            .sysNoticeId(UPDATED_SYS_NOTICE_ID)
            .recipientId(UPDATED_RECIPIENT_ID)
            .updateTime(UPDATED_UPDATE_TIME)
            .status(UPDATED_STATUS);

        restSysNoticeSubMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysNoticeSub.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysNoticeSub))
            )
            .andExpect(status().isOk());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeUpdate);
        SysNoticeSub testSysNoticeSub = sysNoticeSubList.get(sysNoticeSubList.size() - 1);
        assertThat(testSysNoticeSub.getSysNoticeId()).isEqualTo(UPDATED_SYS_NOTICE_ID);
        assertThat(testSysNoticeSub.getRecipientId()).isEqualTo(UPDATED_RECIPIENT_ID);
        assertThat(testSysNoticeSub.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysNoticeSub.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingSysNoticeSub() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeSubRepository.findAll().size();
        sysNoticeSub.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysNoticeSubMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysNoticeSub.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysNoticeSub))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysNoticeSub() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeSubRepository.findAll().size();
        sysNoticeSub.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysNoticeSubMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysNoticeSub))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysNoticeSub() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeSubRepository.findAll().size();
        sysNoticeSub.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysNoticeSubMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysNoticeSub))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysNoticeSub in the database
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysNoticeSub() throws Exception {
        // Initialize the database
        sysNoticeSubRepository.saveAndFlush(sysNoticeSub);

        int databaseSizeBeforeDelete = sysNoticeSubRepository.findAll().size();

        // Delete the sysNoticeSub
        restSysNoticeSubMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysNoticeSub.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysNoticeSub> sysNoticeSubList = sysNoticeSubRepository.findAll();
        assertThat(sysNoticeSubList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
