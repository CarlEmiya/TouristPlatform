package com.qdu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TravelActivity implements Serializable {
    private String activityId;

    private String CancelReason;

    private String userId;

    private Integer participantLevel;

    private Integer requiredParticipants;

    private Integer currentParticipants;

    private Date startDate;

    private Date endDate;

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

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCancelReason() {
        return CancelReason;
    }

    public void setCancelReason(String cancelReason) {
        CancelReason = cancelReason;
    }

    public TravelActivity(String activityid, String cancelReason, String userid, Integer participantlevel, Integer requiredparticipants, Integer currentparticipants, Date startdate, Date enddate, BigDecimal cost, String location, Date deletedat, Integer deletionperiod, Integer status, Date registrationdeadline, Date createdat, Double rate, Integer agree, String title, String description, String label) {
        this.activityId = activityid;
        CancelReason = cancelReason;
        this.userId = userid;
        this.participantLevel = participantlevel;
        this.requiredParticipants = requiredparticipants;
        this.currentParticipants = currentparticipants;
        this.startDate = startdate;
        this.endDate = enddate;
        this.cost = cost;
        this.location = location;
        this.deletedat = deletedat;
        this.deletionperiod = deletionperiod;
        this.status = status;
        this.registrationdeadline = registrationdeadline;
        this.createdat = createdat;
        this.rate = rate;
        this.agree = agree;
        this.title = title;
        this.description = description;
        this.label = label;
    }

    public Integer getParticipantLevel() {
        return participantLevel;
    }

    public void setParticipantLevel(Integer participantLevel) {
        this.participantLevel = participantLevel;
    }

    public Integer getRequiredParticipants() {
        return requiredParticipants;
    }

    public void setRequiredParticipants(Integer requiredParticipants) {
        this.requiredParticipants = requiredParticipants;
    }

    public Integer getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(Integer currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        sb.append(", activityid=").append(activityId);
        sb.append(", userid=").append(userId);
        sb.append(", participantlevel=").append(participantLevel);
        sb.append(", requiredparticipants=").append(requiredParticipants);
        sb.append(", currentparticipants=").append(currentParticipants);
        sb.append(", startdate=").append(startDate);
        sb.append(", enddate=").append(endDate);
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