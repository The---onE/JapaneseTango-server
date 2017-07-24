package common;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import entity.BaseEntity;

public class Constants {
	//是否显示调试信息
	public static final boolean DEBUG_FLAG = true;
	//在控制台中输出调试内容
	public static void showDebugLog(String s) {
		System.out.println("Debug:" + s);
	}
	//在控制台中输出内容
	public static void showLog(String s) {
		System.out.println("Log:" + s);
	}
	//在控制台中输出内容
	public static void show(String s) {
		System.out.println(s);
	}
	
	public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/";
	public static final String DATABASE_NAME = "japanese_tango";
	public static final String MYSQL_USERNAME = "root";
	public static final String MYSQL_PASSWORD = "19951109";
	
	public static final String RESPONSE_STATUS = "status";
	public static final String STATUS_ERROR = "0";
	public static final String STATUS_EXECUTE_SUCCESS = "1";
	public static final String STATUS_QUERY_SUCCESS = "2";
	public static final String RESPONSE_PROMPT = "prompt";
	public static final String RESPONSE_ENTITIES = "entities";
	
	public static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{3,16}$";
	public static final String PASSWORD_REGEX = "^[a-zA-Z0-9`~!@#$%^&*()_+=]{6-18}$";
	
	public static <Entity extends BaseEntity> JSONObject createJSONResult(List<Entity> result) throws JSONException {
		JSONObject res = new JSONObject();
		
		if (result != null) {
			if (result.size() > 0) {
				res.put(Constants.RESPONSE_STATUS, Constants.STATUS_QUERY_SUCCESS);
				res.put(Constants.RESPONSE_PROMPT, "获取成功");
				JSONArray entityList = new JSONArray();
				for (Entity article : result) {
					entityList.put(article.convertToJSON());
				}
				res.put(Constants.RESPONSE_ENTITIES, entityList);
			} else {
				res.put(Constants.RESPONSE_STATUS, Constants.STATUS_EXECUTE_SUCCESS);
				res.put(Constants.RESPONSE_PROMPT, "暂无数据");
			}
		} else {
			res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
			res.put(Constants.RESPONSE_PROMPT, "查询失败");
		}
		
		return res;
	}
	
	public static boolean isEmpty(String s) {
		return s==null || s.trim().equals(""); 
	}
}
