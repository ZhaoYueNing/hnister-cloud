package cn.zynworld.hnister.hnisterbbsservice.mappers;

import cn.zynworld.hnister.hnisterbbsservice.domain.BBSTopic;
import cn.zynworld.hnister.hnisterbbsservice.domain.BBSTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BBSTopicMapper {
    long countByExample(BBSTopicExample example);

    int deleteByExample(BBSTopicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BBSTopic record);

    int insertSelective(BBSTopic record);

    List<BBSTopic> selectByExampleWithRowbounds(BBSTopicExample example, RowBounds rowBounds);

    List<BBSTopic> selectByExample(BBSTopicExample example);

    BBSTopic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BBSTopic record, @Param("example") BBSTopicExample example);

    int updateByExample(@Param("record") BBSTopic record, @Param("example") BBSTopicExample example);

    int updateByPrimaryKeySelective(BBSTopic record);

    int updateByPrimaryKey(BBSTopic record);
}