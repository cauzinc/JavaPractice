package com.sample.controller;


import com.sample.common.ResponseCode;
import com.sample.common.ServerResponse;
import com.sample.daoMapper.StaffMapper;
import com.sample.pojo.Business;
import com.sample.pojo.Staff;
import com.sample.service.StaffService;
import com.sample.vo.StaffDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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
    public ServerResponse quickInsert(@RequestBody StaffDetail staffDetail) {
        String name = staffDetail.getStaffName();
        if(name == null || "".equals(name.trim())) {
            return ServerResponse.createByErrorMessage("スタッフ名は必要です｡");
        }
        ServerResponse<Staff> response = iStaffService.assembleStaff(staffDetail);
        if(!response.isSuccess()) {
            return response;
        }
        staffDetail.setCreateUser("admin");
        staffDetail.setUpdateUser("admin");

        Staff staff = response.getData();
        staffMapper.insertSelective(staff);
        return ServerResponse.createBySuccess(staffDetail);
    }

    @ResponseBody
    @RequestMapping(value = "getStaffList", method = RequestMethod.GET)
    public ServerResponse getStaffList() {
        return iStaffService.getStaffList();
    }


    @ResponseBody
    @RequestMapping(value = "getStaffById", method = RequestMethod.GET)
    public ServerResponse getStaffById(Integer staffId) {
        if(staffId == null) {
            return ServerResponse.createByErrorErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "Need staff id");
        }
        return iStaffService.getStaffById(staffId);
    }

    @ResponseBody
    @RequestMapping(value = "insertStaff", method = RequestMethod.POST)
    public ServerResponse insertStaff(@RequestBody StaffDetail staffDetail) {
        ServerResponse<Staff> response = iStaffService.assembleStaff(staffDetail);
        if(!response.isSuccess()) {
            return response;
        }
        Staff staff = response.getData();
        staffMapper.insertSelective(staff);
        return ServerResponse.createBySuccess("ok");
    }

    @ResponseBody
    @RequestMapping(value = "updateStaff", method = RequestMethod.POST)
    public ServerResponse updateStaff(@RequestBody StaffDetail staffDetail) {

        ServerResponse<Staff> response = iStaffService.assembleStaff(staffDetail);
        if(!response.isSuccess()) {
            return response;
        }
        Staff staff = response.getData();

        staff.setId(staffDetail.getStaffId());

        staffMapper.updateByPrimaryKeySelective(staff);
        return ServerResponse.createBySuccess("ok");
    }

    @ResponseBody
    @RequestMapping(value = "deletePrincipleById", method = RequestMethod.GET)
    public ServerResponse deletePrinciple(Integer staffId) {
        if(staffId == null) {
            return ServerResponse.createByErrorErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "Argument Error");
        }
        return iStaffService.deleteStaff(staffId);
    }

    @ResponseBody
    @RequestMapping(value = "commentStaff", method = RequestMethod.POST)
    public ServerResponse commentStaff(@RequestBody Map<String, Object> params) {
        int businessExperience = (Integer) params.get("businessExperience");
        int leaderExperience = (Integer) params.get("leaderExperience");
        int efficiency = (Integer) params.get("efficiency");
        int staffId = (Integer) params.get("staffId");
        String comment = "";
        comment += businessExperience == 0 ? "業務知識経験ある､" : "業務知識経験ない､";
        comment += leaderExperience == 0 ? "リーダ経験ある､" : "リーダ経験ない､";
        comment += efficiency == 0 ? "生産性高い" :
                efficiency == 1 ? "生産性中" : "生産性低い";

        return iStaffService.commentStaff(staffId, comment);
    }


}
