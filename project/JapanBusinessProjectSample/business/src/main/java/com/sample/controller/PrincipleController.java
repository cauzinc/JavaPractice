package com.sample.controller;

import com.sample.common.ResponseCode;
import com.sample.common.ServerResponse;
import com.sample.daoMapper.PrincipleMapper;
import com.sample.pojo.Principle;
import com.sample.service.PrincipleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/principle/")
public class PrincipleController {
    private PrincipleService principleService;

    @Autowired
    public void setPrincipleService(PrincipleService principleService) {
        this.principleService = principleService;
    }

    @ResponseBody
    @RequestMapping(value = "getPrinciplePortalList", method = RequestMethod.GET)
    public ServerResponse getPrinciplePortalList() {
        return principleService.getPrinciplePortalList();
    }

    // httpServletRequest   只能接受表单数据，我们使用json时不使用该对象
    @ResponseBody
    @RequestMapping(value = "insertPrinciple", method = RequestMethod.POST)
    public ServerResponse insertPrinciple(@RequestBody Map<String, String> params) {
        String principleName = params.get("principleName");
        String department = params.get("department");
        String businessName = params.get("businessName");
        String position = params.get("position");
        String mail = params.get("mail");
        String tel = params.get("tel");
        String phone = params.get("phone");
        String fax = params.get("fax");
        String postcode = params.get("postcode");
        String address = params.get("address");
        String favorite = params.get("favorite");
        String comment = params.get("comment");

        Principle principle = new Principle();
        principle.setPrincipleName(principleName);
        principle.setDepartment(department);
        principle.setPosition(position);
        principle.setMail(mail);
        principle.setTel(tel);
        principle.setPhone(phone);
        principle.setFax(fax);
        principle.setPostcode(postcode);
        principle.setAddress(address);
        principle.setFavorite(favorite);
        principle.setComment(comment);
        principle.setCreateUser("admin");
        principle.setUpdateUser("admin");

        System.out.println(principle.getPrincipleName());
        System.out.println(principle.getDepartment());
        return principleService.insertPrinciple(principle, businessName);
    }

    @ResponseBody
    @RequestMapping(value = "updatePrinciple", method = RequestMethod.POST)
    public ServerResponse updatePrinciple(@RequestBody Principle principle) {
        if(principle == null) {
            return ServerResponse.createByErrorErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "Argument Error");
        }

        return principleService.updatePrinciple(principle);
    }

    @ResponseBody
    @RequestMapping(value = "deletePrincipleById", method = RequestMethod.GET)
    public ServerResponse updatePrinciple(Integer principleId) {
        if(principleId == null) {
            return ServerResponse.createByErrorErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "Argument Error");
        }
        return principleService.deletePrinciple(principleId);
    }

    @ResponseBody
    @RequestMapping(value = "getPrincipleDetail", method = RequestMethod.GET)
    public ServerResponse getPrincipleDetail(Integer principleId) {
        if(principleId == null) {
            return ServerResponse.createByErrorErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "Argument Error");
        }
        return principleService.getPrincipleById(principleId);
    }


}
