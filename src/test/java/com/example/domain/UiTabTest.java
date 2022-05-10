package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UiTabTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UiTab.class);
        UiTab uiTab1 = new UiTab();
        uiTab1.setId(1L);
        UiTab uiTab2 = new UiTab();
        uiTab2.setId(uiTab1.getId());
        assertThat(uiTab1).isEqualTo(uiTab2);
        uiTab2.setId(2L);
        assertThat(uiTab1).isNotEqualTo(uiTab2);
        uiTab1.setId(null);
        assertThat(uiTab1).isNotEqualTo(uiTab2);
    }
}
