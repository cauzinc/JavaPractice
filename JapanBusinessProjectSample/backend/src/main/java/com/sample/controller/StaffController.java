package com.sample.controller;


import com.sample.common.ServerResponse;
import com.sample.daoMapper.StaffMapper;
import com.sample.pojo.Business;
import com.sample.pojo.Staff;
import com.sample.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/staff/")
public class StaffController {
    private StaffMapper staffMapper;
    private StaffService iStaffService;
    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }
    @Autowired
    public void setiStaffService(StaffService iStaffService) {
        this.iStaffService = iStaffService;
    }

    @ResponseBody
    @RequestMapping(value = "quickInsert", method = RequestMethod.POST)
    public ServerResponse quickInsert(@RequestBody Staff staff) {
        String name = staff.getStaffName();
        if(name == null || "".equals(name.trim())) {
            return ServerResponse.createByErrorMessage("スタッフ名は必要です｡");
        }
        staff.setCreateUser("admin");
        staff.setUpdateUser("admin");
        staffMapper.insertSelective(staff);
        return ServerResponse.createBySuccess(staff);
    }

    @ResponseBody
    @RequestMapping(value = "getBusinessList", method = RequestMethod.GET)
    public ServerResponse getBusinessList() {
        return iStaffService.getStaffList();
    }




}
