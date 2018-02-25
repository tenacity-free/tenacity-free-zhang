package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerUserPermissionPo;
import com.tenacity.free.project.manager.po.example.TenacityFreeProjectManagerUserPermissionPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TenacityFreeProjectManagerUserPermissionPoMapper {
    int countByExample(TenacityFreeProjectManagerUserPermissionPoExample example);

    int deleteByExample(TenacityFreeProjectManagerUserPermissionPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TenacityFreeProjectManagerUserPermissionPo record);

    int insertSelective(TenacityFreeProjectManagerUserPermissionPo record);

    List<TenacityFreeProjectManagerUserPermissionPo> selectByExample(TenacityFreeProjectManagerUserPermissionPoExample example);

    TenacityFreeProjectManagerUserPermissionPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TenacityFreeProjectManagerUserPermissionPo record, @Param("example") TenacityFreeProjectManagerUserPermissionPoExample example);

    int updateByExample(@Param("record") TenacityFreeProjectManagerUserPermissionPo record, @Param("example") TenacityFreeProjectManagerUserPermissionPoExample example);

    int updateByPrimaryKeySelective(TenacityFreeProjectManagerUserPermissionPo record);

    int updateByPrimaryKey(TenacityFreeProjectManagerUserPermissionPo record);
}