package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerProjectPo;
import com.tenacity.free.project.manager.po.example.TenacityFreeProjectManagerProjectPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TenacityFreeProjectManagerProjectPoMapper {
    int countByExample(TenacityFreeProjectManagerProjectPoExample example);

    int deleteByExample(TenacityFreeProjectManagerProjectPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TenacityFreeProjectManagerProjectPo record);

    int insertSelective(TenacityFreeProjectManagerProjectPo record);

    List<TenacityFreeProjectManagerProjectPo> selectByExample(TenacityFreeProjectManagerProjectPoExample example);

    TenacityFreeProjectManagerProjectPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TenacityFreeProjectManagerProjectPo record, @Param("example") TenacityFreeProjectManagerProjectPoExample example);

    int updateByExample(@Param("record") TenacityFreeProjectManagerProjectPo record, @Param("example") TenacityFreeProjectManagerProjectPoExample example);

    int updateByPrimaryKeySelective(TenacityFreeProjectManagerProjectPo record);

    int updateByPrimaryKey(TenacityFreeProjectManagerProjectPo record);
}