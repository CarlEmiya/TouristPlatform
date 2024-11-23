package com.qdu.entity;

import java.io.Serializable;
import java.util.Date;

public class TravelDiary implements Serializable {
    private String diaryid;

    private String userid;

    private Boolean iscommentabled;

    private Date createdat;

    private Date deletedat;

    private Integer deletionperiod;

    private Integer status;

    private Integer agree;

    private String title;

    private String content;

    private String label;

    private static final long serialVersionUID = 1L;

    public String getDiaryid() {
        return diaryid;
    }

    public void setDiaryid(String diaryid) {
        this.diaryid = diaryid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Boolean getIscommentabled() {
        return iscommentabled;
    }

    public void setIscommentabled(Boolean iscommentabled) {
        this.iscommentabled = iscommentabled;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", diaryid=").append(diaryid);
        sb.append(", userid=").append(userid);
        sb.append(", iscommentabled=").append(iscommentabled);
        sb.append(", createdat=").append(createdat);
        sb.append(", deletedat=").append(deletedat);
        sb.append(", deletionperiod=").append(deletionperiod);
        sb.append(", status=").append(status);
        sb.append(", agree=").append(agree);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}