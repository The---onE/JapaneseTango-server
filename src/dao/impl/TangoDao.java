package dao.impl;

import java.util.List;

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

}
