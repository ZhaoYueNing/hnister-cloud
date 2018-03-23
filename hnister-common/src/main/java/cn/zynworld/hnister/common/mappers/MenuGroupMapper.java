package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.MenuGroup;
import cn.zynworld.hnister.common.domain.MenuGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuGroupMapper {
    long countByExample(MenuGroupExample example);

    int deleteByExample(MenuGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MenuGroup record);

    int insertSelective(MenuGroup record);

    List<MenuGroup> selectByExample(MenuGroupExample example);

    MenuGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MenuGroup record, @Param("example") MenuGroupExample example);

    int updateByExample(@Param("record") MenuGroup record, @Param("example") MenuGroupExample example);

    int updateByPrimaryKeySelective(MenuGroup record);

    int updateByPrimaryKey(MenuGroup record);
}