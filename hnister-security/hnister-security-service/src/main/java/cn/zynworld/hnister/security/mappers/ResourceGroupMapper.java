package cn.zynworld.hnister.security.mappers;

import cn.zynworld.hnister.security.domain.ResourceGroup;
import cn.zynworld.hnister.security.domain.ResourceGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ResourceGroupMapper {
    long countByExample(ResourceGroupExample example);

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