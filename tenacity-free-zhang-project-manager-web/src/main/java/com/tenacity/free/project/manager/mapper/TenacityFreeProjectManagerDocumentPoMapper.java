package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerDocumentPo;
import com.tenacity.free.project.manager.po.example.TenacityFreeProjectManagerDocumentPoExample;
import com.tenacity.free.project.manager.po.TenacityFreeProjectManagerDocumentPoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TenacityFreeProjectManagerDocumentPoMapper {
    int countByExample(TenacityFreeProjectManagerDocumentPoExample example);

    int deleteByExample(TenacityFreeProjectManagerDocumentPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TenacityFreeProjectManagerDocumentPoWithBLOBs record);

    int insertSelective(TenacityFreeProjectManagerDocumentPoWithBLOBs record);

    List<TenacityFreeProjectManagerDocumentPoWithBLOBs> selectByExampleWithBLOBs(TenacityFreeProjectManagerDocumentPoExample example);

    List<TenacityFreeProjectManagerDocumentPo> selectByExample(TenacityFreeProjectManagerDocumentPoExample example);

    TenacityFreeProjectManagerDocumentPoWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TenacityFreeProjectManagerDocumentPoWithBLOBs record, @Param("example") TenacityFreeProjectManagerDocumentPoExample example);

    int updateByExampleWithBLOBs(@Param("record") TenacityFreeProjectManagerDocumentPoWithBLOBs record, @Param("example") TenacityFreeProjectManagerDocumentPoExample example);

    int updateByExample(@Param("record") TenacityFreeProjectManagerDocumentPo record, @Param("example") TenacityFreeProjectManagerDocumentPoExample example);

    int updateByPrimaryKeySelective(TenacityFreeProjectManagerDocumentPoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TenacityFreeProjectManagerDocumentPoWithBLOBs record);

    int updateByPrimaryKey(TenacityFreeProjectManagerDocumentPo record);
}