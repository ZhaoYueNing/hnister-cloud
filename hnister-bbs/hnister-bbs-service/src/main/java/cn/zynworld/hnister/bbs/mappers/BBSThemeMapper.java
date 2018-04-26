package cn.zynworld.hnister.bbs.mappers;

import cn.zynworld.hnister.bbs.domain.BBSTheme;
import cn.zynworld.hnister.bbs.domain.BBSThemeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BBSThemeMapper {
    long countByExample(BBSThemeExample example);

    int deleteByExample(BBSThemeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BBSTheme record);

    int insertSelective(BBSTheme record);

    List<BBSTheme> selectByExampleWithRowbounds(BBSThemeExample example, RowBounds rowBounds);

    List<BBSTheme> selectByExample(BBSThemeExample example);

    BBSTheme selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BBSTheme record, @Param("example") BBSThemeExample example);

    int updateByExample(@Param("record") BBSTheme record, @Param("example") BBSThemeExample example);

    int updateByPrimaryKeySelective(BBSTheme record);

    int updateByPrimaryKey(BBSTheme record);
}