package com.systop.pss.database.mapper;


import com.systop.pss.database.model.UserPassword;

public interface UserPasswordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    int deleteByPrimaryKey(Integer pwdId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    int insert(UserPassword record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    int insertSelective(UserPassword record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    UserPassword selectByPrimaryKey(Integer pwdId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    int updateByPrimaryKeySelective(UserPassword record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbggenerated Thu Nov 28 20:54:20 CST 2019
     */
    int updateByPrimaryKey(UserPassword record);
}