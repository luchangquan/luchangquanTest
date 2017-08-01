package com.renke.rdbao.beans.rdbao_sms_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.SmsTypeEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_sms_2017.enums.fordsmstemplate.StatusEnum4DSmsTemplate;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSmsTemplate;

public class EnhancedDSmsTemplate extends BaseEnhanced {
	public EnhancedDSmsTemplate() {
	}

	public EnhancedDSmsTemplate(DSmsTemplate smsTemplate) {
		BeanUtils.copyProperties(smsTemplate, this);
		if (smsTemplate.getType() != null) {
			this.type = SmsTypeEnum.getSmsTypeEnumByCode(smsTemplate.getType());
		}
		if (smsTemplate.getStatus() != null) {
			this.status = StatusEnum4DSmsTemplate.getStatusEnumByCode(smsTemplate.getStatus());
		}
	}

	private String id;
	private SmsTypeEnum type;
	private String name;
	private String content;
	private Date createTime;
	private Date updateTime;
	private String code;
	private StatusEnum4DSmsTemplate status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SmsTypeEnum getType() {
		return type;
	}

	public void setType(SmsTypeEnum type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public StatusEnum4DSmsTemplate getStatus() {
		return status;
	}

	public void setStatus(StatusEnum4DSmsTemplate status) {
		this.status = status;
	}

}
