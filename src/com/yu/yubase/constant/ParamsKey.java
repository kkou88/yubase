package com.yu.yubase.constant;

/**
 * @author Admin
 *
 */
public class ParamsKey {
	public final static int RESULT_STATUS_SUCCESS = 1;
	public final static int RESULT_STATUS_FAIL = 0;
	public final static int RESULT_STATUS_SESSION_EXPIRED = -999;
	
	/**
	 * 1.2.5版51活动
	 * 注册
	 */
	public final static String  KEY_IS_NEW="is_new";
	
//	public final static String KEY_IS_SEND_APP_REGREAL="is_send_app_regreal"; 
	public final static String KEY_IS_SEND_APP_REGREAL="is_send_app_regreal"; 
	/**
	 * 实名认证
	 */
	public final static String KEY_IS_SEND_APP_REAL="is_send_app_real"; 
	/**
	 * 投资
	 */
	public final static String KEY_IS_SEND_APP_INVEST="is_send_app_invest"; 
	/**
	 * 充值
	 */
	public final static String KEY_IS_SEND_APP_CHARGE="is_send_app_charge"; 
	/**
	 * 绑定银行卡
	 */
	public final static String KEY_IS_SEND_APP_BANK="is_send_app_bank"; 
	
	public final static String KEY_DATA = "data";
	public final static String KEY_STATUS = "status";
	public final static String KEY_MESSAGE = "message";

	public final static String KEY_PAGE = "page";
	public final static String KEY_PAGE2 = "p";
	public final static String RESULT_PAGE = "current_page";//current_page

	public final static String KEY_BANKCARD1 = "key";
	public final static String KEY_BANKCARD2 = "server";

	// 首页
	public final static String KEY_INVEST_TOTAL = "invest_total";// 累计投资(万)
	public final static String KEY_SUM_REPAY = "sum_repay";// 累计已还(万)
	public final static String KEY_REPAY_TOTAL = "repay_total";// 投资者已赚取收益(万)
	public final static String KEY_NEW_BORROW = "new_borrow";// 宝利通最新标
	public final static String KEY_BAN = "ban";// bannner图url数组
	public final static String KEY_BAN_ID = "id";//
	public final static String KEY_BAN_ADS_NAME = "ads_name";//
	public final static String KEY_BAN_END_TIME = "end_time";//
	public final static String KEY_BAN_LINKURL = "linkurl";//
	public final static String KEY_BAN_PICURL = "picurl";//
	public final static String KEY_BAN_START_TIME = "start_time";//
	public final static String KEY_BAN_ORDERID = "orderid";//
	public final static String KEY_BAN_TYPE_ID = "type_id";//

	// 欢迎 吐槽
	public final static String KEY_DEVID = "device";
	public final static String KEY_CONTENT = "content";

	// 注册
	public final static String KEY_USER_AUTHCODE = "usr_authcode";
	public final static String KEY_USER_PHONE = "user_phone";
	public final static String KEY_USER_PASSWORD = "user_password";
	public final static String KEY_ACTION = "action";
	public final static String KEY_ACTION_REGISTER = "register";
	public final static String KEY_ACTION_SMSCODE = "code_url";
	public final static String KEY = "key";

	// GetSecuritySet账户设置
	public final static String KEY_USER_INFO = "user_info";
	public final static String KEY_REAL_DETAIL = "real_detail";
	public final static String KEY_REAL_NAME = "real_name";
	public final static String KEY_IDCARD = "idcard";
	public final static String RESULT_USER_BANKCARDS = "user_bankcard";
	public final static String RESULT_USER_IMAGE_URL = "user_images";
	public final static String RESULT_USER_EMAIL = "user_email";
	public final static String RESULT_USER_PHONE = "user_phone";

	// 登录
	public final static String RESULT_USER_ID = "uid";
	public final static String KEY_BID = "bid";
	public final static String RESULT_USER_NAME = "user_name";
	public final static String RESULT_USER_TOKEN = "token";
	public final static String RESULT_USER_LOG_TIME = "user_timestamp";

	public final static String KEY_USR_REAL_NAME = "user_realname";// 用户真实姓名
	public final static String KEY_USR_ID_CARD = "user_idcard";// 用户身份证号
	public final static String KEY_USR_INVITE_CODE = "invite_code";// 用户身份证号
	public final static String KEY_USR_CODE = "user_code";// 验证码信息

	// 上传头像
	public final static String KEY_USER_TOKEN = "token";
	public final static String RESULT_IMAGE_URL = "img_url";

	public final static String KEY_RED_ID = "red_id";//红包id
	public final static String KEY_AMOUNT = "amout";//投资金额
	public final static String KEY_BORROW_ID = "borrow_id";//标的号
	public final static String KEY_CHARGE_MONEY = "charge_money";//充值金额
	public final static String KEY_WITH_DRAW_MONEY = "money";//提现金额
	public final static String KEY_QUICK_STATUS = "quick_status";//提现金额

	public final static String KEY_TRADE_IP = "trade_ip";

	public final static String KEY_BANK_CARD_NUMBER = "bank_card";
	public final static String KEY_BANK_CODE = "bank_code";
	public final static String KEY_BANK_BRANCH = "bank_branch";

	// 产品中心--投资
	public final static String KEY_BORROW_NAME = "borrow_name";// 名字
	public final static String KEY_BORROW_DURATION = "borrow_duration";// 期限
	public final static String KEY_BORROW_INTEREST_RATE = "borrow_interest_rate";// 年利率
	public final static String KEY_ADD_TIME = "add_time";// 发布日期
	public final static String KEY_BORROW_MIN = "borrow_min";// 起投金额
	public final static String KEY_BORROW_MONEY = "borrow_money";// 可投金额
	public final static String KEY_HAS_BORROW = "has_borrow";// 已经投资

	// 用户中心数据
	public final static String RESULT_USER_TOTAL_MONEY = "total_money";
	// 用户中心可用余额
	public final static String RESULT_USER_ACCOUNT_MONEY = "account_money";

	// 设置安全密码
	public final static String KEY_SAFE_PASSWORD = "usr_safe_password";

	// 更改密码
	public final static String KEY_OLD_PASSWORD = "user_oldPassword";
	public final static String KEY_NEW_PASSWORD = "user_newPassword";
//	public final static String KEY_USR_PASSWORD_TYPE = "usr_password_type";// 0修改的是登陆密码,1修改的是安全密码

	// 投资记录
	public final static String KEY_RANG_OF_TIME = "range_of_time";

	// 收支明细
	public final static String KEY_TIME_TYPE = "time_type";
	public final static String KEY_DETAIL_TYPE = "detail_type";

	// 银行卡绑定
	public final static String KEY_USR_BANKNAME = "bankname";// 银行名称
	public final static String KEY_PROVINCE = "province";// 省份
	public final static String KEY_CITY = "city";// 城市
	public final static String KEY_AREA = "area";// 地区
	public final static String KEY_BANKADDRESS = "bankaddress";// 开户银行支行名称
	public final static String KEY_BANKID = "bankid";// 银行卡号

	// 绑定邮箱
	public final static String KEY_MAIL = "email";// 邮箱
	public final static String KEY_MAIL_OLD = "old_email";// 原来邮箱
	public final static String KEY_MAIL_NEW = "new_email";// 新邮箱

	// 提现
	public final static String KEY_MONEY = "money";// 金额

	// 开启关闭自动循环投资
	public final static String KEY_TYPE = "type";// 类型

	// 获取公告内容
	public final static String KEY_ID = "id";// id
	// 红包id
	public final static String KEY_RID = "rid";// id
	
	// 密码标
	public final static String KEY_PASSWORD = "password";// password
	
	//修改推荐人
	public final static String KEY_NUM="num";
	//充值第三方支付密码
	public final static String KEY_SMSCODE="smsCode";
	
	
	public final static String KEY_PAYMENT_WAIT = "total_dhk";// 待回款金额
	public final static String KEY_FREEZE_MONEY = "total_djz";// 冻结金额
	public final static String KEY_CAN_USE_MONEY = "account_money";//  可用余额
	public final static String KEY_TOTAL_MONEY = "total_money";//总资产
	public final static String KEY_TOTAL_GET_MONEY = "total_ljsy";// 累计收益
	
	public final static String KEY_SCORES = "scores";// 积分
	public final static String KEY_JINXB = "jinxiu_money";// 锦绣币
	
}

