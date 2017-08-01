import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author jgshun
 * @date 2017-5-16 下午4:37:59
 * @version 1.0
 */
public class UserNppImportTest {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		File f = new File("/Users/guoshunjiang/Desktop/常用文档/imusernpp.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				String in = line;
				String nppCode = "ZJLA";// TODO 需要修改
				String finin = "INSERT INTO `rdbao_2017`.`r_user_npp` (`id`, `user_id`, `npp_code`," 
				+ " `status`, `create_time`, `update_time`, `tenant_code`) " + "VALUES ('"
						+ UUID.randomUUID().toString() + "', '" + in.split("	")[0] + "', '" + nppCode + "', '1', " + "'" + in.split("	")[1] + "', '" + in.split("	")[1] + "', " + "'1010bao');";
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
