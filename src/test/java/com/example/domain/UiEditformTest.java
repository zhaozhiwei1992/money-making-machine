package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UiEditformTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UiEditform.class);
        UiEditform uiEditform1 = new UiEditform();
        uiEditform1.setId(1L);
        UiEditform uiEditform2 = new UiEditform();
        uiEditform2.setId(uiEditform1.getId());
        assertThat(uiEditform1).isEqualTo(uiEditform2);
        uiEditform2.setId(2L);
        assertThat(uiEditform1).isNotEqualTo(uiEditform2);
        uiEditform1.setId(null);
        assertThat(uiEditform1).isNotEqualTo(uiEditform2);
    }
}
