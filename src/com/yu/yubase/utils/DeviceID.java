package com.yu.yubase.utils;

import java.util.UUID;

import com.yu.jar.yujar.Y;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class DeviceID {
	// static String uuid = "";

	/**
	 * 获得�?个设备id
	 * @param context
	 * @return
	 */
	public static String getDeviceId(Context context) {

		StringBuilder deviceId = new StringBuilder();
		// 渠道标志
		deviceId.append("a");

		try {

			// wifi mac地址
			WifiManager wifi = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			deviceId.append("#wifi#");
			///这里的获取wifi，曾经报错，不一定可以获取到
			WifiInfo info = wifi.getConnectionInfo();
			String wifiMac = info.getMacAddress();
			if (!Y.IsEmptyString(wifiMac)) {
				deviceId.append(wifiMac);
				Log.e("getDeviceId : ", deviceId.toString());
				// LcappApplication.getInstance().Y("getDeviceId :",
				// "getDeviceId", deviceId.toString());
				// return deviceId.toString();
			} else {
				deviceId.append("empty");
				// LcappApplication.getInstance().Y("getDeviceId :",
				// "getDeviceId", deviceId.toString());
				Log.e("getDeviceId : ", deviceId.toString());
			}

			// IMEI（imei�?
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = tm.getDeviceId();
			if (!Y.IsEmptyString(imei)) {
				deviceId.append("#imei#");
				deviceId.append(imei);
				// LcappApplication.getInstance().Y("getDeviceId :",
				// "getDeviceId", deviceId.toString());
				Log.e("getDeviceId : ", deviceId.toString());
				// return deviceId.toString();
			} else {
				deviceId.append("#imei#");
				deviceId.append("empty");
				// LcappApplication.getInstance().Y("getDeviceId :",
				// "getDeviceId", deviceId.toString());
				Log.e("getDeviceId : ", deviceId.toString());
			}

			// 序列号（sn�?
			String sn = tm.getSimSerialNumber();
			if (!Y.IsEmptyString(sn)) {
				deviceId.append("#sn#");
				deviceId.append(sn);
				// LcappApplication.getInstance().Y("getDeviceId :",
				// "getDeviceId", deviceId.toString());
				Log.e("getDeviceId : ", deviceId.toString());
				// return deviceId.toString();
			} else {
				deviceId.append("#sn#");
				deviceId.append("empty");
				// LcappApplication.getInstance().Y("getDeviceId :",
				// "getDeviceId", deviceId.toString());
				Log.e("getDeviceId : ", deviceId.toString());
			}

			// 如果上面都没有， 则生成一个id：随机码
			String uuid = getUUID(context);
			if (!Y.IsEmptyString(uuid)) {
				deviceId.append("#id#");
				deviceId.append(uuid);
				// LcappApplication.getInstance().Y("getDeviceId :",
				// "getDeviceId", deviceId.toString());
				Log.e("getDeviceId : ", deviceId.toString());
				return deviceId.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			deviceId.append("id").append(getUUID(context));
		}
		// LcappApplication.getInstance().Y("getDeviceId :", "getDeviceId",
		// deviceId.toString());
		Log.e("getDeviceId : ", deviceId.toString());

		return deviceId.toString();
	}

	/**
	 * 得到全局唯一UUID
	 */
	public static String getUUID(Context context) {
		String uuid = "";
		uuid = getfileString(context);
		uuid = FileOperation.getTxtFile(FileOperation.SAVE_PHOTO_PATH
				+ FileOperation.SAVE_PHOTO_NAME);

		if (Y.IsEmptyString(uuid)) {
			uuid = UUID.randomUUID().toString();
			try {
				FileOperation.writeTxtFile(uuid, FileOperation.SAVE_PHOTO_PATH
						+ FileOperation.SAVE_PHOTO_NAME);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Log.e("DeviceID", "getUUID : " + uuid);
		return uuid;
	}

	public static void savefileString(Context context, String content) {
		SharedPreferences mShare = context.getSharedPreferences("DeviceID",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = mShare.edit();
		editor.putString("uuid", content).commit();
	}

	public static String getfileString(Context context) {
		SharedPreferences mShare = context.getSharedPreferences("DeviceID",
				Activity.MODE_PRIVATE);
		return mShare.getString("uuid", "");
	}

}
