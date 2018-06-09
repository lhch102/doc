package com.jzfq.rms.product.persistence.dao;

import com.jzfq.rms.product.bean.LatitudeLongitude;
import com.jzfq.rms.product.common.PageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LatitudeLongitudeDao {
    int deleteByPrimaryKey(@Param("ids") List<Integer> ids);

    int insert(LatitudeLongitude record);

    int insertSelective(LatitudeLongitude record);

    List<LatitudeLongitude> selectByPrimaryKey(@Param("tacticsKQId") String tacticsKQId);

    int updateByPrimaryKeySelective(LatitudeLongitude record);

    int updateByPrimaryKey(LatitudeLongitude record);

    /**
     * 分页查询经纬度列表
     * @param latitudeLongitudePageParam
     * @return
     */
    List<LatitudeLongitude> queryList(PageParam<LatitudeLongitude> latitudeLongitudePageParam);
}