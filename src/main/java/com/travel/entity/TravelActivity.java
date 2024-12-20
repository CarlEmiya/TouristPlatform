package com.travel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TravelActivity implements Serializable {
    private Long aid;

    private Long uid;

    private Integer min;

    private Integer required;

    private Integer current;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date start;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date end;

    private BigDecimal cost;

    private String location;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deleted;

    private Integer period;

    private Integer status;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date deadline;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created;

    private Double rate;

    private Integer agree;

    private String title;

    private String description;

    private String label;

    private String reason;

    private String firstFilePath;  // 存储第一个文件的ID



    private static final long serialVersionUID = 1L;
    public String getFirstFilePath() {
        return firstFilePath;
    }

    public void setFirstFilePath(String firstFilePath1) {
        this.firstFilePath = firstFilePath1;
    }
    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aid=").append(aid);
        sb.append(", uid=").append(uid);
        sb.append(", min=").append(min);
        sb.append(", required=").append(required);
        sb.append(", current=").append(current);
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", cost=").append(cost);
        sb.append(", location=").append(location);
        sb.append(", deleted=").append(deleted);
        sb.append(", period=").append(period);
        sb.append(", status=").append(status);
        sb.append(", deadline=").append(deadline);
        sb.append(", created=").append(created);
        sb.append(", rate=").append(rate);
        sb.append(", agree=").append(agree);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", label=").append(label);
        sb.append(", reason=").append(reason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}