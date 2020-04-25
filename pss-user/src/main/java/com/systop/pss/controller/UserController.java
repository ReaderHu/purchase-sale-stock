package com.systop.pss.controller;

import com.systop.pss.common.constants.Contents;
import com.systop.pss.common.constants.ResultCodeEnum;
import com.systop.pss.common.error.BusinessException;
import com.systop.pss.common.utils.CommonUtils;
import com.systop.pss.common.vo.R;
import com.systop.pss.controller.vo.UserVo;
import com.systop.pss.model.UserInfo;
import com.systop.pss.model.UserPassword;
import com.systop.pss.service.UserInfoServcie;
import com.systop.pss.service.UserPasswordService;
import com.systop.pss.service.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Controller("userController")
@RequestMapping("/user")
@Api(description = "用户管理")
public class UserController extends BaseController{

    private Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserInfoServcie userInfoServcie;

    @Autowired
    private UserPasswordService userPasswordService;


    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping(value = "/getList")
    public String getList(Model model) {
        List<UserInfo> userInfoList = userInfoServcie.selectUserList();
        model.addAttribute("userList",userInfoList);
        return "userlist";
    }

    /**
     * 登录功能
     * @param telPhone
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "telPhone" ,required = true )String telPhone,
                        @RequestParam(value = "password" ,required = true )String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (!Pattern.matches(Contents.TEL_PHONE_EXP,telPhone)) {
//            return  R.setResult(ResultCodeEnum.ERROR_TELPHONE);
        }

        // 设置登录对象
        UserDto userDto = new UserDto();
        userDto.setTelPhone(telPhone);

        if (!telPhone.equals(Contents.ADMIN_ACCOUNT)) {
            password = CommonUtils.EncodeByMD5(password);
        }

        userDto.setPwd(password);

        // 进行UserInfo查询
        UserInfo userInfo = userInfoServcie.login(userDto);

        if ( null== userInfo) {
//            return R.setResult(ResultCodeEnum.ERROR_LOGIN);
        }
        // 修改用户最后登录时间
        UserInfo updateUser = new UserInfo();
        updateUser.setUuId(userInfo.getUuId());
        updateUser.setLastLoginTime(new Date());

        int updateCount = userInfoServcie.updateByPrimaryKeySelective(updateUser);

        if(updateCount < 0) {
//            return  R.error().message("登录超时，请重新登录");
        }

        request.getSession().setAttribute(Contents.SESSION_USER,userInfo);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userInfo, userVo);
        model.addAttribute("data",userVo);
        return "index";
//        return R.ok().message(ResultCodeEnum.SUCCESS.getMessage()).data("database",userVo);
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
    @ResponseBody
    public R register(HttpServletRequest request,
            @RequestParam(value = "userName",required = true)String userName,
                      @RequestParam(value = "userAge",required = true)int userAge,
                      @RequestParam(value = "telPhone",required = true)String telPhone,
                      @RequestParam(value = "workAge",required = true)int workAge,
                      @RequestParam(value = "pwd",required = true)String pwd,
                      @RequestParam(value = "confirmPwd",required = true)String confirmPwd,
                      @RequestParam(value = "department",required = true)String department) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

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

        UserDto userInfo = new UserDto();
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
        // 注册时间
        userInfo.setEntryTime(new Date());

        // 对密码进行md5加密
        if (!telPhone.equals(Contents.ADMIN_ACCOUNT)) {
            pwd = CommonUtils.EncodeByMD5(pwd);
        }
        // 密码
        userInfo.setPwd(pwd);

        // 保存用户
        int insertCount = userInfoServcie.register(userInfo);

        // 判端是否注册成功
        if (insertCount < 0 ){
            return R.setResult(ResultCodeEnum.ERROR_REGISTER);
        }

        return R.setResult(ResultCodeEnum.SUCCESS_REGISTER);
    }

    /**
     * \退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginout",method = RequestMethod.POST)
    public String loginOut(HttpServletRequest request,Model model){
        // 消除session中user
        request.getSession().removeAttribute(Contents.SESSION_USER);
        // 清空系统中所有的session
//        request.getSession().invalidate();
//        return "redicet: index";
        return "login";
    }

    @ApiOperation("用户信息修改")
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    public String updateUser(HttpServletRequest request,
                      Model model,
                      @RequestParam(value = "uuId",required = true)String uuId,
                      @RequestParam(value = "telPhone",required = false)String telPhone,
                      @RequestParam(value = "department",required = false)String department) {

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
        if (StringUtils.isNotEmpty(telPhone)) {
            userInfo.setTelPhone(telPhone);
        }
        // 部门
        if (StringUtils.isNotEmpty(department)) {
            userInfo.setDepartment(department);
        }
        // 修改时间
        userInfo.setUpdateTime(new Date());

        int updateCount = userInfoServcie.updateByPrimaryKeySelective(userInfo);
        if (updateCount < 0) {
            model.addAttribute("msg","修改信息出错，请稍后重试");
            return "userUpdate";
        }

        return "redirect:/user/getList";
    }


    /**
     * 删除用户
     * @param uuId
     * @return
     */
    @RequestMapping(value = "/deluset",method = RequestMethod.GET)
    public String delUser(Model model,
            @RequestParam(value = "uuId",required = false)String uuId){

        // 根基UUID 删除用户
//        int delCount = userInfoServcie.deleteByPrimaryKey(uuId);
        int delCount = userInfoServcie.deleteByPrimaryKeyByDelFlag(uuId);
        if (delCount > 0) {
            model.addAttribute("msg","修改信息出错，请稍后重试");
        }
        return "redirect:/user/getList";
    }

    /**
     * 获取用户密码
     * @return
     */
    @RequestMapping(value = "/getuserpwd",method = RequestMethod.POST)
    @ResponseBody
    public R getUserPwd(HttpServletRequest request,
                            @RequestParam(value = "oldPwd") String oldPwd) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Contents.SESSION_USER);

        String userPwd = userInfoServcie.selectUserPwd(userInfo.getUuId());
        // MD5加密
        oldPwd = CommonUtils.EncodeByMD5(oldPwd);
        if (!StringUtils.equals(oldPwd,userPwd)){
            return R.setResult(ResultCodeEnum.ERROR_PASSWORD);
        }
        return R.ok();
    }

    /**
     * 获取用户密码
     * @return
     */
    @RequestMapping(value = "/toPassword")
    public String toPassword(HttpServletRequest request,
                             Model model)  {
        // 获取session中用户信息
        UserInfo sessionUser = (UserInfo) request.getSession().getAttribute(Contents.SESSION_USER);

        // 判断用户是否为管理员用户
        if (StringUtils.equals(sessionUser.getAdminFlag(),Contents.ADMIN_FLAG_0)) {
            // 查询所有用户信息
            List<UserInfo> userInfoList = userInfoServcie.selectUserList();
            model.addAttribute("upUserList",userInfoList);
        }
        return "password";
    }

    /**
     * 修改用户密码
     * @param request
     * @param updPwd
     * @param updConfirmPwd
     * @return
     */
    @RequestMapping(value = "/updateuserpwd",method = RequestMethod.POST)
    @Transactional
    public String updateUserPwd(HttpServletRequest request,
                           Model model,
                           @RequestParam(value = "uuId",required = false)String uuId,
                           @RequestParam(value = "oldPwd",required = false)String oldPwd,
                           @RequestParam(value = "updPwd",required = true)String updPwd,
                           @RequestParam(value = "updConfirmPwd",required = true)String updConfirmPwd) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        // 判断两次密码是否一致
        if (!StringUtils.equals(updPwd,updConfirmPwd)) {
            model.addAttribute("msg","请保证确认密码和新密码一致");
            return "password";
        }
        UserPassword userPassword = new UserPassword();
        // 获取session中user信息
        UserInfo sessionUser = (UserInfo) request.getSession().getAttribute(Contents.SESSION_USER);
        if (null == sessionUser) {
            model.addAttribute("msg","用户未登录，请返回登录页面登录后再进行修改");
            return "password";
        }
        // 当uuid 不为空时
        if (StringUtils.isNotEmpty(uuId)) {
            // 判断user是否为管理员账户
            if (!StringUtils.equals(sessionUser.getAdminFlag(),Contents.ADMIN_FLAG_0)) {
                model.addAttribute("msg","权限不足");
                return "password";
            }
            // 当用户为管理员时
            // 设置页面传入的uuid
            userPassword.setUuId(uuId);
        } else {
            // 当未传入uuId时设置登录用户的uuid
            userPassword.setUuId(sessionUser.getUuId());
            // 获取用户密码
            String userPwd = userInfoServcie.selectUserPwd(userPassword.getUuId());
            // 判断用户密码是否与输入的密码一致
            if (!StringUtils.equals(CommonUtils.EncodeByMD5(oldPwd),userPwd)){
                model.addAttribute("msg","密码不正确请重新输入");
                return "password";
            }
        }

        // 设置usePasswork的信息
        userPassword.setPwd(CommonUtils.EncodeByMD5(updPwd));
        userPassword.setUpdateTime(new Date());
        userPassword.setUpdateUser(sessionUser.getUuId());

        int updCount = userPasswordService.updateUserPwdByuuId(userPassword);

        if (updCount < 0) {
            model.addAttribute("msg","密码更新失败");
        }
        // 判断用户是否为管理员用户
        if (StringUtils.equals(sessionUser.getAdminFlag(),Contents.ADMIN_FLAG_0)) {
            // 查询所有用户信息
            List<UserInfo> userInfoList = userInfoServcie.selectUserList();
            model.addAttribute("upUserList",userInfoList);
        }

        model.addAttribute("msg","密码更新成功");
        return "password";
    }

    /**
     * 根据用户ID获取用户信息
     * @param request
     * @param model
     * @param uuId 用户ID
     * @param view 返回页面名称
     * @return
     */
    @RequestMapping("getUserbyId")
    public String getUserByUUID(HttpServletRequest request,
                                Model model,
                                @RequestParam(value = "uuId",required = true)String uuId,
                                @RequestParam(value = "view",required = true)String view){

        // 查询用户
        UserInfo userInfo = userInfoServcie.selectByPrimaryKey(uuId);

        // 将用户信息保存在Model
        model.addAttribute("user",userInfo);

        return view;
    }


    /**
     * 获取部门用户总数量（dept可为空）
     * @return
     */
    @RequestMapping(value = "/getusercount",method = RequestMethod.POST)
    @ResponseBody
    public R getUserCount(@RequestParam(value = "dept",required = false)String dept){

        int userCount = userInfoServcie.getUserCount(dept);

        return R.ok().data("userCount",userCount);
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
