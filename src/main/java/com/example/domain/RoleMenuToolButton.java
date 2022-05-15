package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 角色,菜单,按钮权限\n@author zhaozhiwei
 */
@Schema(description = "角色,菜单,按钮权限\n@author zhaozhiwei")
@Entity
@Table(name = "sys_role_menu_button")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleMenuToolButton implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    @Column(name = "role_id")
    private String roleId;

    /**
     * 菜单id
     */
    @Schema(description = "菜单id")
    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "tool_button_id")
    private Long toolButtonId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RoleMenuToolButton id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public RoleMenuToolButton roleId(String roleId) {
        this.setRoleId(roleId);
        return this;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return this.menuId;
    }

    public RoleMenuToolButton menuId(String menuId) {
        this.setMenuId(menuId);
        return this;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Long getToolButtonId() {
        return this.toolButtonId;
    }

    public RoleMenuToolButton toolButtonId(Long toolButtonId) {
        this.setToolButtonId(toolButtonId);
        return this;
    }

    public void setToolButtonId(Long toolButtonId) {
        this.toolButtonId = toolButtonId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleMenuToolButton)) {
            return false;
        }
        return id != null && id.equals(((RoleMenuToolButton) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleMenuToolButton{" +
            "id=" + getId() +
            ", roleId='" + getRoleId() + "'" +
            ", menuId='" + getMenuId() + "'" +
            ", toolButtonId=" + getToolButtonId() +
            "}";
    }
}
