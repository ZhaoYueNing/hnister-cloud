package cn.zynworld.hnister.security.mappers;

import cn.zynworld.hnister.security.domain.RoleUserRelaExample;
import cn.zynworld.hnister.security.domain.RoleUserRelaKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleUserRelaMapper {
    long countByExample(RoleUserRelaExample example);

    int deleteByExample(RoleUserRelaExample example);

    int deleteByPrimaryKey(RoleUserRelaKey key);

    int insert(RoleUserRelaKey record);

    int insertSelective(RoleUserRelaKey record);

    List<RoleUserRelaKey> selectByExampleWithRowbounds(RoleUserRelaExample example, RowBounds rowBounds);

    List<RoleUserRelaKey> selectByExample(RoleUserRelaExample example);

    int updateByExampleSelective(@Param("record") RoleUserRelaKey record, @Param("example") RoleUserRelaExample example);

    int updateByExample(@Param("record") RoleUserRelaKey record, @Param("example") RoleUserRelaExample example);
}