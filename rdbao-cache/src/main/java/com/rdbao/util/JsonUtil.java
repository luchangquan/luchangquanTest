package com.rdbao.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author jgshun
 * @date 2017-1-3 下午7:22:02
 * @version 1.0 {@link JSONObject}
 */
@Deprecated
public class JsonUtil {

	private static Gson gson = new Gson();
	private static JsonParser parser = new JsonParser();

	/**
	 * @Description: 对象转json
	 * @author 徐国飞
	 * @throws Exception
	 * @date 2015-3-25 上午11:13:01 String
	 */
	public static String obj2Json(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * @Description: json转对象
	 * @author 徐国飞
	 * @throws Exception
	 * @date 2015-3-25 上午11:14:39 T
	 */
	public static <T> T json2Obj(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	/**
	 * @Description: json转对象
	 * @author 徐国飞
	 * @throws Exception
	 * @date 2015-3-25 上午11:14:39 T
	 */
	public static <T> T json2Obj(JsonElement json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	/**
	 * @Description: json转对象<泛型类型>
	 * @author 徐国飞
	 * @date 2015-9-12 下午3:38:53 T
	 */
	public static <T> T json2Obj4ReflectType(String json, TypeToken<T> typeToken) {
		return gson.fromJson(json, typeToken.getType());
	}

	/**
	 * @Description: json转对象集合
	 * @author 徐国飞
	 * @date 2015-5-19 下午3:12:26 List<T>
	 */
	public static <T> List<T> parseList(String jsonArray, Class<T> clazz) {
		List<T> dataList = new ArrayList<T>();
		JsonElement el = parser.parse(jsonArray);
		JsonArray array = null;
		if (el.isJsonArray()) {
			array = el.getAsJsonArray();
		}
		Iterator<JsonElement> it = array.iterator();
		while (it.hasNext()) {
			JsonElement e = (JsonElement) it.next();
			// JsonElement转换为JavaBean对象
			dataList.add(gson.fromJson(e, clazz));
		}
		return dataList;
	}

	/**
	 * @Description: 直接解析json对象
	 * @author 徐国飞
	 * @date 2015-8-18 下午4:00:55 String
	 */
	public static String parseJsonGetNode(String jsonData, String node) {
		JsonObject jsonObj = new JsonParser().parse(jsonData).getAsJsonObject();
		if (jsonObj == null || jsonObj.get(node) == null) {
			return null;
		}
		if (jsonObj.get(node) == null) {
			return null;
		}
		JsonElement jsonElement = jsonObj.get(node);
		if (jsonElement == null) {
			return null;
		}
		return jsonElement.getAsString();
	}

	/**
	 * @Description: 直接解析json对象
	 * @author 徐国飞
	 * @date 2015-8-18 下午4:00:55 String
	 */
	public static JsonElement parseJsonGetJsonElement(String jsonData, String node) {
		JsonObject jsonObj = new JsonParser().parse(jsonData).getAsJsonObject();
		if (jsonObj == null || jsonObj.get(node) == null) {
			return null;
		}
		if (jsonObj.get(node) == null) {
			return null;
		}
		JsonElement jsonElement = jsonObj.get(node);
		return jsonElement;
	}

	/**
	 * @Description: json转对象集合
	 * @author 徐国飞
	 * @date 2015-5-19 下午3:12:26 List<T>
	 */
	public static <T> List<T> parseList(JsonElement jsonArray, Class<T> clazz) {
		List<T> dataList = new ArrayList<T>();
		JsonArray array = null;
		if (jsonArray.isJsonArray()) {
			array = jsonArray.getAsJsonArray();
		} else {
			JsonElement el = parser.parse(jsonArray.getAsString());
			if (el.isJsonArray()) {
				array = el.getAsJsonArray();
			}
		}
		Iterator<JsonElement> it = array.iterator();
		while (it.hasNext()) {
			JsonElement e = (JsonElement) it.next();
			// JsonElement转换为JavaBean对象
			dataList.add(gson.fromJson(e, clazz));
		}
		return dataList;
	}

	/**
	 * @Description: 解析json对象写入节点
	 * @author 徐国飞
	 * @date 2015-8-25 上午11:45:39 String
	 */
	public static String parseJsonPutNode(String jsonData, String node, String value) {
		JsonObject jsonObj = new JsonParser().parse(jsonData).getAsJsonObject();
		if (jsonObj == null) {
			return null;
		}
		jsonObj.addProperty(node, value);
		return jsonObj.toString();
	}

	/**
	 * @Description: json对象写入节点
	 * @author 徐国飞
	 * @date 2015-8-25 上午11:45:39 String
	 */
	public static void putNode(JsonObject jsonObj, String node, String value) {
		if (jsonObj == null) {
			return;
		}
		jsonObj.addProperty(node, value);
	}

	/**
	 * @Description: 解析json字符串为json对象
	 * @author 徐国飞
	 * @date 2015-8-25 上午11:46:11 JsonObject
	 */
	public static JsonObject parse2JsonObject(String jsonData) {
		JsonObject jsonObj = new JsonParser().parse(jsonData).getAsJsonObject();
		return jsonObj;
	}

	/**
	 * @Description: 解析json对象
	 * @author 徐国飞
	 * @date 2015-8-25 上午11:53:06 String
	 */
	public static String getJsonNode(JsonObject jsonObj, String node) {
		if (jsonObj == null || jsonObj.get(node) == null) {
			return null;
		}
		if (jsonObj.get(node) == null) {
			return null;
		}
		return jsonObj.get(node).getAsString();
	}

	public static JsonElement getJsonObjectNode(JsonObject jsonObj, String node) {
		if (jsonObj == null || jsonObj.get(node) == null) {
			return null;
		}
		// JsonElement em = jsonObj.get(node);
		// if(em instanceof JsonObject){
		//
		// }
		return jsonObj.get(node);
	}

	public static void main(String[] args) {
		/*
		 * String str = "{'name':'hh'}"; JsonObject jo = parse2JsonObject(str);
		 * System.out.println(jo); putNode(jo, "age", "1"); putNode(jo, "age",
		 * "3"); System.out.println(jo);
		 * System.out.println(parseJsonPutNode(str, "age", "1"));
		 */
		String str = "{\"AppCode\":\"AppPicture\",\"EvidenceCategoryId\":\"20\",\"Data\":{\"TakeTime\":\"2016/02/25 10:52:54\",\"EvidenceCode\":\"001160200008\"},\"Location\":\"/data/Proof/evidencesNew/20/13917664945/2016/0225/13917664945_3333.jpg\",\"UserAccount\":\"13917664945\",\"EvidenceFileLength\":\"0\",\"EvidRecordViewUrl\":\"http://192.168.3.252:9009/rdbaobargin/appEvidences/goAppPictureRecordViewContent/MTQ1NjIyMzYxMTEzODE5MzdfJl9udWxs_54E85087C80A9D0C0BEA05F0C8BE4CB6\"}";
		JsonElement dataJson = JsonUtil.getJsonObjectNode(JsonUtil.parse2JsonObject(str), "Data");
		System.out.println(dataJson);
		System.out.println(JsonUtil.parseJsonGetNode(str, "EvidenceCode"));
	}

}
