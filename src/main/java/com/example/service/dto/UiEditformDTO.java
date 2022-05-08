package com.example.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A UiEditform.
 */
public class UiEditformDTO implements Serializable {

    private Long id;

    private Long menuid;

    private String code;

    private String name;

    private Integer ordernum;

    private Boolean issource;

    private Boolean isedit;

    private Boolean requirement;

    private String type;

    private String placeholder;

    /**
     * @data: 2022/5/8-下午12:14
     * @User: zhaozhiwei
     * @method:
     * @param null :
     * @return:
     * @Description: 一些特殊属性传入前端
     */
    private Map<String, Object> config;

    /**
     * @data: 2022/5/8-下午12:13
     * @User: zhaozhiwei
     * @method:
     * @param null :
     * @return:
     * @Description: 增加前端数据下拉值集
     */
    private List<Map<String, Object>> mapping;

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    public List<Map<String, Object>> getMapping() {
        return mapping;
    }

    public void setMapping(List<Map<String, Object>> mapping) {
        this.mapping = mapping;
    }

    public Long getId() {
        return this.id;
    }

    public UiEditformDTO id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuid() {
        return this.menuid;
    }

    public UiEditformDTO menuid(Long menuid) {
        this.setMenuid(menuid);
        return this;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public String getCode() {
        return this.code;
    }

    public UiEditformDTO code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public UiEditformDTO name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrdernum() {
        return this.ordernum;
    }

    public UiEditformDTO ordernum(Integer ordernum) {
        this.setOrdernum(ordernum);
        return this;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Boolean getIssource() {
        return this.issource;
    }

    public UiEditformDTO issource(Boolean issource) {
        this.setIssource(issource);
        return this;
    }

    public void setIssource(Boolean issource) {
        this.issource = issource;
    }

    public Boolean getIsedit() {
        return this.isedit;
    }

    public UiEditformDTO isedit(Boolean isedit) {
        this.setIsedit(isedit);
        return this;
    }

    public void setIsedit(Boolean isedit) {
        this.isedit = isedit;
    }

    public Boolean getRequirement() {
        return this.requirement;
    }

    public UiEditformDTO requirement(Boolean requirement) {
        this.setRequirement(requirement);
        return this;
    }

    public void setRequirement(Boolean requirement) {
        this.requirement = requirement;
    }

    public String getType() {
        return this.type;
    }

    public UiEditformDTO type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public UiEditformDTO placeholder(String placeholder) {
        this.setPlaceholder(placeholder);
        return this;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UiEditformDTO)) {
            return false;
        }
        return id != null && id.equals(((UiEditformDTO) o).id);
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
            "}";
    }
}
