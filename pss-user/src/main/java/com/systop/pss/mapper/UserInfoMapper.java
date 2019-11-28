package com.systop.pss.mapper;

import com.systop.pss.controller.vo.UserVo;
import com.systop.pss.mapper.dto.SelectUserAndPwdDto;
import com.systop.pss.model.UserInfo;
import com.systop.pss.service.dto.UserDto;

import java.util.List;

public interface UserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    int deleteByPrimaryKey(String uuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    int insert(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    int insertSelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    UserInfo selectByPrimaryKey(String uuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    int updateByPrimaryKey(UserInfo record);

    /**
     * 查询所有用户信息
     * @return
     */
    List<UserInfo> selectUserList();

    /**
     * 根据用户手机号查询用户
     * @param selectUserAndPwdDto
     * @return
     */
    UserInfo selectUserByTelPhoneAndPwd(SelectUserAndPwdDto selectUserAndPwdDto);
}