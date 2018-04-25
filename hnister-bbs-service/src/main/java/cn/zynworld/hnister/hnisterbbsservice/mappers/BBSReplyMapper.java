package cn.zynworld.hnister.hnisterbbsservice.mappers;

import cn.zynworld.hnister.hnisterbbsservice.domain.BBSReply;
import cn.zynworld.hnister.hnisterbbsservice.domain.BBSReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BBSReplyMapper {
    long countByExample(BBSReplyExample example);

    int deleteByExample(BBSReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BBSReply record);

    int insertSelective(BBSReply record);

    List<BBSReply> selectByExampleWithBLOBsWithRowbounds(BBSReplyExample example, RowBounds rowBounds);

    List<BBSReply> selectByExampleWithBLOBs(BBSReplyExample example);

    List<BBSReply> selectByExampleWithRowbounds(BBSReplyExample example, RowBounds rowBounds);

    List<BBSReply> selectByExample(BBSReplyExample example);

    BBSReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BBSReply record, @Param("example") BBSReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") BBSReply record, @Param("example") BBSReplyExample example);

    int updateByExample(@Param("record") BBSReply record, @Param("example") BBSReplyExample example);

    int updateByPrimaryKeySelective(BBSReply record);

    int updateByPrimaryKeyWithBLOBs(BBSReply record);

    int updateByPrimaryKey(BBSReply record);
}