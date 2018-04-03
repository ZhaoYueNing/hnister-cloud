package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.BBSPlate;
import cn.zynworld.hnister.common.domain.BBSPlateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BBSPlateMapper {
    long countByExample(BBSPlateExample example);

    int deleteByExample(BBSPlateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BBSPlate record);

    int insertSelective(BBSPlate record);

    List<BBSPlate> selectByExample(BBSPlateExample example);

    BBSPlate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BBSPlate record, @Param("example") BBSPlateExample example);

    int updateByExample(@Param("record") BBSPlate record, @Param("example") BBSPlateExample example);

    int updateByPrimaryKeySelective(BBSPlate record);

    int updateByPrimaryKey(BBSPlate record);
}