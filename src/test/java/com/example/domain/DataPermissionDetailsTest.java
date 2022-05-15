package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DataPermissionDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataPermissionDetails.class);
        DataPermissionDetails dataPermissionDetails1 = new DataPermissionDetails();
        dataPermissionDetails1.setId(1L);
        DataPermissionDetails dataPermissionDetails2 = new DataPermissionDetails();
        dataPermissionDetails2.setId(dataPermissionDetails1.getId());
        assertThat(dataPermissionDetails1).isEqualTo(dataPermissionDetails2);
        dataPermissionDetails2.setId(2L);
        assertThat(dataPermissionDetails1).isNotEqualTo(dataPermissionDetails2);
        dataPermissionDetails1.setId(null);
        assertThat(dataPermissionDetails1).isNotEqualTo(dataPermissionDetails2);
    }
}
