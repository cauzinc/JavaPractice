package com.myMall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.myMall.common.ServerResponse;
import com.myMall.dao.ShippingMapper;
import com.myMall.pojo.Shipping;
import com.myMall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service("iShippingService")
public class ShippingService implements IShippingService {
    private ShippingMapper shippingMapper;
    @Autowired
    public void setShippingMapper(ShippingMapper shippingMapper) {
        this.shippingMapper = shippingMapper;
    }

    // 增加后将id返回给前端让前端使用
    public ServerResponse add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if(rowCount > 0) {
            int newId = shipping.getId();
            Map<String, Integer> result = Maps.newHashMap();
            result.put("shippingId", newId);
            return ServerResponse.createBySuccess(result);
        }
        return ServerResponse.createByErrorByMessage("新建地址失败");
    }

    public ServerResponse delete(Integer userId, Integer shippingId) {
        // 防止横向越权要在sql中检查userId
        int result = shippingMapper.deleteByUserId(userId, shippingId);
        if(result > 0) {
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createBySuccessByMessage("删除失败");
    }

    public ServerResponse update(Integer userId, Shipping updateItem) {
        // 防止横向越权，判断userId是否正确
        updateItem.setUserId(userId);
        int result = shippingMapper.updateByUserId(updateItem);
        if(result > 0) {
            return ServerResponse.createBySuccessByMessage("更新成功");
        }
        return ServerResponse.createByErrorByMessage("更新失败");
    }

    public ServerResponse select(Integer userId, Integer shippingId) {
        Shipping shipping = shippingMapper.selectByUserId(userId, shippingId);
        if(shipping == null) {
            return ServerResponse.createByErrorByMessage("找不到对应地址");
        }
        return ServerResponse.createBySuccess(shipping);
    }

    public ServerResponse selectAll(int pageNum, int pageSize, Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.getUserShippingList(userId);
        PageInfo<Shipping> pageInfo = new PageInfo<>(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }

}
