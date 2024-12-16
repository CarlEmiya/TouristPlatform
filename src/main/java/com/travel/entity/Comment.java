package com.travel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Long cid;

    private Long connectid;

    private Long uid;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date created;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deleted;

    private Integer period;

    private Integer status;

    private Double rate;

    private Integer agree;

    private String content;

    private String type;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment(Long cid, Long connectid, Long uid, Date created, Date deleted, Integer period, Integer status, Double rate, Integer agree, String content, String type, User user) {
        this.cid = cid;
        this.connectid = connectid;
        this.uid = uid;
        this.created = created;
        this.deleted = deleted;
        this.period = period;
        this.status = status;
        this.rate = rate;
        this.agree = agree;
        this.content = content;
        this.type = type;
        this.user = user;
    }

    public Comment() {
    }

    public Comment(Long cid, Long connectid, Long uid, Date created, Date deleted, Integer period, Integer status, Double rate, Integer agree, String content, String type) {
        this.cid = cid;
        this.connectid = connectid;
        this.uid = uid;
        this.created = created;
        this.deleted = deleted;
        this.period = period;
        this.status = status;
        this.rate = rate;
        this.agree = agree;
        this.content = content;
        this.type = type;
    }

    private static final long serialVersionUID = 1L;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getConnectid() {
        return connectid;
    }

    public void setConnectid(Long connectid) {
        this.connectid = connectid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", connectid=").append(connectid);
        sb.append(", uid=").append(uid);
        sb.append(", created=").append(created);
        sb.append(", deleted=").append(deleted);
        sb.append(", period=").append(period);
        sb.append(", status=").append(status);
        sb.append(", rate=").append(rate);
        sb.append(", agree=").append(agree);
        sb.append(", content=").append(content);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}