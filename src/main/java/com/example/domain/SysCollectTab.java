package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 采集表信息\n@author zhaozhiwei
 */
@Schema(description = "采集表信息\n@author zhaozhiwei")
@Entity
@Table(name = "sys_collect_tab")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysCollectTab implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 表中文名
     */
    @Schema(description = "表中文名")
    @Column(name = "tab_cname")
    private String tabCname;

    /**
     * 表英文名
     */
    @Schema(description = "表英文名")
    @Column(name = "tab_ename")
    private String tabEname;

    /**
     * 备用配置
     */
    @Schema(description = "备用配置")
    @Column(name = "config")
    private String config;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    @Column(name = "enable")
    private Boolean enable;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SysCollectTab id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTabCname() {
        return this.tabCname;
    }

    public SysCollectTab tabCname(String tabCname) {
        this.setTabCname(tabCname);
        return this;
    }

    public void setTabCname(String tabCname) {
        this.tabCname = tabCname;
    }

    public String getTabEname() {
        return this.tabEname;
    }

    public SysCollectTab tabEname(String tabEname) {
        this.setTabEname(tabEname);
        return this;
    }

    public void setTabEname(String tabEname) {
        this.tabEname = tabEname;
    }

    public String getConfig() {
        return this.config;
    }

    public SysCollectTab config(String config) {
        this.setConfig(config);
        return this;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public SysCollectTab enable(Boolean enable) {
        this.setEnable(enable);
        return this;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysCollectTab)) {
            return false;
        }
        return id != null && id.equals(((SysCollectTab) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysCollectTab{" +
            "id=" + getId() +
            ", tabCname='" + getTabCname() + "'" +
            ", tabEname='" + getTabEname() + "'" +
            ", config='" + getConfig() + "'" +
            ", enable='" + getEnable() + "'" +
            "}";
    }
}
