package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.BBSTopic;
import cn.zynworld.hnister.common.domain.BBSTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BBSTopicMapper {
    long countByExample(BBSTopicExample example);

    int deleteByExample(BBSTopicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BBSTopic record);

    int insertSelective(BBSTopic record);

    List<BBSTopic> selectByExampleWithBLOBsWithRowbounds(BBSTopicExample example, RowBounds rowBounds);

    List<BBSTopic> selectByExampleWithBLOBs(BBSTopicExample example);

    List<BBSTopic> selectByExampleWithRowbounds(BBSTopicExample example, RowBounds rowBounds);

    List<BBSTopic> selectByExample(BBSTopicExample example);

    BBSTopic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BBSTopic record, @Param("example") BBSTopicExample example);

    int updateByExampleWithBLOBs(@Param("record") BBSTopic record, @Param("example") BBSTopicExample example);

    int updateByExample(@Param("record") BBSTopic record, @Param("example") BBSTopicExample example);

    int updateByPrimaryKeySelective(BBSTopic record);

    int updateByPrimaryKeyWithBLOBs(BBSTopic record);

    int updateByPrimaryKey(BBSTopic record);
}