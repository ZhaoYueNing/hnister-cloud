package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.News;
import cn.zynworld.hnister.common.domain.NewsExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface NewsMapper {
    int countByExample(NewsExample example);

    int deleteByExample(NewsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExampleWithBLOBsWithRowbounds(NewsExample example, RowBounds rowBounds);

    List<News> selectByExampleWithBLOBs(NewsExample example);

    List<News> selectByExampleWithRowbounds(NewsExample example, RowBounds rowBounds);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExampleWithBLOBs(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    int updateModuleIdIsNullByModuleId(@Param("moduleId") int moduleId);

}