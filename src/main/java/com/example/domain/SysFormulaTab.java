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
@Table(name = "sys_formula_tab")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysFormulaTab implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 表英文编码
     */
    @Schema(description = "表英文编码")
    @Column(name = "tab_ename")
    private String tabEname;

    /**
     * 字段英文编码
     */
    @Schema(description = "字段英文编码")
    @Column(name = "col_ename")
    private String colEname;

    /**
     * 计算公式
     */
    @Schema(description = "计算公式")
    @Column(name = "cal_formula")
    private String calFormula;

    /**
     * 公式中文描述
     */
    @Schema(description = "公式中文描述")
    @Column(name = "cal_formula_des")
    private String calFormulaDes;

    /**
     * 进位类型, 四舍五入等, BigDecimal
     */
    @Schema(description = "进位类型, 四舍五入等, BigDecimal")
    @Column(name = "round_type")
    private String roundType;

    /**
     * 公式权重
     */
    @Schema(description = "公式权重")
    @Column(name = "weight")
    private Integer weight;

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

    public SysFormulaTab id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTabEname() {
        return this.tabEname;
    }

    public SysFormulaTab tabEname(String tabEname) {
        this.setTabEname(tabEname);
        return this;
    }

    public void setTabEname(String tabEname) {
        this.tabEname = tabEname;
    }

    public String getColEname() {
        return this.colEname;
    }

    public SysFormulaTab colEname(String colEname) {
        this.setColEname(colEname);
        return this;
    }

    public void setColEname(String colEname) {
        this.colEname = colEname;
    }

    public String getCalFormula() {
        return this.calFormula;
    }

    public SysFormulaTab calFormula(String calFormula) {
        this.setCalFormula(calFormula);
        return this;
    }

    public void setCalFormula(String calFormula) {
        this.calFormula = calFormula;
    }

    public String getCalFormulaDes() {
        return this.calFormulaDes;
    }

    public SysFormulaTab calFormulaDes(String calFormulaDes) {
        this.setCalFormulaDes(calFormulaDes);
        return this;
    }

    public void setCalFormulaDes(String calFormulaDes) {
        this.calFormulaDes = calFormulaDes;
    }

    public String getRoundType() {
        return this.roundType;
    }

    public SysFormulaTab roundType(String roundType) {
        this.setRoundType(roundType);
        return this;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public SysFormulaTab weight(Integer weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public SysFormulaTab enable(Boolean enable) {
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
        if (!(o instanceof SysFormulaTab)) {
            return false;
        }
        return id != null && id.equals(((SysFormulaTab) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysFormulaTab{" +
            "id=" + getId() +
            ", tabEname='" + getTabEname() + "'" +
            ", colEname='" + getColEname() + "'" +
            ", calFormula='" + getCalFormula() + "'" +
            ", calFormulaDes='" + getCalFormulaDes() + "'" +
            ", roundType='" + getRoundType() + "'" +
            ", weight=" + getWeight() +
            ", enable='" + getEnable() + "'" +
            "}";
    }
}
