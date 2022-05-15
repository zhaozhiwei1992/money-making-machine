package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RoleMenuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleMenu.class);
        RoleMenu roleMenu1 = new RoleMenu();
        roleMenu1.setId(1L);
        RoleMenu roleMenu2 = new RoleMenu();
        roleMenu2.setId(roleMenu1.getId());
        assertThat(roleMenu1).isEqualTo(roleMenu2);
        roleMenu2.setId(2L);
        assertThat(roleMenu1).isNotEqualTo(roleMenu2);
        roleMenu1.setId(null);
        assertThat(roleMenu1).isNotEqualTo(roleMenu2);
    }
}
