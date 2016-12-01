package com.yu.yubase.constant;

public class DB {
	public static final int VERSION = 1;
	public static final String DB_NAME = "dolabank.db";
	public static final String DB_PACKAGE ="com.jinxqc.lcapp";
	public static final String DB_PATH ="/data/data/"+DB_PACKAGE+"/databases/"+DB_NAME;
	
	public static final int ID_USER = 1 ;
	public static final int ID_BANKCARD = 2 ;
	
	//用户数据表
	public static final String SQL_CREATE_TB_USER = "CREATE TABLE if not exists user(id [TEXT], phone [TEXT], name [TEXT], email [TEXT], token [TEXT], imageurl [TEXT], reg_time [TEXT]);";
	
	public static final String TB_USER ="user";
	public static final String USER_ITEM_ID ="id";
	public static final String USER_ITEM_NAME ="name";
	public static final String USER_ITEM_EMAIL ="email";
	public static final String USER_ITEM_TOKEN ="token";
	public static final String USER_ITEM_IMAGEURL = "imageurl";
	public static final String USER_ITEM_PHONE = "phone";
	public static final String USER_ITEM_REGTIME = "reg_time";
	
	//银行卡数据表
	public static final String SQL_CREATE_TB_BANKCARD = "CREATE TABLE if not exists bankcard(number [TEXT], identification [TEXT], bank [TEXT], province [TEXT], city [TEXT], district [TEXT], status [TEXT], phone [TEXT], real_name [TEXT], sub_branch [TEXT]);";
	
	public static final String TB_BANKCARD ="bankcard";
	public static final String BANKCARD_ITEM_NUMBER ="number";
	public static final String BANKCARD_ITEM_IDENTIFICATION ="identification";
	public static final String BANKCARD_ITEM_BANK ="bank";
	public static final String BANKCARD_ITEM_PROVINCE ="province";
	public static final String BANKCARD_ITEM_CITY ="city";
	public static final String BANKCARD_ITEM_DISTRICT = "district";
	public static final String BANKCARD_ITEM_STATUS = "status";
	public static final String BANKCARD_ITEM_PHONE = "phone";
	public static final String BANKCARD_REAL_NAME = "real_name";
	public static final String BANKCARD_SUB_BRANCH = "sub_branch";
}

