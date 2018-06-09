package com.jzfq.rms.product.constant;
/**
 * 系统常数
 * @author 大连桔子分期科技有限公司
 */
public class RmsConstants {

    public final static int PAGESIZE = 10;// 默认显示行数

    public static final String COMMA = ",";

    public static final String DEFAULT_COUNTRY = "中国";

    public static final String CONTENT_TYPE = "application/json";

    public static final Byte DEFAULT_POST_TASK_STATE_0 = 0;
    public static final Byte DEFAULT_POST_TASK_STATE_1 = 1;

    /** RMS菜单**/
    public static final String PARENTS_ICONCLS = "right-icon x-fa fa-files-o";//一级菜单

    public static final String CHILD_ICONCLS = "right-icon x-fa fa-file-text-o";//二级菜单

    /**添加用户初始密码**/
    public static final String PASSWORD_DEFAUL = "jzfq@123456";

    /**字典TYPE**/
    public static final String DICTIONARY_TYPE = "juzi_interface_api";
    public static final String CLOSE_ORDER_DAY_TYPE = "close_order_day";
    public static final String CONTACTS_VALID_TYPE = "contacts_valid_type";
    public static final String ZMXY_SCORE_TYPE = "zmxy_score_type";
    public static final String XJD_ZMXY_SCORE_TYPE = "xjd_zmxy_score_type";
    public static final String QUOTA_SCALE = "quota_scale";
    public static final String MATCH_SCALE = "match_scale";
    public static final String XJD_MATCH_SCALE = "xjd_match_scale";
    public static final String CAR_DBXJD_ZMXY_SCORE_TYPE = "car_dbxjd_zmxy_score_type";
    /**图片验证码开关，0关闭验证，1打开验证开关**/
    public static final String IMAGE_CHECK_SWITCH = "image_check_switch";

    /**字典KEY**/
    public static final String DICTIONARY_KEY_RMS_TO_PUSH = "rmsToPush";
    public static final String DICTIONARY_KEY_RISK_RECEIVE = "riskReceive";

    public static final String DICTIONARY_KEY_NOTICE_TO_STORE = "riskNotice";
    public static final String DICTIONARY_KEY_DATAAUDIT_NOTICE_TO_STORE = "riskDataAuditNotice";
    public static final String DICTIONARY_KEY_NOTICE_TO_FMS_CAR = "riskFmsCarNotice";
    public static final String DICTIONARY_KEY_GET_ACCESS_TOKEN = "getAccessToken";
    public static final String DICTIONARY_KEY_GET_ACCESS_REPORT_DATA = "getAccessReportData";
    public static final String DICTIONARY_KEY_GET_ACCESS_RAW_DATA = "getAccessRawData";
    public static final String DICTIONARY_KEY_GET_ACCESS_BUSI_RAW_DATA = "getAccessBusiRawData";
    public static final String DICTIONARY_KEY_GET_ACCESS_REPORT_STATUS = "getAccessReportStatus";
    public static final String DICTIONARY_KEY_GET_PRODUCT = "getProduct";
    public static final String DICTIONARY_KEY_FRAUD_BLACK = "fraudBlack";
    public static final String DICTIONARY_KEY_GET_AREA = "getArea";
    public static final String DICTIONARY_KEY_GET_ALL_AREA = "getAllArea";
    public static final String DICTIONARY_KEY_GET_COLLEGE = "getCollege";
    public static final String DICTIONARY_KEY_ACCOUNT_INIT = "riskAccountInit";
    public static final String DICTIONARY_KEY_ACCOUNT_HISTORY_GET = "riskAccountHistoryGet";
    public static final String DICTIONARY_KEY_MULTI_ACCOUNT_HISTORY_GET = "riskMultiAccountHistoryGet";
    public static final String DICTIONARY_KEY_GET_PENGYUAN_URL = "getPengYuanUrl";
    public static final String DICTIONARY_KEY_ADD_TD_FRAUD = "addTdFraudBlack";
    public static final String DICTIONARY_KEY_TDSCORE_TO_DBCENTER = "pushTdScoreToDbCenter";
    public static final String DICTIONARY_KEY_SALE_FRAUD_BLACK = "saleFraudBlack";
    public static final String DICTIONARY_KEY_ADD_QUATA_FRAUD_BLACK = "addQuataFraudBlack";
    public static final String DICTIONARY_KEY_GET_GZT_URL = "getGuoZhengTongUrl";
    public static final String DICTIONARY_KEY_GET_HIS_ORDER_URL = "getHisOrderUrl";
    public static final String DICTIONARY_KEY_GET_HISTORY_ORDER_URL = "getHistoryOrderUrl";
    public static final String DICTIONARY_KEY_DATA_AUDIT_INIT = "riskDataAuditInit";
    public static final String DICTIONARY_KEY_GET_DEVICE = "getTdDevice";
    public static final String DICTIONARY_KEY_QUERY_DEVICE_BY_MOBILE = "queryDeviceByMobile";
    public static final String DICTIONARY_KEY_GET_DEVICE_MOBILE = "getDeviceMobile";
    public static final String DICTIONARY_KEY_GET_BLACK_PERIOD = "getBlackPeriod";
    public static final String DICTIONARY_KEY_GET_STU_TO_WHITE = "getIsStuChangeWhite";
    public static final String DICTIONARY_KEY_GET_DEVICE_NUMBER = "getDeviceNumber";
    public static final String DICTIONARY_KEY_GET_BLACK_PERIOD_BY_IDCARD = "getBlackPeriodByIdCard";
    public static final String DICTIONARY_KEY_GET_LOGIN_TD_SCORE = "getLoginTdScore";
    public static final String DICTIONARY_KEY_GET_IS_DEVICE_BLACK_URL = "validDeviceAddBlack";
    public static final String DICTIONARY_KEY_HAVE_OVERDUE = "haveOverdue";
    public static final String DICTIONARY_KEY_VALID_TD_FRAUD = "validTdDevice";
    public static final String DICTIONARY_KEY_CHECKORDER_LIMIT = "checkOrderLimit";
    public static final String DICTIONARY_KEY_OVERDUE_PERCENT = "overduePercent";
    public static final String DICTIONARY_KEY_OVERDUE_TIME = "overdueTime";
    public static final String DICTIONARY_KEY_OVERDUE_DAY = "overdueDay";
    public static final String DICTIONARY_KEY_OVERDUE_DAY_REPAID = "overdueDayRepaid";
    public static final String DICTIONARY_KEY_OVERDUE_DAY_XJD = "overdueDayXJD";
    public static final String DICTIONARY_KEY_EXTRACT_TYPE = "extract_add_limit";
    public static final String DICTIONARY_KEY_DEVICES_OR_ACCOUNTS_BY_MOBILE = "getDevicesOrAccountsByMobile";
    public static final String A_BR_CLOSE_SOCRE = "a_br_close_socre";
    public static final String B_BR_CLOSE_SCORE = "b_br_close_score";
    public static final String A_TD_CLOSE_SCORE = "a_td_close_score";
    public static final String B_TD_CLOSE_SCORE = "b_td_close_score";
    public static final String REGIST_NUMBER = "regist_number";
    public static final String XJD_REGIST_NUMBER = "xjd_regist_number";
    public static final String LOGIN_NUMBER = "login_number";
    public static final String XJD_LOGIN_NUMBER = "xjd_login_number";
    public static final String DEVICE_NUMBER = "device_number";
    public static final String CAR_DBXJD_INIT_DEVICE_NUMBER = "car_dbxjd_init_device_number";
    public static final String CAR_DBXJD_LOGIN_DEVICE_NUMBER = "car_dbxjd_login_device_number";
    public static final String XJD_DEVICE_NUMBER = "xjd_device_number";
    public static final String CONTACT_NUMBER = "contact_number";
    public static final String NO_APPROVE_AMOUNT = "no_approve_amount";
    public static final String EXTRACT_SUCCESS_URL = "extract_success_url";
    public static final String REGISTE_CODE = "registeCode";
    public static final String LINE_SCORE = "lineScore";
    public static final String MIN_AMOUNT = "minAmount";
    public static final String MAX_AMOUNT = "maxAmount";
    public static final String FYJJBB1 = "fyjjbb1";
    public static final String FYJJBB2 = "fyjjbb2";
    public static final String FHSJBB1 = "fhsjbb1";
    public static final String FHSJBB2 = "fhsjbb2";
    public static final String FHSJBB3 = "fhsjbb3";
    public static final String FHSJBB4 = "fhsjbb4";
    public static final String FRUND_BLACK_ORDER = "frundBlackOrder";
    public static final String FQZ_CLOSE_ORDER = "fqzCloseOrder";
    public static final String BR_CLOSE_ORDER = "brCloseOrder";
    public static final String TD_CLOSE_ORDER = "tdCloseOrder";
    public static final String TXL_CLOSE_ORDER = "txlCloseOrder";
    public static final String PHONE_THREE_CLOSE_ORDER = "phoneThreeCloseOrder";
    public static final String PHONE_PERIOD_CLOSE_ORDER = "phonePeriodCloseOrder";
    public static final String PHONE_STATUS_CLOSE_ORDER = "phoneStatusCloseOrder";
    public static final String ZMXY_CLOSE_ORDER = "zmxyCloseOrder";
    public static final String CONTACTS_CLOSE_ORDER = "contactsCloseOrder";
    public static final String CAR_VALUATION_CLOSE_ORDER = "carValuationCloseOrder";
    public static final String AREA_NOT_MATCH_CLOSE_ORDER = "areaNotMatchCloseOrder";
    public static final String QUOTA_NOT_MATCH_CLOSE_ORDER = "quotaNotMatchCloseOrder";
    public static final String JZ_CLOSE_ORDER = "jzCloseOrder";
    public static final String XJD_CLOSE_ORDER = "xjdCloseOrder";
    public static final String STU_VALID_NUM = "stu_valid_num";
    public static final String STU_VALID_RATE = "stu_valid_rate";
    public static final String WHITE_COLLAR_VALID_NUM = "white_collar_valid_num";
    public static final String WHITE_COLLAR_VALID_RATE = "white_collar_valid_rate";
    public static final String CAR_VALID_NUM = "car_valid_num";
    public static final String CAR_VALID_RATE = "car_valid_rate";
    public static final String CAR_SENSITIVE_NUM = "car_sensitive_num";
    public static final String ZMXY_SCORE_KEY = "zmxy_score_key";
    public static final String ZMXY_SCORE_CLOSE = "zmxy_score_close";
    public static final String XJD_ZMXY_SCORE_CLOSE = "xjd_zmxy_score_close";
    public static final String ONLY_CS = "only_cs";
    public static final String VALID_NUM = "valid_num";
    public static final String VALID_RATE = "valid_rate";
    public static final String IS_QUALITY_USER = "is_quality_user";
    public static final String TD_REGISTER_AND_LOGIN_CLOSE_CHECK = "tdRegisterAndLoginCloseCheck";
    public static final String TD_REGIST_SCORE_LIMIT = "td_regist_score_limit";
    public static final String XJD_TD_REGIST_SCORE_LIMIT = "xjd_td_regist_score_limit";
    public static final String CAR_DBXJD_TD_INIT_SCORE_LIMIT = "car_dbxjd_td_init_score_limit";
    public static final String TD_LOGIN_SCORE_LIMIT = "td_login_score_limit";
    public static final String XJD_TD_LOGIN_SCORE_LIMIT = "xjd_td_login_score_limit";
    public static final String CAR_DBXJD_TD_LOGIN_SCORE_LIMIT = "car_dbxjd_td_login_score_limit";
    public static final String TD_LOAN_SCORE_LIMIT = "td_loan_score_limit";
    public static final String XJD_TD_LOAN_SCORE_LIMIT = "xjd_td_loan_score_limit";
    public static final String CAR_DBXJD_TD_LOAN_SCORE_LIMIT = "car_dbxjd_td_loan_score_limit";
    public static final String GET_JXL_DATA_TIMEOUT_MINUTES = "get_jxl_data_timeout_minutes";
    public static final String XJD_GET_JXL_DATA_TIMEOUT_MINUT = "xjd_get_jxl_data_timeout_minut";
    public static final String VALID_CONTACT_NUM_LIMIT = "valid_contact_num_limit";
    public static final String CAR_DBXJD_CONTACT_NUM_LIMIT = "car_dbxjd_contact_num_limit";
    public static final String XJD_VALID_CONTACT_NUM_LIMIT = "xjd_valid_contact_num_limit";
    public static final String VALID_CONTACT_NUM_LIMIT_XJD = "valid_contact_num_limit_xjd";
    public static final String VALID_CONTACT_CALL_NUM_LIMIT = "valid_contact_call_num_limit";
    public static final String VALID_PHONE_BLACK = "validPhoneBlack";
    public static final String VALID_COMPANY_PHONE_BLACK = "validCompanyPhoneBlack";
    public static final String VALID_THREE_FACTORS_BLACK = "validThreeFactorsBlack";
    public static final String MATCH_SCALE_BLACK_ADDRESS = "match_scale_black_address";
    public static final String VALID_ADDRESS_BLACK = "validAddressBlack";
    public static final String FIRST_RELATE_CUSTOMER_NUM = "first_relate_customer_num";
    public static final String SECOND_RELATE_CUSTOMER_NUM = "second_relate_customer_num";
    public static final String COMPANY_NAME_CONTAIN_WORDS = "company_name_contain_words";
    public static final String COMPANY_NAME_UNCONTAIN_WORDS = "company_name_uncontain_words";
    public static final String QUOTA_SCALE_CAR = "quota_scale_car";
    public static final String QUOTA_SCALE_JUZI = "quota_scale_juzi";
    public static final String MATCH_SCALE_ADDRESS = "match_scale_address";
    public static final String VALID_CONTACTS_NUM = "valid_contacts_num";
    public static final String VALID_CONTACTS_RATE = "valid_contacts_rate";
    public static final String GET_CUSTOMER_INFO = "getCustomerInfo";
    public static final String UPDATE_BASE_CUSTOMER_INFO = "updateBaseCustomerInfo";
    public static final String UPDATE_COMPANY_CUSTOMER_INFO= "updateCompanyCustomerInfo";
    public static final String UPDATE_RELATION_CUSTOMER_INFO = "updateRelationCustomerInfo";
    public static final String UPDATE_CAR_INFO = "updateCarInfo";
    public static final String RMS_PUSH_URL = "rmsPushUrl";
    public static final String BR_CLOSE_WEIGHT_SCORE = "br_close_weight_score";
    public static final String BR_CLOSE_SCORE = "br_close_score";
    public static final String TD_CLOSE_SCORE_VIP = "td_close_score_vip";
    public static final String BR_CLOSE_SCORE_VIP = "br_close_score_vip";
    public static final String MAX_OVERDUE_DAYS = "max_overdue_days";
    public static final String OTHERS_MAX_OVERDUE_DAYS = "others_max_overdue_days";
    public static final String DEVICE_TYPE_36 = "device_type_36";
    public static final String DEVICE_TYPE_37 = "device_type_37";
    public static final String DEVICE_TYPE_38 = "device_type_38";
    public static final String DEVICE_TYPE_39 = "device_type_39";
    public static final String ANYONE_MAX_REGIST_NUMBER = "anyone_max_regist_number";
    public static final String ANYONE_MAX_LOGIN_NUMBER = "anyone_max_login_number";
    public static final String ALL_DEVICE_NUMBER = "all_device_number";
    public static final String LOGIN_ACCOUNT_NUMBER = "login_account_number";
    public static final String CAR_PRIVATE_PRICE = "car_private_price";
    public static final String AMOUNT_RULE_SWITCH = "amount_rule_switch";
    public static final String CONTACTS_RULE_SWITCH = "contacts_rule_switch";

    public static final String DICTIONARY_KEY_GET_IMAGE_SWITCH = "getImageSwitch";
    /**冻结俩端账户**/
    public static final String FREEZEACCOUNT = "freezeAccount";

    /** 根据客户id从核心获取客户当前可用余额 **/
    public static final String GETCANUSELIMIT = "getCanUseLimit";

    /**TOKEN有效期24h**/
    public static final Integer JXL_TOKEN_EXPIRED_HOUR = 24;

    /**获取用户报告数据**/
    public static final Byte JXL_REPORT_INTERFACE = 0;

    /**芝麻信用分数据**/
    public static final String ZMXY_MIN_SCORE = "zmxy_min_score";
    public static final String ZMXY_MAX_SCORE = "zmxy_max_score";
    public static final String ZMXY_SCORE_FLAG = "zmxy_score_flag";
    public static final String ZMXY_LIMIT = "zmxy_limit";

    /**获取增加额度**/
    public static final String EXTRACT_LIMIT_ONE = "ten_hundred";
    public static final String EXTRACT_LIMIT_TWO = "five_hundred";

    public static final String EXTRACT_BASE_INFO = "extract_base_info";
    public static final String EXTRACT_COMPANY_ONE = "extract_company_one";
    public static final String EXTRACT_COMPANY_TWO = "extract_company_two";
    public static final String EXTRACT_CONTACT_ONE = "extract_contact_one";
    public static final String EXTRACT_CONTACT_TWO = "extract_contact_two";
    public static final String EXTRACT_CAR_INFO = "extract_car_info";
    public static final String EXTRACT_BASE_INFO_PASS = "extract_base_info_pass";
    public static final String EXTRACT_CONTACT_INFO_PASS = "extract_contact_info_pass";
    public static final String EXTRACT_COMPANY_INFO_PASS = "extract_company_info_pass";
    public static final String EXTRACT_BASE_INFO_VIP = "extract_base_info_vip";
    public static final String EXTRACT_COMPANY_INFO_VIP = "extract_company_info_vip";
    public static final String EXTRACT_CONTACT_INFO_VIP = "extract_contact_info_vip";
    public static final String EXTRACT_CAR_QUOTA_VIP = "extract_car_quota_vip";
    public static final String JUZISCORE_CAR_QUOTA_VIP = "juziscore_car_quota_vip";
    public static final String EXTRACT_REGULAR_INFO = "extract_regular_info";
    public static final String EXTRACT_GET_AMOUNT = "extract_get_amount";
    public static final String EXTRACT_ADJUST_QUOTA = "extract_adjust_quota";
    public static final String EXTRACT_DAYS = "extract_days";

    /**企业内购**/
    public static final String  JZFQ_OWN_BUY="jzfq_own_buy";
    public static final String  OWN_BUY_LIMIT="own_buy_limit";
    public static final String  OUTER_BUY_LIMIT="outer_buy_limit";

    /**获取运营商数据**/
    public static final Byte JXL_RAW_INTERFACE = 1;

    /**获取电商数据**/
    public static final Byte JXL_BUSI_RAW_INTERFACE = 2;

    /**同盾是否闭单**/
    public static final Byte TD_FLAG_NORMAL = 0;
    public static final Byte TD_FLAG_CLOSED = 1;

    /**是否免签**/
    public static final Byte NO_SIGN = 1;
    public static final Byte SIGN_NORMAL = 0;

    /**是否免审**/
    public static final Byte NO_APPROVE = 1;
    public static final Byte APPROVE_NORMAL = 0;

    /**是否走免签免审规则**/
    public static final Byte SIGN_FLAG_0 = 0;
    public static final Byte SIGN_FLAG_1 = 1;

    /**额度计算限制参数**/
    public static final String MAXDAYLIMIT = "0";           //最大逾期天数限制
    public static final String RATE = "1";                  //逾期期数之和/总还款期数比例限制
    public static final String NOSETTLENUMBERLIMIT = "2";   //未结清的订单当前逾期的总次数限制
    public static final String NOSETTLEDAYLIMIT = "3";      //未结清的订单当前最大逾期天数
    public static final String NOSETTILEDIVTOTAL = "4";     //未结清订单已还期数/未结清订单期数之和

    /**是否聚信立认证，0未认证，1已认证**/
    public static final Byte IS_JXL_0 = 0;
    public static final Byte IS_JXL_1 = 1;


    /**计算额度时的产品线*/
    public static final Byte JUZI_FENQI_ORDER = 0;
    public static final Byte CHEZHU_BAITIAO_XJDB = 1;
    public static final Byte JUZI_SHANGCHENG_H5 = 2;


}
