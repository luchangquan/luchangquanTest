import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-5-16 下午4:37:59
 * @version 1.0
 */
public class EvidenceImportTest {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		File f = new File("/Users/guoshunjiang/Desktop/常用文档/im2.txt");
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
				String Id = vs[0];
				String Name = vs[1];
				String Description = vs[2];
				String CreateTime = vs[3];
				String LastUpdateTime = vs[4];
				String PNO_Id = vs[5];
				PNO_Id = getPno_id(PNO_Id);
				
				String User_Id = vs[6];
				String Code = vs[7];
				String size = vs[8];
				String App_Id = vs[9];
				String Company_Id = vs[10];
				String Category_Id = vs[11];
				String FileName = vs[12].replaceFirst("/", "");
				String Deleted = vs[13].trim();

				int status = Deleted.equals("'0'") ? 1 : 0;

				String finin = "INSERT INTO `rdbao_2017`.`m_evidence` (`id`, `name`, `code`, `size`, `description`," + " `create_time`, `update_time`, `npp_code`, `user_id`, `evidence_source_id`, "
						+ "`signature_key_id`, `company_id`, `category_id`, `status`, `upload_status`, " + "`tenant_code`) VALUES " + "("
						+ Id
						+ ", "
						+ Name
						+ ", "
						+ Code
						+ ","
						+ " "
						+ size
						+ ", "
						+ Description
						+ ", "
						+ CreateTime
						+ ", "
						+ LastUpdateTime
						+ ", "
						+ PNO_Id
						+ ", "
						+ User_Id
						+ ", "
						+ App_Id
						+ ", "
						+ "'', " + Company_Id + ", " + Category_Id + ", " + status + ", '0', " + "'189');";
				String fileSuffix = FileName.substring(FileName.lastIndexOf(".")).replace("'", "").replace(".", "");
				short fileType = Detect.getFileType(fileSuffix).getCode();
				String finin2 = "INSERT INTO `rdbao_2017`.`m_r_evidence_file` (`id`, `evidence_id`, `path`, `bucket`, " + "`storage_type`, `npp_code`, `md5`, `size`, `file_type`,"
						+ " `upload_status`, `sort`, `create_time`, `update_time`, " + "`extra`) VALUES " + "('" + UUID.randomUUID().toString() + "', " + Id + ", " + "" + FileName + ", "
						+ "'rdbao-evidence-resources', '1', 'ZJLA', '', " + size + ", " + fileType + ", '0'," + " '1.00'," + " " + CreateTime + ", " + "" + LastUpdateTime + ", NULL);";

				System.out.println(finin);
				System.out.println(finin2);
			}
		} catch (IOException e) {
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}

	}

	private static String getPno_id(String pNO_Id) {
		String ids = "1	SHMH\r\n" + 
				"10	HNCS\r\n" + 
				"11	SDLW\r\n" + 
				"12	SHSJ\r\n" + 
				"13	TJJH\r\n" + 
				"14	TJSFJ\r\n" + 
				"15	TJBF\r\n" + 
				"16	TJBD\r\n" + 
				"17	TJDG\r\n" + 
				"18	TJDL\r\n" + 
				"19	TJHP\r\n" + 
				"2	HZDF\r\n" + 
				"20	TJHD\r\n" + 
				"21	TJHX\r\n" + 
				"22	TJHQ\r\n" + 
				"23	TJTG\r\n" + 
				"24	TJLT\r\n" + 
				"25	TJHH\r\n" + 
				"26	TJWQ\r\n" + 
				"27	TJHB\r\n" + 
				"28	TJBF\r\n" + 
				"29	TJTD\r\n" + 
				"3	WHCJ\r\n" + 
				"30	TJXQ\r\n" + 
				"31	TJBC\r\n" + 
				"32	TJBH\r\n" + 
				"33	\r\n" + 
				"34	SHJS\r\n" + 
				"35	CDSCD\r\n" + 
				"36	SHSF\r\n" + 
				"37	SHMHSPE\r\n" + 
				"38	NCDH\r\n" + 
				"39	ZJLA\r\n" + 
				"4	JSSZ\r\n" + 
				"5	ZJNB\r\n" + 
				"6	BJFZ\r\n" + 
				"7	ZJHN\r\n" + 
				"8	JSNJ\r\n" + 
				"9	JHZX";
		String[] iis =  ids.split("\\r\\n");
		for(String i : iis){
			String[] ss = i.split("	");
			if(ss[0].equals(pNO_Id.trim().replaceAll("'", ""))){
				return "'"+ss[1].trim()+"'";
			}
		}
		
		return null;
	}
}
