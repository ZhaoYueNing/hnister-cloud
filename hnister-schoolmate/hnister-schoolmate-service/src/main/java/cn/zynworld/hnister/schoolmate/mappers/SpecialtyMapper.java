package cn.zynworld.hnister.schoolmate.mappers;

import cn.zynworld.hnister.schoolmate.domain.Specialty;
import cn.zynworld.hnister.schoolmate.domain.SpecialtyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SpecialtyMapper {
    long countByExample(SpecialtyExample example);

    int deleteByExample(SpecialtyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Specialty record);

    int insertSelective(Specialty record);

    List<Specialty> selectByExampleWithRowbounds(SpecialtyExample example, RowBounds rowBounds);

    List<Specialty> selectByExample(SpecialtyExample example);

    Specialty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Specialty record, @Param("example") SpecialtyExample example);

    int updateByExample(@Param("record") Specialty record, @Param("example") SpecialtyExample example);

    int updateByPrimaryKeySelective(Specialty record);

    int updateByPrimaryKey(Specialty record);
}