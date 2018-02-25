package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerTestHistoryPo;
import com.tenacity.free.project.manager.po.example.TenacityFreeProjectManagerTestHistoryPoExample;
import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerTestHistoryPoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TenacityFreeProjectManagerTestHistoryPoMapper {
    int countByExample(TenacityFreeProjectManagerTestHistoryPoExample example);

    int deleteByExample(TenacityFreeProjectManagerTestHistoryPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TenacityFreeProjectManagerTestHistoryPoWithBLOBs record);

    int insertSelective(TenacityFreeProjectManagerTestHistoryPoWithBLOBs record);

    List<TenacityFreeProjectManagerTestHistoryPoWithBLOBs> selectByExampleWithBLOBs(TenacityFreeProjectManagerTestHistoryPoExample example);

    List<TenacityFreeProjectManagerTestHistoryPo> selectByExample(TenacityFreeProjectManagerTestHistoryPoExample example);

    TenacityFreeProjectManagerTestHistoryPoWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TenacityFreeProjectManagerTestHistoryPoWithBLOBs record, @Param("example") TenacityFreeProjectManagerTestHistoryPoExample example);

    int updateByExampleWithBLOBs(@Param("record") TenacityFreeProjectManagerTestHistoryPoWithBLOBs record, @Param("example") TenacityFreeProjectManagerTestHistoryPoExample example);

    int updateByExample(@Param("record") TenacityFreeProjectManagerTestHistoryPo record, @Param("example") TenacityFreeProjectManagerTestHistoryPoExample example);

    int updateByPrimaryKeySelective(TenacityFreeProjectManagerTestHistoryPoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TenacityFreeProjectManagerTestHistoryPoWithBLOBs record);

    int updateByPrimaryKey(TenacityFreeProjectManagerTestHistoryPo record);
}