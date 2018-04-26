package cn.zynworld.hnister.bbs.mappers;

import cn.zynworld.hnister.bbs.domain.BBSPlate;
import cn.zynworld.hnister.bbs.domain.BBSPlateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BBSPlateMapper {
    long countByExample(BBSPlateExample example);

    int deleteByExample(BBSPlateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BBSPlate record);

    int insertSelective(BBSPlate record);

    List<BBSPlate> selectByExampleWithRowbounds(BBSPlateExample example, RowBounds rowBounds);

    List<BBSPlate> selectByExample(BBSPlateExample example);

    BBSPlate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BBSPlate record, @Param("example") BBSPlateExample example);

    int updateByExample(@Param("record") BBSPlate record, @Param("example") BBSPlateExample example);

    int updateByPrimaryKeySelective(BBSPlate record);

    int updateByPrimaryKey(BBSPlate record);
}