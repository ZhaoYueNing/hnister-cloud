package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.ResourceGroup;
import cn.zynworld.hnister.common.domain.ResourceGroupExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ResourceGroupMapper {
    int countByExample(ResourceGroupExample example);

    int deleteByExample(ResourceGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ResourceGroup record);

    int insertSelective(ResourceGroup record);

    List<ResourceGroup> selectByExampleWithRowbounds(ResourceGroupExample example, RowBounds rowBounds);

    List<ResourceGroup> selectByExample(ResourceGroupExample example);

    ResourceGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ResourceGroup record, @Param("example") ResourceGroupExample example);

    int updateByExample(@Param("record") ResourceGroup record, @Param("example") ResourceGroupExample example);

    int updateByPrimaryKeySelective(ResourceGroup record);

    int updateByPrimaryKey(ResourceGroup record);
}