package com.example.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UiEditform.
 */
@Entity
@Table(name = "sys_uieditform")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UiEditform implements Serializable {

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

    @Column(name = "isedit")
    private Boolean isedit;

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

    public UiEditform id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuid() {
        return this.menuid;
    }

    public UiEditform menuid(Long menuid) {
        this.setMenuid(menuid);
        return this;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public String getCode() {
        return this.code;
    }

    public UiEditform code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public UiEditform name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrdernum() {
        return this.ordernum;
    }

    public UiEditform ordernum(Integer ordernum) {
        this.setOrdernum(ordernum);
        return this;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Boolean getIssource() {
        return this.issource;
    }

    public UiEditform issource(Boolean issource) {
        this.setIssource(issource);
        return this;
    }

    public void setIssource(Boolean issource) {
        this.issource = issource;
    }

    public Boolean getIsedit() {
        return this.isedit;
    }

    public UiEditform isedit(Boolean isedit) {
        this.setIsedit(isedit);
        return this;
    }

    public void setIsedit(Boolean isedit) {
        this.isedit = isedit;
    }

    public Boolean getRequirement() {
        return this.requirement;
    }

    public UiEditform requirement(Boolean requirement) {
        this.setRequirement(requirement);
        return this;
    }

    public void setRequirement(Boolean requirement) {
        this.requirement = requirement;
    }

    public String getType() {
        return this.type;
    }

    public UiEditform type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public UiEditform placeholder(String placeholder) {
        this.setPlaceholder(placeholder);
        return this;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getConfig() {
        return this.config;
    }

    public UiEditform config(String config) {
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
        if (!(o instanceof UiEditform)) {
            return false;
        }
        return id != null && id.equals(((UiEditform) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UiEditform{" +
            "id=" + getId() +
            ", menuid=" + getMenuid() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", ordernum=" + getOrdernum() +
            ", issource='" + getIssource() + "'" +
            ", isedit='" + getIsedit() + "'" +
            ", requirement='" + getRequirement() + "'" +
            ", type='" + getType() + "'" +
            ", placeholder='" + getPlaceholder() + "'" +
            ", config='" + getConfig() + "'" +
            "}";
    }
}
