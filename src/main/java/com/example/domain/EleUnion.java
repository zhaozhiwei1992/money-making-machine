package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 基础数据总表\n
 * 数据量小的可以统一使用总表创建即可\n
 * 单独的表可以使用ele_001001类似的表名
 */
@Schema(description = "基础数据总表")
@Entity
@Table(name = "ele_union")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EleUnion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 代码集分类编码\n如: 001001\n如果在该表不存在数据, 则通过ele_eleCatCode方式来查其它表
     */
    @Schema(description = "代码集分类编码\n如: 001001\n如果在该表不存在数据, 则通过ele_eleCatCode方式来查其它表")
    @Column(name = "ele_cat_code")
    private String eleCatCode;

    /**
     * 代码集分类名称\n如: 单位
     */
    @Schema(description = "代码集分类名称\n如: 单位")
    @Column(name = "ele_cat_name")
    private String eleCatName;

    /**
     * 代码集代码
     */
    @Schema(description = "代码集代码")
    @Column(name = "ele_code")
    private String eleCode;

    /**
     * 代码集名称
     */
    @Schema(description = "代码集名称")
    @Column(name = "ele_name")
    private String eleName;

    /**
     * 父级节点主键
     */
    @Schema(description = "父级节点主键")
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 级次
     */
    @Schema(description = "级次")
    @Column(name = "level_no")
    private Integer levelNo;

    /**
     * 是否末级
     */
    @Schema(description = "是否末级")
    @Column(name = "is_leaf")
    private Boolean isLeaf;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @Column(name = "update_time")
    private String updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EleUnion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEleCatCode() {
        return this.eleCatCode;
    }

    public EleUnion eleCatCode(String eleCatCode) {
        this.setEleCatCode(eleCatCode);
        return this;
    }

    public void setEleCatCode(String eleCatCode) {
        this.eleCatCode = eleCatCode;
    }

    public String getEleCatName() {
        return this.eleCatName;
    }

    public EleUnion eleCatName(String eleCatName) {
        this.setEleCatName(eleCatName);
        return this;
    }

    public void setEleCatName(String eleCatName) {
        this.eleCatName = eleCatName;
    }

    public String getEleCode() {
        return this.eleCode;
    }

    public EleUnion eleCode(String eleCode) {
        this.setEleCode(eleCode);
        return this;
    }

    public void setEleCode(String eleCode) {
        this.eleCode = eleCode;
    }

    public String getEleName() {
        return this.eleName;
    }

    public EleUnion eleName(String eleName) {
        this.setEleName(eleName);
        return this;
    }

    public void setEleName(String eleName) {
        this.eleName = eleName;
    }

    public String getParentId() {
        return this.parentId;
    }

    public EleUnion parentId(String parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getLevelNo() {
        return this.levelNo;
    }

    public EleUnion levelNo(Integer levelNo) {
        this.setLevelNo(levelNo);
        return this;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public Boolean getIsLeaf() {
        return this.isLeaf;
    }

    public EleUnion isLeaf(Boolean isLeaf) {
        this.setIsLeaf(isLeaf);
        return this;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public EleUnion isEnabled(Boolean isEnabled) {
        this.setIsEnabled(isEnabled);
        return this;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public EleUnion createTime(String createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public EleUnion updateTime(String updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EleUnion)) {
            return false;
        }
        return id != null && id.equals(((EleUnion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EleUnion{" +
            "id=" + getId() +
            ", eleCatCode='" + getEleCatCode() + "'" +
            ", eleCatName='" + getEleCatName() + "'" +
            ", eleCode='" + getEleCode() + "'" +
            ", eleName='" + getEleName() + "'" +
            ", parentId='" + getParentId() + "'" +
            ", levelNo=" + getLevelNo() +
            ", isLeaf='" + getIsLeaf() + "'" +
            ", isEnabled='" + getIsEnabled() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
