package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.BBSTier;
import cn.zynworld.hnister.common.domain.BBSTierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BBSTierMapper {
    long countByExample(BBSTierExample example);

    int deleteByExample(BBSTierExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BBSTier record);

    int insertSelective(BBSTier record);

    List<BBSTier> selectByExampleWithBLOBs(BBSTierExample example);

    List<BBSTier> selectByExample(BBSTierExample example);

    BBSTier selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BBSTier record, @Param("example") BBSTierExample example);

    int updateByExampleWithBLOBs(@Param("record") BBSTier record, @Param("example") BBSTierExample example);

    int updateByExample(@Param("record") BBSTier record, @Param("example") BBSTierExample example);

    int updateByPrimaryKeySelective(BBSTier record);

    int updateByPrimaryKeyWithBLOBs(BBSTier record);

    int updateByPrimaryKey(BBSTier record);
}