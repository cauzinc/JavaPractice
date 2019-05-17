package com.sample.vo;

public class PrincipleListItem {
    private String principleName;
    private String businessName;
    private String tel;

    public PrincipleListItem(String principleName, String businessName, String tel) {
        this.principleName = principleName;
        this.businessName = businessName;
        this.tel = tel;
    }

    public PrincipleListItem() { }

    public String getPrincipleName() {
        return principleName;
    }

    public void setPrincipleName(String principleName) {
        this.principleName = principleName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
