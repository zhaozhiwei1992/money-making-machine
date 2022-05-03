package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UiToolButtonTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UiToolButton.class);
        UiToolButton uiToolButton1 = new UiToolButton();
        uiToolButton1.setId(1L);
        UiToolButton uiToolButton2 = new UiToolButton();
        uiToolButton2.setId(uiToolButton1.getId());
        assertThat(uiToolButton1).isEqualTo(uiToolButton2);
        uiToolButton2.setId(2L);
        assertThat(uiToolButton1).isNotEqualTo(uiToolButton2);
        uiToolButton1.setId(null);
        assertThat(uiToolButton1).isNotEqualTo(uiToolButton2);
    }
}
