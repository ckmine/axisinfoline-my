package com.axisInfoline.helpDesk.tickets.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    Integer serialNo;
    String complaintNo;
    LocalDateTime complaintDatetime;
    String circle;
    String division;
    String complainantName;
    String complainantDesignation;
    String complainantContactNo;
    String defectiveItemName;
    String uxb1jsi364g4453780;
    String engineerAssigned;
    LocalDateTime engineerAssignedDateTime;
    String engineerContactNo;
    LocalDateTime complaintCompletionDatetime;
    String status;
    String actionTakenAndSpareUsed;
    String oldSerialNoMbHddTft;
    String newSerialNoMbHddTft;
    String remarks;
    String projectName;
    String product;
    String machineMake;
    String problemType;
    String substation;
    String landmark;
    String pinCode;
    String docPath;
    Boolean approved;
    String approverName;
    String approverPhone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getComplaintNo() {
        return complaintNo;
    }

    public void setComplaintNo(String complaintNo) {
        this.complaintNo = complaintNo;
    }

    public LocalDateTime getComplaintDatetime() {
        return complaintDatetime;
    }

    public void setComplaintDatetime(LocalDateTime complaintDatetime) {
        this.complaintDatetime = complaintDatetime;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getComplainantName() {
        return complainantName;
    }

    public void setComplainantName(String complainantName) {
        this.complainantName = complainantName;
    }

    public String getComplainantDesignation() {
        return complainantDesignation;
    }

    public void setComplainantDesignation(String complainantDesignation) {
        this.complainantDesignation = complainantDesignation;
    }

    public String getComplainantContactNo() {
        return complainantContactNo;
    }

    public void setComplainantContactNo(String complainantContactNo) {
        this.complainantContactNo = complainantContactNo;
    }

    public String getDefectiveItemName() {
        return defectiveItemName;
    }

    public void setDefectiveItemName(String defectiveItemName) {
        this.defectiveItemName = defectiveItemName;
    }

    public String getUxb1jsi364g4453780() {
        return uxb1jsi364g4453780;
    }

    public void setUxb1jsi364g4453780(String uxb1jsi364g4453780) {
        this.uxb1jsi364g4453780 = uxb1jsi364g4453780;
    }

    public String getEngineerAssigned() {
        return engineerAssigned;
    }

    public void setEngineerAssigned(String engineerAssigned) {
        this.engineerAssigned = engineerAssigned;
    }

    public LocalDateTime getEngineerAssignedDateTime() {
        return engineerAssignedDateTime;
    }

    public void setEngineerAssignedDateTime(LocalDateTime engineerAssignedDateTime) {
        this.engineerAssignedDateTime = engineerAssignedDateTime;
    }

    public String getEngineerContactNo() {
        return engineerContactNo;
    }

    public void setEngineerContactNo(String engineerContactNo) {
        this.engineerContactNo = engineerContactNo;
    }

    public LocalDateTime getComplaintCompletionDatetime() {
        return complaintCompletionDatetime;
    }

    public void setComplaintCompletionDatetime(LocalDateTime complaintCompletionDatetime) {
        this.complaintCompletionDatetime = complaintCompletionDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActionTakenAndSpareUsed() {
        return actionTakenAndSpareUsed;
    }

    public void setActionTakenAndSpareUsed(String actionTakenAndSpareUsed) {
        this.actionTakenAndSpareUsed = actionTakenAndSpareUsed;
    }

    public String getOldSerialNoMbHddTft() {
        return oldSerialNoMbHddTft;
    }

    public void setOldSerialNoMbHddTft(String oldSerialNoMbHddTft) {
        this.oldSerialNoMbHddTft = oldSerialNoMbHddTft;
    }

    public String getNewSerialNoMbHddTft() {
        return newSerialNoMbHddTft;
    }

    public void setNewSerialNoMbHddTft(String newSerialNoMbHddTft) {
        this.newSerialNoMbHddTft = newSerialNoMbHddTft;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getMachineMake() {
        return machineMake;
    }

    public void setMachineMake(String machineMake) {
        this.machineMake = machineMake;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getSubstation() {
        return substation;
    }

    public void setSubstation(String substation) {
        this.substation = substation;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getApproverPhone() {
        return approverPhone;
    }

    public void setApproverPhone(String approverPhone) {
        this.approverPhone = approverPhone;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", serialNo=" + serialNo +
                ", complaintNo='" + complaintNo + '\'' +
                ", complaintDatetime=" + complaintDatetime +
                ", circle='" + circle + '\'' +
                ", division='" + division + '\'' +
                ", complainantName='" + complainantName + '\'' +
                ", complainantDesignation='" + complainantDesignation + '\'' +
                ", complainantContactNo='" + complainantContactNo + '\'' +
                ", defectiveItemName='" + defectiveItemName + '\'' +
                ", uxb1jsi364g4453780='" + uxb1jsi364g4453780 + '\'' +
                ", engineerAssigned='" + engineerAssigned + '\'' +
                ", engineerContactNo='" + engineerContactNo + '\'' +
                ", complaintCompletionDatetime=" + complaintCompletionDatetime +
                ", status='" + status + '\'' +
                ", actionTakenAndSpareUsed='" + actionTakenAndSpareUsed + '\'' +
                ", oldSerialNoMbHddTft='" + oldSerialNoMbHddTft + '\'' +
                ", newSerialNoMbHddTft='" + newSerialNoMbHddTft + '\'' +
                ", remarks='" + remarks + '\'' +
                ", projectName='" + projectName + '\'' +
                ", product='" + product + '\'' +
                ", machineMake='" + machineMake + '\'' +
                ", problemType='" + problemType + '\'' +
                ", substation='" + substation + '\'' +
                ", landmark='" + landmark + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", docPath='" + docPath + '\'' +
                ", approved='" + docPath + '\'' +
                ", approverName='" + docPath + '\'' +
                ", approverPhone='" + docPath + '\'' +
                '}';
    }
}
