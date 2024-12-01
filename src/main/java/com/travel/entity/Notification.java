package com.travel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {
    private Long nid;

    private Long senderid;

    private Long readerid;

    private Integer status;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date deleted;

    private Integer period;

    private String content;

    private static final long serialVersionUID = 1L;

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public Long getSenderid() {
        return senderid;
    }

    public void setSenderid(Long senderid) {
        this.senderid = senderid;
    }

    public Long getReaderid() {
        return readerid;
    }

    public void setReaderid(Long readerid) {
        this.readerid = readerid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nid=").append(nid);
        sb.append(", senderid=").append(senderid);
        sb.append(", readerid=").append(readerid);
        sb.append(", status=").append(status);
        sb.append(", created=").append(created);
        sb.append(", deleted=").append(deleted);
        sb.append(", period=").append(period);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}