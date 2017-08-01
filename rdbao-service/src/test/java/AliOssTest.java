import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.Callback.CalbackBodyType;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.PropertiesConfUtil;

/**
 * @author jgshun
 * @date 2017-3-8 下午1:18:15
 * @version 1.0
 */
public class AliOssTest {
	public static void main(String[] args) throws AliOperateException, FileNotFoundException, IOException {

		// // 上传回调参数
		Callback callback = new Callback();
		callback.setCallbackUrl("http://jgshun.oicp.net:37974/rdbao-app/app/upload/callback/video");
		// callback.setCallbackHost("oss-cn-hangzhou.aliyuncs.com");
		callback.setCallbackBody("{\\\"request\\\":{\\\"length\\\":${x:length},\\\"filetype\\\":${x:filetype},\\\"sort\\\":${x:sort},\\\"bucketName\\\":${x:bucketName},\\\"fileIdentity\\\":${x:fileIdentity},\\\"userAccount\\\":${x:userAccount}}}");
		callback.setCalbackBodyType(CalbackBodyType.JSON);
		callback.addCallbackVar("x:length", "6581506");
		callback.addCallbackVar("x:filetype", "" + FileTypeEnum.VIDEO.getCode());
		callback.addCallbackVar("x:sort", "0");
		callback.addCallbackVar("x:bucketName", AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName());
		callback.addCallbackVar("x:fileIdentity", "appvideo/caily/20161214T104300Z/APPVIDEO_caily_20170301114123000789_7b63b937b79db50ebdf4b7ed1b8b91d0_1.mp4");
		callback.addCallbackVar("x:userAccount", "caily");

		ResponseData responseData = AliOssUtil.uploadFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
				null, new BufferedInputStream(new FileInputStream("E:\\QQPCmgr\\Desktop/cars.mp4")), AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES,
				"appvideo/caily/20161214T104300Z/APPVIDEO_caily_20170301114123000789_7b63b937b79db50ebdf4b7ed1b8b91d0_1.mp4", callback);
		System.out.println(JSONObject.toJSONString(responseData));
	}
}
