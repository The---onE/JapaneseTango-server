package common;

public class Constants {
	public static final boolean DEBUG_FLAG = true;
	
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
}
