package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 用户请求日志信息\n@author zhaozhiwei
 */
@Schema(description = "用户请求日志信息\n@author zhaozhiwei")
@Entity
@Table(name = "sys_request_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RequestLogging implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 请求唯一id, 方便问题定位
     */
    @Schema(description = "请求唯一id, 方便问题定位")
    @Column(name = "trace_id")
    private String traceId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @Column(name = "login_name")
    private String loginName;

    /**
     * 请求地址
     */
    @Schema(description = "请求地址")
    @Column(name = "request_uri")
    private String requestURI;

    /**
     * 客户端ip
     */
    @Schema(description = "客户端ip")
    @Column(name = "client_ip")
    private String clientIP;

    /**
     * 当前时间
     */
    @Schema(description = "当前时间")
    @Column(name = "jhi_current_time")
    private String currentTime;

    /**
     * 请求中文名
     */
    @Schema(description = "请求中文名")
    @Column(name = "request_name")
    private String requestName;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    @Column(name = "params")
    private String params;

    /**
     * 是否请求成功
     */
    @Schema(description = "是否请求成功")
    @Column(name = "success")
    private String success;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RequestLogging id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public RequestLogging traceId(String traceId) {
        this.setTraceId(traceId);
        return this;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public RequestLogging loginName(String loginName) {
        this.setLoginName(loginName);
        return this;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRequestURI() {
        return this.requestURI;
    }

    public RequestLogging requestURI(String requestURI) {
        this.setRequestURI(requestURI);
        return this;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getClientIP() {
        return this.clientIP;
    }

    public RequestLogging clientIP(String clientIP) {
        this.setClientIP(clientIP);
        return this;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getCurrentTime() {
        return this.currentTime;
    }

    public RequestLogging currentTime(String currentTime) {
        this.setCurrentTime(currentTime);
        return this;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getRequestName() {
        return this.requestName;
    }

    public RequestLogging requestName(String requestName) {
        this.setRequestName(requestName);
        return this;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getParams() {
        return this.params;
    }

    public RequestLogging params(String params) {
        this.setParams(params);
        return this;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getSuccess() {
        return this.success;
    }

    public RequestLogging success(String success) {
        this.setSuccess(success);
        return this;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestLogging)) {
            return false;
        }
        return id != null && id.equals(((RequestLogging) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestLogging{" +
            "id=" + getId() +
            ", traceId='" + getTraceId() + "'" +
            ", loginName='" + getLoginName() + "'" +
            ", requestURI='" + getRequestURI() + "'" +
            ", clientIP='" + getClientIP() + "'" +
            ", currentTime='" + getCurrentTime() + "'" +
            ", requestName='" + getRequestName() + "'" +
            ", params='" + getParams() + "'" +
            ", success='" + getSuccess() + "'" +
            "}";
    }
}
