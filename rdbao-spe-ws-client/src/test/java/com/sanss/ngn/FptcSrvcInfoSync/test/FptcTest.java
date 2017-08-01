package com.sanss.ngn.FptcSrvcInfoSync.test;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import com.alibaba.fastjson.JSONObject;
import com.sanss.ngn.FptcSrvcInfoSync.FptcSrvcInfoSyncProxy;
import com.sanss.ngn.FptcSrvcInfoSync.SrvcInfo;
import com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeRequest;
import com.sanss.ngn.FptcSrvcInfoSync.SrvcSubscribeResponse;
import com.sanss.ngn.FptcSrvcInfoSync.VSubProdInfo;

/**
 * @author jgshun
 * @date 2017-3-13 下午2:23:23
 * @version 1.0
 */
public class FptcTest {
	public static void main(String[] args) throws RemoteException, UnsupportedEncodingException {
		FptcSrvcInfoSyncProxy proxy = new FptcSrvcInfoSyncProxy("http://101.95.49.32/FptcSrvcInfoSync/FptcSrvcInfoSyncSOAP");
		DateTime curDateTime = new DateTime();
		String streamingNo = curDateTime.toString("yyyyMMddHHmmss");// 流水号
		String srcSystemNo = "SrcSystemNo";// 服务端分配 固定
		String key = "6K7sJ91D1e231XW1";// 服务端分配 固定
		short actionType = 0;// 0：订购 1：退订
		String timeStamp = curDateTime.toString("yyyyMMddHHmmss");// 时间戳
		String userNumber = "13636621498";// 用户号码
		String orderId = "RK" + streamingNo;
		short userIDType = 2;// 0：MSISDN 1：PHS 2：PSTN 3：宽带帐号 4：IPTV帐号
								// 5：VNET帐号：仅在此账号为付费账号时使用 6：密号
		String signature = DigestUtils.md5Hex(streamingNo + srcSystemNo + userNumber + actionType + key);// MD5(StreamingNo+SrcSystemNo+UserNumber+ActionType+Key)

		short srvcType = 0;// 0：纯增值销售品（音证宝） 1：增值捆绑套餐 2：（传统+增值）捆绑套餐
		String srvcID = "y13500000000000000001";// 销售品ID，参考固网提供的service
												// y13500000000000000001<--主叫serviceId
												// y13500000000000000002<--被叫serviceId

		String effDate = curDateTime.toString("yyyyMMddHHmmss");// 生效时间
		String expDate = curDateTime.plusYears(99).toString("yyyyMMddHHmmss");// 失效时间
		short isExperience = 1;// 订购类型：0：正常订购 1：免费体验
		short subscribeType = 0;// 订购方式：0：非批量受理 1：批量受理

		VSubProdInfo[] vSubProdInfos = new VSubProdInfo[1];
		String VProductID = "yzbzj-renke";// 增值产品ID 参考固网提供的productKey
		VSubProdInfo vSubProdInfo = new VSubProdInfo(VProductID, effDate, expDate);
		vSubProdInfos[0] = vSubProdInfo;

		SrvcInfo srvcInfo = new SrvcInfo(srvcType, srvcID, vSubProdInfos, effDate, expDate, isExperience, subscribeType);

		SrvcSubscribeRequest inputParams = new SrvcSubscribeRequest(streamingNo, srcSystemNo, signature, timeStamp, actionType, orderId, userIDType, userNumber, srvcInfo);
		SrvcSubscribeResponse srvcSubscribeResponse = proxy.srvcSubscribe(inputParams);

		System.out.println(new String(JSONObject.toJSONString(srvcSubscribeResponse).getBytes("gbk"), "utf8"));

	}
}
