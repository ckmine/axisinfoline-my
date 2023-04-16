package com.axisInfoline.helpDesk.survey.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name ="survey")

public class Survey {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String circle;
    private String division;
    private String subdivision;

    private String endLocationAddress;
    private String itHardwareName;
    private String machineMake;
    private String model;
    private String serialNo;
    private String upsBatteryStatus;
    private String windowsType;
    private String domainJoiningStatus;
    private String utilityContactPersonName;
    private String utilityContactPersonContact;
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getEndLocationAddress() {
        return endLocationAddress;
    }

    public void setEndLocationAddress(String endLocationAddress) {
        this.endLocationAddress = endLocationAddress;
    }

    public String getItHardwareName() {
        return itHardwareName;
    }

    public void setItHardwareName(String itHardwareName) {
        this.itHardwareName = itHardwareName;
    }

    public String getMachineMake() {
        return machineMake;
    }

    public void setMachineMake(String machineMake) {
        this.machineMake = machineMake;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getUpsBatteryStatus() {
        return upsBatteryStatus;
    }

    public void setUpsBatteryStatus(String upsBatteryStatus) {
        this.upsBatteryStatus = upsBatteryStatus;
    }

    public String getWindowsType() {
        return windowsType;
    }

    public void setWindowsType(String windowsType) {
        this.windowsType = windowsType;
    }

    public String getDomainJoiningStatus() {
        return domainJoiningStatus;
    }

    public void setDomainJoiningStatus(String domainJoiningStatus) {
        this.domainJoiningStatus = domainJoiningStatus;
    }

    public String getUtilityContactPersonName() {
        return utilityContactPersonName;
    }

    public void setUtilityContactPersonName(String utilityContactPersonName) {
        this.utilityContactPersonName = utilityContactPersonName;
    }

    public String getUtilityContactPersonContact() {
        return utilityContactPersonContact;
    }

    public void setUtilityContactPersonContact(String utilityContactPersonContact) {
        this.utilityContactPersonContact = utilityContactPersonContact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
