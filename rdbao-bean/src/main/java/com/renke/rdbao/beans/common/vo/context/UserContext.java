package com.renke.rdbao.beans.common.vo.context;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.GenderEnum;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTableEnum;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedARole;
import com.renke.rdbao.beans.rdbao_v3.pojo.User189;
import com.renke.rdbao.beans.rdbao_v3.pojo.Users;

/**
 * 用户上下文
 * 
 * @author jgshun
 * 
 */
public class UserContext implements Serializable {
	/**
	 * 当前登录用户令牌
	 */
	private String accessToken;
	/**
	 * 当前登录用户
	 */
	// private User189 user189;

	/**
	 * 当前账号下所管理的用户
	 */
	// private List<User189> containUser189s;

	/**
	 * 当前登录用户id
	 */
	private String userId;

	/**
	 * 当前账号下所管理的用户id
	 */
	private List<String> containUserIds;

	private UserVo user;

	private UserTableEnum userTable;
	private TenantEnum tenant;// 租户
	private String sourceNppCode;// 用户登录来源

	private List<EnhancedARole> enhancedARoles;// 当前用户角色
	private List<EnhancedAOrganization> enhancedAOrganizations;// 当前组织

	// public List<User189> getContainUser189s() {
	// return containUser189s;
	// }
	//
	// public void setContainUser189s(List<User189> containUser189s) {
	// this.containUser189s = containUser189s;
	// if (containUser189s != null && containUser189s.size() > 0) {
	// this.containUserIds = new ArrayList<String>();
	// for (User189 _User189 : containUser189s) {
	// this.containUserIds.add(_User189.getId());
	// }
	// }
	// }

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	// public User189 getUser189() {
	// return user189;
	// }
	//
	// public void setUser189(User189 user189) {
	// this.user189 = user189;
	// if (user189 != null) {
	// this.userId = this.user189.getId();
	// }
	// }

	public List<String> getContainUserIds() {
		return containUserIds;
	}

	public void setContainUserIds(List<String> containUserIds) {
		this.containUserIds = containUserIds;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(Users users) {
		this.user = new UserVo();
		user.setId(users.getId());
		user.setAccount(users.getAccount());
		user.setPhoneNo(users.getMobile());
		user.setName(users.getName());
		user.setEmail(users.getEmail());
		user.setPassword(users.getPassword());
		user.setCredentialsType(users.getCredentialsType());
		user.setCredentialsNo(users.getCredentialsNumber());
		user.setType(users.getUserType());
		user.setCompanyId(users.getCompanyId());
		String genderStr = users.getGender() == null ? "" : users.getGender();
		short gender = genderStr.equals("") ? GenderEnum.UNKNOWN.getCode()
				: (genderStr.equals("男") ? GenderEnum.MALE.getCode() : (genderStr.equals("女") ? GenderEnum.FEMALE.getCode() : GenderEnum.UNKNOWN.getCode()));
		user.setGender(gender);
		user.setStatus(users.getState());
		user.setDisabled(users.getDisabled());
		user.setCreateTime(users.getCreateTime());
		user.setUpdateTime(users.getLastUpdateTime());
		user.setCredentialsPath(users.getCredentialsPath());
		user.setDefaultPnoesId(users.getDefaultPnoesId());
		user.setOpenSource(users.getOpenSource());
		user.setNickname(users.getNickname());
		user.setPhoneNoStatus(users.getMobileActive());
		user.setEmailStatus(users.getEmailActive());
		user.setTenant(TenantEnum.TENANT_1010BAO);

		this.userTable = UserTableEnum.USERS;
		this.userId = users.getId();
	}

	// 老189对象
	public void setUser(User189 user189) {
		this.user = new UserVo();
		BeanUtils.copyProperties(user189, this.user);
		this.userTable = UserTableEnum.E_189_USER;
		this.userId = user189.getId();
	}

	// 新189对象
	public void setUser(EUser189 eUser189) {
		this.user = new UserVo();
		BeanUtils.copyProperties(eUser189, this.user);
		this.userTable = UserTableEnum.E_189_USER;
		user.setTenant(TenantEnum.TENANT_189);
		this.tenant = TenantEnum.TENANT_189;
		this.userId = eUser189.getId();
	}

	public void setUser(UserVo userVo) {
		this.user = userVo;
	}

	public UserTableEnum getUserTable() {
		return userTable;
	}

	public void setUserTable(UserTableEnum userTable) {
		this.userTable = userTable;
	}

	public void setUser(EUser euser) {
		this.user = new UserVo();
		BeanUtils.copyProperties(euser, this.user);
		user.setDefaultPnoesId(euser.getNppId());
		this.setTenant(TenantEnum.TENANT_1010BAO);
		this.userId = euser.getId();
	}

	public TenantEnum getTenant() {
		return tenant;
	}

	public void setTenant(TenantEnum tenant) {
		this.tenant = tenant;
	}

	public String getSourceNppCode() {
		return sourceNppCode;
	}

	public void setSourceNppCode(String sourceNppCode) {
		this.sourceNppCode = sourceNppCode;
	}

	public List<EnhancedARole> getEnhancedARoles() {
		return enhancedARoles;
	}

	public void setEnhancedARoles(List<EnhancedARole> enhancedARoles) {
		this.enhancedARoles = enhancedARoles;
	}

	public List<EnhancedAOrganization> getEnhancedAOrganizations() {
		return enhancedAOrganizations;
	}

	public void setEnhancedAOrganizations(List<EnhancedAOrganization> enhancedAOrganizations) {
		this.enhancedAOrganizations = enhancedAOrganizations;
	}

}
