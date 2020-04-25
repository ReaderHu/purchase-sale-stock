package com.systop.pss.controller;

import com.systop.pss.common.constants.Contents;
import com.systop.pss.service.UserInfoServcie;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * ClassName IndexController
 * PackageName com.systop.pss.controller
 *
 * @Description
 * @auther wang
 * @create 2019-12-11
 */
@Controller("indexController")
@RequestMapping("/login")
@Api(description = "登录管理")
public class IndexController {

    private Logger logger = LogManager.getLogger(UserController.class);

    /**
     * 用户查询
     */
    @Autowired
    private UserInfoServcie userInfoServcie;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *  登录页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String hello(HttpServletRequest request, Model model){


        model.addAttribute("msg","欢迎登录");

        //获取redis中用户数量
        Integer userCount = (Integer) redisTemplate.opsForValue().get(Contents.USER_COUNT);

        if (null == userCount) {
            userCount = userInfoServcie.getUserCount(null);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForValue().set(Contents.USER_COUNT,userCount,2, TimeUnit.HOURS);
        }
        request.getSession().setAttribute(Contents.USER_COUNT,userCount);

        return "login";
    }
    @RequestMapping("/index1")
    @ResponseBody
    public String index1() {
        logger.info("web 启动");
        return "index";
    }

    /**
     * \退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginout")
    public String loginOut(HttpServletRequest request,Model model){
        // 消除session中user
        request.getSession().removeAttribute(Contents.SESSION_USER);
        // 清空系统中所有的session
//        request.getSession().invalidate();
//        return "redicet: index";
        return "login";
    }


}
