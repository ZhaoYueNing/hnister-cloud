package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.Zlass;
import cn.zynworld.hnister.common.domain.ZlassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ZlassMapper {
    long countByExample(ZlassExample example);

    int deleteByExample(ZlassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Zlass record);

    int insertSelective(Zlass record);

    List<Zlass> selectByExampleWithRowbounds(ZlassExample example, RowBounds rowBounds);

    List<Zlass> selectByExample(ZlassExample example);

    Zlass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Zlass record, @Param("example") ZlassExample example);

    int updateByExample(@Param("record") Zlass record, @Param("example") ZlassExample example);

    int updateByPrimaryKeySelective(Zlass record);

    int updateByPrimaryKey(Zlass record);
}