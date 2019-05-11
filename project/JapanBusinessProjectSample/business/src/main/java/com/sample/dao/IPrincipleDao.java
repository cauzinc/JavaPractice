package com.sample.dao;

import com.sample.pojo.Principle;
import com.sample.vo.PrincipleListItem;

import java.util.List;

public interface IPrincipleDao {
    List<PrincipleListItem> getPrincipleRelatedList();
    void insertPrinciple(Principle principle);
    void updatePrinciple(Principle principle);
    void deletePrinciple(Integer id);
    Principle getPrincipleById(Integer id);
}
