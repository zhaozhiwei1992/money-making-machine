package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysNoticeSubTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysNoticeSub.class);
        SysNoticeSub sysNoticeSub1 = new SysNoticeSub();
        sysNoticeSub1.setId(1L);
        SysNoticeSub sysNoticeSub2 = new SysNoticeSub();
        sysNoticeSub2.setId(sysNoticeSub1.getId());
        assertThat(sysNoticeSub1).isEqualTo(sysNoticeSub2);
        sysNoticeSub2.setId(2L);
        assertThat(sysNoticeSub1).isNotEqualTo(sysNoticeSub2);
        sysNoticeSub1.setId(null);
        assertThat(sysNoticeSub1).isNotEqualTo(sysNoticeSub2);
    }
}
