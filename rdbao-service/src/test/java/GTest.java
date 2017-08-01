import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

import javax.persistence.Table;

/**
 * @author jgshun
 * @date 2017-4-12 上午10:55:59
 * @version 1.0
 */
public class GTest {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException {
		String baseUrl = "E:\\workspace_renke_soa_git\\rdbao-project-soa\\rdbao-bean\\src\\main\\java\\com\\renke\\rdbao\\beans\\rdbao_2017\\pojo";
		File[] files = new File(baseUrl).listFiles();
		for (File file : files) {
			String imp = "package com.renke.rdbao.services.rdbao_2017.service.impl;\r\n" + "\r\n" + "import com.renke.rdbao.beans.rdbao_2017.pojo.AModule;\r\n"
					+ "import com.renke.rdbao.services.base.impl.BaseService;\r\n" + "import com.renke.rdbao.services.rdbao_2017.service.IAModuleService;\r\n" + "\r\n" + "/**\r\n"
					+ " * @author jgshun\r\n" + " * @date 2016-11-11 上午11:09:11\r\n" + " * @version 1.0\r\n" + " */\r\n"
					+ "public class AModuleService extends BaseService<AModule> implements IAModuleService {\r\n" + "\r\n" + "}\r\n" + "";
			String javaName = file.getName().replace(".java", "");
			PrintWriter out = new PrintWriter("E:\\QQPCmgr\\Desktop\\java\\" + javaName + "Service.java");
			out.println(imp.replaceAll("AModule", javaName));
			out.flush();
			out.close();
		}

		for (File file : files) {
			String imp = "package com.renke.rdbao.services.rdbao_2017.service;\r\n" + "\r\n" + "import com.renke.rdbao.beans.rdbao_2017.pojo.AModule;\r\n"
					+ "import com.renke.rdbao.services.base.IBaseService;\r\n" + "\r\n" + "/**\r\n" + " * @author jgshun\r\n" + " * @date 2016-11-10 下午5:51:27\r\n" + " * @version 1.0\r\n" + " */\r\n"
					+ "public interface IAModuleService extends IBaseService<AModule> {\r\n" + "\r\n" + "}\r\n" + "";
			String javaName = file.getName().replace(".java", "");
			PrintWriter out = new PrintWriter("E:\\QQPCmgr\\Desktop\\java2\\I" + javaName + "Service.java");
			out.println(imp.replaceAll("AModule", javaName));
			out.flush();
			out.close();
		}

		for (File file : files) {
			String imp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<beans xmlns=\"http://www.springframework.org/schema/beans\"\r\n"
					+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tx=\"http://www.springframework.org/schema/tx\"\r\n"
					+ "	xmlns:context=\"http://www.springframework.org/schema/context\"\r\n"
					+ "	xmlns:mvc=\"http://www.springframework.org/schema/mvc\" xmlns:aop=\"http://www.springframework.org/schema/aop\"\r\n"
					+ "	xmlns:util=\"http://www.springframework.org/schema/util\" xmlns:dubbo=\"http://code.alibabatech.com/schema/dubbo\"\r\n"
					+ "	xsi:schemaLocation=\"http://www.springframework.org/schema/beans    \r\n" + "    http://www.springframework.org/schema/beans/spring-beans.xsd    \r\n"
					+ "    http://www.springframework.org/schema/tx    \r\n" + "    http://www.springframework.org/schema/tx/spring-tx.xsd   \r\n"
					+ "    http://www.springframework.org/schema/context   \r\n" + "    http://www.springframework.org/schema/context/spring-context.xsd   \r\n"
					+ "    http://www.springframework.org/schema/mvc   \r\n" + "    http://www.springframework.org/schema/mvc/spring-mvc.xsd\r\n"
					+ "    http://www.springframework.org/schema/aop \r\n" + "	http://www.springframework.org/schema/aop/spring-aop.xsd\r\n" + "	http://www.springframework.org/schema/util \r\n"
					+ "	http://www.springframework.org/schema/util/spring-util.xsd\r\n" + "	http://code.alibabatech.com/schema/dubbo\r\n"
					+ "    http://code.alibabatech.com/schema/dubbo/dubbo.xsd\">\r\n" + "\r\n" + "	" + "<dubbo:reference id=\"amoduleService\"\r\n"
					+ "		interface=\"com.renke.rdbao.services.rdbao_2017.service.IAModuleService\"\r\n" + "	" + "	version=\"1.0\" url=\"dubbo://localhost:20880\" timeout=\"15000\" />\r\n"
					+ "</beans>";
			String javaName = file.getName().replace(".java", "");
			if (javaName.equals("enhanced")) {
				continue;
			}
			String classJava = "com.renke.rdbao.beans.rdbao_2017.pojo." + javaName;
			String showname = javaName.substring(0, 1).toLowerCase() + javaName.substring(1);

			Class class1 = Class.forName(classJava);
			Annotation annotation = class1.getAnnotation(Table.class);
			String tableName = (String) annotation.getClass().getDeclaredMethod("name", null).invoke(annotation, null);

			PrintWriter out = new PrintWriter("E:\\QQPCmgr\\Desktop\\javadev\\" + tableName + "_service_consumer.xml");
			out.println(imp.replaceAll("AModule", javaName).replaceAll("amoduleService", showname + "Service"));
			out.flush();
			out.close();
		}

		for (File file : files) {
			String imp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<beans xmlns=\"http://www.springframework.org/schema/beans\"\r\n"
					+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tx=\"http://www.springframework.org/schema/tx\"\r\n"
					+ "	xmlns:context=\"http://www.springframework.org/schema/context\"\r\n"
					+ "	xmlns:mvc=\"http://www.springframework.org/schema/mvc\" xmlns:aop=\"http://www.springframework.org/schema/aop\"\r\n"
					+ "	xmlns:util=\"http://www.springframework.org/schema/util\" xmlns:dubbo=\"http://code.alibabatech.com/schema/dubbo\"\r\n"
					+ "	xsi:schemaLocation=\"http://www.springframework.org/schema/beans    \r\n" + "    http://www.springframework.org/schema/beans/spring-beans.xsd    \r\n"
					+ "    http://www.springframework.org/schema/tx    \r\n" + "    http://www.springframework.org/schema/tx/spring-tx.xsd   \r\n"
					+ "    http://www.springframework.org/schema/context   \r\n" + "    http://www.springframework.org/schema/context/spring-context.xsd   \r\n"
					+ "    http://www.springframework.org/schema/mvc   \r\n" + "    http://www.springframework.org/schema/mvc/spring-mvc.xsd\r\n"
					+ "    http://www.springframework.org/schema/aop \r\n" + "	http://www.springframework.org/schema/aop/spring-aop.xsd\r\n" + "	http://www.springframework.org/schema/util \r\n"
					+ "	http://www.springframework.org/schema/util/spring-util.xsd\r\n" + "	http://code.alibabatech.com/schema/dubbo\r\n"
					+ "    http://code.alibabatech.com/schema/dubbo/dubbo.xsd\">\r\n" + "\r\n" + "	" + "<dubbo:reference id=\"amoduleService\"\r\n"
					+ "		interface=\"com.renke.rdbao.services.rdbao_2017.service.IAModuleService\"\r\n" + "	" + "	version=\"1.0\"  timeout=\"15000\" />\r\n" + "</beans>";
			String javaName = file.getName().replace(".java", "");
			if (javaName.equals("enhanced")) {
				continue;
			}
			String classJava = "com.renke.rdbao.beans.rdbao_2017.pojo." + javaName;
			String showname = javaName.substring(0, 1).toLowerCase() + javaName.substring(1);

			Class class1 = Class.forName(classJava);
			Annotation annotation = class1.getAnnotation(Table.class);
			String tableName = (String) annotation.getClass().getDeclaredMethod("name", null).invoke(annotation, null);

			PrintWriter out = new PrintWriter("E:\\QQPCmgr\\Desktop\\javaprod\\" + tableName + "_service_consumer.xml");
			out.println(imp.replaceAll("AModule", javaName).replaceAll("amoduleService", showname + "Service"));
			out.flush();
			out.close();
		}

		for (File file : files) {
			String imp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<beans xmlns=\"http://www.springframework.org/schema/beans\"\r\n"
					+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tx=\"http://www.springframework.org/schema/tx\"\r\n"
					+ "	xmlns:context=\"http://www.springframework.org/schema/context\"\r\n"
					+ "	xmlns:mvc=\"http://www.springframework.org/schema/mvc\" xmlns:aop=\"http://www.springframework.org/schema/aop\"\r\n"
					+ "	xmlns:util=\"http://www.springframework.org/schema/util\" xmlns:dubbo=\"http://code.alibabatech.com/schema/dubbo\"\r\n"
					+ "	xsi:schemaLocation=\"http://www.springframework.org/schema/beans    \r\n" + "    http://www.springframework.org/schema/beans/spring-beans.xsd    \r\n"
					+ "    http://www.springframework.org/schema/tx    \r\n" + "    http://www.springframework.org/schema/tx/spring-tx.xsd   \r\n"
					+ "    http://www.springframework.org/schema/context   \r\n" + "    http://www.springframework.org/schema/context/spring-context.xsd   \r\n"
					+ "    http://www.springframework.org/schema/mvc   \r\n" + "    http://www.springframework.org/schema/mvc/spring-mvc.xsd\r\n"
					+ "    http://www.springframework.org/schema/aop \r\n" + "	http://www.springframework.org/schema/aop/spring-aop.xsd\r\n" + "	http://www.springframework.org/schema/util \r\n"
					+ "	http://www.springframework.org/schema/util/spring-util.xsd\r\n" + "	http://code.alibabatech.com/schema/dubbo\r\n"
					+ "    http://code.alibabatech.com/schema/dubbo/dubbo.xsd\">\r\n" + "\r\n" + "	<dubbo:service\r\n"
					+ "		interface=\"com.renke.rdbao.services.rdbao_2017.service.IAppNoticeService\"\r\n"
					+ "		provider=\"dubbo_provider_1\" accesslog=\"${rdbao.home}/logs/provider/app_notice_service/s.log\"\r\n" + "		timeout=\"15000\" ref=\"appNoticeService\">\r\n"
					+ "	</dubbo:service>\r\n" + "\r\n" + "	<bean id=\"appNoticeService\"\r\n" + "		class=\"com.renke.rdbao.services.rdbao_2017.service.impl.AppNoticeService\" />\r\n" + "\r\n"
					+ "</beans>    ";
			String javaName = file.getName().replace(".java", "");
			if (javaName.equals("enhanced")) {
				continue;
			}
			String classJava = "com.renke.rdbao.beans.rdbao_2017.pojo." + javaName;
			String showname = javaName.substring(0, 1).toLowerCase() + javaName.substring(1);

			Class class1 = Class.forName(classJava);
			Annotation annotation = class1.getAnnotation(Table.class);
			String tableName = (String) annotation.getClass().getDeclaredMethod("name", null).invoke(annotation, null);

			PrintWriter out = new PrintWriter("E:\\QQPCmgr\\Desktop\\javaprovider\\" + tableName + "_service_provider.xml");
			out.println(imp.replaceAll("AppNotice", javaName).replaceAll("appNoticeService", showname + "Service").replaceAll("app_notice", tableName));
			out.flush();
			out.close();
		}

	}
}
