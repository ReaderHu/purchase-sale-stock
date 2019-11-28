package com.systop.pss.service.impl;

import com.systop.pss.controller.vo.UserVo;
import com.systop.pss.mapper.UserInfoMapper;
import com.systop.pss.mapper.dto.SelectUserAndPwdDto;
import com.systop.pss.model.UserInfo;
import com.systop.pss.service.UserInfoServcie;
import com.systop.pss.service.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userInfoServiceImpl")
public class UserInfoServiceImpl implements UserInfoServcie {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(String uuId) {
        return userInfoMapper.deleteByPrimaryKey(uuId);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(String uuId) {
        return userInfoMapper.selectByPrimaryKey(uuId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<UserInfo> selectUserList() {
        return userInfoMapper.selectUserList();
    }

    /**
     * 根据用户手机号查询用户
     * @param userDto
     */
    @Override
    public UserDto login(UserDto userDto) {
        SelectUserAndPwdDto selectUserAndPwdDto = new SelectUserAndPwdDto();
        // 判端UserDto是否为null
        if (null != userDto){
            BeanUtils.copyProperties(userDto,selectUserAndPwdDto);
            // 根据TelPhone和pwd查询用户信息
            UserInfo userInfo = userInfoMapper.selectUserByTelPhoneAndPwd(selectUserAndPwdDto);
            // 查询结果不为空，进行对象Copy
            if (null != userInfo) {
                UserDto resultUserDto = new UserDto();
                BeanUtils.copyProperties(userInfo,resultUserDto);
                return resultUserDto;
            }
        }
        return null;
    }
}
