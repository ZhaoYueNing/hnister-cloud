package cn.zynworld.hnister.schoolmate.mappers;

import cn.zynworld.hnister.schoolmate.domain.College;
import cn.zynworld.hnister.schoolmate.domain.CollegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CollegeMapper {
    long countByExample(CollegeExample example);

    int deleteByExample(CollegeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(College record);

    int insertSelective(College record);

    List<College> selectByExampleWithRowbounds(CollegeExample example, RowBounds rowBounds);

    List<College> selectByExample(CollegeExample example);

    College selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") College record, @Param("example") CollegeExample example);

    int updateByExample(@Param("record") College record, @Param("example") CollegeExample example);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);
}