package com.systop.pss.timer;

import com.systop.pss.database.mapper.UserInfoBaseMapper;
import com.systop.pss.database.model.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @auther wang
 * @create 2019-12-02
 */
@RestController
public class TimerController {

    @Resource
    private UserInfoBaseMapper userInfoBaseMapper;

    @RequestMapping("/test")
    public UserInfo test() {

        UserInfo userInfo = userInfoBaseMapper.selectByPrimaryKey("1");

        return userInfo;
    }

}
