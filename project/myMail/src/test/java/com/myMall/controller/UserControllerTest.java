package com.myMall.controller;

import com.myMall.controller.portal.UserController;
import org.junit.Test;

public class UserControllerTest {

    @Test
    public void test() {
        UserController uc = new UserController();
        String question = uc.getForgetQuestion("geely").getData();
        System.out.println(question);
    }
}
