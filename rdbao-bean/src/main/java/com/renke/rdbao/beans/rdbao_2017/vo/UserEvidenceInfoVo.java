package com.renke.rdbao.beans.rdbao_2017.vo;

import java.util.List;

import com.renke.rdbao.beans.common.vo.BaseVo;
/**
 * 用户中心证据信息数据
 * @author wy
 *
 */
public class UserEvidenceInfoVo extends BaseVo {

	
	int  evidenceCount;//用户统计证据条数
	
	long spaceAmount;//用户统计空间使用量
	long notaryApplyCount;//公证申请总数量
	List<EnhancedDNppVo> listEnhancedDNpp;//用户所开通的公证处 集合
	
	
	public class EnhancedDNppVo{
		String id;//公证处id
		String code;//公证处code
		String name;//公证处名称
		String spaceAmount;//用户统计空间使用量
		int notaryApplyCount;//公证申请数量

		int evidenceFaxCount;//传真数量
		int  evidenceVideoCount;//视频数量
		int  evidenceApppictureCount;//照片数量
		int  evidenceAppvoiceCount;//录音数量
		int  evidenceAppvideoCount;//APP视频数量
		
		
		
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getSpaceAmount() {
			return spaceAmount;
		}
		public void setSpaceAmount(String spaceAmount) {
			this.spaceAmount = spaceAmount;
		}
		public int getNotaryApplyCount() {
			return notaryApplyCount;
		}
		public void setNotaryApplyCount(int notaryApplyCount) {
			this.notaryApplyCount = notaryApplyCount;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public int getEvidenceFaxCount() {
			return evidenceFaxCount;
		}
		public void setEvidenceFaxCount(int evidenceFaxCount) {
			this.evidenceFaxCount = evidenceFaxCount;
		}
		public int getEvidenceVideoCount() {
			return evidenceVideoCount;
		}
		public void setEvidenceVideoCount(int evidenceVideoCount) {
			this.evidenceVideoCount = evidenceVideoCount;
		}
		public int getEvidenceApppictureCount() {
			return evidenceApppictureCount;
		}
		public void setEvidenceApppictureCount(int evidenceApppictureCount) {
			this.evidenceApppictureCount = evidenceApppictureCount;
		}
		public int getEvidenceAppvoiceCount() {
			return evidenceAppvoiceCount;
		}
		public void setEvidenceAppvoiceCount(int evidenceAppvoiceCount) {
			this.evidenceAppvoiceCount = evidenceAppvoiceCount;
		}
		public int getEvidenceAppvideoCount() {
			return evidenceAppvideoCount;
		}
		public void setEvidenceAppvideoCount(int evidenceAppvideoCount) {
			this.evidenceAppvideoCount = evidenceAppvideoCount;
		}
		
		
	}


	public int getEvidenceCount() {
		return evidenceCount;
	}


	public void setEvidenceCount(int evidenceCount) {
		this.evidenceCount = evidenceCount;
	}


	public long getSpaceAmount() {
		return spaceAmount;
	}


	public void setSpaceAmount(long spaceAmount) {
		this.spaceAmount = spaceAmount;
	}


	public List<EnhancedDNppVo> getListEnhancedDNpp() {
		return listEnhancedDNpp;
	}


	public void setListEnhancedDNpp(List<EnhancedDNppVo> listEnhancedDNpp) {
		this.listEnhancedDNpp = listEnhancedDNpp;
	}


	public long getNotaryApplyCount() {
		return notaryApplyCount;
	}


	public void setNotaryApplyCount(long notaryApplyCount) {
		this.notaryApplyCount = notaryApplyCount;
	} 
	
}
