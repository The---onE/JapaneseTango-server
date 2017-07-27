package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import dao.ITangoDao;
import entity.Tango;

public class TangoDao extends BaseDao<Tango> implements ITangoDao {
	
	public TangoDao() {
		super();
		template = new Tango();
		tablename = "tango";
	}

	@Override
	public List<Tango> selectAll() {
		return selectByCondition(null);
	}

	@Override
	public List<Tango> selectByType(String type) {
		Map<String, Object> con = new HashMap<>();
		if (!type.trim().equals("")) {
			con.put("type", type);
		}
		return selectByCondition(con);
	}

}
