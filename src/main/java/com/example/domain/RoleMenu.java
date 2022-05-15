package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 角色对菜单\n@author zhaozhiwei
 */
@Schema(description = "角色对菜单\n@author zhaozhiwei")
@Entity
@Table(name = "sys_role_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleMenu implements Serializable {

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RoleMenu id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public RoleMenu roleId(String roleId) {
        this.setRoleId(roleId);
        return this;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return this.menuId;
    }

    public RoleMenu menuId(String menuId) {
        this.setMenuId(menuId);
        return this;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleMenu)) {
            return false;
        }
        return id != null && id.equals(((RoleMenu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleMenu{" +
            "id=" + getId() +
            ", roleId='" + getRoleId() + "'" +
            ", menuId='" + getMenuId() + "'" +
            "}";
    }
}
