package com.systop.pss.service.impl;

import com.systop.pss.mapper.UserInfoMapper;
import com.systop.pss.mapper.UserPasswordMapper;
import com.systop.pss.model.UserInfo;
import com.systop.pss.model.UserPassword;
import com.systop.pss.service.UserInfoServcie;
import com.systop.pss.service.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("userInfoServiceImpl")
public class UserInfoServiceImpl implements UserInfoServcie {
    /**
     * RedisTemplate 这个bean是springboot自动配置好的，可以直接使用
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserPasswordMapper userPasswordMapper;

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
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        List<UserInfo> userInfoList = (List<UserInfo>)redisTemplate.opsForValue().get("userList");

        if (null == userInfoList) {
            userInfoList = userInfoMapper.selectUserList();
            redisTemplate.opsForValue().set("userList",userInfoList ,15, TimeUnit.MINUTES);
            System.out.println("数据库查询");
        } else {
            System.out.println("查询redis缓存");
        }
        return userInfoList;
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

    /**
     * 注册用户
     * @param userDto
     * @return
     */
    @Override
    @Transactional
    public int register(UserDto userDto) {
        int result = 0;
        UserInfo userInfo = new UserInfo();
        // 对UserInfo进行copy
        BeanUtils.copyProperties(userDto,userInfo);

        // 保存userInfo
        result = this.insertSelective(userInfo);
        if(result > 0) {
            // 查询User
            UserInfo registerUser = this.selectUserByTelPhone(userInfo.getTelPhone());

            if (null == registerUser) {
                return -1;
            }

            // 对密码进行保存
            UserPassword userPassword = new UserPassword();
            BeanUtils.copyProperties(userDto,userPassword);
            userPassword.setUuId(registerUser.getUuId());
            return userPasswordMapper.insertSelective(userPassword);
        }

        return result;
    }

    /**
     * 获取用户密码
     * @param uuId
     * @return
     */
    @Override
    public String selectUserPwd(String uuId) {
        return userPasswordMapper.selectPwdByUuId(uuId);
    }

    /**
     * 获取部门用户总数量（dept可为空）
     * @return
     */
    @Override
    public int getUserCount(String dept) {

        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("dept",dept);
        return userInfoMapper.getUserCount(selectMap);
    }

    @Override
    public int deleteByPrimaryKeyByDelFlag(String uuId) {


        return userInfoMapper.deleteByprimaryKeyByDelFlag(uuId);
    }
}
