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
public class SelectUserImportTest {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		File f = new File("E:\\QQPCmgr\\Desktop/imselectuser.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		String line = null;
		try {
			String ss = "";
			while ((line = reader.readLine()) != null) {

				// String in =
				// "INSERT INTO `rdbao_v3`.`evidences` (`Id`, `Name`, `Description`, `CreateTime`, `LastUpdateTime`, `PNO_Id`, `User_Id`, `Code`, `Size`, `App_Id`, `Company_Id`, `Category_Id`, `FileName`, `Deleted`) VALUES ('ffd59183-4f5d-4655-9953-b0842f9b2455', '017771902073来电', NULL, '2017-03-20 11:06:25', '2017-03-20 11:06:25', '39', 'd1fdadb2-088c-46ff-a799-fa9dbad91ac8', 'ZJLA011489979171616', '551470', 'NGCCNJ', '5d868a41-7243-4bac-b811-67fae9374089', '5', '/evidencesNew/Proof/laxbk/2017/0320/NGCC13970320105818097521.wav', '0');";
				String in = line;
			     ss=ss+"'"+in+"',";
			}
			ss = ss.substring(0,ss.length()-1);
			System.out.println(ss);
			
			String finin = "SELECT e.Id,\r\n" + 
					"e.`Name`,e.Description,e.CreateTime,e.LastUpdateTime,e.PNO_Id,e.User_Id,\r\n" + 
					"e.`Code`,e.Size,e.App_Id,e.Company_Id,e.Category_Id,e.FileName,e.Deleted\r\n" + 
					" from evidences e where e.User_Id \r\n" + 
					"in (\r\n" + 
					"SELECT u.Id from users u where u.id in ("+ss+") " + 
					") ORDER BY e.CreateTime";
			System.out.println(finin);
		} catch (IOException e) {
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}

	}
}
