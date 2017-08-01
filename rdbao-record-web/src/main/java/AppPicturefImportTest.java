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
public class AppPicturefImportTest {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		File f = new File("E:\\QQPCmgr\\Desktop/imapppicture.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {

				// String in =
				// "INSERT INTO `rdbao_v3`.`evidences` (`Id`, `Name`, `Description`, `CreateTime`, `LastUpdateTime`, `PNO_Id`, `User_Id`, `Code`, `Size`, `App_Id`, `Company_Id`, `Category_Id`, `FileName`, `Deleted`) VALUES ('ffd59183-4f5d-4655-9953-b0842f9b2455', '017771902073来电', NULL, '2017-03-20 11:06:25', '2017-03-20 11:06:25', '39', 'd1fdadb2-088c-46ff-a799-fa9dbad91ac8', 'ZJLA011489979171616', '551470', 'NGCCNJ', '5d868a41-7243-4bac-b811-67fae9374089', '5', '/evidencesNew/Proof/laxbk/2017/0320/NGCC13970320105818097521.wav', '0');";
				String in = line;
				String vStr = in.split("VALUES")[1].trim();
				vStr = vStr.substring(1, vStr.length() - 2);
				// System.out.println(vStr);
				String[] vs = vStr.split(",");

				
				String Evidence_Id = vs[0];
				String TakeTime = vs[1];
				String CreateTime = vs[2];
				String UpdateTime = vs[3];
				
				String finin = "INSERT INTO `rdbao_2017`.`m_evidence_app_picture` (`evidence_id`, `take_time`, " +
						"`create_time`, `update_time`, `location`, `location_desc`) " +
						"VALUES ("+Evidence_Id+", "+TakeTime+", " +
						""+CreateTime+", "+UpdateTime+", NULL, NULL);";
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
