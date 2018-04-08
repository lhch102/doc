package com.jzfq.rms.account.common;

import com.jzfq.rms.account.bean.BaseBean;

/**
 * 分页
 * @ClassName:  Page
 * @author: 大连桔子分期科技有限公司
 * @date:   2015年11月6日 下午3:34:15
 */
public abstract class Page extends BaseBean{

	/**
	 * 当前第几页；默认第1条
	 */
    private int pageNum = 1;
	/**
	 * 每页显示记录数，默认10条
	 */
    private int numPerPage = 10;
    
    public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public int getStartOfPage() {
		if (pageNum == 0) {
			// 页数是从第一页是从1开始计算的
			pageNum = 1;
		}
		return (pageNum - 1) * numPerPage;
	}
}
