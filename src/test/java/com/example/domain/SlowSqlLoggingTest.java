package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SlowSqlLoggingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlowSqlLogging.class);
        SlowSqlLogging slowSqlLogging1 = new SlowSqlLogging();
        slowSqlLogging1.setId(1L);
        SlowSqlLogging slowSqlLogging2 = new SlowSqlLogging();
        slowSqlLogging2.setId(slowSqlLogging1.getId());
        assertThat(slowSqlLogging1).isEqualTo(slowSqlLogging2);
        slowSqlLogging2.setId(2L);
        assertThat(slowSqlLogging1).isNotEqualTo(slowSqlLogging2);
        slowSqlLogging1.setId(null);
        assertThat(slowSqlLogging1).isNotEqualTo(slowSqlLogging2);
    }
}
