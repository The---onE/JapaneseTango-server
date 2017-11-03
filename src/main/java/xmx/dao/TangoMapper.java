package xmx.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import xmx.model.Tango;
import xmx.model.TangoExample;

public interface TangoMapper {
    long countByExample(TangoExample example);

    int deleteByExample(TangoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tango record);

    int insertSelective(Tango record);

    List<Tango> selectByExampleWithRowbounds(TangoExample example, RowBounds rowBounds);

    List<Tango> selectByExample(TangoExample example);

    Tango selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tango record, @Param("example") TangoExample example);

    int updateByExample(@Param("record") Tango record, @Param("example") TangoExample example);

    int updateByPrimaryKeySelective(Tango record);

    int updateByPrimaryKey(Tango record);
}