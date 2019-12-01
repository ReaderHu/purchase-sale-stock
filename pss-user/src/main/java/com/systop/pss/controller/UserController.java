package com.systop.pss.controller;

import com.systop.pss.common.constants.Contents;
import com.systop.pss.common.constants.ResultCodeEnum;
import com.systop.pss.common.vo.R;
import com.systop.pss.controller.vo.UserVo;
import com.systop.pss.model.UserInfo;
import com.systop.pss.model.UserPassword;
import com.systop.pss.service.UserInfoServcie;
import com.systop.pss.service.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
@Api(description = "用户管理")
public class UserController {

    @Autowired
    private UserInfoServcie userInfoServcie;

    @RequestMapping(value = "/getList" ,method = RequestMethod.POST)
    public List<UserInfo> getList() {

        return userInfoServcie.selectUserList();
    }

    /**
     * 登录功能
     * @param telPhone
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public R login(@RequestParam(value = "telPhone" ,required = true )String telPhone,
                        @RequestParam(value = "password" ,required = true )String password) {
        if (!Pattern.matches(Contents.TEL_PHONE_EXP,telPhone)) {
            return  R.setResult(ResultCodeEnum.ERROR_TELPHONE);
        }

        // 设置登录对象
        UserDto userDto = new UserDto();
        userDto.setTelPhone(telPhone);
        userDto.setPwd(password);

        // 进行UserInfo查询
        UserInfo userInfo = userInfoServcie.login(userDto);

        if ( null== userInfo) {
            return R.setResult(ResultCodeEnum.ERROR_LOGIN);
        }
        // 修改用户最后登录时间
        UserInfo updateUser = new UserInfo();
        updateUser.setUuId(userInfo.getUuId());
        updateUser.setLastLoginTime(new Date());

        int updateCount = userInfoServcie.updateByPrimaryKeySelective(updateUser);

        if(updateCount < 0) {
            return  R.error().message("登录超时，请重新登录");
        }

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userInfo, userVo);

        return R.ok().message(ResultCodeEnum.SUCCESS.getMessage()).data("database",userVo);
    }

    /**
     * 注册用户
     *
     * uuid = new Date() + 000
     *
     * @param userName
     * @param userAge
     * @param telPhone
     * @param workAge
     * @param department
     * @return
     */
    @ApiOperation("用户注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public R register(HttpServletRequest request,
            @RequestParam(value = "userName",required = true)String userName,
                      @RequestParam(value = "userAge",required = true)int userAge,
                      @RequestParam(value = "telPhone",required = true)String telPhone,
                      @RequestParam(value = "workAge",required = true)int workAge,
                      @RequestParam(value = "pwd",required = true)String pwd,
                      @RequestParam(value = "confirmPwd",required = true)String confirmPwd,
                      @RequestParam(value = "department",required = true)String department){

        // 判断用户手机号是否正确
        if (!Pattern.matches(Contents.TEL_PHONE_EXP,telPhone)) {
            return R.setResult(ResultCodeEnum.ERROR_TELPHONE);
        }
        if (userAge < Contents.INT_0) {
            return R.error().message("请输入正确的年龄");
        }
        if (null == department) {
            return R.error().message("请输入部门信息");
        }
        if (null == pwd || null == confirmPwd) {
            return R.error().message("密码不能为空");
        }
        if (!pwd.equals(confirmPwd)) {
            return R.error().message("两次密码不一样，请重新输入");
        }

        // 编辑用户UUID
        String uuid = redactUserUUID();

        UserInfo userInfo = new UserInfo();
        // UUID
        userInfo.setUuId(StringUtils.substring(uuid,0,12));
        // 用户名称
        userInfo.setUserName(userName);
        // 用户年龄
        userInfo.setUserAge(userAge);
        // 电话
        userInfo.setTelPhone(telPhone);
        // 工作年龄
        userInfo.setWorkAge(workAge);
        // 部门Code
        userInfo.setDepartment(department);

        // 保存用户
        int insertCount = userInfoServcie.insertSelective(userInfo);

        // 判端是否注册成功
        if (insertCount < 0 ){
            return R.setResult(ResultCodeEnum.ERROR_REGISTER);
        }
        // 用手机号查询新登录用户的信息并放入session中
        UserInfo sessionUserInfo = userInfoServcie.selectUserByTelPhone(telPhone);
        request.getSession().setAttribute(Contents.SESSION_USER,sessionUserInfo);

        // 保存用户密码
        UserPassword userPassword = new UserPassword();
        userPassword.setPwd(pwd);
        userPassword.setUuId(sessionUserInfo.getUuId());


        return R.setResult(ResultCodeEnum.SUCCESS_REGISTER).data(Contents.SESSION_USER,sessionUserInfo);
    }

    /**
     * \退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginout",method = RequestMethod.POST)
    public R loginOut(HttpServletRequest request){
        // 消除session中user
        request.getSession().removeAttribute(Contents.SESSION_USER);
        // 清空系统中所有的session
//        request.getSession().invalidate();
//        return "redicet: index";
        return R.ok().message("用户退出登录");
    }

    @ApiOperation("用户信息修改")
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    public R updateUser(HttpServletRequest request,
                      @RequestParam(value = "uuId",required = false)String uuId,
                      @RequestParam(value = "telPhone",required = true)String telPhone,
                      @RequestParam(value = "department",required = true)String department) {

        // 获取session中user信息
        UserInfo sessionUser = (UserInfo) request.getSession().getAttribute(Contents.SESSION_USER);

        // 修改的User信息
        UserInfo userInfo = new UserInfo();
        // 用户ID
        if (StringUtils.isNotEmpty(uuId)) {
            userInfo.setUuId(uuId);
        } else {
            userInfo.setUuId(sessionUser.getUuId());
        }
        // 更改信息用户
        userInfo.setUpdateUser(sessionUser.getUuId());
        // 手机
        userInfo.setTelPhone(telPhone);
        // 部门
        userInfo.setDepartment(department);

        userInfoServcie.updateByPrimaryKeySelective(userInfo);

        return R.ok();
    }


    /**
     * 删除用户
     * @param uuId
     * @return
     */
    @RequestMapping(value = "/deluset",method = RequestMethod.POST)
    public R delUser(@RequestParam(value = "uuId",required = false)String uuId){

        // 根基UUID 删除用户
        int delCount = userInfoServcie.deleteByPrimaryKey(uuId);
        if (delCount > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     *  获取用户的UUID
     *  UUID = newDate() YYYYMMDDHHSSMM
     * @return
     */
    private String redactUserUUID() {

        // 获取当前时间
        DateTime dateTime = new DateTime();

        return dateTime.toString(Contents.DATE_PATTERN_YYYYMMDDHHSSMM);
    }

}
