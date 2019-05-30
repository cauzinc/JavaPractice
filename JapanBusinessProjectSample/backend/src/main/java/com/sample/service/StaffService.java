package com.sample.service;

import com.sample.common.ServerResponse;
import com.sample.daoMapper.BusinessMapper;
import com.sample.daoMapper.StaffMapper;
import com.sample.pojo.Business;
import com.sample.pojo.Staff;
import com.sample.vo.StaffDetail;
import com.sample.vo.StaffListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("iStaffService")
public class StaffService {
    private StaffMapper staffMapper;
    private BusinessMapper businessMapper;
    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }
    @Autowired
    public void setBusinessMapper(BusinessMapper businessMapper) {
        this.businessMapper = businessMapper;
    }

    public ServerResponse getStaffList() {
        List<Staff> staffList = staffMapper.getStaffList();
        List<StaffListItem> staffListItems = new ArrayList<>();
        for(Staff staff : staffList) {
            StaffListItem staffListItem = this.assembleStaffItemList(staff);
            staffListItems.add(staffListItem);
        }
        return ServerResponse.createBySuccess(staffListItems);
    }

    public ServerResponse getStaffById(Integer staffId) {
        Staff staff = staffMapper.selectByPrimaryKey(staffId);
        if(staff == null) {
            return ServerResponse.createByErrorMessage("データはみつかりません");
        }
        StaffDetail staffDetail = this.assembleStaffDetail(staff);

        return ServerResponse.createBySuccess(staffDetail);
    }

    public ServerResponse deleteStaff(Integer staffId) {
        int count = staffMapper.deleteByPrimaryKey(staffId);
        if(count > 0) {
            return ServerResponse.createBySuccess("ok");
        }
        return ServerResponse.createByErrorMessage("Delete failed");
    }

    public ServerResponse commentStaff(int staffId, String comment) {
        Staff staff = new Staff();
        staff.setComment(comment);
        staff.setId(staffId);
        int count = staffMapper.updateByPrimaryKeySelective(staff);
        if(count > 0) {
            return ServerResponse.createBySuccess("ok");
        }
        return ServerResponse.createByErrorMessage("comment failed");
    }

    public StaffListItem assembleStaffItemList(Staff staff) {
        StaffListItem staffListItem = new StaffListItem();
        staffListItem.setStaffName(staff.getStaffName());
        staffListItem.setTel(staff.getTel());
        Business business = businessMapper.selectByPrimaryKey(staff.getBusinessId());
        staffListItem.setBusinessName(business.getBusinessName());
        return staffListItem;
    }

    public StaffDetail assembleStaffDetail(Staff staff) {
        StaffDetail staffDetail = new StaffDetail();
        Business business = businessMapper.selectByPrimaryKey(staff.getBusinessId());
        staffDetail.setStaffName(staff.getStaffName());
        staffDetail.setBusinessName(business.getBusinessName());
        staffDetail.setPosition(staff.getPosition());
        staffDetail.setAddress(staff.getAddress());
        staffDetail.setStaffGender(staff.getStaffGender());
        staffDetail.setBirthday(staff.getBirthday());
        staffDetail.setCloestStation(staff.getCloestStation());
        staffDetail.setTel(staff.getTel());
        staffDetail.setComment(staff.getComment());
        staffDetail.setEmergencyAddress(staff.getEmergencyAddress());
        staffDetail.setCreateUser(staff.getCreateUser());
        staffDetail.setUpdateUser(staff.getUpdateUser());
        staffDetail.setMail(staff.getMail());
        staffDetail.setFavorite(staff.getFavorite());
        staffDetail.setNote(staff.getNote());
        staffDetail.setPreferedField(staff.getPreferedField());
        staffDetail.setPreferedLanguage(staff.getPreferedLanguage());
        return staffDetail;
    }

    public ServerResponse<Staff> assembleStaff(StaffDetail staffDetail) {
        if(staffDetail == null) {
            return ServerResponse.createByErrorMessage("need data");
        }


        Staff staff = new Staff();

        Business business = businessMapper.selectByName(staffDetail.getBusinessName());
        if(business == null) {
            return ServerResponse.createByErrorMessage("所属会社はみつかりません");
        }
        staff.setBusinessId(business.getId());

        staff.setStaffName(staffDetail.getStaffName());
        staff.setPosition(staffDetail.getPosition());
        staff.setAddress(staffDetail.getAddress());
        staff.setStaffGender(staffDetail.getStaffGender());
        staff.setBirthday(staffDetail.getBirthday());
        staff.setCloestStation(staffDetail.getCloestStation());
        staff.setTel(staffDetail.getTel());
        staff.setComment(staffDetail.getComment());
        staff.setEmergencyAddress(staffDetail.getEmergencyAddress());
        staff.setCreateUser(staffDetail.getCreateUser());
        staff.setUpdateUser(staffDetail.getUpdateUser());
        staff.setMail(staffDetail.getMail());
        staff.setFavorite(staffDetail.getFavorite());
        staff.setNote(staffDetail.getNote());
        staff.setPreferedField(staffDetail.getPreferedField());
        staff.setPreferedLanguage(staffDetail.getPreferedLanguage());

        staff.setCreateUser("admin");
        staff.setUpdateUser("admin");
        return ServerResponse.createBySuccess(staff);
    }

}
