package com.example.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Menu.
 */
@Entity
@Table(name = "sys_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "path")
    private String path;

    @Column(name = "component")
    private String component;

    @Column(name = "name")
    private String name;

    @Column(name = "icon_cls")
    private String iconCls;

    @Column(name = "keep_alive")
    private Integer keepAlive;

    @Column(name = "require_auth")
    private Integer requireAuth;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "enabled")
    private Integer enabled;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Menu id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public Menu url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return this.path;
    }

    public Menu path(String path) {
        this.setPath(path);
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return this.component;
    }

    public Menu component(String component) {
        this.setComponent(component);
        return this;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return this.name;
    }

    public Menu name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return this.iconCls;
    }

    public Menu iconCls(String iconCls) {
        this.setIconCls(iconCls);
        return this;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getKeepAlive() {
        return this.keepAlive;
    }

    public Menu keepAlive(Integer keepAlive) {
        this.setKeepAlive(keepAlive);
        return this;
    }

    public void setKeepAlive(Integer keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Integer getRequireAuth() {
        return this.requireAuth;
    }

    public Menu requireAuth(Integer requireAuth) {
        this.setRequireAuth(requireAuth);
        return this;
    }

    public void setRequireAuth(Integer requireAuth) {
        this.requireAuth = requireAuth;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public Menu parentId(Integer parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getEnabled() {
        return this.enabled;
    }

    public Menu enabled(Integer enabled) {
        this.setEnabled(enabled);
        return this;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Menu)) {
            return false;
        }
        return id != null && id.equals(((Menu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Menu{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", path='" + getPath() + "'" +
            ", component='" + getComponent() + "'" +
            ", name='" + getName() + "'" +
            ", iconCls='" + getIconCls() + "'" +
            ", keepAlive=" + getKeepAlive() +
            ", requireAuth=" + getRequireAuth() +
            ", parentId=" + getParentId() +
            ", enabled=" + getEnabled() +
            "}";
    }
}
