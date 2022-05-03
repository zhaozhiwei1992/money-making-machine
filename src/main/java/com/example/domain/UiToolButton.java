package com.example.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UiToolButton.
 */
@Entity
@Table(name = "sys_uitoolbutton")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UiToolButton implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "menuid")
    private Long menuid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "ordernum")
    private Integer ordernum;

    @Column(name = "action")
    private String action;

    @Column(name = "config")
    private String config;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UiToolButton id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuid() {
        return this.menuid;
    }

    public UiToolButton menuid(Long menuid) {
        this.setMenuid(menuid);
        return this;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public String getCode() {
        return this.code;
    }

    public UiToolButton code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public UiToolButton name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrdernum() {
        return this.ordernum;
    }

    public UiToolButton ordernum(Integer ordernum) {
        this.setOrdernum(ordernum);
        return this;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getAction() {
        return this.action;
    }

    public UiToolButton action(String action) {
        this.setAction(action);
        return this;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getConfig() {
        return this.config;
    }

    public UiToolButton config(String config) {
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
        if (!(o instanceof UiToolButton)) {
            return false;
        }
        return id != null && id.equals(((UiToolButton) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UiToolButton{" +
            "id=" + getId() +
            ", menuid=" + getMenuid() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", ordernum=" + getOrdernum() +
            ", action='" + getAction() + "'" +
            ", config='" + getConfig() + "'" +
            "}";
    }
}
