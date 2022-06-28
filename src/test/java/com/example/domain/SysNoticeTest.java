package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysNoticeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysNotice.class);
        SysNotice sysNotice1 = new SysNotice();
        sysNotice1.setId(1L);
        SysNotice sysNotice2 = new SysNotice();
        sysNotice2.setId(sysNotice1.getId());
        assertThat(sysNotice1).isEqualTo(sysNotice2);
        sysNotice2.setId(2L);
        assertThat(sysNotice1).isNotEqualTo(sysNotice2);
        sysNotice1.setId(null);
        assertThat(sysNotice1).isNotEqualTo(sysNotice2);
    }
}
