package com.yu.yubase.entities;

import com.yu.jar.yujar.Y;
import com.yu.yubase.BaseApplication;
import com.yu.yubase.MainActivity;

public class ErrorMessage {
	private int type;
	private String message = "";

	public final static int TYPE_NETWORK_DISCONNECT = 0; // 网络未连接
	public final static int TYPE_SERVER_RETURN = 1; // 服务器返回错误
	public final static int TYPE_DATA_EMPTY = 2; // 返回空数据
	public final static int TYPE_UNABLE_TO_DECRYPT = 3; // 解密数据出错
	public final static int TYPE_UNABLE_TO_ANALYZE = 4; // 解析数据出错
	public final static int TYPE_SERVER_ERROR = 5; // volley报出的错误，大部分是服务器的问题，如连接超时
	public final static int TYPE_SESSION_EXPIRED = -999; // 会话过期
	public final static int TYPE_LOGIN_READLY = -888; // 已登录

	public ErrorMessage(int type) {
		this.type = type;
	}

	public ErrorMessage(int type, String message) {
		this.type = type;
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String show() {
		switch (type) {
		case TYPE_NETWORK_DISCONNECT:
			return "网络未连接";
		case TYPE_SERVER_RETURN:
			return message;
		case TYPE_SESSION_EXPIRED:
			return "会话信息已经过期，请重新登录";
		case TYPE_LOGIN_READLY:
			return "您已重复登录，请先退出之前的登陆，继续操作";
		default:
			return "暂时无法获取数据，请稍后重试";
		}
	}

	public void showToast() {
		String temp;
		switch (type) {
		case TYPE_NETWORK_DISCONNECT:
			temp = "网络未连接";
			break;
		case TYPE_SERVER_RETURN:
			temp = message;
			break;
		case TYPE_SESSION_EXPIRED:
			temp = "会话信息已经过期，请重新登录";
//			loginOutAction();
			break;
		case TYPE_LOGIN_READLY:
			temp = "您已重复登录，请先退出之前的登陆，继续操作";
//			loginOutAction();
			break;
		default:
			temp = "暂时无法获取数据，请稍后重试";
			break;
		}
		BaseApplication.getInstance().showtoast(temp,BaseApplication.TIP);
	}
	
	/*
	public static void loginOutAction() {
		if (LcappApplication.mUserInfo == null) {
			return;
		}
		LcappApplication.mUserInfo = null;
//		C.islogined = false;
		LcappApplication.getInstance().setLoginOutClearDB();
//		LcappApplication.getInstance().clearSP();
		MainActivity.getInstance().startLogin();
		if (MoreFragment.getIntance() != null) {

			MoreFragment
					.getIntance()
					.sendMessage(
							MoreFragment.MSG_CHANGE_LOGIN_STAUS_UNLOGINED);
		}
		if (MineFragment.getIntance() != null) {
			MineFragment
					.getIntance()
					.sendMessage(
							MineFragment.MSG_CHANGE_LOGIN_STAUS_UNLOGINED);
		}
		
	}*/
	
}
