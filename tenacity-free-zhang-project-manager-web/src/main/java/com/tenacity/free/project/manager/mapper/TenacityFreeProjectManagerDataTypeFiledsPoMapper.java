package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerDataTypeFiledsPo;
import com.tenacity.free.project.manager.po.example.TenacityFreeProjectManagerDataTypeFiledsPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TenacityFreeProjectManagerDataTypeFiledsPoMapper {
    int countByExample(TenacityFreeProjectManagerDataTypeFiledsPoExample example);

    int deleteByExample(TenacityFreeProjectManagerDataTypeFiledsPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TenacityFreeProjectManagerDataTypeFiledsPo record);

    int insertSelective(TenacityFreeProjectManagerDataTypeFiledsPo record);

    List<TenacityFreeProjectManagerDataTypeFiledsPo> selectByExample(TenacityFreeProjectManagerDataTypeFiledsPoExample example);

    TenacityFreeProjectManagerDataTypeFiledsPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TenacityFreeProjectManagerDataTypeFiledsPo record, @Param("example") TenacityFreeProjectManagerDataTypeFiledsPoExample example);

    int updateByExample(@Param("record") TenacityFreeProjectManagerDataTypeFiledsPo record, @Param("example") TenacityFreeProjectManagerDataTypeFiledsPoExample example);

    int updateByPrimaryKeySelective(TenacityFreeProjectManagerDataTypeFiledsPo record);

    int updateByPrimaryKey(TenacityFreeProjectManagerDataTypeFiledsPo record);
}