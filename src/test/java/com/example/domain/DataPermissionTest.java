package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DataPermissionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataPermission.class);
        DataPermission dataPermission1 = new DataPermission();
        dataPermission1.setId(1L);
        DataPermission dataPermission2 = new DataPermission();
        dataPermission2.setId(dataPermission1.getId());
        assertThat(dataPermission1).isEqualTo(dataPermission2);
        dataPermission2.setId(2L);
        assertThat(dataPermission1).isNotEqualTo(dataPermission2);
        dataPermission1.setId(null);
        assertThat(dataPermission1).isNotEqualTo(dataPermission2);
    }
}
