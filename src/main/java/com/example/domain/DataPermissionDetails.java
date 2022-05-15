package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 数据权限明细\n@author zhaozhiwei
 */
@Schema(description = "数据权限明细\n@author zhaozhiwei")
@Entity
@Table(name = "sys_data_permission_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataPermissionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    @Column(name = "rule_id")
    private String ruleId;

    /**
     * 左括号
     */
    @Schema(description = "左括号")
    @Pattern(regexp = "^\\($")
    @Column(name = "left_bracket")
    private String leftBracket;

    @Column(name = "jhi_column")
    private String column;

    @Column(name = "op")
    private String op;

    @Column(name = "value")
    private String value;

    /**
     * 右括号
     */
    @Schema(description = "右括号")
    @Pattern(regexp = "^\\)$")
    @Column(name = "right_bracket")
    private String rightBracket;

    @Column(name = "ordernum")
    private Integer ordernum;

    @Column(name = "logical_rel")
    private String logicalRel;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DataPermissionDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleId() {
        return this.ruleId;
    }

    public DataPermissionDetails ruleId(String ruleId) {
        this.setRuleId(ruleId);
        return this;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getLeftBracket() {
        return this.leftBracket;
    }

    public DataPermissionDetails leftBracket(String leftBracket) {
        this.setLeftBracket(leftBracket);
        return this;
    }

    public void setLeftBracket(String leftBracket) {
        this.leftBracket = leftBracket;
    }

    public String getColumn() {
        return this.column;
    }

    public DataPermissionDetails column(String column) {
        this.setColumn(column);
        return this;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOp() {
        return this.op;
    }

    public DataPermissionDetails op(String op) {
        this.setOp(op);
        return this;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getValue() {
        return this.value;
    }

    public DataPermissionDetails value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRightBracket() {
        return this.rightBracket;
    }

    public DataPermissionDetails rightBracket(String rightBracket) {
        this.setRightBracket(rightBracket);
        return this;
    }

    public void setRightBracket(String rightBracket) {
        this.rightBracket = rightBracket;
    }

    public Integer getOrdernum() {
        return this.ordernum;
    }

    public DataPermissionDetails ordernum(Integer ordernum) {
        this.setOrdernum(ordernum);
        return this;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getLogicalRel() {
        return this.logicalRel;
    }

    public DataPermissionDetails logicalRel(String logicalRel) {
        this.setLogicalRel(logicalRel);
        return this;
    }

    public void setLogicalRel(String logicalRel) {
        this.logicalRel = logicalRel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataPermissionDetails)) {
            return false;
        }
        return id != null && id.equals(((DataPermissionDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataPermissionDetails{" +
            "id=" + getId() +
            ", ruleId='" + getRuleId() + "'" +
            ", leftBracket='" + getLeftBracket() + "'" +
            ", column='" + getColumn() + "'" +
            ", op='" + getOp() + "'" +
            ", value='" + getValue() + "'" +
            ", rightBracket='" + getRightBracket() + "'" +
            ", ordernum=" + getOrdernum() +
            ", logicalRel='" + getLogicalRel() + "'" +
            "}";
    }
}
