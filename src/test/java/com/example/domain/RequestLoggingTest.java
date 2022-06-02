package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RequestLoggingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestLogging.class);
        RequestLogging requestLogging1 = new RequestLogging();
        requestLogging1.setId(1L);
        RequestLogging requestLogging2 = new RequestLogging();
        requestLogging2.setId(requestLogging1.getId());
        assertThat(requestLogging1).isEqualTo(requestLogging2);
        requestLogging2.setId(2L);
        assertThat(requestLogging1).isNotEqualTo(requestLogging2);
        requestLogging1.setId(null);
        assertThat(requestLogging1).isNotEqualTo(requestLogging2);
    }
}
