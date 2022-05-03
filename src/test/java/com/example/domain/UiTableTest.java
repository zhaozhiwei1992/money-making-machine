package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UiTableTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UiTable.class);
        UiTable uiTable1 = new UiTable();
        uiTable1.setId(1L);
        UiTable uiTable2 = new UiTable();
        uiTable2.setId(uiTable1.getId());
        assertThat(uiTable1).isEqualTo(uiTable2);
        uiTable2.setId(2L);
        assertThat(uiTable1).isNotEqualTo(uiTable2);
        uiTable1.setId(null);
        assertThat(uiTable1).isNotEqualTo(uiTable2);
    }
}
