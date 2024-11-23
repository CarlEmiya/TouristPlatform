package com.qdu.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private String commentId;

    private String associatedId;

    private String userId;

    private Date createdAt;

    private Date deletedAt;

    private Integer deletionPeriod;

    private Integer status;

    private Double rate;

    private Integer agree;

    private String associatedType;

    private String content;

    private static final long serialVersionUID = 1L;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(String associatedId) {
        this.associatedId = associatedId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getDeletionPeriod() {
        return deletionPeriod;
    }

    public void setDeletionPeriod(Integer deletionPeriod) {
        this.deletionPeriod = deletionPeriod;
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

    public String getAssociatedType() {
        return associatedType;
    }

    public void setAssociatedType(String associatedType) {
        this.associatedType = associatedType;
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
        sb.append(", commentid=").append(commentId);
        sb.append(", associatedid=").append(associatedId);
        sb.append(", userid=").append(userId);
        sb.append(", createdat=").append(createdAt);
        sb.append(", deletedat=").append(deletedAt);
        sb.append(", deletionperiod=").append(deletionPeriod);
        sb.append(", status=").append(status);
        sb.append(", rate=").append(rate);
        sb.append(", agree=").append(agree);
        sb.append(", associatedtype=").append(associatedType);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}