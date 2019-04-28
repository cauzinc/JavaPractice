package com.myMall.common;

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
}
