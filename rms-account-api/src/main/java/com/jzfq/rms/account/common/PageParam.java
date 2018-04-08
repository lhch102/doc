package com.jzfq.rms.account.common;

import java.io.Serializable;

/**
 * 数据库、内存分页对象. 包含当前页数据及分页信息， T为返回列表中的类型，P为入参的类型。
 * @ClassName:  PageParam
 * @author: 大连桔子分期科技有限公司
 * @date:   2015年11月3日 下午3:17:08
 */
public class PageParam<P> implements Serializable
{
    private static final long serialVersionUID = 1L;

    public final static int DEFAULT_PAGE_No = 1;
    public final static int DEFAULT_PAGE_SIZE = 20;

    /**
     * 以Bean形式包装查询条件（不含参数：页号，每页记录数，总记录数，总分页数，是否有前一页，否有下一页）
     */
    private P p;

    /**
     * 跳转页数，页数是从第一页是从1开始计算的
     */
    private int pageNo = DEFAULT_PAGE_No;

    /**
     * 每页的记录数(每页尺寸)
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 总记录数
     */
    private int dataTotal = 0;

    public PageParam()
    {

    }

    public PageParam(P p, int pageNo, int pageSize)
    {
        super();
        this.p = p;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public P getP()
    {
        return p;
    }

    public void setP(P p)
    {
        this.p = p;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getDataTotal()
    {
        return dataTotal;
    }

    public void setDataTotal(int dataTotal)
    {
        this.dataTotal = dataTotal;
    }

    @Override
    public String toString()
    {
        return "PageParam [p=" + p + ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", dataTotal=" + dataTotal + "]";
    }

}
