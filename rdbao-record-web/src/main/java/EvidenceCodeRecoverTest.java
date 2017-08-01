import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.common.collect.Lists;

public class EvidenceCodeRecoverTest {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		String ss = "";
		File f = new File("/Users/guoshunjiang/Desktop/常用文档/EvidenceCodeRecover.txt");
		File of = new File("/Users/guoshunjiang/Desktop/常用文档/EvidenceCodeRecoverOut.txt");

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		PrintWriter p = new PrintWriter(of);
		String line = null;

		List<String> distinctCodes = Lists.newArrayList();
		List<String> distinctIds = Lists.newArrayList();

		try {
			while ((line = reader.readLine()) != null) {
				String[] sx = line.split("	");
				String r = "UPDATE evidences set deleted = '2' where id = '" + sx[0] + "';";
				p.println(r);

				if (!distinctCodes.contains(sx[1])) {
					distinctCodes.add(sx[1]);
					distinctIds.add(sx[0]);
				}
			}
			for (String id : distinctIds) {
				String r = "UPDATE evidences set deleted = '0' where id = '" + id + "';";
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

	}
}
