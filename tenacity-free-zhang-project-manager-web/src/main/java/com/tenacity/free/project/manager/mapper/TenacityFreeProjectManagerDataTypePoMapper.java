package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerDataTypePo;
import com.tenacity.free.project.manager.po.example.TenacityFreeProjectManagerDataTypePoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TenacityFreeProjectManagerDataTypePoMapper {
    int countByExample(TenacityFreeProjectManagerDataTypePoExample example);

    int deleteByExample(TenacityFreeProjectManagerDataTypePoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TenacityFreeProjectManagerDataTypePo record);

    int insertSelective(TenacityFreeProjectManagerDataTypePo record);

    List<TenacityFreeProjectManagerDataTypePo> selectByExample(TenacityFreeProjectManagerDataTypePoExample example);

    TenacityFreeProjectManagerDataTypePo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TenacityFreeProjectManagerDataTypePo record, @Param("example") TenacityFreeProjectManagerDataTypePoExample example);

    int updateByExample(@Param("record") TenacityFreeProjectManagerDataTypePo record, @Param("example") TenacityFreeProjectManagerDataTypePoExample example);

    int updateByPrimaryKeySelective(TenacityFreeProjectManagerDataTypePo record);

    int updateByPrimaryKey(TenacityFreeProjectManagerDataTypePo record);
}