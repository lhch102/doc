package com.jzfq.rms.product.persistence.dao;

import com.jzfq.rms.product.bean.ConfigMobile;
import com.jzfq.rms.product.common.PageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigMobileDao {
    int deleteByPrimaryKey(@Param("ids") List<Integer> ids);

    int insert(ConfigMobile record);

    int insertSelective(ConfigMobile record);

    List<ConfigMobile> selectByPrimaryKey(@Param("tacticsKQId") String tacticsKQId);

    int updateByPrimaryKeySelective(ConfigMobile record);

    int updateByPrimaryKey(ConfigMobile record);

    /**
     * 分页查询手机号列表
     * @param mobilePage
     * @return
     */
    List<ConfigMobile> queryList(PageParam<ConfigMobile> mobilePage);
}