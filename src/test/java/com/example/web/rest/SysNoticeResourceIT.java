package com.example.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.IntegrationTest;
import com.example.domain.SysNotice;
import com.example.repository.SysNoticeRepository;
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
 * Integration tests for the {@link SysNoticeResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class SysNoticeResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_CREATER = "AAAAAAAAAA";
    private static final String UPDATED_CREATER = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_REC_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_REC_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_URGENT = false;
    private static final Boolean UPDATED_URGENT = true;

    private static final Integer DEFAULT_NOTI_TYPE = 1;
    private static final Integer UPDATED_NOTI_TYPE = 2;

    private static final String ENTITY_API_URL = "/api/sys-notices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SysNoticeRepository sysNoticeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysNoticeMockMvc;

    private SysNotice sysNotice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysNotice createEntity(EntityManager em) {
        SysNotice sysNotice = new SysNotice()
            .title(DEFAULT_TITLE)
            .content(DEFAULT_CONTENT)
            .creater(DEFAULT_CREATER)
            .createTime(DEFAULT_CREATE_TIME)
            .recType(DEFAULT_REC_TYPE)
            .receiver(DEFAULT_RECEIVER)
            .urgent(DEFAULT_URGENT)
            .notiType(DEFAULT_NOTI_TYPE);
        return sysNotice;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysNotice createUpdatedEntity(EntityManager em) {
        SysNotice sysNotice = new SysNotice()
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .creater(UPDATED_CREATER)
            .createTime(UPDATED_CREATE_TIME)
            .recType(UPDATED_REC_TYPE)
            .receiver(UPDATED_RECEIVER)
            .urgent(UPDATED_URGENT)
            .notiType(UPDATED_NOTI_TYPE);
        return sysNotice;
    }

    @BeforeEach
    public void initTest() {
        sysNotice = createEntity(em);
    }

    @Test
    @Transactional
    void createSysNotice() throws Exception {
        int databaseSizeBeforeCreate = sysNoticeRepository.findAll().size();
        // Create the SysNotice
        restSysNoticeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysNotice)))
            .andExpect(status().isCreated());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeCreate + 1);
        SysNotice testSysNotice = sysNoticeList.get(sysNoticeList.size() - 1);
        assertThat(testSysNotice.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testSysNotice.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testSysNotice.getCreater()).isEqualTo(DEFAULT_CREATER);
        assertThat(testSysNotice.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysNotice.getRecType()).isEqualTo(DEFAULT_REC_TYPE);
        assertThat(testSysNotice.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testSysNotice.getUrgent()).isEqualTo(DEFAULT_URGENT);
        assertThat(testSysNotice.getNotiType()).isEqualTo(DEFAULT_NOTI_TYPE);
    }

    @Test
    @Transactional
    void createSysNoticeWithExistingId() throws Exception {
        // Create the SysNotice with an existing ID
        sysNotice.setId(1L);

        int databaseSizeBeforeCreate = sysNoticeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysNoticeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysNotice)))
            .andExpect(status().isBadRequest());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysNotices() throws Exception {
        // Initialize the database
        sysNoticeRepository.saveAndFlush(sysNotice);

        // Get all the sysNoticeList
        restSysNoticeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysNotice.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].creater").value(hasItem(DEFAULT_CREATER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.[*].recType").value(hasItem(DEFAULT_REC_TYPE)))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER)))
            .andExpect(jsonPath("$.[*].urgent").value(hasItem(DEFAULT_URGENT.booleanValue())))
            .andExpect(jsonPath("$.[*].notiType").value(hasItem(DEFAULT_NOTI_TYPE)));
    }

    @Test
    @Transactional
    void getSysNotice() throws Exception {
        // Initialize the database
        sysNoticeRepository.saveAndFlush(sysNotice);

        // Get the sysNotice
        restSysNoticeMockMvc
            .perform(get(ENTITY_API_URL_ID, sysNotice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysNotice.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.creater").value(DEFAULT_CREATER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME))
            .andExpect(jsonPath("$.recType").value(DEFAULT_REC_TYPE))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER))
            .andExpect(jsonPath("$.urgent").value(DEFAULT_URGENT.booleanValue()))
            .andExpect(jsonPath("$.notiType").value(DEFAULT_NOTI_TYPE));
    }

    @Test
    @Transactional
    void getNonExistingSysNotice() throws Exception {
        // Get the sysNotice
        restSysNoticeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSysNotice() throws Exception {
        // Initialize the database
        sysNoticeRepository.saveAndFlush(sysNotice);

        int databaseSizeBeforeUpdate = sysNoticeRepository.findAll().size();

        // Update the sysNotice
        SysNotice updatedSysNotice = sysNoticeRepository.findById(sysNotice.getId()).get();
        // Disconnect from session so that the updates on updatedSysNotice are not directly saved in db
        em.detach(updatedSysNotice);
        updatedSysNotice
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .creater(UPDATED_CREATER)
            .createTime(UPDATED_CREATE_TIME)
            .recType(UPDATED_REC_TYPE)
            .receiver(UPDATED_RECEIVER)
            .urgent(UPDATED_URGENT)
            .notiType(UPDATED_NOTI_TYPE);

        restSysNoticeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSysNotice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSysNotice))
            )
            .andExpect(status().isOk());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeUpdate);
        SysNotice testSysNotice = sysNoticeList.get(sysNoticeList.size() - 1);
        assertThat(testSysNotice.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSysNotice.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testSysNotice.getCreater()).isEqualTo(UPDATED_CREATER);
        assertThat(testSysNotice.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysNotice.getRecType()).isEqualTo(UPDATED_REC_TYPE);
        assertThat(testSysNotice.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testSysNotice.getUrgent()).isEqualTo(UPDATED_URGENT);
        assertThat(testSysNotice.getNotiType()).isEqualTo(UPDATED_NOTI_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingSysNotice() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeRepository.findAll().size();
        sysNotice.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysNoticeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysNotice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysNotice))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysNotice() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeRepository.findAll().size();
        sysNotice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysNoticeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysNotice))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysNotice() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeRepository.findAll().size();
        sysNotice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysNoticeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysNotice)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysNoticeWithPatch() throws Exception {
        // Initialize the database
        sysNoticeRepository.saveAndFlush(sysNotice);

        int databaseSizeBeforeUpdate = sysNoticeRepository.findAll().size();

        // Update the sysNotice using partial update
        SysNotice partialUpdatedSysNotice = new SysNotice();
        partialUpdatedSysNotice.setId(sysNotice.getId());

        partialUpdatedSysNotice
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .recType(UPDATED_REC_TYPE)
            .receiver(UPDATED_RECEIVER)
            .urgent(UPDATED_URGENT);

        restSysNoticeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysNotice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysNotice))
            )
            .andExpect(status().isOk());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeUpdate);
        SysNotice testSysNotice = sysNoticeList.get(sysNoticeList.size() - 1);
        assertThat(testSysNotice.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSysNotice.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testSysNotice.getCreater()).isEqualTo(DEFAULT_CREATER);
        assertThat(testSysNotice.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysNotice.getRecType()).isEqualTo(UPDATED_REC_TYPE);
        assertThat(testSysNotice.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testSysNotice.getUrgent()).isEqualTo(UPDATED_URGENT);
        assertThat(testSysNotice.getNotiType()).isEqualTo(DEFAULT_NOTI_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateSysNoticeWithPatch() throws Exception {
        // Initialize the database
        sysNoticeRepository.saveAndFlush(sysNotice);

        int databaseSizeBeforeUpdate = sysNoticeRepository.findAll().size();

        // Update the sysNotice using partial update
        SysNotice partialUpdatedSysNotice = new SysNotice();
        partialUpdatedSysNotice.setId(sysNotice.getId());

        partialUpdatedSysNotice
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .creater(UPDATED_CREATER)
            .createTime(UPDATED_CREATE_TIME)
            .recType(UPDATED_REC_TYPE)
            .receiver(UPDATED_RECEIVER)
            .urgent(UPDATED_URGENT)
            .notiType(UPDATED_NOTI_TYPE);

        restSysNoticeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysNotice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysNotice))
            )
            .andExpect(status().isOk());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeUpdate);
        SysNotice testSysNotice = sysNoticeList.get(sysNoticeList.size() - 1);
        assertThat(testSysNotice.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSysNotice.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testSysNotice.getCreater()).isEqualTo(UPDATED_CREATER);
        assertThat(testSysNotice.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysNotice.getRecType()).isEqualTo(UPDATED_REC_TYPE);
        assertThat(testSysNotice.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testSysNotice.getUrgent()).isEqualTo(UPDATED_URGENT);
        assertThat(testSysNotice.getNotiType()).isEqualTo(UPDATED_NOTI_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingSysNotice() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeRepository.findAll().size();
        sysNotice.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysNoticeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysNotice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysNotice))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysNotice() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeRepository.findAll().size();
        sysNotice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysNoticeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysNotice))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysNotice() throws Exception {
        int databaseSizeBeforeUpdate = sysNoticeRepository.findAll().size();
        sysNotice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysNoticeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysNotice))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysNotice in the database
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysNotice() throws Exception {
        // Initialize the database
        sysNoticeRepository.saveAndFlush(sysNotice);

        int databaseSizeBeforeDelete = sysNoticeRepository.findAll().size();

        // Delete the sysNotice
        restSysNoticeMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysNotice.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysNotice> sysNoticeList = sysNoticeRepository.findAll();
        assertThat(sysNoticeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
