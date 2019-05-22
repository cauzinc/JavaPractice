package com.sample.daoMapper;

import com.sample.pojo.Business;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);

    List<Business> getBusinessNameByPrincipleId(@Param("principleId") Integer principleId);

    int clearPrincipleId(Integer principleId);

    int setPrincipleIdByName(@Param("businessName") String businessName,
                             @Param("principleId") Integer principleId);
}