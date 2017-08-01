import com.rdbao.model.CallProcedureResultModel;
import com.rdbao.util.EliteGeneralWSClientService;
import com.renke.rdbao.beans.common.exception.RdbaoException;

public class JiangsuZhihengOpTest {
	public static void main(String[] args) throws Exception {
		String phoneNo = "13636621498";
		CallProcedureResultModel rs = EliteGeneralWSClientService.callEliteGeneralWS(phoneNo, "1", "3");
		System.out.println(rs);
		if (rs.getState() == CallProcedureResultModel.STATE_USERWITHOUT) {// 当他通过当前手机号查询表数据为空
																			// 返回4
			rs = EliteGeneralWSClientService.callEliteGeneralWS(phoneNo, "1", "1");
		} else {
			rs = EliteGeneralWSClientService.callEliteGeneralWS(phoneNo, "1", "2");
		}
		if (CallProcedureResultModel.RESULTNO_SUC != rs.getResultNo()) {
			throw new RdbaoException("智恒接口调用失败 ResultMsg：" + rs.getResultMsg() + " error：" + rs.getErrorDesc());
		}
		System.out.println(rs);
	}
}
