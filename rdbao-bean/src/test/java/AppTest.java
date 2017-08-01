import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import javax.persistence.Column;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ParseException {
//							  Thu Oct 16 07:13:48 GMT 2014
//	System.out.println(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'",Locale.ENGLISH).parse("Fri, 19 May 2017 12:03:30 GMT"));	;
	for(int i =0;i<10;i++){
		System.out.println(UUID.randomUUID().toString());

	}
//		System.out.println("rdp/18649809213/20170509/aa75bf89-3db5-4778-b90d-7de39172958a/2017-05-09-1626-30_CC0C678B45E556D8738E8D0CDBB10F3B.flv".length());
		// String sss = "张三";
		// System.out.println(sss == "张三");
		//
		// Class class1 =
		// Class.forName("com.renke.rdbao.beans.rdbao_v3.pojo.EvidencePicture");
//		Class class1 = Class.forName("com.renke.rdbao.beans.rdbao_2017.pojo.EPhoneNoProductInterimRecord");
		Class class1 = Class.forName("com.renke.rdbao.beans.rdbao_sms_2017.pojo.ESmsInfo");

		// Class class1 =
		// Class.forName("com.renke.rdbao.beans.rdbaosms.pojo.ReplySms");
		// Class class1 =
		// Class.forName("com.renke.rdbao.beans.rdbaoapp.pojo.ArchiveCancel");
		Field[] fields = class1.getDeclaredFields();

		for (Field field : fields) {
			System.out.println("public static final String FIELD_" + field.getName().toUpperCase() + "=\"" + field.getName() + "\";");
		}
		System.out.println();
		for (Field field : fields) {
			String columnName = "";
			Annotation annotation = field.getAnnotation(Column.class);
			if (annotation != null) {
				columnName = (String) annotation.getClass().getDeclaredMethod("name", null).invoke(annotation, null);
			} else {
				columnName = field.getName();
			}
			System.out.println("public static final String COLUMN_" + columnName.toUpperCase() + " = \"" + columnName + "\";");
		}

	}

}
