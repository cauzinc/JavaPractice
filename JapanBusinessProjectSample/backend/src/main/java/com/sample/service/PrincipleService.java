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
import java.util.Map;

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

    public ServerResponse checkIfBusinessExist(String name) {
        Business business = businessMapper.selectByName(name);
        if(business == null) {
            return ServerResponse.createByErrorMessage("入力した取引先はいません､ まずは取引先情報を作成してください｡");
        } else {
            return ServerResponse.createBySuccessMessage("ok");
        }
    }

    public ServerResponse insertPrinciple(Principle principle, String businessName) {
        // check if business exists
        ServerResponse ifBusinessExist = this.checkIfBusinessExist(businessName);
        if(!ifBusinessExist.isSuccess()) {
            return ifBusinessExist;
        }
        // 返回的id是在principle对象中，而不是方法的返回值，返回值依然是插入成功的数据的条数
        principleMapper.insertSelective(principle);

        // todo businessName is not unique now
        principleMapper.setBusinessPrincipleIdByName(businessName, principle.getId());

        return ServerResponse.createBySuccessMessage("ok");
    }

    public ServerResponse updatePrinciple(Principle principle, String businessName) {
        ServerResponse ifBusinessExist = this.checkIfBusinessExist(businessName);
        if(!ifBusinessExist.isSuccess()) {
            return ifBusinessExist;
        }

        // judge if business charged has changed
        boolean ifChargeBusinessChanged = true;

        List<Business> businessList = businessMapper.getBusinessNameByPrincipleId(principle.getId());
        for(Business business : businessList) {
            if(business.getBusinessName().equals(businessName.trim())) {
                ifChargeBusinessChanged = false;
            }
        }
        // todo 这里的三条sql应该整合成一项事务
        if(ifChargeBusinessChanged) {
            businessMapper.clearPrincipleId(principle.getId());
            businessMapper.setPrincipleIdByName(businessName, principle.getId());
        }

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
        principleDetail.setMail(principle.getMail());
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

    public Principle assemblePrinciple(Map<String, Object> params) {
        Principle principle = new Principle();
        if(params.get("id") != null) {
            int id = (Integer)params.get("id");
            principle.setId(id);
        }

        if(params.get("principleName") != null) {
            principle.setPrincipleName((String)params.get("principleName"));
        }
        if(params.get("department") != null) {
            principle.setDepartment((String)params.get("department"));
        }
        if(params.get("position") != null) {
            principle.setPosition((String)params.get("position"));
        }
        if(params.get("mail") != null) {
            principle.setMail((String)params.get("mail"));
        }
        if(params.get("tel") != null) {
            principle.setTel((String)params.get("tel"));
        }
        if(params.get("phone") != null) {
            principle.setPhone((String)params.get("phone"));
        }
        if(params.get("fax") != null) {
            principle.setFax((String)params.get("fax"));
        }
        if(params.get("postcode") != null) {
            principle.setPostcode((String)params.get("postcode"));
        }
        if(params.get("address") != null) {
            principle.setAddress((String)params.get("address"));
        }
        if(params.get("favorite") != null) {
            principle.setFavorite((String)params.get("favorite"));
        }
        if(params.get("comment") != null) {
            principle.setComment((String)params.get("comment"));
        }
        principle.setCreateUser("admin");
        principle.setUpdateUser("admin");


        return principle;
    }

}
