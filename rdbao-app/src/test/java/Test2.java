import java.util.Arrays;
import java.util.List;

/**
 * @author jgshun
 * @date 2017-5-22 上午11:06:05
 * @version 1.0
 */
public class Test2 {
	public static void main(String[] args) {
		String ps = "18121235085\n" + 
				"18121235119\n" + 
				"18121235120\n" + 
				"02165448768\n" + 
				"02167678242\n" + 
				"02167678943\n" + 
				"02167678247\n" + 
				"02167678141\n" + 
				"18918106457\n" + 
				"18918106462\n" + 
				"18918106476\n" + 
				"18918106452\n" + 
				"18918107115\n" + 
				"18918106924\n" + 
				"18918107113\n" + 
				"18918106446\n" + 
				"18918106964\n" + 
				"18918106461\n" + 
				"18918106467\n" + 
				"18918106947\n" + 
				"18918106449\n" + 
				"18918107110\n" + 
				"18918107116\n" + 
				"18918106481\n" + 
				"18918106447\n" + 
				"18918106463\n" + 
				"18918107119\n" + 
				"18918106472\n" + 
				"18918106480\n" + 
				"18918106453\n" + 
				"18918106459\n" + 
				"18918107112\n" + 
				"18918106942\n" + 
				"18918106450\n" + 
				"18918106479\n" + 
				"18918106473\n" + 
				"18918106471\n" + 
				"18918106475\n" + 
				"18964836058\n" + 
				"18964832918\n" + 
				"17721194202\n" + 
				"18901880098";

		String s2 = "18918106453\n" + 
				"18918106473\n" + 
				"18918107110\n" + 
				"18918106472\n" + 
				"02167678242\n" + 
				"18918106467\n" + 
				"17721194202\n" + 
				"18121235119\n" + 
				"18918106476\n" + 
				"18918107113\n" + 
				"18918106461\n" + 
				"18918106449\n" + 
				"18918106452\n" + 
				"18918106450\n" + 
				"18918106924\n" + 
				"18918106463\n" + 
				"18918106457\n" + 
				"18918106447\n" + 
				"18121235120\n" + 
				"18918106947\n" + 
				"02167678247\n" + 
				"18918106462\n" + 
				"18918106964\n" + 
				"18918106459\n" + 
				"18964832918\n" + 
				"18918106480\n" + 
				"18918106471\n" + 
				"18121235085\n" + 
				"18918106481\n" + 
				"18918106942\n" + 
				"18918107112\n" + 
				"02167678141\n" + 
				"18918106475\n" + 
				"18918106479\n" + 
				"18964836058\n" + 
				"18918107115\n" + 
				"02167678943\n" + 
				"18918107119\n" + 
				"18918106446\n" + 
				"18918107116";
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
		ps = ps.replaceAll("\n", ",");
		List<String> sss = Arrays.asList(ps.split(","));
		List<String> sss2 = Arrays.asList(s2.replaceAll("\n", ",").split(","));
		for (String _s : sss) {
			if (!sss2.contains(_s)) {
				System.out.println(_s);
			}
		}

		System.out.println(sss.size());
		System.out.println(sss2.size());

		// System.out.println(Arrays.asList(ps.split(",")));
	}
}
