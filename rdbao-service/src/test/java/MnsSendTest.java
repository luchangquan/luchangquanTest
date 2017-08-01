import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.util.AliMnsUtil;

/**
 * @author jgshun
 * @date 2017-3-10 下午4:17:50
 * @version 1.0
 */
public class MnsSendTest {
	public static void main(String[] args) throws AliOperateException {
		Message message = new Message();
		message.setMessageBody("{\r\n" + 
				"    \"remarkSupplement\": {\r\n" + 
				"        \"evidenceName\": \"张三取证名称\",\r\n" + 
				"        \"evidenceRemark\": \"张三取证备注\"\r\n" + 
				"    },\r\n" + 
				"    \"supplementType\": 2,\r\n" + 
				"    \"taskId\": \"cc21a7ab-c095-4132-9ece-95deab8125a2\"\r\n" + 
				"}", MessageBodyType.RAW_STRING);
		AliMnsUtil.sendMessage("rdbao-evidence-rdp-notice-supplement", message);
	}
}
