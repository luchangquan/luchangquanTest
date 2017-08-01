import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author jgshun
 * @date 2017-5-16 下午4:37:59
 * @version 1.0
 */
public class UserImportTest {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		File f = new File("/Users/guoshunjiang/Desktop/常用文档/im.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {

				// String in =
				// "INSERT INTO `rdbao_v3`.`users` (`Id`, `Name`, `Account`, `Password`, `Mobile`, `Credentials_Type`, `Credentials_Number`, `CreateTime`, `LastUpdateTime`, `UserType`, `Company_Id`, `Email`, `DefaultPnoesId`, `Nickname`) VALUES ('076e3ee7-8687-4ee2-b521-b60fb17baace', '王滨', '13429665203', '25D55AD283AA400AF464C76D713C07AD', '13429665203', '1', '111111111111111111', '2016-12-26 13:53:54', '2016-12-26 13:53:54', '2', '5d868a41-7243-4bac-b811-67fae9374089', NULL, '39', NULL);";
				String in = line;
				String vStr = in.split("VALUES")[1].trim();
				vStr = vStr.substring(1, vStr.length() - 2);
//				System.out.println(vStr);
				String[] vs = vStr.split(",");
				String id = vs[0];
				String name = vs[1];
				String account = vs[2];
				;
				String password = vs[3];
				;
				String mobile = vs[4];
				;
				String Credentials_Type = vs[5];
				;
				String Credentials_Number = vs[6];
				;
				String CreateTime = vs[7];
				;
				String LastUpdateTime = vs[8];
				;
				String UserType = vs[9];
				;
				String Company_Id = vs[10];
				;
				String Email = vs[11];
				;
				String DefaultPnoesId = vs[12];
				;
				String Nickname = vs[13];

				String finin = "INSERT INTO `rdbao_2017`.`e_user` (`id`, `account`, `phone_no`, `name`, `email`, `password`, "
						+ "`credentials_type`, `credentials_no`, `phone_no_status`, `email_status`, `type`, `company_id`, `gender`," + " `status`, `create_time`, `update_time`, "
						+ "`credentials_path`, `npp_id`, `open_source`, `nickname`, `associate_phone_no`) VALUES ("
						+ id
						+ ", "
						+ ""
						+ account
						+ ", "
						+ mobile
						+ ", "
						+ name
						+ ", "
						+ Email
						+ ", "
						+ password
						+ ", "
						+ Credentials_Type
						+ ", "
						+ ""
						+ Credentials_Number
						+ ", '1', '1', "
						+ UserType + ", " + Company_Id + ", '0', '1', " + CreateTime + ", " + LastUpdateTime + ", NULL, " + "" + DefaultPnoesId + ", '97', " + Nickname + ", NULL);";

				System.out.println(finin);
			}
		} catch (IOException e) {
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}

	}
}
