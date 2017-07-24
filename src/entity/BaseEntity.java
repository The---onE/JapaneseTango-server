package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public abstract class BaseEntity {
	//用于生成Insert语句
	public class InsertUtil {
//		public int count; //插入语句所需的参数个数
//		public String[] params; //插入语句所需的参数
		public Object[] values; //插入语句的值
	}
	
	//将一条查询结果转化为实体
	public abstract BaseEntity convert(ResultSet rs) throws SQLException;
	
	//用于在数据库中插入数据
	public abstract InsertUtil insertValue();
	
	//生成JSON对象
	public abstract JSONObject convertToJSON() throws JSONException;
}
