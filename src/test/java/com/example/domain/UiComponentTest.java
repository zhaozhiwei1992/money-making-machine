package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UiComponentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UiComponent.class);
        UiComponent uiComponent1 = new UiComponent();
        uiComponent1.setId(1L);
        UiComponent uiComponent2 = new UiComponent();
        uiComponent2.setId(uiComponent1.getId());
        assertThat(uiComponent1).isEqualTo(uiComponent2);
        uiComponent2.setId(2L);
        assertThat(uiComponent1).isNotEqualTo(uiComponent2);
        uiComponent1.setId(null);
        assertThat(uiComponent1).isNotEqualTo(uiComponent2);
    }
}
