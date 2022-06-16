package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysCollectTabTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCollectTab.class);
        SysCollectTab sysCollectTab1 = new SysCollectTab();
        sysCollectTab1.setId(1L);
        SysCollectTab sysCollectTab2 = new SysCollectTab();
        sysCollectTab2.setId(sysCollectTab1.getId());
        assertThat(sysCollectTab1).isEqualTo(sysCollectTab2);
        sysCollectTab2.setId(2L);
        assertThat(sysCollectTab1).isNotEqualTo(sysCollectTab2);
        sysCollectTab1.setId(null);
        assertThat(sysCollectTab1).isNotEqualTo(sysCollectTab2);
    }
}
