package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EleUnionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EleUnion.class);
        EleUnion eleUnion1 = new EleUnion();
        eleUnion1.setId(1L);
        EleUnion eleUnion2 = new EleUnion();
        eleUnion2.setId(eleUnion1.getId());
        assertThat(eleUnion1).isEqualTo(eleUnion2);
        eleUnion2.setId(2L);
        assertThat(eleUnion1).isNotEqualTo(eleUnion2);
        eleUnion1.setId(null);
        assertThat(eleUnion1).isNotEqualTo(eleUnion2);
    }
}
