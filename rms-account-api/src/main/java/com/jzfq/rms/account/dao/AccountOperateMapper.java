package com.jzfq.rms.account.dao;

import com.jzfq.rms.account.bean.AccountOperate;
import com.jzfq.rms.account.common.PageParam;
import com.jzfq.rms.account.web.requestModel.AccountMenuRequestModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface AccountOperateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountOperate record);

    int insertSelective(AccountOperate record);

    AccountOperate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountOperate record);

    int updateByPrimaryKey(AccountOperate record);

    /**
     * 分页查询操作权限数据
     *
     * @param params
     * @return
     */
    List<AccountOperate> findOperaters(PageParam<AccountMenuRequestModel> params);

    /**
     * 删除操作权限数据
     *
     * @param enable,ids
     * @return
     */
    int updateEnable(@Param("list") List<String> list, @Param("enable") String enable);


    /**
     * 添加操作权限数据返回主键
     *
     * @param record
     * @return
     */
    int insertOperate(AccountOperate record);

    public List<AccountOperate> selectByMenuNo(HashMap<String, Object> paramNo);

    /**
     * 操作权限  启用/停用  方法
     * @param paramMap
     */
    public void updateOperRelation(HashMap<String, Object> paramMap);

    /**
     *
     * @param paramMap
     * @return
     */
    public List<AccountOperate> selectByType(HashMap<String, Object> paramMap);
}