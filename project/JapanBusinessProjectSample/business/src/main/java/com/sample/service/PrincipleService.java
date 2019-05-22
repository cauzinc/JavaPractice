package com.sample.service;

import com.sample.common.ServerResponse;
import com.sample.daoMapper.BusinessMapper;
import com.sample.daoMapper.PrincipleMapper;
import com.sample.pojo.Business;
import com.sample.pojo.Principle;
import com.sample.vo.PrincipleDetail;
import com.sample.vo.PrincipleListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrincipleService {
    private PrincipleMapper principleMapper;
    private BusinessMapper businessMapper;
    @Autowired
    public void setPrincipleMapper(PrincipleMapper principleMapper) {
        this.principleMapper = principleMapper;
    }
    @Autowired
    public void setBusinessMapper(BusinessMapper businessMapper) {
        this.businessMapper = businessMapper;
    }

    public ServerResponse getPrinciplePortalList() {
        List<PrincipleListItem> principleListItemList = principleMapper.getPrincipleWithBusinessInfo();
        return ServerResponse.createBySuccess(principleListItemList);
    }

    public ServerResponse insertPrinciple(Principle principle, String businessName) {
        // 返回的id是在principle对象中，而不是方法的返回值，返回值依然是插入成功的数据的条数
        Integer id = principleMapper.insertSelective(principle);
        // todo businessName is not unique now
        principleMapper.setBusinessPrincipleIdByName(businessName, principle.getId());

        return ServerResponse.createBySuccessMessage("ok");
    }

    public ServerResponse updatePrinciple(Principle principle) {
        int rowCount = principleMapper.updateByPrimaryKeySelective(principle);
        if(rowCount > 0) {
            return ServerResponse.createBySuccessMessage("ok");
        }
        return ServerResponse.createByErrorMessage("Update failed");
    }

    public ServerResponse deletePrinciple(Integer principleId) {
        int rowCount = principleMapper.deleteByPrimaryKey(principleId);
        if(rowCount > 0) {
            return ServerResponse.createBySuccessMessage("ok");
        }
        return ServerResponse.createByErrorMessage("Delete failed");
    }

    public ServerResponse getPrincipleById(Integer principleId) {
        Principle principle = principleMapper.selectByPrimaryKey(principleId);
        if(principle == null) {
            return ServerResponse.createByErrorMessage("データは検索できません");
        }
        StringBuilder businessName = new StringBuilder();
        List<Business> businessList = businessMapper.getBusinessNameByPrincipleId(principle.getId());

        for(Business business : businessList) {
            if(business != null) {
                businessName.append(business.getBusinessName()).append(" ");
            }
        }

        PrincipleDetail principleDetail = new PrincipleDetail();
        principleDetail.setPrincipleName(principle.getPrincipleName());
        principleDetail.setAddress(principle.getAddress());
        principleDetail.setPosition(principle.getPosition());
        principleDetail.setDepartment(principle.getDepartment());
        principleDetail.setBusinessName(businessName.toString());
        principleDetail.setFavorite(principle.getFavorite());
        principleDetail.setFax(principle.getFax());
        principleDetail.setPhone(principle.getPhone());
        principleDetail.setTel(principle.getTel());
        principleDetail.setPostcode(principle.getPostcode());

        return ServerResponse.createBySuccess(principleDetail);
    }

}
