package com.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 通知公告信息存储
 */
@Schema(description = "通知公告信息存储")
@Entity
@Table(name = "sys_notice")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 标题
     */
    @Schema(description = "标题")
    @Column(name = "title")
    private String title;

    /**
     * 公告内容
     */
    @Schema(description = "公告内容")
    @Column(name = "content")
    private String content;

    /**
     * 公告发起人
     */
    @Schema(description = "公告发起人")
    @Column(name = "creater")
    private String creater;

    /**
     * 创建日期
     */
    @Schema(description = "创建日期")
    @Column(name = "create_time")
    private String createTime;

    /**
     * 可以是 所有人/单个或多个用户/角色/单位\n选择不同的类型，　使用不同的接收者值集
     */
    @Schema(description = "可以是 所有人/单个或多个用户/角色/单位\n选择不同的类型，　使用不同的接收者值集")
    @Column(name = "rec_type")
    private String recType;

    /**
     * 可以是 所有人/单个或多个用户/角色/单位
     */
    @Schema(description = "可以是 所有人/单个或多个用户/角色/单位")
    @Column(name = "receiver")
    private String receiver;

    /**
     * 紧急程度 (紧急的同时短信通知) (是/否)
     */
    @Schema(description = "紧急程度 (紧急的同时短信通知) (是/否)")
    @Column(name = "urgent")
    private Boolean urgent;

    /**
     * 通知类型 通知公告/规章制度/政策文件
     */
    @Schema(description = "通知类型 通知公告/规章制度/政策文件")
    @Column(name = "noti_type")
    private Integer notiType;

    /**
     * 通知拉取状态
     */
    @Schema(description = "通知拉取状态")
    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SysNotice id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public SysNotice title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public SysNotice content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreater() {
        return this.creater;
    }

    public SysNotice creater(String creater) {
        this.setCreater(creater);
        return this;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public SysNotice createTime(String createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRecType() {
        return this.recType;
    }

    public SysNotice recType(String recType) {
        this.setRecType(recType);
        return this;
    }

    public void setRecType(String recType) {
        this.recType = recType;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public SysNotice receiver(String receiver) {
        this.setReceiver(receiver);
        return this;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Boolean getUrgent() {
        return this.urgent;
    }

    public SysNotice urgent(Boolean urgent) {
        this.setUrgent(urgent);
        return this;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    public Integer getNotiType() {
        return this.notiType;
    }

    public SysNotice notiType(Integer notiType) {
        this.setNotiType(notiType);
        return this;
    }

    public void setNotiType(Integer notiType) {
        this.notiType = notiType;
    }

    public Integer getStatus() {
        return this.status;
    }

    public SysNotice status(Integer status) {
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
        if (!(o instanceof SysNotice)) {
            return false;
        }
        return id != null && id.equals(((SysNotice) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysNotice{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", creater='" + getCreater() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", recType='" + getRecType() + "'" +
            ", receiver='" + getReceiver() + "'" +
            ", urgent='" + getUrgent() + "'" +
            ", notiType=" + getNotiType() +
            ", status=" + getStatus() +
            "}";
    }
}
