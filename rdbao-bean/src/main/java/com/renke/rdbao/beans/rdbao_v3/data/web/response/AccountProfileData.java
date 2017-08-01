package com.renke.rdbao.beans.rdbao_v3.data.web.response;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.data.response.base.BaseResponseData;

/**
 * 账户概览数据
 * 
 * @author jgshun
 * @date 2016-12-29 下午1:28:59
 * @version 1.0
 */
public class AccountProfileData extends BaseResponseData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1972623318305517898L;

	private int countCalling; // 呼出电话个数
	private int countCalled; // 呼入电话个数
	private int countCallingTime; // 呼出电话时长
	private int countCalledTime; // 呼入电话时长

	private String accountName; // 公司名称
	private String accountOpenTime; // 开通时间
	private StatusEnum4User accountStatus; // 账号状态
	private String accountUsername; // 姓名
	private String accountPhoneNo; // 手机号
	private String accountCredentialsNo; // 身份证号
	private int openCount; // 开通号码数量

	private List<OpenPhoneNoData> openPhoneNoDatas; // 已开通号码 集合

	public static class OpenPhoneNoData extends BaseResponseData {
		/**
		 * 
		 */
		private static final long serialVersionUID = 185347671919864265L;
		/**
		 * 姓名
		 */
		private String name;
		/**
		 * 电话号码
		 */
		private String phoneNo;
		/**
		 * 开通时间
		 */
		private Date openDate;
		/**
		 * 当前状态
		 */
		private StatusEnum4User status;

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the phoneNo
		 */
		public String getPhoneNo() {
			return phoneNo;
		}

		/**
		 * @param phoneNo
		 *            the phoneNo to set
		 */
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		/**
		 * @return the openDate
		 */
		public Date getOpenDate() {
			return openDate;
		}

		/**
		 * @param openDate
		 *            the openDate to set
		 */
		public void setOpenDate(Date openDate) {
			this.openDate = openDate;
		}

		/**
		 * @return the status
		 */
		public StatusEnum4User getStatus() {
			return status;
		}

		/**
		 * @param status
		 *            the status to set
		 */
		public void setStatus(StatusEnum4User status) {
			this.status = status;
		}

	}

	/**
	 * @return the countCalling
	 */
	public int getCountCalling() {
		return countCalling;
	}

	/**
	 * @param countCalling
	 *            the countCalling to set
	 */
	public void setCountCalling(int countCalling) {
		this.countCalling = countCalling;
	}

	/**
	 * @return the countCalled
	 */
	public int getCountCalled() {
		return countCalled;
	}

	/**
	 * @param countCalled
	 *            the countCalled to set
	 */
	public void setCountCalled(int countCalled) {
		this.countCalled = countCalled;
	}

	/**
	 * @return the countCallingTime
	 */
	public int getCountCallingTime() {
		return countCallingTime;
	}

	/**
	 * @param countCallingTime
	 *            the countCallingTime to set
	 */
	public void setCountCallingTime(int countCallingTime) {
		this.countCallingTime = countCallingTime;
	}

	/**
	 * @return the countCalledTime
	 */
	public int getCountCalledTime() {
		return countCalledTime;
	}

	/**
	 * @param countCalledTime
	 *            the countCalledTime to set
	 */
	public void setCountCalledTime(int countCalledTime) {
		this.countCalledTime = countCalledTime;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the accountOpenTime
	 */
	public String getAccountOpenTime() {
		return accountOpenTime;
	}

	/**
	 * @param accountOpenTime
	 *            the accountOpenTime to set
	 */
	public void setAccountOpenTime(String accountOpenTime) {
		this.accountOpenTime = accountOpenTime;
	}

	/**
	 * @return the accountStatus
	 */

	/**
	 * @return the accountUsername
	 */
	public String getAccountUsername() {
		return accountUsername;
	}

	public StatusEnum4User getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(StatusEnum4User accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * @param accountUsername
	 *            the accountUsername to set
	 */
	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	/**
	 * @return the accountPhoneNo
	 */
	public String getAccountPhoneNo() {
		return accountPhoneNo;
	}

	/**
	 * @param accountPhoneNo
	 *            the accountPhoneNo to set
	 */
	public void setAccountPhoneNo(String accountPhoneNo) {
		this.accountPhoneNo = accountPhoneNo;
	}

	/**
	 * @return the accountCredentialsNo
	 */
	public String getAccountCredentialsNo() {
		return accountCredentialsNo;
	}

	/**
	 * @param accountCredentialsNo
	 *            the accountCredentialsNo to set
	 */
	public void setAccountCredentialsNo(String accountCredentialsNo) {
		this.accountCredentialsNo = accountCredentialsNo;
	}

	/**
	 * @return the openPhoneNoDatas
	 */
	public List<OpenPhoneNoData> getOpenPhoneNoDatas() {
		return openPhoneNoDatas;
	}

	/**
	 * @param openPhoneNoDatas
	 *            the openPhoneNoDatas to set
	 */
	public void setOpenPhoneNoDatas(List<OpenPhoneNoData> openPhoneNoDatas) {
		this.openPhoneNoDatas = openPhoneNoDatas;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getOpenCount() {
		return openCount;
	}

	public void setOpenCount(int openCount) {
		this.openCount = openCount;
	}

}
