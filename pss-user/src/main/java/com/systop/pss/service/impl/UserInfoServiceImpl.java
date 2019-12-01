package com.systop.pss.service.impl;

import com.systop.pss.mapper.UserInfoMapper;
import com.systop.pss.model.UserInfo;
import com.systop.pss.service.UserInfoServcie;
import com.systop.pss.service.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 用户登录鉴证
     * @param userDto
     */
    @Override
    public UserInfo login(UserDto userDto) {
        // 判端UserDto是否为null
        if (null != userDto){
            // 根据TelPhone和pwd查询用户信息
            Map<String,Object> selectMap = new HashMap<>();

            selectMap.put("telPhone",userDto.getTelPhone());
            selectMap.put("pwd",userDto.getPwd());

            UserInfo userInfo = userInfoMapper.selectUserByTelPhoneAndPwd(selectMap);
            // 查询结果不为空，进行对象Copy
            return userInfo;
        }
        return null;
    }

    /**
     * 根据手机查询用户信息
     * @return
     */
    @Override
    public UserInfo selectUserByTelPhone(String telPhone) {
        // 根据TelPhone查询用户信息
        Map<String,Object> selectMap = new HashMap<>();

        selectMap.put("telPhone",telPhone);

        UserInfo userInfo = userInfoMapper.selectUserByTelPhoneAndPwd(selectMap);

        return userInfo;
    }
}
