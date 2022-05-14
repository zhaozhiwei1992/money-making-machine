package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 数据权限主表\n@author zhaozhiwei
 */
@Schema(description = "数据权限主表\n@author zhaozhiwei")
@Entity
@Table(name = "sys_data_permissions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 权限编码
     */
    @Schema(description = "权限编码")
    @Column(name = "code")
    private String code;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    @Column(name = "name")
    private String name;

    /**
     * 规则sql, 配置明细时候才生成
     */
    @Schema(description = "规则sql, 配置明细时候才生成")
    @Column(name = "rule_sql")
    private String ruleSql;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DataPermission id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public DataPermission code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public DataPermission name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuleSql() {
        return this.ruleSql;
    }

    public DataPermission ruleSql(String ruleSql) {
        this.setRuleSql(ruleSql);
        return this;
    }

    public void setRuleSql(String ruleSql) {
        this.ruleSql = ruleSql;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataPermission)) {
            return false;
        }
        return id != null && id.equals(((DataPermission) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataPermission{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", ruleSql='" + getRuleSql() + "'" +
            "}";
    }
}
