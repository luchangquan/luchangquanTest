import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class ProviderNohupRecoverTest {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		String[] ps = "057163723063,057161084411,057161067907,057158688111,057163741811,057163710083,057123662791,057163888912,057163669730,057161100135".split(",");

		File f = new File("/Users/guoshunjiang/Downloads/nohup.out");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		String line = null;
		Set<String> fs = new HashSet<String>();
		List<String> fsT = new ArrayList<String>();
		try {
			while ((line = reader.readLine()) != null) {
				String patt = "com.renke.rdbao.services.rdbao_2017.service.impl.VoiceNoticeService.saveReceivedRedirectVoiceNotice4JSZH-208";
				String value = "";
				if (line.contains(patt)) {
					value = line.split("收到转发语音通知:")[1].trim();
					value = value.substring(0, value.length() - 1).trim();
					JSONObject vj = JSONObject.parseObject(value);
					String callingNumber = vj.getString("CallingNumber");
					String calledNumber = vj.getString("CalledNumber");
					for (String p : ps) {
						if (p.trim().equals(callingNumber.trim()) || p.trim().equals(calledNumber.trim())) {
							String prefix = "/shoujiluyin/" + vj.getString("CallTime").split(" ")[0].replaceAll("-", "");
							vj.put("Location", vj.getString("Location").replace("//other", prefix));
							fs.add(vj.toJSONString());

							vj.put("Location", vj.getString("Location").replace(prefix, "/filenfs/renke/upload/ivrrecord/callin"));
							fs.add(vj.toJSONString());

							vj.put("Location", vj.getString("Location").replace("/filenfs/renke/upload/ivrrecord/callin", "/filenfs/renke/upload/ivrrecord/callout"));
							fs.add(vj.toJSONString());
						}
					}
				}

			}
			System.out.println(fs.size());
			for (String fq : fs) {
				System.out.println(fq);
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
