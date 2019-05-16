package com.sample.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Business {
    private Integer id;

    private String businessName;

    private String businessTel;

    private String businessFax;

    private String website;

    private String address;

    private String postcode;

    private String advantageField;

    private String bankName;

    private String bankBranchName;

    private String bankNumber;

    private String bankRepresent;

    private Integer stuffNum;

    private BigDecimal annualSales;

    private Date transactionDeadline;

    private String paymentSight;

    private String accountKind;

    private String comment;

    private Integer principalId;

    private Integer status;

    private String createUser;

    private String updateUser;

    private Date createTime;

    private Date updateTime;

    public Business(Integer id, String businessName, String businessTel, String businessFax, String website, String address, String postcode, String advantageField, String bankName, String bankBranchName, String bankNumber, String bankRepresent, Integer stuffNum, BigDecimal annualSales, Date transactionDeadline, String paymentSight, String accountKind, String comment, Integer principalId, Integer status, String createUser, String updateUser, Date createTime, Date updateTime) {
        this.id = id;
        this.businessName = businessName;
        this.businessTel = businessTel;
        this.businessFax = businessFax;
        this.website = website;
        this.address = address;
        this.postcode = postcode;
        this.advantageField = advantageField;
        this.bankName = bankName;
        this.bankBranchName = bankBranchName;
        this.bankNumber = bankNumber;
        this.bankRepresent = bankRepresent;
        this.stuffNum = stuffNum;
        this.annualSales = annualSales;
        this.transactionDeadline = transactionDeadline;
        this.paymentSight = paymentSight;
        this.accountKind = accountKind;
        this.comment = comment;
        this.principalId = principalId;
        this.status = status;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Business() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    public String getBusinessTel() {
        return businessTel;
    }

    public void setBusinessTel(String businessTel) {
        this.businessTel = businessTel == null ? null : businessTel.trim();
    }

    public String getBusinessFax() {
        return businessFax;
    }

    public void setBusinessFax(String businessFax) {
        this.businessFax = businessFax == null ? null : businessFax.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getAdvantageField() {
        return advantageField;
    }

    public void setAdvantageField(String advantageField) {
        this.advantageField = advantageField == null ? null : advantageField.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName == null ? null : bankBranchName.trim();
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber == null ? null : bankNumber.trim();
    }

    public String getBankRepresent() {
        return bankRepresent;
    }

    public void setBankRepresent(String bankRepresent) {
        this.bankRepresent = bankRepresent == null ? null : bankRepresent.trim();
    }

    public Integer getStuffNum() {
        return stuffNum;
    }

    public void setStuffNum(Integer stuffNum) {
        this.stuffNum = stuffNum;
    }

    public BigDecimal getAnnualSales() {
        return annualSales;
    }

    public void setAnnualSales(BigDecimal annualSales) {
        this.annualSales = annualSales;
    }

    public Date getTransactionDeadline() {
        return transactionDeadline;
    }

    public void setTransactionDeadline(Date transactionDeadline) {
        this.transactionDeadline = transactionDeadline;
    }

    public String getPaymentSight() {
        return paymentSight;
    }

    public void setPaymentSight(String paymentSight) {
        this.paymentSight = paymentSight == null ? null : paymentSight.trim();
    }

    public String getAccountKind() {
        return accountKind;
    }

    public void setAccountKind(String accountKind) {
        this.accountKind = accountKind == null ? null : accountKind.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Integer principalId) {
        this.principalId = principalId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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