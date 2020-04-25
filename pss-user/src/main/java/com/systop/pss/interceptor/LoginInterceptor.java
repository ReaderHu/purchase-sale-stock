package com.systop.pss.interceptor;

import com.systop.pss.common.constants.Contents;
import com.systop.pss.model.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName LoginInterceptor
 * PackageName com.systop.pss.interceptor
 *
 * @Description
 * @auther wang
 * @create 2019-12-11
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {


    private Logger logger = LogManager.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("拦截器进行拦截");
        UserInfo sessionUser = (UserInfo) request.getSession().getAttribute(Contents.SESSION_USER);

        if (null == sessionUser) {
            response.sendRedirect(request.getContextPath()+"/login/index");
            return false;
        }
        return true;
    }
}
