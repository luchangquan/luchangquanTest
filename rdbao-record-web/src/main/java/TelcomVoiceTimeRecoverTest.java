import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class TelcomVoiceTimeRecoverTest {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		/*
		 * SELECT * from m_evidence_telecom_voice m where m.evidence_id in (
		 * SELECT m.id from m_evidence m where m.user_id in ( SELECT e.id from
		 * e_user e where e.company_id = '5d868a41-7243-4bac-b811-67fae9374089'
		 * ) and m.category_id = 5 and m.create_time >= '2017-06-02 09:57:07'
		 * ORDER BY m.create_time DESC );
		 * 
		 * 
		 */
		String ss = "";
		File f = new File("/Users/guoshunjiang/Desktop/常用文档/voiceRecover.txt");
		File of = new File("/Users/guoshunjiang/Desktop/常用文档/voiceRecoverOut.txt");

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		PrintWriter p = new PrintWriter(of);
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				String[] sx = line.split("	");
				String r = "UPDATE evidences set createtime = '" + sx[1] + "' where id = '" + sx[0] + "';";
				p.println(r);
			}
			p.flush();
		} catch (IOException e) {
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}

		// String[] ts = ss.split("\n");
		// for (String t : ts) {
		// String[] sx = t.split(" ");
		// System.out.println("UPDATE evidences set createtime = '" + sx[1] + "'
		// where id = '" + sx[0] + "';");
		//// System.out.println("UPDATE m_evidence set create_time = '" + sx[1]
		// + "' where id = '" + sx[0] + "';");
		// }
	}
}
