package com.travel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Like implements Serializable {
    private Long lid;

    private Long uid;

    private Long connectid;

    private String type;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date created;

    private Boolean status;

    private static final long serialVersionUID = 1L;

    public Like(Long uid, Long connectid, String type, Date created, Boolean status) {
        this.uid = uid;
        this.connectid = connectid;
        this.type = type;
        this.created = created;
        this.status = status;
    }

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getConnectid() {
        return connectid;
    }

    public void setConnectid(Long connectid) {
        this.connectid = connectid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lid=").append(lid);
        sb.append(", uid=").append(uid);
        sb.append(", connectid=").append(connectid);
        sb.append(", type=").append(type);
        sb.append(", created=").append(created);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}