package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UiQueryformTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UiQueryform.class);
        UiQueryform uiQueryform1 = new UiQueryform();
        uiQueryform1.setId(1L);
        UiQueryform uiQueryform2 = new UiQueryform();
        uiQueryform2.setId(uiQueryform1.getId());
        assertThat(uiQueryform1).isEqualTo(uiQueryform2);
        uiQueryform2.setId(2L);
        assertThat(uiQueryform1).isNotEqualTo(uiQueryform2);
        uiQueryform1.setId(null);
        assertThat(uiQueryform1).isNotEqualTo(uiQueryform2);
    }
}
