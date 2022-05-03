package com.example.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UiQueryform.
 */
@Entity
@Table(name = "sys_uiqueryform")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UiQueryform implements Serializable {

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

    @Column(name = "issource")
    private Boolean issource;

    @Column(name = "requirement")
    private Boolean requirement;

    @Column(name = "type")
    private String type;

    @Column(name = "placeholder")
    private String placeholder;

    @Column(name = "config")
    private String config;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UiQueryform id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuid() {
        return this.menuid;
    }

    public UiQueryform menuid(Long menuid) {
        this.setMenuid(menuid);
        return this;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public String getCode() {
        return this.code;
    }

    public UiQueryform code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public UiQueryform name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrdernum() {
        return this.ordernum;
    }

    public UiQueryform ordernum(Integer ordernum) {
        this.setOrdernum(ordernum);
        return this;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Boolean getIssource() {
        return this.issource;
    }

    public UiQueryform issource(Boolean issource) {
        this.setIssource(issource);
        return this;
    }

    public void setIssource(Boolean issource) {
        this.issource = issource;
    }

    public Boolean getRequirement() {
        return this.requirement;
    }

    public UiQueryform requirement(Boolean requirement) {
        this.setRequirement(requirement);
        return this;
    }

    public void setRequirement(Boolean requirement) {
        this.requirement = requirement;
    }

    public String getType() {
        return this.type;
    }

    public UiQueryform type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public UiQueryform placeholder(String placeholder) {
        this.setPlaceholder(placeholder);
        return this;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getConfig() {
        return this.config;
    }

    public UiQueryform config(String config) {
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
        if (!(o instanceof UiQueryform)) {
            return false;
        }
        return id != null && id.equals(((UiQueryform) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UiQueryform{" +
            "id=" + getId() +
            ", menuid=" + getMenuid() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", ordernum=" + getOrdernum() +
            ", issource='" + getIssource() + "'" +
            ", requirement='" + getRequirement() + "'" +
            ", type='" + getType() + "'" +
            ", placeholder='" + getPlaceholder() + "'" +
            ", config='" + getConfig() + "'" +
            "}";
    }
}
