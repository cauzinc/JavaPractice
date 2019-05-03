package com.myMall.common;

import com.google.common.collect.Sets;

import java.util.Set;

// 常量类
public class Const {
    public static final String CURRENT_USER = "currentUser";

    // 这里如果使用枚举则太重，因此使用接口来保存用户的分组
    // 但是不要用接口类文件来保存常量，因为接口是多继承的，可能导致变量名冲突
    public interface Roles {
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1; //管理员
    }

    // data need to be validated
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";

    // 价格排序的规则, 使用set的保存方式, 搜索的复杂度比list要低 O(n) - O(1)
    public interface ProductListOrderRule {
        Set<String> PRICE_RULE_SET = Sets.newHashSet("price_asc", "price_desc");
    }

    // 产品状态
    public enum ProductStatement {
        ONBOARD("上架中", 1),
        OFFBOARD("已下架", 0);
        private String desc;
        private Integer state;

        ProductStatement(String desc, Integer state) {
            this.desc = desc;
            this.state = state;
        }

        public String getDesc() {
            return desc;
        }

        public Integer getState() {
            return state;
        }
    }

    // 购物车状态
    public interface CartStatement{
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }
}
