package com.yu.yubase.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

public class GLFont {
    /*
     * 默认采用黑色字体，宋体文字加�?
     */
	public static Bitmap getImage(Context context, int width, int height, String mString, int size) {
		return getImage(context, width, height, mString, size, Color.BLACK, Typeface.create("宋体",Typeface.BOLD));
	}
	
	public static Bitmap getImage(Context context, int width, int height, String mString, int size, int color) {
		return getImage(context, width, height, mString, size, color, Typeface.create("宋体",Typeface.BOLD));
	}
	
	public static Bitmap getImage(Context context, int width, int height, String mString, int size, int color, String familyName) {
		return getImage(context, width, height, mString, size, color, Typeface.create(familyName,Typeface.BOLD));
	}
	
	public static Bitmap getImage(Context context, int width, int height, String mString, int size, int color, Typeface font) {
		int x = width;
		int y = height;
		
		Bitmap bmp = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888); 
		//图象大小要根据文字大小算�?,以和文本长度对应 
		Canvas canvasTemp = new Canvas(bmp); 
//		canvasTemp.drawColor(Color.BLACK); 
		Paint p = new Paint(); 
		p.setColor(color);
		p.setTypeface(font);
		p.setAntiAlias(true);//去除锯齿
		p.setFilterBitmap(true);//对位图进行滤波处�?
		p.setTextSize(scalaFonts(context, size));
		float tX = (x - getFontWidth(p, mString))/2;
//		float tY = (y - getFontHeight(p))/2 + getFontLeading(p);
		float tY = (y - getFontHeight(p))/2;
		canvasTemp.drawText(mString, tX, tY, p); 

		return bmp;
	}

	/**
	 * 根据屏幕系数比例获取文字大小
	 * @return
	 */
	private static float scalaFonts(Context context, int size) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		size = (int) (size* dm.density);
		return size;
	}

	/**
	 * @return 返回文字的宽�?
	 */
	public static float getFontWidth(Paint paint, String str) {
		return paint.measureText(str);
	}
	
	/**
	 * @return 返回文字的高�?
	 */
	public static float getFontHeight(Paint paint) {
		return paint.ascent() + paint.descent();
	}
	
//	/**
//	 * @return 返回指定笔的文字高度
//	 */
//	public static float getFontHeight(Paint paint)  {  
//	    FontMetrics fm = paint.getFontMetrics(); 
//	    return fm.descent - fm.ascent;  
//	}
//	
//	/**
//	 * @return 返回指定笔离文字顶部的基准距�?
//	 */
//	public static float getFontLeading(Paint paint)  {  
//	    FontMetrics fm = paint.getFontMetrics(); 
//	    return fm.leading- fm.ascent;  
//	} 
	
}
