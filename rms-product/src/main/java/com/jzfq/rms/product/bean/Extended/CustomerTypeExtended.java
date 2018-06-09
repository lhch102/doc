package com.jzfq.rms.product.bean.Extended;

import com.jzfq.rms.product.bean.ConfigMobile;
import com.jzfq.rms.product.bean.LatitudeLongitude;
import com.jzfq.rms.product.bean.RegistCode;
import com.jzfq.rms.product.common.Page;
import com.jzfq.rms.product.common.PageData;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/17 14:15
 */
public class CustomerTypeExtended extends Page {
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 状态名称
     */
    private String statusName;
    /**
     * 手机号列表
     */
    private List<ConfigMobile> configMobileList;
    /**
     * 手机号分页列表
     */
    private PageData<ConfigMobile> configMobilePage;
    /**
     * F码列表
     */
    private List<RegistCode> registCodeList;
    /**
     * F码分页列表
     */
    private PageData<RegistCode> registCodePage;
    /**
     * 经纬度列表
     */
    private List<LatitudeLongitude> latitudeLongitudeList;
    /**
     * 经纬度分页
     */
    private PageData<LatitudeLongitude> latitudeLongitudePage;
    /**
     * 渠道名称字典项
     */
    private List<Map<String, Object>> channelNameList;
    /**
     * 状态名称字典项
     */
    private List<Map<String, Object>> statusNameList;
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<ConfigMobile> getConfigMobileList() {
        return configMobileList;
    }

    public void setConfigMobileList(List<ConfigMobile> configMobileList) {
        this.configMobileList = configMobileList;
    }

    public List<RegistCode> getRegistCodeList() {
        return registCodeList;
    }

    public void setRegistCodeList(List<RegistCode> registCodeList) {
        this.registCodeList = registCodeList;
    }

    public List<LatitudeLongitude> getLatitudeLongitudeList() {
        return latitudeLongitudeList;
    }

    public void setLatitudeLongitudeList(List<LatitudeLongitude> latitudeLongitudeList) {
        this.latitudeLongitudeList = latitudeLongitudeList;
    }

    public PageData<ConfigMobile> getConfigMobilePage() {
        return configMobilePage;
    }

    public void setConfigMobilePage(PageData<ConfigMobile> configMobilePage) {
        this.configMobilePage = configMobilePage;
    }

    public PageData<RegistCode> getRegistCodePage() {
        return registCodePage;
    }

    public void setRegistCodePage(PageData<RegistCode> registCodePage) {
        this.registCodePage = registCodePage;
    }

    public PageData<LatitudeLongitude> getLatitudeLongitudePage() {
        return latitudeLongitudePage;
    }

    public void setLatitudeLongitudePage(PageData<LatitudeLongitude> latitudeLongitudePage) {
        this.latitudeLongitudePage = latitudeLongitudePage;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Map<String, Object>> getChannelNameList() {
        return channelNameList;
    }

    public void setChannelNameList(List<Map<String, Object>> channelNameList) {
        this.channelNameList = channelNameList;
    }

    public List<Map<String, Object>> getStatusNameList() {
        return statusNameList;
    }

    public void setStatusNameList(List<Map<String, Object>> statusNameList) {
        this.statusNameList = statusNameList;
    }
}
