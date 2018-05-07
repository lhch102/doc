package com.jzfq.rms.account.persistence.queue;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public interface IProducerService {

  public void sendMessage(String topic, String data);

  public void sendMessage(String topic, int key, String data);


}
