package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.RoleResourceRelaExample;
import cn.zynworld.hnister.common.domain.RoleResourceRelaKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RoleResourceRelaMapper {
    int countByExample(RoleResourceRelaExample example);

    int deleteByExample(RoleResourceRelaExample example);

    int deleteByPrimaryKey(RoleResourceRelaKey key);

    int insert(RoleResourceRelaKey record);

    int insertSelective(RoleResourceRelaKey record);

    List<RoleResourceRelaKey> selectByExampleWithRowbounds(RoleResourceRelaExample example, RowBounds rowBounds);

    List<RoleResourceRelaKey> selectByExample(RoleResourceRelaExample example);

    int updateByExampleSelective(@Param("record") RoleResourceRelaKey record, @Param("example") RoleResourceRelaExample example);

    int updateByExample(@Param("record") RoleResourceRelaKey record, @Param("example") RoleResourceRelaExample example);
}