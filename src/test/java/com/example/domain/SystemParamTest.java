package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SystemParamTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemParam.class);
        SystemParam systemParam1 = new SystemParam();
        systemParam1.setId(1L);
        SystemParam systemParam2 = new SystemParam();
        systemParam2.setId(systemParam1.getId());
        assertThat(systemParam1).isEqualTo(systemParam2);
        systemParam2.setId(2L);
        assertThat(systemParam1).isNotEqualTo(systemParam2);
        systemParam1.setId(null);
        assertThat(systemParam1).isNotEqualTo(systemParam2);
    }
}
