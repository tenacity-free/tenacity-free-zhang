package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerMockPo;
import com.tenacity.free.project.manager.po.example.TenacityFreeProjectManagerMockPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TenacityFreeProjectManagerMockPoMapper {
    int countByExample(TenacityFreeProjectManagerMockPoExample example);

    int deleteByExample(TenacityFreeProjectManagerMockPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TenacityFreeProjectManagerMockPo record);

    int insertSelective(TenacityFreeProjectManagerMockPo record);

    List<TenacityFreeProjectManagerMockPo> selectByExampleWithBLOBs(TenacityFreeProjectManagerMockPoExample example);

    List<TenacityFreeProjectManagerMockPo> selectByExample(TenacityFreeProjectManagerMockPoExample example);

    TenacityFreeProjectManagerMockPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TenacityFreeProjectManagerMockPo record, @Param("example") TenacityFreeProjectManagerMockPoExample example);

    int updateByExampleWithBLOBs(@Param("record") TenacityFreeProjectManagerMockPo record, @Param("example") TenacityFreeProjectManagerMockPoExample example);

    int updateByExample(@Param("record") TenacityFreeProjectManagerMockPo record, @Param("example") TenacityFreeProjectManagerMockPoExample example);

    int updateByPrimaryKeySelective(TenacityFreeProjectManagerMockPo record);

    int updateByPrimaryKeyWithBLOBs(TenacityFreeProjectManagerMockPo record);

    int updateByPrimaryKey(TenacityFreeProjectManagerMockPo record);
}