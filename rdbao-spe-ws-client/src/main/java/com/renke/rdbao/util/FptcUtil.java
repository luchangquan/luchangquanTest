package com.renke.rdbao.util;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSyncProxy;
import com.sanss.ngn.FptcSrvcInfoSync.Property;
import com.sanss.ngn.FptcSrvcInfoSync.SrvcInfo;
import com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyRequest;
import com.sanss.ngn.FptcSrvcInfoSync.SrvcModifyResponse;
import com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeRequest;
import com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeResponse;
import com.sanss.ngn.FptcSrvcInfoSync.VSubProdInfo;

/**
 * @author jgshun
 * @date 2017-3-13 下午2:23:23
 * @version 1.0
 */
public class FptcUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(FptcUtil.class);

	private static final String SRC_SYSTEM_NO = "02000201";// 服务端分配 固定
	private static final String KEY = "6K7sJ91D1e231XW1";// 服务端分配 固定

	/**
	 * 调用SPE开户
	 * 
	 * @param userNumber
	 *            用户号码 固话以021开头
	 * @param srvcID
	 *            销售品ID 参考固网提供的service y13500000000000000001<--主叫serviceId
	 *            y13500000000000000002<--被叫serviceId
	 * @param VProductID
	 *            增值产品ID 参考固网提供的productKey 默认是"yzbzj-renke"
	 * @param actionType
	 *            0;// 0：订购 1：退订
	 * @param isExperience
	 *            订购类型：0：正常订购 1：免费体验
	 * @param
	 * @return
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 * @throws RdbaoException
	 */
	public static SrvcSubscribeResponse srvcSubscribe(String userNumber, String srvcID, String VProductID, short actionType, short isExperience) throws RemoteException, UnsupportedEncodingException,
			RdbaoException {
		FptcSrvcInfoSyncProxy proxy = new FptcSrvcInfoSyncProxy("http://101.95.49.32/FptcSrvcInfoSync/FptcSrvcInfoSyncSOAP");
		DateTime curDateTime = new DateTime();
		String streamingNo = curDateTime.toString("yyyyMMddHHmmss");// 流水号

		String timeStamp = curDateTime.toString("yyyyMMddHHmmss");// 时间戳
		String orderId = "RK" + streamingNo;
		short userIDType = 2;// 0：MSISDN 1：PHS 2：PSTN 3：宽带帐号 4：IPTV帐号
								// 5：VNET帐号：仅在此账号为付费账号时使用 6：密号
		String signature = DigestUtils.md5Hex(streamingNo + SRC_SYSTEM_NO + userNumber + actionType + KEY);// MD5(StreamingNo+SrcSystemNo+UserNumber+ActionType+Key)

		short srvcType = 0;// 0：纯增值销售品（音证宝） 1：增值捆绑套餐 2：（传统+增值）捆绑套餐 固定填0

		String effDate = curDateTime.toString("yyyyMMddHHmmss");// 生效时间
		String expDate = curDateTime.plusYears(99).toString("yyyyMMddHHmmss");// 失效时间
		short subscribeType = 0;// 订购方式：0：非批量受理 1：批量受理 固定填0

		VSubProdInfo[] vSubProdInfos = new VSubProdInfo[1];
		VSubProdInfo vSubProdInfo = new VSubProdInfo(VProductID, effDate, expDate);
		vSubProdInfos[0] = vSubProdInfo;

		SrvcInfo srvcInfo = new SrvcInfo(srvcType, srvcID, vSubProdInfos, effDate, expDate, isExperience, subscribeType);

		String optype = actionType == 0 ? "订购" : "退订";

		SrvcSubscribeRequest inputParams = new SrvcSubscribeRequest(streamingNo, SRC_SYSTEM_NO, signature, timeStamp, actionType, orderId, userIDType, userNumber, srvcInfo);
		_LOGGER.info("[调用上海SPE开户接口--" + optype + "入参:(" + JSONObject.toJSONString(inputParams) + ") ]");
		SrvcSubscribeResponse srvcSubscribeResponse = proxy.srvcSubscribe(inputParams);
		_LOGGER.info("[调用上海SPE开户接口--" + optype + "回参:(" + JSONObject.toJSONString(srvcSubscribeResponse) + ") ]");

		if (srvcSubscribeResponse.getResultCode() != 0) {
			throw new RdbaoException("[调用上海SPE开户接口--" + optype + "失败:(" + JSONObject.toJSONString(srvcSubscribeResponse) + ")");
		}
		return srvcSubscribeResponse;
	}

	/**
	 * 配置放音
	 * 
	 * @param userNumber
	 *            配置号码
	 * @param srvcID
	 *            spe提供的服务id
	 * @param recordType
	 *            配置类型：0关闭放音 1打开放音
	 * @return
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 * @throws RdbaoException
	 */
	public static SrvcModifyResponse srvcModify(String userNumber, String srvcID, String recordType) throws RemoteException, UnsupportedEncodingException, RdbaoException {
		FptcSrvcInfoSyncProxy proxy = new FptcSrvcInfoSyncProxy("http://101.95.49.32/FptcSrvcInfoSync/FptcSrvcInfoSyncSOAP");

		DateTime curDateTime = new DateTime();
		String streamingNo = curDateTime.toString("yyyyMMddHHmmss");// 流水号
		String signature = DigestUtils.md5Hex(streamingNo + SRC_SYSTEM_NO + userNumber + srvcID + KEY);
		// MD5(StreamingNo+SrcSystemNo+UserNumber+SrvcID+Key)
		String timeStamp = curDateTime.toString("yyyyMMddHHmmss");// 时间戳
		String orderId = "RK" + streamingNo;
		Property[] propertyList = new Property[1];
		Property property = new Property("recordType", recordType);
		propertyList[0] = property;

		String optype = recordType.equals("1") ? "开启" : "关闭";

		SrvcModifyRequest inputParams = new SrvcModifyRequest(streamingNo, SRC_SYSTEM_NO, signature, timeStamp, orderId, userNumber, srvcID, propertyList);
		_LOGGER.info("[调用上海SPE放音接口-" + optype + "入参:(" + JSONObject.toJSONString(inputParams) + ") ]");
		SrvcModifyResponse srvcModifyResponse = proxy.srvcModify(inputParams);
		_LOGGER.info("[调用上海SPE放音接口-" + optype + "回参:(" + new String(JSONObject.toJSONString(srvcModifyResponse).getBytes("gbk"), "utf8") + ") ]");

		if (srvcModifyResponse.getResultCode() != 0) {
			throw new RdbaoException("[调用上海SPE放音接口-" + optype + "失败:(" + new String(JSONObject.toJSONString(srvcModifyResponse).getBytes("gbk"), "utf8") + ")");
		}
		return srvcModifyResponse;
	}

}
