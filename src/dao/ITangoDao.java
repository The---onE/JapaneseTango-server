package dao;

import java.util.List;

import entity.Tango;

public interface ITangoDao {
	List<Tango> selectAll();
	List<Tango> selectByType(String type);
}
