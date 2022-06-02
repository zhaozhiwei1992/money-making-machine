package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 系统参数信息\n\n一些需要经常性手动调整, 跟业务相关的配置放这里\n程序相关的可以方式spring配置文件\n\n@author zhaozhiwei
 */
@Schema(
    description = "系统参数信息\n\n一些需要经常性手动调整, 跟业务相关的配置放这里\n程序相关的可以方式spring配置文件\n\n@author zhaozhiwei"
)
@Entity
@Table(name = "sys_param")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 配置编码\n\n使用时通过编码获取参数
     */
    @Schema(description = "配置编码\n\n使用时通过编码获取参数")
    @Column(name = "code")
    private String code;

    /**
     * 配置名称
     */
    @Schema(description = "配置名称")
    @Column(name = "name")
    private String name;

    /**
     * 参数信息\n可以是普通value或者json等，使时自行解析
     */
    @Schema(description = "参数信息\n可以是普通value或者json等，使时自行解析")
    @Column(name = "value")
    private String value;

    /**
     * 备注\n根据需要对参数更细致的描述
     */
    @Schema(description = "备注\n根据需要对参数更细致的描述")
    @Column(name = "remark")
    private String remark;

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

    public SystemParam id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public SystemParam code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public SystemParam name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public SystemParam value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return this.remark;
    }

    public SystemParam remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public SystemParam enable(Boolean enable) {
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
        if (!(o instanceof SystemParam)) {
            return false;
        }
        return id != null && id.equals(((SystemParam) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SystemParam{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", remark='" + getRemark() + "'" +
            ", enable='" + getEnable() + "'" +
            "}";
    }
}
