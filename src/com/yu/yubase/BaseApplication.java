package com.yu.yubase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.yu.jar.yujar.Y;
import com.yu.yubase.common.CrashHandler;
import com.yu.yubase.common.LruImageCache;
import com.yu.yubase.constant.DB;
import com.yu.yubase.constant.ParamsKey;
import com.yu.yubase.constant.Url;
import com.yu.yubase.database.DBHelper;
import com.yu.yubase.entities.DevInfo;
import com.yu.yubase.entities.UserInfo;
import com.yu.yubase.utils.DES3;
import com.yu.yubase.utils.DeviceID;
import com.yu.yubase.utils.FileUtil;
import com.yu.yubase.utils.GLFont;
import com.yu.yubase.utils.ScreenUtils;

/**
 * @author yu 272604545@qq.com
 *
 */
public class BaseApplication extends Application {

	public static final String TAG = BaseApplication.class.getSimpleName();
	private static BaseApplication mApp;
	public String appName = Url.SECRET_KEY;
	public static String mDeviceId; // 设备号
	public static String mVersionName = "error"; // 版本号
	private RequestQueue mRequestQueue; // volley队列
	public Bitmap backgroud; // 背景
	public static int width = 720;
	public static int height = 1280;
	public static UserInfo mUserInfo; // 用户信息
	
	//这里定义了基础名字，作为文件夹目录的名称和一些其他与程序相关连的内容
	public final static String mainName = "yubase";
	
	public static final String SAVE_PHOTO_PATH = Environment
			.getExternalStorageDirectory() + "/" + BaseApplication.mainName+"/";
	public static final String CACHE_PATH = Environment
			.getExternalStorageDirectory() + "/"+BaseApplication.mainName+"/Cache/";
	public static final String ERROR_PATH = Environment
			.getExternalStorageDirectory() + "/"+BaseApplication.mainName+"/Error/";
	public static String SAVE_PHOTO_NAME = "usericon.png";
	
	public static final int TIP=0;//提示
	public static final int GOU=1;//沟
	public static final int WU=2;//无

	@Override
	public void onCreate() {
		super.onCreate();
		mApp = this;
		
		// 设置全局异常处理
		CrashHandler.getInstance().init(getApplicationContext());
		// 获取设备信息
		try {
			mVersionName = getPackageManager().getPackageInfo(
					"com.yu."+BaseApplication.mainName, 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		// 初始化分享组件
		// ShareSDK.initSDK(this);
		// 初始化内存缓存
		LruImageCache.getInstance(this);
		
		//创建程序缓存目录
		FileUtil.creatFileDir(BaseApplication.CACHE_PATH);
		FileUtil.creatFileDir(BaseApplication.SAVE_PHOTO_PATH);

		// 获取手机基本信息
		getINfo();
		
		//获取变化的配置信息,如分享的时候的信息内容
//		if (mShareData == null) {
//			getBaseData();
//		}

		
	}
	
	/**
	 * 获取变化的配置信息，用于后期的升级维护，运营推广
	 */
	private void getBaseData() {
//		new BaseData(Url.URL_SHARE_BASE, new BaseData.SuccessCallback() {
//			
//			@Override
//			public void onSuccess(ShareData versionInfo) {
//				versionInfo.show();
//				LcappApplication.mShareData = versionInfo;
//			}
//		}, new BaseData.FBaseApplication() {
//			
//			@Override
//			public void onFail(ErrorMessage message) {
//				message.showToast();
//				
//			}
//		});
	}
	

	public static synchronized BaseApplication getInstance() {
		return mApp;
	}

	public RequestQueue BaseApplicationstQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}

	/**
	 * 上传手机的基本信息，用于后期的升级维护，运营推广
	 */
	public void getINfo() {
		try {
			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			StringBuilder sb = new StringBuilder();
			String devid =  DeviceID.getDeviceId(mApp);
			
			sb.append("\nDeviceId(IMEI) = " + tm.getDeviceId());
			sb.append("\nDeviceSoftwareVersion = "
					+ tm.getDeviceSoftwareVersion());
			sb.append("\nLine1Number = " + tm.getLine1Number());
			sb.append("\nNetworkCountryIso = " + tm.getNetworkCountryIso());
			sb.append("\nNetworkOperator = " + tm.getNetworkOperator());
			sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());
			sb.append("\nNetworkType = " + tm.getNetworkType());
			sb.append("\nPhoneType = " + tm.getPhoneType());
			sb.append("\nSimCountryIso = " + tm.getSimCountryIso());
			sb.append("\nSimOperator = " + tm.getSimOperator());
			sb.append("\nSimOperatorName = " + tm.getSimOperatorName());
			sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
			sb.append("\nSimState = " + tm.getSimState());
			sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());
			sb.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber());
			String localVersion = getPackageManager().getPackageInfo(
					getPackageName(), 0).versionName;
			sb.append("\nVersion = " + localVersion);
			boolean abc = checkDeviceHasNavigationBar(mApp);
			sb.append("\nDeviceHasNavigationBar=" + abc);
			Log.e("info", sb.toString());
			DevInfo df = new DevInfo();
			df.setDeviceId(tm.getDeviceId());
			df.setDeviceSoftwareVersion(tm.getDeviceSoftwareVersion());
			df.setLine1Number(tm.getLine1Number());
			df.setNetworkCountryIso(tm.getNetworkCountryIso());
			df.setNetworkOperator(tm.getNetworkOperator());
			df.setNetworkOperatorName(tm.getNetworkOperatorName());
			df.setNetworkType(""+ tm.getNetworkType());
			df.setPhoneType("" +tm.getPhoneType());
			df.setSimCountryIso(tm.getSimCountryIso());
			df.setSimOperator(tm.getSimOperator());
			df.setSimoperatorName(tm.getSimOperatorName());
			df.setSimSerialNumber(tm.getSimSerialNumber());
			df.setSimState(tm.getSimState()+"");
			df.setSubscriberId(tm.getSubscriberId());
			df.setVoiceMailNumber(tm.getVoiceMailNumber());
			df.setVersion(localVersion);
			df.setDeviceHasHavigationBar("" + abc);
			df.setDevid(devid);
			mDeviceId = devid;
			
//			new BaseDataUpload(Url.URL_GETDEVICEINFO, df, new BaseDataUpload.SuccessCallback() {
//
//				@Override
//				public void onSuccess(String data) {
//					Yu.LogE(TAG, "", data);
//				}
//			}, new BaseDataUpload.FailCallback() {
//
//				@Override
//				public void onFail(ErrorMessage message) {
//					Yu.LogE(TAG, "", message.show());
//				}
//			});
//			testNet.BaseUp(df);

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			Y.e(TAG, e.toString());
		}
	}

	// 将请求加入到volley队列
	public <T> void addToRequestQueue(com.android.volley.Request<T> req) {
		addToRequestQueue(req, null);
	}

	public <T> void addToRequestQueue(com.android.volley.Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}
	
	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}

	// 取消请求
	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

	// 获取栈顶Activity
	public String getTopActivityName() {
		String topActivityClassName = null;
		ActivityManager activityManager = (ActivityManager) (getSystemService(android.content.Context.ACTIVITY_SERVICE));
		List<RunningTaskInfo> runningTaskInfos = activityManager
				.getRunningTasks(1);
		if (runningTaskInfos != null) {
			ComponentName f = runningTaskInfos.get(0).topActivity;
			topActivityClassName = f.getClassName();
		}
		return topActivityClassName;
	}

	// 当前应用是否处于前台运行
	public boolean isRunningForeground() {
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		if (!powerManager.isScreenOn()) {
			// Log.i(TAG, "---> 屏幕关闭");
			Y.e(TAG, "isRunningForeground", "屏幕关闭");
			return false;
		}
		String packageName = getPackageName();
		String topActivityClassName = getTopActivityName();
		Log.i(TAG, "packageName=" + packageName + ",topActivityClassName="
				+ topActivityClassName);
		if (packageName != null && topActivityClassName != null
				&& topActivityClassName.startsWith(packageName)) {
			// Log.i(TAG, "---> 前台运行");
			Y.e(TAG, "isRunningForeground", "前台运行");
			return true;
		} else {
			Y.e(TAG, "isRunningForeground", "后台运行");
			// Log.i(TAG, "---> 后台运行");
			return false;
		}
	}

	public void installApk(Uri uri) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	public List<Activity> activityList = new ArrayList<Activity>();

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public void removeActivity(Activity activity) {
		activityList.remove(activity);
	}

	// 关闭所有Activity
	public void exit() {
		for (int i = 0; i < activityList.size(); i++) {
			Activity activity = activityList.get(i);
			if (null != activity) {
				activity.finish();
			}
		}
		System.exit(0);
	}

	public void showToastWithAm(String message) {// showToastWithAm
		Toast.makeText(mApp, message, Toast.LENGTH_LONG).show();
	}

	// public void showtoast(String s) {
	// LinearLayout toastView;
	// ImageView imageCodeProject;
	// imageCodeProject = new ImageView(this);
	// Toast toast = null;
	// if (toast == null) {
	// toast = Toast.makeText(getApplicationContext(), "",
	// Toast.LENGTH_SHORT);
	// // toast = Toast.makeText(getApplicationContext(), "",
	// // Toast.LENGTH_LONG);
	// toast.setGravity(Gravity.CENTER, 0, 0);
	// toastView = (LinearLayout) toast.getView();
	// imageCodeProject.setImageResource(R.drawable.attem);
	// toastView.addView(imageCodeProject, 0);
	// }
	// toast.setText(s);
	// toast.show();
	// }
	/**
	 * @param s
	 * @param type
	 *            0是tips,1是finish成功的表情,2是无
	 * Y.tip 提示 0
	 * Y.gou 勾    1
	 * Y.wu  无  2
	 */
	public void showtoast(String s, int type) {
		LinearLayout toastView;
		ImageView imageCodeProject;
		imageCodeProject = new ImageView(this);
		Toast toast = null;
		if (toast == null) {
			toast = Toast.makeText(getApplicationContext(), "",
					Toast.LENGTH_SHORT);
			// toast = Toast.makeText(getApplicationContext(), "",
			// Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toastView = (LinearLayout) toast.getView();
			int icon = -1;
			switch (type) {
			case 0:
				icon = R.string.icon_tips;
				break;
			case 1:
				icon = R.string.icon_finish;
				break;
			case 2:
				icon = -1;
				break;
			default:
				break;
			}
			if (icon != -1) {
				Drawable drawable = IconStringToDrawable(
						getApplicationContext(), icon, getApplicationContext()
								.getResources().getColor(R.color.write_text),
						40, 30);
				imageCodeProject.setImageDrawable(drawable);
				toastView.addView(imageCodeProject, 0);
			}
			// imageCodeProject.setImageResource(R.drawable.attem);
			// toastView.setBackgroundResource(R.color.orange_gray);
		}
		toast.setText(s);
		toast.show();
	}
	
//	/**
//	 * @param s
//	 * @param type
//	 *            0是tips,1是finish成功的表情,2是无
//	 */
//	public void showtoast(String s) {
//		LinearLayout toastView;
//		ImageView imageCodeProject;
//		imageCodeProject = new ImageView(this);
//		Toast toast = null;
//		if (toast == null) {
//			toast = Toast.makeText(getApplicationContext(), "",
//					Toast.LENGTH_SHORT);
//			// toast = Toast.makeText(getApplicationContext(), "",
//			// Toast.LENGTH_LONG);
//			toast.setGravity(Gravity.CENTER, 0, 0);
//			toastView = (LinearLayout) toast.getView();
//			int icon = -1;
//			if (icon != -1) {
//				Drawable drawable = Y.IconStringToDrawable(
//						getApplicationContext(), icon, getApplicationContext()
//						.getResources().getColor(R.color.write_text),
//						40, 30);
//				imageCodeProject.setImageDrawable(drawable);
//				toastView.addView(imageCodeProject, 0);
//			}
//			// imageCodeProject.setImageResource(R.drawable.attem);
//			// toastView.setBackgroundResource(R.color.orange_gray);
//		}
//		toast.setText(s);
//		toast.show();
//	}

	
	
	public ProgressDialog pd;

	public void showProgressDialog(Context c, String message,
			boolean isCancelable) {
		if (pd == null) {
			pd = new ProgressDialog(c);
		} else {
			pd = null;
			pd = new ProgressDialog(c);
		}
		if (pd.isShowing()) {
			return;
		}
		pd.setIndeterminateDrawable(null);
		Drawable tem = getResources().getDrawable(
				R.drawable.rotate_loading_github);
		pd.setIndeterminateDrawable(tem);
		pd.setCancelable(isCancelable);
		pd.setMessage(message);
		pd.show();
	}

	public void changeProgressDialog(Context c, String message) {
		if (pd == null) {
			return;
		}
		if (!pd.isShowing()) {
			return;
		}
		pd.setIndeterminateDrawable(null);
		pd.setMessage(message);
		pd.show();
	}

	public void dimissProgressDialog() {
		if (pd != null && pd.isShowing()) {
			pd.dismiss();
		}
	}

	public void cancelProgressDialog() {
		if (pd != null && pd.isShowing()) {
			pd.cancel();
		}
	}

	public void saveUserInfo(UserInfo mInfo) {
		String tem = "_" + mInfo.getUid();
		saveSP_String(ParamsKey.RESULT_USER_ID + tem, mInfo.getUid());
		saveSP_String(ParamsKey.RESULT_USER_NAME + tem, mInfo.getUser_name());
		saveSP_String(ParamsKey.RESULT_USER_TOKEN + tem, mInfo.getToken());
		// saveSP_String(ParamsKey.RESULT_USER_EMAIL + tem,
		// mInfo.getUser_email());
		// saveSP_String(ParamsKey.RESULT_USER_BANKCARDS + tem,
		// mInfo.getUser_bankcard());
		// saveSP_String(ParamsKey.RESULT_USER_IMAGE_URL + tem,
		// mInfo.getUser_images());
		// saveSP_String(ParamsKey.RESULT_USER_LOG_TIME + tem,
		// mInfo.getUser_timestamp());
	}

	/**
	 * 将字符串类型数据保存到本地
	 * 
	 * @param key
	 * @param value
	 */
	public void saveSP_String(String key, String value) {
		SharedPreferences preferences = getSharedPreferences(appName,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		String tempkey = DES3.encode(appName, key);
		// String tempkey=ThreeDES.encryptString(appName, key);
		String tempvalue = DES3.encode(appName, value);
		if (editor.putString(tempkey, tempvalue).commit()) {
			// password = pwd;
		}
	}

	/**
	 * 将整形数据保存到本地
	 * 
	 * @param key
	 * @param value
	 */
	public void saveSP_Int(String key, int value) {
		SharedPreferences preferences = getSharedPreferences(appName,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		String tempkey = DES3.encode(appName, key);
		// String tempvalue=ThreeDES.encryptString(appName, value);
		if (editor.putInt(tempkey, value).commit()) {
			// password = pwd;
		}
	}

	/**
	 * 根据key从本地取出字符串数据
	 * 
	 * @param key
	 * @return
	 */
	public String getSP_String(String key) {
		SharedPreferences preferences = getSharedPreferences(appName,
				Activity.MODE_PRIVATE);
		// SharedPreferences.Editor editor = preferences.edit();
		String tempkey = DES3.encode(appName, key);
		String tempvalue = preferences.getString(tempkey, "");
		String value = DES3.decode(appName, tempvalue);
		return value;
	}

	/**
	 * 根据key从本地取出整形数据
	 * 
	 * @param key
	 * @return
	 */
	public int getSP_Int(String key) {
		SharedPreferences preferences = getSharedPreferences(appName,
				Activity.MODE_PRIVATE);
		// SharedPreferences.Editor editor = preferences.edit();
		String tempkey = DES3.encode(appName, key);
		int value = preferences.getInt(tempkey, -1);
		return value;
	}

	/**
	 * 清除数据
	 */
	public void clearSP() {
		SharedPreferences preferences = getSharedPreferences(appName,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear().commit();
	}


	// 获取本地用户信息
	public UserInfo getLocalUserInfo() {
		// 获取用户表数据
		UserInfo userInfo = null;
		Cursor cursor = DBHelper.getInstance(getApplicationContext()).query(
				DB.ID_USER, null, null, null, null);
		if (cursor != null && cursor.moveToNext()) {
			userInfo = new UserInfo();

			userInfo.setUid(cursor.getString(cursor
					.getColumnIndex(DB.USER_ITEM_ID)));
			userInfo.setUser_name(cursor.getString(cursor
					.getColumnIndex(DB.USER_ITEM_NAME)));
			userInfo.setUser_email(cursor.getString(cursor
					.getColumnIndex(DB.USER_ITEM_EMAIL)));
			userInfo.setToken(cursor.getString(cursor
					.getColumnIndex(DB.USER_ITEM_TOKEN)));
			userInfo.setUser_images(cursor.getString(cursor
					.getColumnIndex(DB.USER_ITEM_IMAGEURL)));
			userInfo.setUser_phone(cursor.getString(cursor
					.getColumnIndex(DB.USER_ITEM_PHONE)));
			// userInfo.setRegTime(cursor.getString(cursor.getColumnIndex(DB.USER_ITEM_REGTIME)));

			// //获取银行卡表数据
			// Cursor cursorBankCard =
			// DBHelper.getInstance(getApplicationContext()).query(DB.ID_BANKCARD,
			// null, null, null, null);
			// if(cursorBankCard != null && cursorBankCard.moveToNext()) {
			// BankCard bankCard = new BankCard();
			// bankCard.setBank(cursorBankCard.getString(cursorBankCard.getColumnIndex(DB.BANKCARD_ITEM_BANK)));
			// bankCard.setCity(cursorBankCard.getString(cursorBankCard.getColumnIndex(DB.BANKCARD_ITEM_CITY)));
			// bankCard.setDistrict(cursorBankCard.getString(cursorBankCard.getColumnIndex(DB.BANKCARD_ITEM_DISTRICT)));
			// bankCard.setIdentification(cursorBankCard.getString(cursorBankCard.getColumnIndex(DB.BANKCARD_ITEM_IDENTIFICATION)));
			// bankCard.setNumber(cursorBankCard.getString(cursorBankCard.getColumnIndex(DB.BANKCARD_ITEM_NUMBER)));
			// bankCard.setPhone(cursorBankCard.getString(cursorBankCard.getColumnIndex(DB.BANKCARD_ITEM_PHONE)));
			// bankCard.setProvince(cursorBankCard.getString(cursorBankCard.getColumnIndex(DB.BANKCARD_ITEM_PROVINCE)));
			// bankCard.setStatus(cursorBankCard.getString(cursorBankCard.getColumnIndex(DB.BANKCARD_ITEM_STATUS)));
			// bankCard.setRealName(cursorBankCard.getString(cursorBankCard.getColumnIndex(DB.BANKCARD_REAL_NAME)));
			// bankCard.setSubBranch(cursorBankCard.getString(cursorBankCard
			// .getColumnIndex(DB.BANKCARD_SUB_BRANCH)));
			// userInfo.setBankcard(bankCard);
			// }
			// cursorBankCard.close();
			// cursorBankCard = null;
		}
		cursor.close();
		cursor = null;

		return userInfo;
	}

	public void setLocalUserInfo(UserInfo userInfo) {
		DBHelper.getInstance(getApplicationContext()).delete(DB.ID_USER, null,
				null);
		DBHelper.getInstance(getApplicationContext()).delete(DB.ID_BANKCARD,
				null, null);
		if (userInfo != null) {
			ContentValues values = new ContentValues();
			values.put(DB.USER_ITEM_ID, userInfo.getUid());
			values.put(DB.USER_ITEM_NAME, userInfo.getUser_name());
			values.put(DB.USER_ITEM_EMAIL, userInfo.getUser_email());
			values.put(DB.USER_ITEM_TOKEN, userInfo.getToken());
			values.put(DB.USER_ITEM_IMAGEURL, userInfo.getUser_images());
			values.put(DB.USER_ITEM_PHONE, userInfo.getUser_phone());
			// values.put(DB.USER_ITEM_REGTIME, userInfo.getRegTime());
			DBHelper.getInstance(getApplicationContext()).insert(DB.ID_USER,
					values);

			// BankCard bankCard = userInfo.getBankcard();
			// if(bankCard != null) {
			// values = new ContentValues();
			// values.put(DB.BANKCARD_ITEM_BANK, bankCard.getBank());
			// values.put(DB.BANKCARD_ITEM_CITY, bankCard.getCity());
			// values.put(DB.BANKCARD_ITEM_DISTRICT, bankCard.getDistrict());
			// values.put(DB.BANKCARD_ITEM_IDENTIFICATION,
			// bankCard.getIdentification());
			// values.put(DB.BANKCARD_ITEM_NUMBER, bankCard.getNumber());
			// values.put(DB.BANKCARD_ITEM_PHONE, bankCard.getPhone());
			// values.put(DB.BANKCARD_ITEM_PROVINCE, bankCard.getProvince());
			// values.put(DB.BANKCARD_ITEM_STATUS, bankCard.getStatus());
			// values.put(DB.BANKCARD_REAL_NAME, bankCard.getRealName());
			// values.put(DB.BANKCARD_SUB_BRANCH, bankCard.getSubBranch());
			// DBHelper.getInstance(getApplicationContext()).insert(DB.ID_BANKCARD,
			// values);
			// }
		}
	}

	public void setLoginOutClearDB() {
		DBHelper.getInstance(getApplicationContext()).delete(DB.ID_USER, null,
				null);
		DBHelper.getInstance(getApplicationContext()).delete(DB.ID_BANKCARD,
				null, null);
	}
	
	//获取是否存在NavigationBar
		public static boolean checkDeviceHasNavigationBar(Context context) {
		    boolean hasNavigationBar = false;
		    Resources rs = context.getResources();
		    int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
		    if (id > 0) {
		        hasNavigationBar = rs.getBoolean(id);
		    }
		    try {
		        Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
		        Method m = systemPropertiesClass.getMethod("get", String.class);
		        String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
		        if ("1".equals(navBarOverride)) {
		            hasNavigationBar = false;
		        } else if ("0".equals(navBarOverride)) {
		            hasNavigationBar = true;
		        }
		    } catch (Exception e) {

		    }
		    return hasNavigationBar;
		}
		
		/**
		 * @param context
		 * @param StringId
		 * @param temColor
		 * @param dp     背景大小
		 * @param size   内部字体图标大小
		 * @return
		 */
		public static Drawable IconStringToDrawable(Context context, int StringId,
				int temColor, int dp,int size) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(),
					context.getResources().getString(R.string.if_iconfont));
			String temString = context.getResources().getString(StringId);
			int px = ScreenUtils.dpToPx(context.getResources(), dp);
			Bitmap tempBitmap = GLFont.getImage(context, px, px, temString, size,
					temColor, typeface);
			Drawable tempDrawable = new BitmapDrawable(context.getResources(),
					tempBitmap);
			tempDrawable.setBounds(0, 0, tempDrawable.getMinimumWidth(),
					tempDrawable.getMinimumHeight());
			return tempDrawable;
		}
}
