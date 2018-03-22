package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.ContentMenuGroup;
import cn.zynworld.hnister.common.domain.ContentMenuGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContentMenuGroupMapper {
    long countByExample(ContentMenuGroupExample example);

    int deleteByExample(ContentMenuGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContentMenuGroup record);

    int insertSelective(ContentMenuGroup record);

    List<ContentMenuGroup> selectByExample(ContentMenuGroupExample example);

    ContentMenuGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContentMenuGroup record, @Param("example") ContentMenuGroupExample example);

    int updateByExample(@Param("record") ContentMenuGroup record, @Param("example") ContentMenuGroupExample example);

    int updateByPrimaryKeySelective(ContentMenuGroup record);

    int updateByPrimaryKey(ContentMenuGroup record);
}