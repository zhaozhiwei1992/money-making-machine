package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DataPermissionsRelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataPermissionsRel.class);
        DataPermissionsRel dataPermissionsRel1 = new DataPermissionsRel();
        dataPermissionsRel1.setId(1L);
        DataPermissionsRel dataPermissionsRel2 = new DataPermissionsRel();
        dataPermissionsRel2.setId(dataPermissionsRel1.getId());
        assertThat(dataPermissionsRel1).isEqualTo(dataPermissionsRel2);
        dataPermissionsRel2.setId(2L);
        assertThat(dataPermissionsRel1).isNotEqualTo(dataPermissionsRel2);
        dataPermissionsRel1.setId(null);
        assertThat(dataPermissionsRel1).isNotEqualTo(dataPermissionsRel2);
    }
}
