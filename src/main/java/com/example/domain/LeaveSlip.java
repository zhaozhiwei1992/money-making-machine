package com.example.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LeaveSlip.
 */
@Entity
@Table(name = "t_leaveslip")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LeaveSlip implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "reason")
    private String reason;

    @Column(name = "file")
    private String file;

    @Column(name = "superior")
    private Long superior;

    @Column(name = "wfstatus")
    private String wfstatus;

    @Column(name = "leave_person")
    private String leavePerson;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LeaveSlip id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public LeaveSlip type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public LeaveSlip startTime(String startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public LeaveSlip endTime(String endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return this.reason;
    }

    public LeaveSlip reason(String reason) {
        this.setReason(reason);
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFile() {
        return this.file;
    }

    public LeaveSlip file(String file) {
        this.setFile(file);
        return this;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Long getSuperior() {
        return this.superior;
    }

    public LeaveSlip superior(Long superior) {
        this.setSuperior(superior);
        return this;
    }

    public void setSuperior(Long superior) {
        this.superior = superior;
    }

    public String getWfstatus() {
        return this.wfstatus;
    }

    public LeaveSlip wfstatus(String wfstatus) {
        this.setWfstatus(wfstatus);
        return this;
    }

    public void setWfstatus(String wfstatus) {
        this.wfstatus = wfstatus;
    }

    public String getLeavePerson() {
        return this.leavePerson;
    }

    public LeaveSlip leavePerson(String leavePerson) {
        this.setLeavePerson(leavePerson);
        return this;
    }

    public void setLeavePerson(String leavePerson) {
        this.leavePerson = leavePerson;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeaveSlip)) {
            return false;
        }
        return id != null && id.equals(((LeaveSlip) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeaveSlip{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", reason='" + getReason() + "'" +
            ", file='" + getFile() + "'" +
            ", superior=" + getSuperior() +
            ", wfstatus='" + getWfstatus() + "'" +
            ", leavePerson='" + getLeavePerson() + "'" +
            "}";
    }
}
