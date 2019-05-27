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
    public ServerResponse insertPrinciple(@RequestBody Map<String, Object> params) {
        String businessName = (String) params.get("businessName");

        Principle principle = principleService.assemblePrinciple(params);

        return principleService.insertPrinciple(principle, businessName);
    }

    @ResponseBody
    @RequestMapping(value = "updatePrinciple", method = RequestMethod.POST)
    public ServerResponse updatePrinciple(@RequestBody Map<String, Object> params) {
        String businessName = (String) params.get("businessName");

        Principle principle = principleService.assemblePrinciple(params);

        return principleService.updatePrinciple(principle, businessName);
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
