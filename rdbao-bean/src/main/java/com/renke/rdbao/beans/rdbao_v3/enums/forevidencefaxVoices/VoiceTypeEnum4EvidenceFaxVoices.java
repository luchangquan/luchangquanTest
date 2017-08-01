package com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices;

/**
 * 呼叫类型
 * 
 * @author jgshun
 * @date 2016-12-30 下午1:30:36
 * @version 1.0
 */
public enum VoiceTypeEnum4EvidenceFaxVoices {
	/**
	 * (short) 1, "语音"
	 */
	VOICE((short) 1, "语音"),
	/**
	 * (short) 2, "传真"
	 */
	FAX((short) 2, "传真");

	/**
	 * 编码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	private VoiceTypeEnum4EvidenceFaxVoices(short code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * @return the code
	 */
	public short getCode() {
		return code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	public static VoiceTypeEnum4EvidenceFaxVoices getVoiceTypeEnumByCode(short code) {
		for (VoiceTypeEnum4EvidenceFaxVoices _Enum : VoiceTypeEnum4EvidenceFaxVoices.values()) {
			if (_Enum.getCode() == code) {
				return _Enum;
			}
		}
		return null;
	}
}
