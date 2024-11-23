package com.qdu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TravelActivity implements Serializable {
    private String activityid;

    private String userid;

    private Integer participantlevel;

    private Integer requiredparticipants;

    private Integer currentparticipants;

    private Date startdate;

    private Date enddate;

    private BigDecimal cost;

    private String location;

    private Date deletedat;

    private Integer deletionperiod;

    private Integer status;

    private Date registrationdeadline;

    private Date createdat;

    private Double rate;

    private Integer agree;

    private String title;

    private String description;

    private String label;

    private static final long serialVersionUID = 1L;

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getParticipantlevel() {
        return participantlevel;
    }

    public void setParticipantlevel(Integer participantlevel) {
        this.participantlevel = participantlevel;
    }

    public Integer getRequiredparticipants() {
        return requiredparticipants;
    }

    public void setRequiredparticipants(Integer requiredparticipants) {
        this.requiredparticipants = requiredparticipants;
    }

    public Integer getCurrentparticipants() {
        return currentparticipants;
    }

    public void setCurrentparticipants(Integer currentparticipants) {
        this.currentparticipants = currentparticipants;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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

    public Date getRegistrationdeadline() {
        return registrationdeadline;
    }

    public void setRegistrationdeadline(Date registrationdeadline) {
        this.registrationdeadline = registrationdeadline;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activityid=").append(activityid);
        sb.append(", userid=").append(userid);
        sb.append(", participantlevel=").append(participantlevel);
        sb.append(", requiredparticipants=").append(requiredparticipants);
        sb.append(", currentparticipants=").append(currentparticipants);
        sb.append(", startdate=").append(startdate);
        sb.append(", enddate=").append(enddate);
        sb.append(", cost=").append(cost);
        sb.append(", location=").append(location);
        sb.append(", deletedat=").append(deletedat);
        sb.append(", deletionperiod=").append(deletionperiod);
        sb.append(", status=").append(status);
        sb.append(", registrationdeadline=").append(registrationdeadline);
        sb.append(", createdat=").append(createdat);
        sb.append(", rate=").append(rate);
        sb.append(", agree=").append(agree);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}