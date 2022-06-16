package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysFormulaTabTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysFormulaTab.class);
        SysFormulaTab sysFormulaTab1 = new SysFormulaTab();
        sysFormulaTab1.setId(1L);
        SysFormulaTab sysFormulaTab2 = new SysFormulaTab();
        sysFormulaTab2.setId(sysFormulaTab1.getId());
        assertThat(sysFormulaTab1).isEqualTo(sysFormulaTab2);
        sysFormulaTab2.setId(2L);
        assertThat(sysFormulaTab1).isNotEqualTo(sysFormulaTab2);
        sysFormulaTab1.setId(null);
        assertThat(sysFormulaTab1).isNotEqualTo(sysFormulaTab2);
    }
}
