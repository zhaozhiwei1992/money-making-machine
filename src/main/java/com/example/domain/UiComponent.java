package com.example.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UiComponent.
 */
@Entity
@Table(name = "sys_uicomponent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UiComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "menuid")
    private Long menuid;

    @Column(name = "ordernum")
    private Integer ordernum;

    @Column(name = "componentid")
    private String componentid;

    @Column(name = "config")
    private String config;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UiComponent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuid() {
        return this.menuid;
    }

    public UiComponent menuid(Long menuid) {
        this.setMenuid(menuid);
        return this;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public Integer getOrdernum() {
        return this.ordernum;
    }

    public UiComponent ordernum(Integer ordernum) {
        this.setOrdernum(ordernum);
        return this;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getComponentid() {
        return this.componentid;
    }

    public UiComponent componentid(String componentid) {
        this.setComponentid(componentid);
        return this;
    }

    public void setComponentid(String componentid) {
        this.componentid = componentid;
    }

    public String getConfig() {
        return this.config;
    }

    public UiComponent config(String config) {
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
        if (!(o instanceof UiComponent)) {
            return false;
        }
        return id != null && id.equals(((UiComponent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UiComponent{" +
            "id=" + getId() +
            ", menuid=" + getMenuid() +
            ", ordernum=" + getOrdernum() +
            ", componentid='" + getComponentid() + "'" +
            ", config='" + getConfig() + "'" +
            "}";
    }
}
