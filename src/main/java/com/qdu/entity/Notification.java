package com.qdu.entity;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {
    private String notificationid;

    private String senderid;

    private String receiverid;

    private Integer status;

    private Date createdtime;

    private Date deletedat;

    private Integer deletionperiod;

    private String content;

    private static final long serialVersionUID = 1L;

    public String getNotificationid() {
        return notificationid;
    }

    public void setNotificationid(String notificationid) {
        this.notificationid = notificationid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Date getDeletedat() {
        return deletedat;
    }

    public void setDeletedat(Date deletedat) {
        this.deletedat = deletedat;
    }

    public Integer getDeletionperiod() {
        return deletionperiod;
    }

    public void setDeletionperiod(Integer deletionperiod) {
        this.deletionperiod = deletionperiod;
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
        sb.append(", notificationid=").append(notificationid);
        sb.append(", senderid=").append(senderid);
        sb.append(", receiverid=").append(receiverid);
        sb.append(", status=").append(status);
        sb.append(", createdtime=").append(createdtime);
        sb.append(", deletedat=").append(deletedat);
        sb.append(", deletionperiod=").append(deletionperiod);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}