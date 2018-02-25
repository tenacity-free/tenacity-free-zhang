package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerUserPo;
import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerUserPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TenacityFreeProjectManagerUserPoMapper {
    int countByExample(TenacityFreeProjectManagerUserPoExample example);

    int deleteByExample(TenacityFreeProjectManagerUserPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TenacityFreeProjectManagerUserPo record);

    int insertSelective(TenacityFreeProjectManagerUserPo record);

    List<TenacityFreeProjectManagerUserPo> selectByExample(TenacityFreeProjectManagerUserPoExample example);

    TenacityFreeProjectManagerUserPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TenacityFreeProjectManagerUserPo record, @Param("example") TenacityFreeProjectManagerUserPoExample example);

    int updateByExample(@Param("record") TenacityFreeProjectManagerUserPo record, @Param("example") TenacityFreeProjectManagerUserPoExample example);

    int updateByPrimaryKeySelective(TenacityFreeProjectManagerUserPo record);

    int updateByPrimaryKey(TenacityFreeProjectManagerUserPo record);
}