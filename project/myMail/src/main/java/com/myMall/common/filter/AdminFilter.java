package com.myMall.common.filter;

import com.myMall.common.Const;
import com.myMall.common.ResponseCode;
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

    @Pointcut("execution(public * com.myMall.controller.backend.*ManagerController.*(..)) " +
            "&& !execution(public * com.myMall.controller.backend.UserManagerController.login(..))")
    public void CategoryManagerFilter() { }
    @Pointcut("execution(public * com.myMall.controller.portal.CartController.*(..))")
    public void CartManagerFilter() { }
    @Pointcut("execution(public * com.myMall.controller.portal.ShippingController.*(..))")
    public void ShippingManagerFilter() { }

    // 检查用户是否为管理员
    @Around("CategoryManagerFilter() || ShippingManagerFilter()")
    public ServerResponse adminAuth(ProceedingJoinPoint pjp) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse response = user == null ? ServerResponse.createByErrorByErrorCode(ResponseCode.NEED_LOGIN.getCode(), "用户未登录")
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

    // 检查用户是否登录
    @Around("CartManagerFilter()")
    public ServerResponse loginAuth(ProceedingJoinPoint pjp) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);

        if(user != null) {
            try {
                return (ServerResponse) pjp.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        ServerResponse response = ServerResponse.createByErrorByErrorCode(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        return response;
    }

}
