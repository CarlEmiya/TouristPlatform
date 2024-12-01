package com.travel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ActivityRegistration implements Serializable {
    private Long arid;

    private Long aid;

    private Long uid;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getArid() {
        return arid;
    }

    public void setArid(Long arid) {
        this.arid = arid;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", arid=").append(arid);
        sb.append(", aid=").append(aid);
        sb.append(", uid=").append(uid);
        sb.append(", time=").append(time);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}