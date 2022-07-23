package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 通知公告明细
 */
@Schema(description = "通知公告明细")
@Entity
@Table(name = "sys_notice_sub")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysNoticeSub implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 通知id
     */
    @Schema(description = "通知id")
    @Column(name = "sys_notice_id")
    private Long sysNoticeId;

    /**
     * 接收通知用户的id
     */
    @Schema(description = "接收通知用户的id")
    @Column(name = "recipient_id")
    private String recipientId;

    /**
     * 拉取数据时间
     */
    @Schema(description = "拉取数据时间")
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 通知读取状态, 是否已读
     */
    @Schema(description = "通知读取状态, 是否已读")
    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SysNoticeSub id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysNoticeId() {
        return this.sysNoticeId;
    }

    public SysNoticeSub sysNoticeId(Long sysNoticeId) {
        this.setSysNoticeId(sysNoticeId);
        return this;
    }

    public void setSysNoticeId(Long sysNoticeId) {
        this.sysNoticeId = sysNoticeId;
    }

    public String getRecipientId() {
        return this.recipientId;
    }

    public SysNoticeSub recipientId(String recipientId) {
        this.setRecipientId(recipientId);
        return this;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public SysNoticeSub updateTime(String updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public SysNoticeSub status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysNoticeSub)) {
            return false;
        }
        return id != null && id.equals(((SysNoticeSub) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysNoticeSub{" +
            "id=" + getId() +
            ", sysNoticeId=" + getSysNoticeId() +
            ", recipientId='" + getRecipientId() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
