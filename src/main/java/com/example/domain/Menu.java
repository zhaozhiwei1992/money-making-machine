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

    @Column(name = "name")
    private String name;

    @Column(name = "icon_cls")
    private String iconCls;

    @Column(name = "ordernum")
    private Integer ordernum;

    @Column(name = "keep_alive")
    private Boolean keepAlive;

    @Column(name = "require_auth")
    private Boolean requireAuth;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "config")
    private String config;

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

    public Integer getOrdernum() {
        return this.ordernum;
    }

    public Menu ordernum(Integer ordernum) {
        this.setOrdernum(ordernum);
        return this;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Boolean getKeepAlive() {
        return this.keepAlive;
    }

    public Menu keepAlive(Boolean keepAlive) {
        this.setKeepAlive(keepAlive);
        return this;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getRequireAuth() {
        return this.requireAuth;
    }

    public Menu requireAuth(Boolean requireAuth) {
        this.setRequireAuth(requireAuth);
        return this;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public Menu parentId(Long parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public Menu enabled(Boolean enabled) {
        this.setEnabled(enabled);
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfig() {
        return this.config;
    }

    public Menu config(String config) {
        this.setConfig(config);
        return this;
    }

    public void setConfig(String config) {
        this.config = config;
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
            ", name='" + getName() + "'" +
            ", iconCls='" + getIconCls() + "'" +
            ", ordernum=" + getOrdernum() +
            ", keepAlive='" + getKeepAlive() + "'" +
            ", requireAuth='" + getRequireAuth() + "'" +
            ", parentId=" + getParentId() +
            ", enabled='" + getEnabled() + "'" +
            ", config='" + getConfig() + "'" +
            "}";
    }
}
