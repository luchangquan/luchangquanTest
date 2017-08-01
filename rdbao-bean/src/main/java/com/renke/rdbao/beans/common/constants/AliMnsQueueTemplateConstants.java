package com.renke.rdbao.beans.common.constants;

/**
 * 阿里云消息服务 命名模板
 * 
 * @author jgshun
 * @date 2017-2-27 上午10:54:51
 * @version 1.0
 */
public class AliMnsQueueTemplateConstants {
	/**
	 * 替换地点的变量
	 */
	public static final String REPLACE_LOCALE = "#locale#";

	/**
	 * APP视频证据队列 替换里面的“#local#”成具体的地点
	 */
	public static final String RDBAO_EVIDENCE_APP_VIDEO = "rdbao-#locale#-evidence-app-video";

	/**
	 * APP音频证据队列 替换里面的“#local#”成具体的地点
	 */
	public static final String RDBAO_EVIDENCE_APP_AUDIO = "rdbao-#locale#-evidence-app-audio";
	/**
	 * APP图片证据队列 替换里面的“#local#”成具体的地点
	 */
	public static final String RDBAO_EVIDENCE_APP_PICTURE = "rdbao-#locale#-evidence-app-picture";
	/**
	 * 公证录音证据队列 替换里面的“#local#”成具体的地点
	 */
	public static final String RDBAO_EVIDENCE_VOICE = "rdbao-#locale#-evidence-voice";

	/**
	 * 公证录音转发证据队列--江苏智恒
	 */
	public static final String RDBAO_EVIDENCE_VOICE_RECEIVED_REDIRECT_4_JSZH_SUFFIX = "-received-redirect-4-jszh-suffix";

	/**
	 * 公证录音已签名证据队列
	 */
	public static final String RDBAO_EVIDENCE_VOICE_NOTICE_SIGN = "rdbao-evidence-voice-notice-sign";

	/**
	 * APP视频已签名证据队列
	 */
	public static final String RDBAO_EVIDENCE_APP_VIDEO_NOTICE_SIGN = "rdbao-evidence-app-video-notice-sign";

	/**
	 * APP音频已签名证据队列
	 */
	public static final String RDBAO_EVIDENCE_APP_AUDIO_NOTICE_SIGN = "rdbao-evidence-app-audio-notice-sign";

	/**
	 * APP图片已签名证据队列
	 */
	public static final String RDBAO_EVIDENCE_APP_PICTURE_NOTICE_SIGN = "rdbao-evidence-app-picture-notice-sign";

	/**
	 * STS签名失败待恢复队列
	 */
	public static final String RDBAO_EVIDENCE_NOTICE_STS_PENDING_RECOVERY = "rdbao-evidence-notice-sts-pending-recovery";

	/**
	 * APP视频上传成功后的补充信息队列
	 */
	public static final String RDBAO_EVIDENCE_APP_VIDEO_NOTICE_CALLBACK_INFO = "rdbao-evidence-app-video-notice-callback-info";

	/**
	 * APP音频上传成功后的补充信息队列
	 */
	public static final String RDBAO_EVIDENCE_APP_AUDIO_NOTICE_CALLBACK_INFO = "rdbao-evidence-app-audio-notice-callback-info";

	/**
	 * APP图片上传成功后的补充信息队列
	 */
	public static final String RDBAO_EVIDENCE_APP_PICTURE_NOTICE_CALLBACK_INFO = "rdbao-evidence-app-picture-notice-callback-info";

}
