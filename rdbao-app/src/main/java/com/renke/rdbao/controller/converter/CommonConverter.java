package com.renke.rdbao.controller.converter;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.data.response.base.BaseResponseData;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.DNppResponse;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.ECompanyResponse;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.ENotarizationReserveResponse;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.EUserResponse;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.MEvidenceAppPictureResponse;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.MEvidenceAppVideoResponse;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.MEvidenceAppVoiceResponse;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.MEvidenceRemotePcResponse;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.MEvidenceResponse;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.MEvidenceTelecomVoiceResponse;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.MREvidenceFileResponse;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedECompany;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser189;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceAppPicture;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceAppVideo;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceAppVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceRemotePc;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-3-24 下午4:27:40
 * @version 1.0
 */
public abstract class CommonConverter {

	public static EUserResponse convert2UserResponse(EnhancedEUser enhancedEUser) {
		EUserResponse eUserResponse = new EUserResponse();
		BeanUtils.copyProperties(enhancedEUser, eUserResponse);
		if (enhancedEUser.getCredentialsType() != null) {
			eUserResponse.setCredentialsType(enhancedEUser.getCredentialsType().getCode());
		}

		if (enhancedEUser.getType() != null) {
			eUserResponse.setType(enhancedEUser.getType().getCode());
		}
		if (enhancedEUser.getGender() != null) {
			eUserResponse.setGender(enhancedEUser.getGender().getCode());
		}

		if (enhancedEUser.getEnhancedDNpp() != null) {
			eUserResponse.setDefaultNpp(convert2DNppResponse(enhancedEUser.getEnhancedDNpp()));
		}
		if (enhancedEUser.getOpenSource() != null) {
			eUserResponse.setOpenSource(enhancedEUser.getOpenSource().getCode());
		}

		if (enhancedEUser.getPhoneNoStatus() != null) {
			eUserResponse.setPhoneNoStatus(enhancedEUser.getPhoneNoStatus().getCode());
		}
		if (enhancedEUser.getEmailStatus() != null) {
			eUserResponse.setEmailStatus(enhancedEUser.getEmailStatus().getCode());
		}

		if (enhancedEUser.getEnhancedECompany() != null) {
			eUserResponse.setCompany(convert2CompanyResponse(enhancedEUser.getEnhancedECompany()));
		}

		return eUserResponse;
	}

	public static EUserResponse convert2UserResponse(EnhancedEUser189 enhancedEUser189) {
		EUserResponse eUserResponse = new EUserResponse();
		BeanUtils.copyProperties(enhancedEUser189, eUserResponse);
		if (enhancedEUser189.getCredentialsType() != null) {
			eUserResponse.setCredentialsType(enhancedEUser189.getCredentialsType().getCode());
		}

		if (enhancedEUser189.getType() != null) {
			eUserResponse.setType(enhancedEUser189.getType().getCode());
		}
		if (enhancedEUser189.getGender() != null) {
			eUserResponse.setGender(enhancedEUser189.getGender().getCode());
		}

		// if (enhancedEUser189.getEnhancedDNpp() != null) {
		// eUserResponse.setDefaultNpp(convert2DNppResponse(enhancedEUser189.getEnhancedDNpp()));
		// }
		if (enhancedEUser189.getOpenSource() != null) {
			eUserResponse.setOpenSource(enhancedEUser189.getOpenSource().getCode());
		}

		if (enhancedEUser189.getPhoneNoStatus() != null) {
			eUserResponse.setPhoneNoStatus(enhancedEUser189.getPhoneNoStatus().getCode());
		}
		if (enhancedEUser189.getEmailStatus() != null) {
			eUserResponse.setEmailStatus(enhancedEUser189.getEmailStatus().getCode());
		}

		if (enhancedEUser189.getEnhancedECompany() != null) {
			eUserResponse.setCompany(convert2CompanyResponse(enhancedEUser189.getEnhancedECompany()));
		}

		return eUserResponse;
	}

	public static ECompanyResponse convert2CompanyResponse(EnhancedECompany enhancedECompany) {
		ECompanyResponse eCompanyResponse = new ECompanyResponse();
		BeanUtils.copyProperties(enhancedECompany, eCompanyResponse);
		return eCompanyResponse;
	}

	public static List<MEvidenceResponse> convert2MEvidenceResponses(List<EnhancedMEvidence> enhancedMEvidences) {
		List<MEvidenceResponse> evidenceResponses = Lists.newArrayList();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			evidenceResponses.add(convert2MEvidenceResponse(_EnhancedMEvidence));
		}
		return evidenceResponses;
	}

	public static MEvidenceResponse convert2MEvidenceResponse(EnhancedMEvidence enhancedMEvidence) {
		MEvidenceResponse evidenceResponse = new MEvidenceResponse();
		BeanUtils.copyProperties(enhancedMEvidence, evidenceResponse);

		if (enhancedMEvidence.getStatus() != null) {
			evidenceResponse.setStatusCode(enhancedMEvidence.getStatus().getCode());
		}
		if (enhancedMEvidence.getUploadStatus() != null) {
			evidenceResponse.setUploadStatusCode(enhancedMEvidence.getUploadStatus().getCode());
		}
		if (enhancedMEvidence.getEnhancedDNpp() != null) {
			evidenceResponse.setNpp(convert2DNppResponse(enhancedMEvidence.getEnhancedDNpp()));
		}

		if (Detect.notEmpty(enhancedMEvidence.getEnhancedMREvidenceFiles())) {
			evidenceResponse.setEvidenceFileList(convert2MREvidenceFileResponses(enhancedMEvidence.getEnhancedMREvidenceFiles()));
		}

		if (enhancedMEvidence.getCategory() != null) {
			evidenceResponse.setCategoryId(enhancedMEvidence.getCategory().getCode());

			if (enhancedMEvidence.getEnhancedItem() != null) {
				BaseResponseData item = null;
				if (enhancedMEvidence.getCategory() == CategoryEnum4MEvidence.APPVIDEO) {
					item = convert2MEvidenceAppVideoResponse((EnhancedMEvidenceAppVideo) enhancedMEvidence.getEnhancedItem());
				} else if (enhancedMEvidence.getCategory() == CategoryEnum4MEvidence.APPVOICE) {
					item = convert2MEvidenceAppVoiceResponse((EnhancedMEvidenceAppVoice) enhancedMEvidence.getEnhancedItem());
				} else if (enhancedMEvidence.getCategory() == CategoryEnum4MEvidence.APPPICTURE) {
					item = convert2MEvidenceAppPictureResponse((EnhancedMEvidenceAppPicture) enhancedMEvidence.getEnhancedItem());
				} else if (enhancedMEvidence.getCategory() == CategoryEnum4MEvidence.FAX) {
					item = convert2MEvidenceTelecomVoiceResponse((EnhancedMEvidenceTelecomVoice) enhancedMEvidence.getEnhancedItem());
				} else if (enhancedMEvidence.getCategory() == CategoryEnum4MEvidence.VIDEO) {
					item = convert2MEvidenceRemotePcResponse((EnhancedMEvidenceRemotePc) enhancedMEvidence.getEnhancedItem());
				}
				// else {//TODO 继续增加}
				evidenceResponse.setItem(item);
			}
		}

		return evidenceResponse;
	}

	private static MEvidenceRemotePcResponse convert2MEvidenceRemotePcResponse(EnhancedMEvidenceRemotePc enhancedMEvidenceRemotePc) {
		MEvidenceRemotePcResponse mEvidenceRemotePcResponse = new MEvidenceRemotePcResponse();
		BeanUtils.copyProperties(enhancedMEvidenceRemotePc, mEvidenceRemotePcResponse);
		if (enhancedMEvidenceRemotePc.getEnhancedMEvidence() != null) {
			mEvidenceRemotePcResponse.setEvidence(convert2MEvidenceResponse(enhancedMEvidenceRemotePc.getEnhancedMEvidence()));
		}
		return mEvidenceRemotePcResponse;
	}

	private static MEvidenceTelecomVoiceResponse convert2MEvidenceTelecomVoiceResponse(EnhancedMEvidenceTelecomVoice enhancedMEvidenceTelecomVoice) {
		MEvidenceTelecomVoiceResponse mEvidenceTelecomVoiceResponse = new MEvidenceTelecomVoiceResponse();
		BeanUtils.copyProperties(enhancedMEvidenceTelecomVoice, mEvidenceTelecomVoiceResponse);
		if (enhancedMEvidenceTelecomVoice.getEnhancedMEvidence() != null) {
			mEvidenceTelecomVoiceResponse.setEvidence(convert2MEvidenceResponse(enhancedMEvidenceTelecomVoice.getEnhancedMEvidence()));
		}
		return mEvidenceTelecomVoiceResponse;
	}

	private static MEvidenceAppPictureResponse convert2MEvidenceAppPictureResponse(EnhancedMEvidenceAppPicture enhancedMEvidenceAppPicture) {
		MEvidenceAppPictureResponse mEvidenceAppPictureResponse = new MEvidenceAppPictureResponse();
		BeanUtils.copyProperties(enhancedMEvidenceAppPicture, mEvidenceAppPictureResponse);
		if (enhancedMEvidenceAppPicture.getEnhancedMEvidence() != null) {
			mEvidenceAppPictureResponse.setEvidence(convert2MEvidenceResponse(enhancedMEvidenceAppPicture.getEnhancedMEvidence()));
		}
		return mEvidenceAppPictureResponse;
	}

	private static MEvidenceAppVoiceResponse convert2MEvidenceAppVoiceResponse(EnhancedMEvidenceAppVoice enhancedMEvidenceAppVoice) {
		MEvidenceAppVoiceResponse mEvidenceAppVoiceResponse = new MEvidenceAppVoiceResponse();
		BeanUtils.copyProperties(enhancedMEvidenceAppVoice, mEvidenceAppVoiceResponse);
		if (enhancedMEvidenceAppVoice.getEnhancedMEvidence() != null) {
			mEvidenceAppVoiceResponse.setEvidence(convert2MEvidenceResponse(enhancedMEvidenceAppVoice.getEnhancedMEvidence()));
		}
		return mEvidenceAppVoiceResponse;
	}

	private static MEvidenceAppVideoResponse convert2MEvidenceAppVideoResponse(EnhancedMEvidenceAppVideo enhancedMEvidenceAppVideo) {
		MEvidenceAppVideoResponse mEvidenceAppVideoResponse = new MEvidenceAppVideoResponse();
		BeanUtils.copyProperties(enhancedMEvidenceAppVideo, mEvidenceAppVideoResponse);
		if (enhancedMEvidenceAppVideo.getEnhancedMEvidence() != null) {
			mEvidenceAppVideoResponse.setEvidence(convert2MEvidenceResponse(enhancedMEvidenceAppVideo.getEnhancedMEvidence()));
		}
		return mEvidenceAppVideoResponse;
	}

	private static List<MREvidenceFileResponse> convert2MREvidenceFileResponses(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles) {
		List<MREvidenceFileResponse> evidenceResponses = Lists.newArrayList();
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMREvidenceFiles) {
			evidenceResponses.add(convert2MREvidenceFileResponse(_EnhancedMREvidenceFile));
		}
		return evidenceResponses;
	}

	private static MREvidenceFileResponse convert2MREvidenceFileResponse(EnhancedMREvidenceFile enhancedMREvidenceFile) {
		MREvidenceFileResponse mrEvidenceFileResponse = new MREvidenceFileResponse();
		BeanUtils.copyProperties(enhancedMREvidenceFile, mrEvidenceFileResponse);
		if (enhancedMREvidenceFile.getEnhancedMEvidence() != null) {
			mrEvidenceFileResponse.setEvidence(convert2MEvidenceResponse(enhancedMREvidenceFile.getEnhancedMEvidence()));
		}
		if (enhancedMREvidenceFile.getEnhancedDNpp() != null) {
			mrEvidenceFileResponse.setNpp(convert2DNppResponse(enhancedMREvidenceFile.getEnhancedDNpp()));
		}
		mrEvidenceFileResponse.setFileIdentity(enhancedMREvidenceFile.getPath());
		if (enhancedMREvidenceFile.getFileType() != null) {
			mrEvidenceFileResponse.setFileType(enhancedMREvidenceFile.getFileType().getCode());
		}
		if (enhancedMREvidenceFile.getUploadStatus() != null) {
			mrEvidenceFileResponse.setUploadStatusCode(enhancedMREvidenceFile.getUploadStatus().getCode());
		}
		return mrEvidenceFileResponse;
	}

	private static DNppResponse convert2DNppResponse(EnhancedDNpp enhancedDNpp) {
		DNppResponse dNppResponse = new DNppResponse();
		BeanUtils.copyProperties(enhancedDNpp, dNppResponse);
		if (enhancedDNpp.getStatus() != null) {
			dNppResponse.setStatusCode(enhancedDNpp.getStatus().getCode());
		}
		return dNppResponse;
	}

	public static List<ENotarizationReserveResponse> convert2ENotarizationReserveResponses(List<EnhancedENotarizationReserve> enhancedENotarizationReserves) {
		List<ENotarizationReserveResponse> notarizationReserveResponses = Lists.newArrayList();
		for (EnhancedENotarizationReserve _EnhancedENotarizationReserve : enhancedENotarizationReserves) {
			notarizationReserveResponses.add(convert2ENotarizationReserveResponse(_EnhancedENotarizationReserve));
		}
		return notarizationReserveResponses;
	}

	public static ENotarizationReserveResponse convert2ENotarizationReserveResponse(EnhancedENotarizationReserve enhancedENotarizationReserve) {
		ENotarizationReserveResponse eNotarizationReserveResponse = new ENotarizationReserveResponse();
		BeanUtils.copyProperties(enhancedENotarizationReserve, eNotarizationReserveResponse);
		if (enhancedENotarizationReserve.getStatus() != null) {
			eNotarizationReserveResponse.setStatus(enhancedENotarizationReserve.getStatus().getCode());
		}
		if (enhancedENotarizationReserve.getEnhancedDNpp() != null) {
			eNotarizationReserveResponse.setNpp(convert2DNppResponse(enhancedENotarizationReserve.getEnhancedDNpp()));
		}
		if (enhancedENotarizationReserve.getEnhancedUser() != null) {
			if (enhancedENotarizationReserve.getEnhancedUser() instanceof EnhancedEUser) {
				eNotarizationReserveResponse.setUser(convert2UserResponse((EnhancedEUser) enhancedENotarizationReserve.getEnhancedUser()));
			} else if (enhancedENotarizationReserve.getEnhancedUser() instanceof EnhancedEUser189) {
				eNotarizationReserveResponse.setUser(convert2UserResponse((EnhancedEUser189) enhancedENotarizationReserve.getEnhancedUser()));
			} else {
				throw new RdbaoException("[暂未支持此用户类型]");
			}
		}
		if (Detect.notEmpty(enhancedENotarizationReserve.getEnhancedMEvidences())) {
			eNotarizationReserveResponse.setItems(convert2MEvidenceResponses(enhancedENotarizationReserve.getEnhancedMEvidences()));
		}

		return eNotarizationReserveResponse;

	}
}
