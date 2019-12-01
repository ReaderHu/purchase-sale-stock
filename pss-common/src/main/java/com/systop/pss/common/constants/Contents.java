package com.systop.pss.common.constants;

public class Contents {

	/**
	 *  钢排钉编号
	 */
	public static final Integer PRODUCT_STEEL_NAILS = 1;
	
	/**
	 * 卷钉编号
	 */
	public static final Integer PRODUCT_ROLL_NAILS = 2;

	/**
	 *  手机号的正则表达式
	 *  //身份证正则表达式(15位)
	 * isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	 * //身份证正则表达式(18位)
	 * isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	 * 身份证正则合并：(^\d{15}$)|(^\d{17}([0-9]|X)$)
	 *
	 *  "^1[1-9]\\d{9}$" ;
	 */
	public static final String TEL_PHONE_EXP = "^1(3|4|5|7|8)\\d{9}$" ;

	/**
	 * 数字_0
	 */
    public static final int INT_0 = 0;

	/**
	 * 时间格式YYYYMMDDHHSSMM
	 */
	public static final String DATE_PATTERN_YYYYMMDDHHSSMM = "YYYYMMDDHHSSMM";

	/**
	 * session用户
	 */
    public static final String SESSION_USER = "user";
}
