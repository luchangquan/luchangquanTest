import java.util.UUID;

/**
 * @author jgshun
 * @date 2017-5-22 上午11:06:05
 * @version 1.0
 */
public class CopyOfTest {
	public static void main(String[] args) {
		String ps = "1c57dcee-7c8c-4d64-910f-c28cd5caa36b\n" + 
				"1c715d7e-0718-4498-8c24-bc369d7fb52f\n" + 
				"21f9422f-582e-4630-b14e-7168518eab8e\n" + 
				"2c899c8d-8cc7-4973-b45c-eb2f05d50e0b\n" + 
				"4df1e014-a3d2-4e63-9bd4-54792b66c9a5\n" + 
				"61637053-9490-4c1e-b273-8f15649bf9ac\n" + 
				"8d34f001-c39e-48a5-a4b7-56a2549b062c\n" + 
				"bb3912c1-9252-41b2-aaf6-97e56b3a6539\n" + 
				"e7f7e04e-094a-432c-aa25-b51e9f4f797e";
		String[] ts = ps.split("\\n");
		String r = "";
		for(String t: ts){
			r = r + ",'"+t+"'";
		}
		System.out.println(r.substring(1));
	}
}
