package cn.zynworld.hnister.security.mappers;

import cn.zynworld.hnister.security.domain.RoleResourceRelaExample;
import cn.zynworld.hnister.security.domain.RoleResourceRelaKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleResourceRelaMapper {
    long countByExample(RoleResourceRelaExample example);

    int deleteByExample(RoleResourceRelaExample example);

    int deleteByPrimaryKey(RoleResourceRelaKey key);

    int insert(RoleResourceRelaKey record);

    int insertSelective(RoleResourceRelaKey record);

    List<RoleResourceRelaKey> selectByExampleWithRowbounds(RoleResourceRelaExample example, RowBounds rowBounds);

    List<RoleResourceRelaKey> selectByExample(RoleResourceRelaExample example);

    int updateByExampleSelective(@Param("record") RoleResourceRelaKey record, @Param("example") RoleResourceRelaExample example);

    int updateByExample(@Param("record") RoleResourceRelaKey record, @Param("example") RoleResourceRelaExample example);
}