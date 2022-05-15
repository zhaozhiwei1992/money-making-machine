package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RoleMenuToolButtonTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleMenuToolButton.class);
        RoleMenuToolButton roleMenuToolButton1 = new RoleMenuToolButton();
        roleMenuToolButton1.setId(1L);
        RoleMenuToolButton roleMenuToolButton2 = new RoleMenuToolButton();
        roleMenuToolButton2.setId(roleMenuToolButton1.getId());
        assertThat(roleMenuToolButton1).isEqualTo(roleMenuToolButton2);
        roleMenuToolButton2.setId(2L);
        assertThat(roleMenuToolButton1).isNotEqualTo(roleMenuToolButton2);
        roleMenuToolButton1.setId(null);
        assertThat(roleMenuToolButton1).isNotEqualTo(roleMenuToolButton2);
    }
}
