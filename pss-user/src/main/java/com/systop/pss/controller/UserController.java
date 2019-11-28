package com.systop.pss.controller;

import com.systop.pss.common.constants.Contents;
import com.systop.pss.common.constants.ResultCodeEnum;
import com.systop.pss.common.vo.R;
import com.systop.pss.controller.vo.UserVo;
import com.systop.pss.model.UserInfo;
import com.systop.pss.service.UserInfoServcie;
import com.systop.pss.service.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

@RestController
public class UserController {

    @Autowired
    private UserInfoServcie userInfoServcie;

    @RequestMapping("/list")
    public List<UserInfo> getList() {

        return userInfoServcie.selectUserList();
    }

    @RequestMapping("/login")
    public R login(@RequestParam(value = "telPhone" ,required = true )String telPhone,
                        @RequestParam(value = "password" ,required = true )String password) {
        if (!Pattern.matches(Contents.TEL_PHONE_EXP,telPhone)) {
            return  R.error().message("请输入正确的手机号");
        }

        // 设置登录对象
        UserDto userDto = new UserDto();
        userDto.setTelPhone(telPhone);
        userDto.setPwd(password);

        UserDto userInfo = userInfoServcie.login(userDto);

        if ( null== userInfo) {
            return R.error().message("用户或者密码不正确，请重新输入");
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userInfo, userVo);



        return R.ok().message(ResultCodeEnum.SUCCESS.getMessage()).data("data",userVo);
    }

}
