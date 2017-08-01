package com.renke.rdbao.beans.rdbao_v3.vo;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.vo.BaseVo;

/**
 * 语音报表统计数据
 * 
 * @author jgshun
 * @date 2016-12-29 下午4:43:33
 * @version 1.0
 */
public class VoiceStatisticsVo extends BaseVo {

	private String countName;// 统计名称
	private int countCalled;// 呼入电话数量
	private int countCalling;// 呼出电话数量
	private int countCalledTime;// 呼入电话时长 秒
	private int countCallingTime;// 呼出电话时长 秒
	private List<String> curPhoneNos;// 当前被保全的号码列表
	private Date countStartTime;// 统计开始时间
	private Date countEndTime;// 统计结束时间
	private Date createTime;// 报表创建时间

	/**
	 * @return the countName
	 */
	public String getCountName() {
		return countName;
	}

	/**
	 * @param countName
	 *            the countName to set
	 */
	public void setCountName(String countName) {
		this.countName = countName;
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
	 * @return the curPhoneNos
	 */
	public List<String> getCurPhoneNos() {
		return curPhoneNos;
	}

	/**
	 * @param curPhoneNos
	 *            the curPhoneNos to set
	 */
	public void setCurPhoneNos(List<String> curPhoneNos) {
		this.curPhoneNos = curPhoneNos;
	}

	/**
	 * @return the countStartTime
	 */
	public Date getCountStartTime() {
		return countStartTime;
	}

	/**
	 * @param countStartTime
	 *            the countStartTime to set
	 */
	public void setCountStartTime(Date countStartTime) {
		this.countStartTime = countStartTime;
	}

	/**
	 * @return the countEndTime
	 */
	public Date getCountEndTime() {
		return countEndTime;
	}

	/**
	 * @param countEndTime
	 *            the countEndTime to set
	 */
	public void setCountEndTime(Date countEndTime) {
		this.countEndTime = countEndTime;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
