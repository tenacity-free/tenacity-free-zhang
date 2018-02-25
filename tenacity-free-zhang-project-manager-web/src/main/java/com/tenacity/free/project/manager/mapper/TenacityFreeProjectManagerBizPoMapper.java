package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerBizPo;
import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerBizPoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TenacityFreeProjectManagerBizPoMapper {
    int countByExample(TenacityFreeProjectManagerBizPoExample example);

    int deleteByExample(TenacityFreeProjectManagerBizPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TenacityFreeProjectManagerBizPo record);

    int insertSelective(TenacityFreeProjectManagerBizPo record);

    List<TenacityFreeProjectManagerBizPo> selectByExample(TenacityFreeProjectManagerBizPoExample example);

    TenacityFreeProjectManagerBizPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TenacityFreeProjectManagerBizPo record, @Param("example") TenacityFreeProjectManagerBizPoExample example);

    int updateByExample(@Param("record") TenacityFreeProjectManagerBizPo record, @Param("example") TenacityFreeProjectManagerBizPoExample example);

    int updateByPrimaryKeySelective(TenacityFreeProjectManagerBizPo record);

    int updateByPrimaryKey(TenacityFreeProjectManagerBizPo record);
}