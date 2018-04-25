package cn.zynworld.hnister.hnisterbbsservice.mappers;

import cn.zynworld.hnister.hnisterbbsservice.domain.BBSTier;
import cn.zynworld.hnister.hnisterbbsservice.domain.BBSTierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BBSTierMapper {
    long countByExample(BBSTierExample example);

    int deleteByExample(BBSTierExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BBSTier record);

    int insertSelective(BBSTier record);

    List<BBSTier> selectByExampleWithBLOBsWithRowbounds(BBSTierExample example, RowBounds rowBounds);

    List<BBSTier> selectByExampleWithBLOBs(BBSTierExample example);

    List<BBSTier> selectByExampleWithRowbounds(BBSTierExample example, RowBounds rowBounds);

    List<BBSTier> selectByExample(BBSTierExample example);

    BBSTier selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BBSTier record, @Param("example") BBSTierExample example);

    int updateByExampleWithBLOBs(@Param("record") BBSTier record, @Param("example") BBSTierExample example);

    int updateByExample(@Param("record") BBSTier record, @Param("example") BBSTierExample example);

    int updateByPrimaryKeySelective(BBSTier record);

    int updateByPrimaryKeyWithBLOBs(BBSTier record);

    int updateByPrimaryKey(BBSTier record);
}