package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskParamTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskParam.class);
        TaskParam taskParam1 = new TaskParam();
        taskParam1.setId(1L);
        TaskParam taskParam2 = new TaskParam();
        taskParam2.setId(taskParam1.getId());
        assertThat(taskParam1).isEqualTo(taskParam2);
        taskParam2.setId(2L);
        assertThat(taskParam1).isNotEqualTo(taskParam2);
        taskParam1.setId(null);
        assertThat(taskParam1).isNotEqualTo(taskParam2);
    }
}
