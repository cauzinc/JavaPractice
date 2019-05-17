package com.sample.daoMapper;

import com.sample.pojo.Principle;
import com.sample.vo.PrincipleListItem;

import java.util.List;

public interface PrincipleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Principle record);

    int insertSelective(Principle record);

    Principle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Principle record);

    int updateByPrimaryKey(Principle record);

    List<PrincipleListItem> getPrincipleWithBusinessInfo();
}