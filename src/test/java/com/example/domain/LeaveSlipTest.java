package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeaveSlipTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaveSlip.class);
        LeaveSlip leaveSlip1 = new LeaveSlip();
        leaveSlip1.setId(1L);
        LeaveSlip leaveSlip2 = new LeaveSlip();
        leaveSlip2.setId(leaveSlip1.getId());
        assertThat(leaveSlip1).isEqualTo(leaveSlip2);
        leaveSlip2.setId(2L);
        assertThat(leaveSlip1).isNotEqualTo(leaveSlip2);
        leaveSlip1.setId(null);
        assertThat(leaveSlip1).isNotEqualTo(leaveSlip2);
    }
}
