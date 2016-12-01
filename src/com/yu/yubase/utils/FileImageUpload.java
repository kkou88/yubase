package com.yu.yubase.utils;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import android.util.Log;

/**
 * 
 * 实现文件上传的工具类
 * @Title: 
 * @Description: 实现TODO
 * @Copyright:Copyright (c) 2011
 * @Company:易程科技股份有限公司
 * @Date:2012-7-2
 * @author  longgangbai
 * @version 1.0
 */
public class FileImageUpload {
	private static final String TAG = "uploadFile";
	private static final int TIME_OUT = 10*10000000;   //超时时间
	private static final String CHARSET = "utf-8"; //设置编码
	public static final String SUCCESS="1";
	public static final String FAILURE="0";
	/**
	 * android上传文件到服务器
	 * @param file  �?要上传的文件
	 * @param RequestURL  请求的rul
	 * @return  返回响应的内�?
	 */
	public static String uploadFile(File file,String RequestURL)
	{
		String  BOUNDARY =  UUID.randomUUID().toString();  //边界标识   随机生成
		String PREFIX = "--" , LINE_END = "\r\n"; 
		String CONTENT_TYPE = "multipart/form-data";   //内容类型
		
		try {
			URL url = new URL(RequestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIME_OUT);
			conn.setConnectTimeout(TIME_OUT);
			conn.setDoInput(true);  //允许输入�?
			conn.setDoOutput(true); //允许输出�?
			conn.setUseCaches(false);  //不允许使用缓�?
			conn.setRequestMethod("POST");  //请求方式
			conn.setRequestProperty("Charset", CHARSET);  //设置编码
			conn.setRequestProperty("connection", "keep-alive");   
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY); 
			if(file!=null)
			{
				/**
				 * 当文件不为空，把文件包装并且上传
				 */
				OutputStream outputSteam=conn.getOutputStream();
				
				DataOutputStream dos = new DataOutputStream(outputSteam);
				StringBuffer sb = new StringBuffer();
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINE_END);
				/**
				 * 这里重点注意�?
				 * name里面的�?�为服务器端�?要key   只有这个key 才可以得到对应的文件
				 * filename是文件的名字，包含后�?名的   比如:abc.png  
				 */
				
				sb.append("Content-Disposition: form-data; name=\"img\"; filename=\""+file.getName()+"\""+LINE_END); 
				sb.append("Content-Type: application/octet-stream; charset="+CHARSET+LINE_END);
				sb.append(LINE_END);
				dos.write(sb.toString().getBytes());
				InputStream is = new FileInputStream(file);
				byte[] bytes = new byte[1024];
				int len = 0;
				while((len=is.read(bytes))!=-1)
				{
					dos.write(bytes, 0, len);
				}
				is.close();
				dos.write(LINE_END.getBytes());
				byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
				dos.write(end_data);
				dos.flush();
				/**
				 * 获取响应�?  200=成功
				 * 当响应成功，获取响应的流  
				 */
				int res = conn.getResponseCode();  
				Log.e(TAG, "response code:"+res);
				if(res==200)
				{
			     return SUCCESS;
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return FAILURE;
	}
}
