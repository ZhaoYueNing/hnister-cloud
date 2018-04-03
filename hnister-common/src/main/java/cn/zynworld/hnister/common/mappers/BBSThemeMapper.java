package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.BBSTheme;
import cn.zynworld.hnister.common.domain.BBSThemeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BBSThemeMapper {
    long countByExample(BBSThemeExample example);

    int deleteByExample(BBSThemeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BBSTheme record);

    int insertSelective(BBSTheme record);

    List<BBSTheme> selectByExampleWithRowbounds(BBSThemeExample example, RowBounds rowBounds);

    List<BBSTheme> selectByExample(BBSThemeExample example);

    BBSTheme selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BBSTheme record, @Param("example") BBSThemeExample example);

    int updateByExample(@Param("record") BBSTheme record, @Param("example") BBSThemeExample example);

    int updateByPrimaryKeySelective(BBSTheme record);

    int updateByPrimaryKey(BBSTheme record);
}