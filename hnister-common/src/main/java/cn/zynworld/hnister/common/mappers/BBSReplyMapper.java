package cn.zynworld.hnister.common.mappers;

import cn.zynworld.hnister.common.domain.BBSReply;
import cn.zynworld.hnister.common.domain.BBSReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BBSReplyMapper {
    long countByExample(BBSReplyExample example);

    int deleteByExample(BBSReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BBSReply record);

    int insertSelective(BBSReply record);

    List<BBSReply> selectByExampleWithBLOBs(BBSReplyExample example);

    List<BBSReply> selectByExample(BBSReplyExample example);

    BBSReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BBSReply record, @Param("example") BBSReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") BBSReply record, @Param("example") BBSReplyExample example);

    int updateByExample(@Param("record") BBSReply record, @Param("example") BBSReplyExample example);

    int updateByPrimaryKeySelective(BBSReply record);

    int updateByPrimaryKeyWithBLOBs(BBSReply record);

    int updateByPrimaryKey(BBSReply record);
}