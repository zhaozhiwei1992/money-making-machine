package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysCollectColTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCollectCol.class);
        SysCollectCol sysCollectCol1 = new SysCollectCol();
        sysCollectCol1.setId(1L);
        SysCollectCol sysCollectCol2 = new SysCollectCol();
        sysCollectCol2.setId(sysCollectCol1.getId());
        assertThat(sysCollectCol1).isEqualTo(sysCollectCol2);
        sysCollectCol2.setId(2L);
        assertThat(sysCollectCol1).isNotEqualTo(sysCollectCol2);
        sysCollectCol1.setId(null);
        assertThat(sysCollectCol1).isNotEqualTo(sysCollectCol2);
    }
}
