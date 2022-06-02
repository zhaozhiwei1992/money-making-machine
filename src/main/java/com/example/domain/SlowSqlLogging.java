package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 慢sql记录\n@author zhaozhiwei
 */
@Schema(description = "慢sql记录\n@author zhaozhiwei")
@Entity
@Table(name = "sys_slow_sql_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SlowSqlLogging implements Serializable {

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
     * 当前时间
     */
    @Schema(description = "当前时间")
    @Column(name = "jhi_current_time")
    private String currentTime;

    /**
     * 完整sql
     */
    @Schema(description = "完整sql")
    @Column(name = "jhi_sql")
    private String sql;

    /**
     * 运行时间
     */
    @Schema(description = "运行时间")
    @Column(name = "execute_millis")
    private String executeMillis;

    /**
     * 运行时涉及的参数
     */
    @Schema(description = "运行时涉及的参数")
    @Column(name = "execute_params")
    private String executeParams;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SlowSqlLogging id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public SlowSqlLogging traceId(String traceId) {
        this.setTraceId(traceId);
        return this;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getCurrentTime() {
        return this.currentTime;
    }

    public SlowSqlLogging currentTime(String currentTime) {
        this.setCurrentTime(currentTime);
        return this;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getSql() {
        return this.sql;
    }

    public SlowSqlLogging sql(String sql) {
        this.setSql(sql);
        return this;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getExecuteMillis() {
        return this.executeMillis;
    }

    public SlowSqlLogging executeMillis(String executeMillis) {
        this.setExecuteMillis(executeMillis);
        return this;
    }

    public void setExecuteMillis(String executeMillis) {
        this.executeMillis = executeMillis;
    }

    public String getExecuteParams() {
        return this.executeParams;
    }

    public SlowSqlLogging executeParams(String executeParams) {
        this.setExecuteParams(executeParams);
        return this;
    }

    public void setExecuteParams(String executeParams) {
        this.executeParams = executeParams;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SlowSqlLogging)) {
            return false;
        }
        return id != null && id.equals(((SlowSqlLogging) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SlowSqlLogging{" +
            "id=" + getId() +
            ", traceId='" + getTraceId() + "'" +
            ", currentTime='" + getCurrentTime() + "'" +
            ", sql='" + getSql() + "'" +
            ", executeMillis='" + getExecuteMillis() + "'" +
            ", executeParams='" + getExecuteParams() + "'" +
            "}";
    }
}
