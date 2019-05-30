package com.sample.service;

import com.sample.common.ServerResponse;
import com.sample.daoMapper.BusinessMapper;
import com.sample.daoMapper.StaffMapper;
import com.sample.pojo.Business;
import com.sample.pojo.Staff;
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


    public StaffListItem assembleStaffItemList(Staff staff) {
        StaffListItem staffListItem = new StaffListItem();
        staffListItem.setStaffName(staff.getStaffName());
        staffListItem.setTel(staff.getTel());
        Business business = businessMapper.selectByPrimaryKey(staff.getBusinessId());
        staffListItem.setBusinessName(business.getBusinessName());
        return staffListItem;
    }

}
