package com.systop.pss.common.constants;

public enum ResultCodeEnum {

	SUCCESS_REGISTER(true,10000,"用户注册成功"),
	SUCCESS_UPDATE_PWD(true,1001,"密码更新成功"),
	ERROR_REGISTER(false,40001,"用户注册失败"),
	ERROR_LOGIN(false,40002,"用户或者密码不正确，请重新输入"),
	ERROR_TELPHONE(false,40003,"手机号格式不正确，请输入正确的手机号"),
	ERROR_PASSWORD(false,40004,"确认密码不正确，请重新输入"),
	ERROR_POWER(false,40005,"权限不足，请登录管理员账户"),
	ERROR_NOT_LOGIN(false,40006,"用户未登录，请登录后重试"),

	SUCCESS(true, 20000, "成功"),
	UNKNOWN_REASON(false, 20001, "未知错误"),
	BAD_SQL_GRAMMAR(false, 21001, "sql语法错误"),
	JSON_PARSE_ERROR(false, 21002, "json解析异常"), 
	PARAM_ERROR(false, 21003, "参数不正确"),
	FILE_UPLOAD_ERROR(false, 21004, "文件上传错误"), 
	EXCEL_DATA_IMPORT_ERROR(false, 21005, "Excel数据导入错误"),

	VIDEO_UPLOAD_ERROR(false, 22001, "阿里云视频上传错误"), 
	VIDEO_DELETE_ALIYUN_ERROR(false, 22002, "阿里云视频删除错误"),
	FETCH_VIDEO_UPLOADAUTH_ERROR(false, 22003, "阿里云获取上传凭证错误"),
	REFRESH_VIDEO_UPLOADAUTH_ERROR(false, 22004, "阿里云刷新传凭证错误"), 
	FETCH_VIDEO_PLAYAUTH_ERROR(false, 22005, "阿里云播放凭证获取错误"),

	URL_ENCODE_ERROR(false, 23001, "URL转码错误"), 
	ILLEGAL_CALLBACK_REQUEST_ERROR(false, 23002, "非法回调参数错误"),
	FETCH_ACCESS_TOKEN_ERROR(false, 23003, "获取授权码错误"), 
	FETCH_USER_INFO_ERROR(false, 23004, "获取微信用户信息错误");

	private Boolean success;// 响应是否成功
	private Integer code;// 返回码
	private String message;// 返回消息

	ResultCodeEnum(Boolean success, Integer code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
