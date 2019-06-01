package com.sample.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaffDetail {

    private Integer staffId;

    private String staffName;

    private Integer staffGender;

    private String position;

    private String businessName;

    private String preferedField;

    private String preferedLanguage;

    private String mail;

    private String tel;

    private Date birthday;

    private String postcode;

    private String address;

    private String emergencyAddress;

    private String cloestStation;

    private String favorite;

    private String note;

    private String comment;

    private String createUser;

    private String updateUser;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    public String getStaffName() {
        return staffName;
    }

    public StaffDetail() { }

    public StaffDetail(Integer staffId, String staffName, Integer staffGender, String position, String businessName, String preferedField, String preferedLanguage, String mail, String tel, Date birthday, String postcode, String address, String emergencyAddress, String cloestStation, String favorite, String note, String comment, String createUser, String updateUser) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffGender = staffGender;
        this.position = position;
        this.businessName = businessName;
        this.preferedField = preferedField;
        this.preferedLanguage = preferedLanguage;
        this.mail = mail;
        this.tel = tel;
        this.birthday = birthday;
        this.postcode = postcode;
        this.address = address;
        this.emergencyAddress = emergencyAddress;
        this.cloestStation = cloestStation;
        this.favorite = favorite;
        this.note = note;
        this.comment = comment;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(Integer staffGender) {
        this.staffGender = staffGender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPreferedField() {
        return preferedField;
    }

    public void setPreferedField(String preferedField) {
        this.preferedField = preferedField;
    }

    public String getPreferedLanguage() {
        return preferedLanguage;
    }

    public void setPreferedLanguage(String preferedLanguage) {
        this.preferedLanguage = preferedLanguage;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyAddress() {
        return emergencyAddress;
    }

    public void setEmergencyAddress(String emergencyAddress) {
        this.emergencyAddress = emergencyAddress;
    }

    public String getCloestStation() {
        return cloestStation;
    }

    public void setCloestStation(String cloestStation) {
        this.cloestStation = cloestStation;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
