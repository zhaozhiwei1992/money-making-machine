package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 页签配置\n@author zhaozhiwei
 */
@Schema(description = "页签配置\n@author zhaozhiwei")
@Entity
@Table(name = "sys_uitab")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UiTab implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "menuid")
    private Long menuid;

    /**
     * 页签的显示编码, 唯一,根据code业务做自己的处理
     */
    @Schema(description = "页签的显示编码, 唯一,根据code业务做自己的处理")
    @Column(name = "code")
    private String code;

    /**
     * 页签显示的标题
     */
    @Schema(description = "页签显示的标题")
    @Column(name = "name")
    private String name;

    @Column(name = "ordernum")
    private Integer ordernum;

    @Column(name = "config")
    private String config;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UiTab id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuid() {
        return this.menuid;
    }

    public UiTab menuid(Long menuid) {
        this.setMenuid(menuid);
        return this;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public String getCode() {
        return this.code;
    }

    public UiTab code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public UiTab name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrdernum() {
        return this.ordernum;
    }

    public UiTab ordernum(Integer ordernum) {
        this.setOrdernum(ordernum);
        return this;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getConfig() {
        return this.config;
    }

    public UiTab config(String config) {
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
        if (!(o instanceof UiTab)) {
            return false;
        }
        return id != null && id.equals(((UiTab) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UiTab{" +
            "id=" + getId() +
            ", menuid=" + getMenuid() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", ordernum=" + getOrdernum() +
            ", config='" + getConfig() + "'" +
            "}";
    }
}
