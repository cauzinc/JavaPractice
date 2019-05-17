package com.sample.service;

import com.sample.common.ServerResponse;
import com.sample.daoMapper.PrincipleMapper;
import com.sample.pojo.Principle;
import com.sample.vo.PrincipleListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrincipleService {
    private PrincipleMapper principleMapper;
    @Autowired
    public void setPrincipleMapper(PrincipleMapper principleMapper) {
        this.principleMapper = principleMapper;
    }

    public ServerResponse getPrinciplePortalList() {
        List<PrincipleListItem> principleListItemList = principleMapper.getPrincipleWithBusinessInfo();
        return ServerResponse.createBySuccess(principleListItemList);
    }

    public ServerResponse insertPrinciple(Principle principle) {
        int rowCount = principleMapper.insert(principle);
        if(rowCount > 0) {
            return ServerResponse.createBySuccessMessage("ok");
        }
        return ServerResponse.createByErrorMessage("Insert failed");
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
        return ServerResponse.createBySuccessMessage("ok");
    }

}
