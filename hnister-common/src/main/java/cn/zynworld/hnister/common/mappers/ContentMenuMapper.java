package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.ContentMenu;
import cn.zynworld.hnister.common.domain.ContentMenuExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ContentMenuMapper {
    int countByExample(ContentMenuExample example);

    int deleteByExample(ContentMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContentMenu record);

    int insertSelective(ContentMenu record);

    List<ContentMenu> selectByExampleWithRowbounds(ContentMenuExample example, RowBounds rowBounds);

    List<ContentMenu> selectByExample(ContentMenuExample example);

    ContentMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContentMenu record, @Param("example") ContentMenuExample example);

    int updateByExample(@Param("record") ContentMenu record, @Param("example") ContentMenuExample example);

    int updateByPrimaryKeySelective(ContentMenu record);

    int updateByPrimaryKey(ContentMenu record);
}