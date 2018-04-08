package com.jzfq.rms.account.persistence.queue;


/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public interface IConsumerService {

  public void onMessage();

  //设置监听
  public void afterPropertiesSet() throws Exception;
}