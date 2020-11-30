package com.baizhi.dao;


import java.util.List;

import com.baizhi.entity.VideoExample;
import org.apache.ibatis.annotations.Param;

public interface videoExampleMapper {
    long countByExample(VideoExample example);

    int deleteByExample(VideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoExample record);

    int insertSelective(VideoExample record);

    List<VideoExample> selectByExample(VideoExample example);

    int updateByExampleSelective(@Param("record") VideoExample record, @Param("example") VideoExample example);

    int updateByExample(@Param("record") VideoExample record, @Param("example") VideoExample example);
}