package cn.zynworld.hnister.news.mappers;

import cn.zynworld.hnister.news.domain.NewsModule;
import cn.zynworld.hnister.news.domain.NewsModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface NewsModuleMapper {
    long countByExample(NewsModuleExample example);

    int deleteByExample(NewsModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NewsModule record);

    int insertSelective(NewsModule record);

    List<NewsModule> selectByExampleWithRowbounds(NewsModuleExample example, RowBounds rowBounds);

    List<NewsModule> selectByExample(NewsModuleExample example);

    NewsModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NewsModule record, @Param("example") NewsModuleExample example);

    int updateByExample(@Param("record") NewsModule record, @Param("example") NewsModuleExample example);

    int updateByPrimaryKeySelective(NewsModule record);

    int updateByPrimaryKey(NewsModule record);

	int updateChangeNumberByExample(int val, NewsModuleExample newsModuleExample);
}