package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 采集表列信息\n@author zhaozhiwei
 */
@Schema(description = "采集表列信息\n@author zhaozhiwei")
@Entity
@Table(name = "sys_collect_col")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysCollectCol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 列中文名
     */
    @Schema(description = "列中文名")
    @Column(name = "col_cname")
    private String colCname;

    /**
     * 列英文名
     */
    @Schema(description = "列英文名")
    @Column(name = "col_ename")
    private String colEname;

    /**
     * 关联采集表id
     */
    @Schema(description = "关联采集表id")
    @Column(name = "tab_id")
    private Long tabId;

    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 数据源头, 可以是bean或者数据项ele_
     */
    @Schema(description = "数据源头, 可以是bean或者数据项ele_")
    @Column(name = "source")
    private String source;

    /**
     * 是否可编辑
     */
    @Schema(description = "是否可编辑")
    @Column(name = "is_edit")
    private Boolean isEdit;

    /**
     * 是否必填
     */
    @Schema(description = "是否必填")
    @Column(name = "requirement")
    private Boolean requirement;

    /**
     * 数据类型, tree, select, input等
     */
    @Schema(description = "数据类型, tree, select, input等")
    @Column(name = "type")
    private String type;

    /**
     * 扩展配置
     */
    @Schema(description = "扩展配置")
    @Column(name = "config")
    private String config;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SysCollectCol id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColCname() {
        return this.colCname;
    }

    public SysCollectCol colCname(String colCname) {
        this.setColCname(colCname);
        return this;
    }

    public void setColCname(String colCname) {
        this.colCname = colCname;
    }

    public String getColEname() {
        return this.colEname;
    }

    public SysCollectCol colEname(String colEname) {
        this.setColEname(colEname);
        return this;
    }

    public void setColEname(String colEname) {
        this.colEname = colEname;
    }

    public Long getTabId() {
        return this.tabId;
    }

    public SysCollectCol tabId(Long tabId) {
        this.setTabId(tabId);
        return this;
    }

    public void setTabId(Long tabId) {
        this.tabId = tabId;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }

    public SysCollectCol orderNum(Integer orderNum) {
        this.setOrderNum(orderNum);
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getSource() {
        return this.source;
    }

    public SysCollectCol source(String source) {
        this.setSource(source);
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getIsEdit() {
        return this.isEdit;
    }

    public SysCollectCol isEdit(Boolean isEdit) {
        this.setIsEdit(isEdit);
        return this;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public Boolean getRequirement() {
        return this.requirement;
    }

    public SysCollectCol requirement(Boolean requirement) {
        this.setRequirement(requirement);
        return this;
    }

    public void setRequirement(Boolean requirement) {
        this.requirement = requirement;
    }

    public String getType() {
        return this.type;
    }

    public SysCollectCol type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConfig() {
        return this.config;
    }

    public SysCollectCol config(String config) {
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
        if (!(o instanceof SysCollectCol)) {
            return false;
        }
        return id != null && id.equals(((SysCollectCol) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysCollectCol{" +
            "id=" + getId() +
            ", colCname='" + getColCname() + "'" +
            ", colEname='" + getColEname() + "'" +
            ", tabId=" + getTabId() +
            ", orderNum=" + getOrderNum() +
            ", source='" + getSource() + "'" +
            ", isEdit='" + getIsEdit() + "'" +
            ", requirement='" + getRequirement() + "'" +
            ", type='" + getType() + "'" +
            ", config='" + getConfig() + "'" +
            "}";
    }
}
