package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.Constants;
import entity.BaseEntity;
import entity.BaseEntity.InsertUtil;
import util.JDBCUtil;

public class BaseDao<Entity extends BaseEntity> {
	protected JDBCUtil util;
	protected Entity template; //空模版
	protected String tablename; //数据库中表名

	public BaseDao() {
		try {
			util = JDBCUtil.getInstance();
			util.connectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//根据ID查询数据
	@SuppressWarnings("unchecked")
	public Entity selectById(String idColumn, int id) {
		String sql = "SELECT * FROM " + tablename + " WHERE " + idColumn + " = ?";
		Object[] params = new Object[]{ id };
		try {
			ResultSet rs = util.executeQuery(sql, params);
			Entity entity = null;
			if (rs.next()) {
				entity = (Entity) template.convert(rs);
			}
			return entity;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//筛选查询数据
	public List<Entity> selectByCondition(Map<String, Object> condition) {
		String sql = "SELECT * FROM " + tablename;
		Object[] params = new Object[]{};
		if (condition != null && condition.size() > 0) {
			sql += " WHERE ";
			params = new Object[condition.size()];
			int i = 0;
			for(Map.Entry<String, Object> entry : condition.entrySet()){
				if (i == 0) {
					sql += entry.getKey() + " = ?";
				} else {
					sql += " AND " + entry.getKey() + " = ?";
				}
				params[i] = entry.getValue();
			    i++;
			}
		}
		if (Constants.DEBUG_FLAG) {
			Constants.showDebugLog(sql);
		}
		try {
			ResultSet rs = util.executeQuery(sql, params);
			List<Entity> list = new ArrayList<>();
			while (rs.next()) {
				@SuppressWarnings("unchecked")
				Entity entity = (Entity) template.convert(rs);
				list.add(entity);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//插入数据，autoKey表示主键是否自增
	public int insertEntity(Entity entity, boolean autoKey) {
		InsertUtil insert = entity.insertValue();
		int count = insert.values.length;
		String v = "?";
//		String p = insert.params[0];
		for (int i = 1; i < count; ++i) {
			v += ", ?";
//			p += ", " + insert.params[i];
		}
		String sql = "INSERT INTO " + tablename + " VALUES (" + v + ")";
		Object[] params = insert.values;
		int result = 0;
		try {
			if (autoKey) {
				result = util.executeUpdate(sql, params, true);
				if (result > 0) {
					int id = util.getGeneratedKey();
					return id;
				}
			} else {
				result = util.executeUpdate(sql, params, false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteEntity(String idColumn, int id) {
		String sql = "DELETE FROM " + tablename + " WHERE " + idColumn + " = ?";
		Object[] params = { id };
		int result = 0;
		try {
			result = util.executeUpdate(sql, params, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result > 0;
	}

	@Override
	protected void finalize() throws Throwable {
		util.closeConn();
		super.finalize();
	}
}
