package com.rdbao.bean;

import java.io.Serializable;

public class SSOUser implements Serializable {
	private static final long serialVersionUID = 1L;
	// /Users的id
	private String Id;
	// / 企业简称，或企业代码
	private String Company;
	// / 企业名称
	private String CompanyName;
	// / 别名
	private String Alias;
	// / 用户身份标识
	private String Identity;
	// / 用户姓名
	private String UserName;
	// 昵称
	private String Nickname;
	// / 最后一次活动时间
	private String LastActiveTime;
	private String Email;
	private int UserType;
	// /用户权限
	private int Permission;

	private String Mobile;// 手机
	private String CompanyId;// 公司ID
	private String Token;
	private String CardNo;
	private Integer RealValidate;

	private String DefaultPnoesId;// 默认公证处Id

	private String loginAppCode;// 登陆AppCode

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getAlias() {
		return Alias;
	}

	public void setAlias(String alias) {
		Alias = alias;
	}

	public String getIdentity() {
		return Identity;
	}

	public void setIdentity(String identity) {
		Identity = identity;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getLastActiveTime() {
		return LastActiveTime;
	}

	public void setLastActiveTime(String lastActiveTime) {
		LastActiveTime = lastActiveTime;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getUserType() {
		return UserType;
	}

	public void setUserType(int userType) {
		UserType = userType;
	}

	public int getPermission() {
		return Permission;
	}

	public void setPermission(int permission) {
		Permission = permission;
	}

	public void setMobile(String mobile) {
		this.Mobile = mobile;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setCompanyId(String companyId) {
		this.CompanyId = companyId;
	}

	public String getCompanyId() {
		return CompanyId;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public Integer getRealValidate() {
		return RealValidate;
	}

	public void setRealValidate(Integer realValidate) {
		RealValidate = realValidate;
	}

	public String getDefaultPnoesId() {
		return DefaultPnoesId;
	}

	public void setDefaultPnoesId(String defaultPnoesId) {
		DefaultPnoesId = defaultPnoesId;
	}

	public String getLoginAppCode() {
		return loginAppCode;
	}

	public void setLoginAppCode(String loginAppCode) {
		this.loginAppCode = loginAppCode;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

}