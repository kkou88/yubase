package com.yu.yubase.utils;



import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

//获得屏幕相关的辅助类
public class ScreenUtils
{
	private ScreenUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 获得屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 获得屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	/**
	 * 获得状�?�栏的高�?
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusHeight(Context context)
	{

		int statusHeight = -1;
		try
		{
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return statusHeight;
	}

	/**
	 * 获取当前屏幕截图，包含状态栏
	 * 
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithStatusBar(Activity activity)
	{
		View view = activity.getWindow().getDecorView();
		return getViewBitmap(view);
//		view.setDrawingCacheEnabled(true);
//		view.buildDrawingCache();
//		Bitmap bmp = view.getDrawingCache();
//		int width = getScreenWidth(activity);
//		int height = getScreenHeight(activity);
//		Bitmap bp = null;
//		bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
//		view.destroyDrawingCache();
//		return bp;
	}

	/**
	 * 获取当前屏幕截图，不包含状�?�栏
	 * 
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithoutStatusBar(Activity activity)
	{
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;

		int width = getScreenWidth(activity);
		int height = getScreenHeight(activity);
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
				- statusBarHeight);
		view.destroyDrawingCache();
		return bp;

	}

	public static int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
	}
	
//	@Nullable
	public static Bitmap getViewBitmap(View v) {
        v.clearFocus();  
        v.setPressed(false);  
  
        boolean willNotCache = v.willNotCacheDrawing();  
        v.setWillNotCacheDrawing(false);  
        int color = v.getDrawingCacheBackgroundColor();  
        v.setDrawingCacheBackgroundColor(0);  
  
        if (color != 0) {  
            v.destroyDrawingCache();  
        }  
        v.buildDrawingCache();  
        Bitmap cacheBitmap = v.getDrawingCache();  
        if (cacheBitmap == null) {
            return null;  
        }
  
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);  
  
        // Restore the view  
        v.destroyDrawingCache();  
        v.setWillNotCacheDrawing(willNotCache);  
        v.setDrawingCacheBackgroundColor(color);  
  
        return bitmap;  
    }
}
