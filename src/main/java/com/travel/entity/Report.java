package com.travel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {
    private Long rid;

    private Long reporter;

    private Long reported;

    private String type;

    private String category;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date created;

    private Integer status;

    private String handler;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date handled;

    private String description;

    private static final long serialVersionUID = 1L;

    public Report(Long rid, Long reporter, Long reported, String type, String category, String description) {
        this.rid = rid;
        this.reporter = reporter;
        this.reported = reported;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public Report(Long rid, Long reporter, Long reported, String type, String category, Date created, Integer status, String handler, Date handled, String description) {
        this.rid = rid;
        this.reporter = reporter;
        this.reported = reported;
        this.type = type;
        this.category = category;
        this.created = created;
        this.status = status;
        this.handler = handler;
        this.handled = handled;
        this.description = description;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getReporter() {
        return reporter;
    }

    public void setReporter(Long reporter) {
        this.reporter = reporter;
    }

    public Long getReported() {
        return reported;
    }

    public void setReported(Long reported) {
        this.reported = reported;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Date getHandled() {
        return handled;
    }

    public void setHandled(Date handled) {
        this.handled = handled;
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
        sb.append(", rid=").append(rid);
        sb.append(", reporter=").append(reporter);
        sb.append(", reported=").append(reported);
        sb.append(", type=").append(type);
        sb.append(", category=").append(category);
        sb.append(", created=").append(created);
        sb.append(", status=").append(status);
        sb.append(", handler=").append(handler);
        sb.append(", handled=").append(handled);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}