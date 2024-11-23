package com.qdu.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String userId;

    private String userName;

    private String password;

    private String gender;

    private Date birthDate;

    private String idCard;

    private String email;

    private String phone;

    private String city;

    private String occupation;

    private String recoveryQuestion;

    private Integer level;

    private Date createdAt;

    private Date deletedAt;

    private Integer deletionPeriod;

    private Integer status;

    private Integer identify;

    private String answer;

    private String picture;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getRecoveryQuestion() {
        return recoveryQuestion;
    }

    public void setRecoveryQuestion(String recoveryQuestion) {
        this.recoveryQuestion = recoveryQuestion;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getIdentify() {
        return identify;
    }

    public void setIdentify(Integer identify) {
        this.identify = identify;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userId);
        sb.append(", username=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", gender=").append(gender);
        sb.append(", birthdate=").append(birthDate);
        sb.append(", idcard=").append(idCard);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", city=").append(city);
        sb.append(", occupation=").append(occupation);
        sb.append(", recoveryquestion=").append(recoveryQuestion);
        sb.append(", level=").append(level);
        sb.append(", createdat=").append(createdAt);
        sb.append(", deletedat=").append(deletedAt);
        sb.append(", deletionperiod=").append(deletionPeriod);
        sb.append(", status=").append(status);
        sb.append(", identify=").append(identify);
        sb.append(", answer=").append(answer);
        sb.append(", picture=").append(picture);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}