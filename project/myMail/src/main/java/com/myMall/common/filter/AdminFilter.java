package com.myMall.common.filter;

import com.myMall.common.Const;
import com.myMall.common.ServerResponse;
import com.myMall.pojo.User;
import com.myMall.service.IUserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class AdminFilter {
    private IUserService iUserService;

    AdminFilter() { }

    @Autowired
    AdminFilter(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Pointcut("execution(public * com.myMall.controller.backend.CategoryManagerController.*(..))")
    public void CategoryManagerFilter() { }


    // 检查用户是否为管理员
    @Around("CategoryManagerFilter()")
    public ServerResponse adminAuth(ProceedingJoinPoint pjp) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse response = user == null ? ServerResponse.createByErrorByMessage("用户未登录")
                : iUserService.checkAdmin(user.getId());

        if(response.isSuccess()) {
            try{
                return (ServerResponse) pjp.proceed();
            } catch(Throwable e) {
                e.printStackTrace();
            }
        }
        return response;
    }

}
