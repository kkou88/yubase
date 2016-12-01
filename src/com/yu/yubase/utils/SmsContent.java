package com.yu.yubase.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.widget.EditText;

public class SmsContent extends ContentObserver {
	 
	 public static final String SMS_URI_INBOX = "content://sms/inbox";
	 private Activity activity = null;
	 private String smsContent = "";
	 private EditText verifyText = null;
	 public SmsContent(Activity activity, Handler handler, EditText verifyText) {
	  super(handler);
	  this.activity = activity;
	  this.verifyText = verifyText;
	 }
	 @Override
	 public void onChange(boolean selfChange) {
	  super.onChange(selfChange);
	  Cursor cursor = null;// 光标
	  // 读取收件箱中指定号码的短�?
	  cursor = activity.managedQuery(Uri.parse(SMS_URI_INBOX), new String[] { "_id", "address", "body", "read" }, " address =? AND read=0",
	                                 new String[] { "5554", "0" }, "date desc");
	  if (cursor != null) {// 如果短信为未读模�?
	   cursor.moveToFirst();
	   if (cursor.moveToFirst()) {
	    String smsbody = cursor.getString(cursor.getColumnIndex("body"));
	    System.out.println("smsbody=======================" + smsbody);
	    String regEx = "[a-z0-9]{8}";
//	    String regEx = "[^0-9]";
	    Pattern p = Pattern.compile(regEx);
	    Matcher m = p.matcher(smsbody.toString());
	    smsContent = m.replaceAll("").trim().toString();
	    verifyText.setText(smsContent);
	   }
	  }
	  
//	  ContentResolver cr =this.get;  
//      String[] projection = new String[] { "body" };//"_id", "address", "person",, "date", "type  
//      String where = " address = '1066321332' AND date >  "  
//              + (System.currentTimeMillis() - 10 * 60 * 1000);  
//      Cursor cur = this.query(Uri.parse(SMS_URI_INBOX), projection, where, null, "date desc");  
//      if (null == cur)  
//          return;  
//      if (cur.moveToNext()) {  
//          String number = cur.getString(cur.getColumnIndex("address"));//手机�?  
//          String name = cur.getString(cur.getColumnIndex("person"));//联系人姓名列�?  
//          String body = cur.getString(cur.getColumnIndex("body"));  
//          //这里我是要获取自己短信服务号码中的验证码~~  
//          Pattern pattern = Pattern.compile("[a-z0-9]{8}");  
//          Matcher matcher = pattern.matcher(body);  
//          if (matcher.find()) {  
//              String res = matcher.group().substring(1, 11);  
//              verifyText.setText(res);  
//          }  
//      }  
	 }
	}
