package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.RoleUserRelaExample;
import cn.zynworld.hnister.common.domain.RoleUserRelaKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RoleUserRelaMapper {
    int countByExample(RoleUserRelaExample example);

    int deleteByExample(RoleUserRelaExample example);

    int deleteByPrimaryKey(RoleUserRelaKey key);

    int insert(RoleUserRelaKey record);

    int insertSelective(RoleUserRelaKey record);

    List<RoleUserRelaKey> selectByExampleWithRowbounds(RoleUserRelaExample example, RowBounds rowBounds);

    List<RoleUserRelaKey> selectByExample(RoleUserRelaExample example);

    int updateByExampleSelective(@Param("record") RoleUserRelaKey record, @Param("example") RoleUserRelaExample example);

    int updateByExample(@Param("record") RoleUserRelaKey record, @Param("example") RoleUserRelaExample example);
}