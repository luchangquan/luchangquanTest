/**
 * @author jgshun
 * @date 2017-5-22 上午11:06:05
 * @version 1.0
 */
public class Test {
	public static void main(String[] args) {
		String ps = 
				"17788599300 \n" + 
				"17788599709 \n" + 
				"17788599710 \n" + 
				"17788599711 \n" + 
				"17788599712 \n" + 
				"17788599713 \n" + 
				"17788599715 \n" + 
				"17788599718 \n" + 
				"17788599722 \n" + 
				"17788599728 ";
		// ps = "18019290172\r\n" +
		// "18019290173\r\n" +
		// "18019290175\r\n" +
		// "18019290176\r\n" +
		// "18019290178\r\n" +
		// "18019290179\r\n" +
		// "18019290180\r\n" +
		// "18019290181\r\n" +
		// "18019290182\r\n" +
		// "18019290183\r\n" +
		// "18019290185\r\n" +
		// "18019290186\r\n" +
		// "18019290187\r\n" +
		// "18019290189\r\n" +
		// "18019290190\r\n" +
		// "18019290191\r\n" +
		// "18019290192\r\n" +
		// "18019290193\r\n" +
		// "18019290196";
		System.out.println(ps.replaceAll("\n", ","));
		 System.out.println(ps.replaceAll("\n", ",").split(",").length);
		 
		String[] pps =  ps.replaceAll("\n", ",").split(",");
		for(String p: pps){
			String pr = "\""+p+"\"";
			System.out.print(pr +",");
			
//			String is = "INSERT INTO ItemCountry(Id,ItemId,CountryId,CreateTime) VALUE(UUID(),'"+ p  +"',\"329d8b5b-5a24-11e6-97b9-ac853d9d55ad\",NOW());";
//			System.out.println(is);
		}
	}
}
