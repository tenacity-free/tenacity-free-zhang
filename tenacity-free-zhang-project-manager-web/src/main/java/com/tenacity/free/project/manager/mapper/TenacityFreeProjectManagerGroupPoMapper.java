package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerGroupPo;
import com.tenacity.free.project.manager.po.example.TenacityFreeProjectManagerGroupPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TenacityFreeProjectManagerGroupPoMapper {
    int countByExample(TenacityFreeProjectManagerGroupPoExample example);

    int deleteByExample(TenacityFreeProjectManagerGroupPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TenacityFreeProjectManagerGroupPo record);

    int insertSelective(TenacityFreeProjectManagerGroupPo record);

    List<TenacityFreeProjectManagerGroupPo> selectByExample(TenacityFreeProjectManagerGroupPoExample example);

    TenacityFreeProjectManagerGroupPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TenacityFreeProjectManagerGroupPo record, @Param("example") TenacityFreeProjectManagerGroupPoExample example);

    int updateByExample(@Param("record") TenacityFreeProjectManagerGroupPo record, @Param("example") TenacityFreeProjectManagerGroupPoExample example);

    int updateByPrimaryKeySelective(TenacityFreeProjectManagerGroupPo record);

    int updateByPrimaryKey(TenacityFreeProjectManagerGroupPo record);
}