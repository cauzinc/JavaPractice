package com.sample.pojo;

import java.util.Date;

public class Staff {
    private Integer id;

    private String staffName;

    private Integer staffGender;

    private String position;

    private String businessId;

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

    private Date createTime;

    private Date updateTime;

    public Staff(Integer id, String staffName, Integer staffGender, String position, String businessId, String preferedField, String preferedLanguage, String mail, String tel, Date birthday, String postcode, String address, String emergencyAddress, String cloestStation, String favorite, String note, String comment, String createUser, String updateUser, Date createTime, Date updateTime) {
        this.id = id;
        this.staffName = staffName;
        this.staffGender = staffGender;
        this.position = position;
        this.businessId = businessId;
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
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Staff() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
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
        this.position = position == null ? null : position.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getPreferedField() {
        return preferedField;
    }

    public void setPreferedField(String preferedField) {
        this.preferedField = preferedField == null ? null : preferedField.trim();
    }

    public String getPreferedLanguage() {
        return preferedLanguage;
    }

    public void setPreferedLanguage(String preferedLanguage) {
        this.preferedLanguage = preferedLanguage == null ? null : preferedLanguage.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
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
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmergencyAddress() {
        return emergencyAddress;
    }

    public void setEmergencyAddress(String emergencyAddress) {
        this.emergencyAddress = emergencyAddress == null ? null : emergencyAddress.trim();
    }

    public String getCloestStation() {
        return cloestStation;
    }

    public void setCloestStation(String cloestStation) {
        this.cloestStation = cloestStation == null ? null : cloestStation.trim();
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite == null ? null : favorite.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}