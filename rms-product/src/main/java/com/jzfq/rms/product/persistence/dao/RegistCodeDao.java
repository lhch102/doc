package com.jzfq.rms.product.persistence.dao;

import com.jzfq.rms.product.bean.RegistCode;
import com.jzfq.rms.product.common.PageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistCodeDao {
    int deleteByPrimaryKey(@Param("ids") List<Integer> ids);

    int insert(RegistCode record);

    int insertSelective(RegistCode record);

    List<RegistCode> selectByPrimaryKey(@Param("tacticsKQId") String tacticsKQId);

    int updateByPrimaryKeySelective(RegistCode record);

    int updateByPrimaryKey(RegistCode record);

    /**
     * 分页查询F码列表
     * @param registCodePage
     * @return
     */
    List<RegistCode> queryList(PageParam<RegistCode> registCodePage);
}