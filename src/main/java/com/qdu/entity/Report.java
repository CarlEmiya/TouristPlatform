package com.qdu.entity;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {
    private String reportid;

    private String reporterid;

    private String reportedid;

    private String reportedtype;

    private String category;

    private Date createdat;

    private Integer status;

    private String handlerid;

    private Date handledat;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public String getReporterid() {
        return reporterid;
    }

    public void setReporterid(String reporterid) {
        this.reporterid = reporterid;
    }

    public String getReportedid() {
        return reportedid;
    }

    public void setReportedid(String reportedid) {
        this.reportedid = reportedid;
    }

    public String getReportedtype() {
        return reportedtype;
    }

    public void setReportedtype(String reportedtype) {
        this.reportedtype = reportedtype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHandlerid() {
        return handlerid;
    }

    public void setHandlerid(String handlerid) {
        this.handlerid = handlerid;
    }

    public Date getHandledat() {
        return handledat;
    }

    public void setHandledat(Date handledat) {
        this.handledat = handledat;
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
        sb.append(", reportid=").append(reportid);
        sb.append(", reporterid=").append(reporterid);
        sb.append(", reportedid=").append(reportedid);
        sb.append(", reportedtype=").append(reportedtype);
        sb.append(", category=").append(category);
        sb.append(", createdat=").append(createdat);
        sb.append(", status=").append(status);
        sb.append(", handlerid=").append(handlerid);
        sb.append(", handledat=").append(handledat);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}