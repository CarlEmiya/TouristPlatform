package com.travel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long uid;

    private String name;

    private String password;

    private String gender;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;

    private String idcard;

    private String email;

    private String phone;

    private String city;

    private String occupation;

    private String question;

    private Integer level;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date created;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deleted;

    private Integer period;

    private Integer status;

    private Integer identify;

    private String answer;

    private String picture;

    private String description;

    private static final long serialVersionUID = 1L;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
        sb.append(", uid=").append(uid);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", gender=").append(gender);
        sb.append(", birth=").append(birth);
        sb.append(", idcard=").append(idcard);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", city=").append(city);
        sb.append(", occupation=").append(occupation);
        sb.append(", question=").append(question);
        sb.append(", level=").append(level);
        sb.append(", created=").append(created);
        sb.append(", deleted=").append(deleted);
        sb.append(", period=").append(period);
        sb.append(", status=").append(status);
        sb.append(", identify=").append(identify);
        sb.append(", answer=").append(answer);
        sb.append(", picture=").append(picture);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public User(Long uid, Integer status) {
        this.uid = uid;
        this.status = status;
    }

    public User(Long uid, String password) {
        this.uid = uid;
        this.password = password;
    }

    public User(Long uid, String name, String password, String gender, Date birth, String idcard, String email, String phone, String city, String occupation, String question, Integer level, Date created, Date deleted, Integer period, Integer status, Integer identify, String answer, String picture, String description) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birth = birth;
        this.idcard = idcard;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.occupation = occupation;
        this.question = question;
        this.level = level;
        this.created = created;
        this.deleted = deleted;
        this.period = period;
        this.status = status;
        this.identify = identify;
        this.answer = answer;
        this.picture = picture;
        this.description = description;
    }

    public User() {
    }

    public User(String name, String password, String email, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}