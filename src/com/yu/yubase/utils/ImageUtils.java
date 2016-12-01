package com.yu.yubase.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.util.Log;

public class ImageUtils {
	private static final String TAG = ImageUtils.class.getSimpleName();

	/**
	 * 根据指定路径和指定宽高来加载图片
	 * @param path
	 * @param twidth
	 * @param theight
	 * @param inSampleSize
	 * @return
	 */
	public static Bitmap loadImage(String path, int twidth, int theight, int inSampleSize) {
		if (inSampleSize > 10) {
			return null;
		}
		Bitmap bitmap = null;
		Bitmap bitmapResult = null;
		Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeFile(path, options);
		if (inSampleSize == 0) {
			options.inSampleSize = getinSampleSize(options.outWidth, options.outHeight, twidth, theight);
		} else {
			options.inSampleSize = inSampleSize;
		}
		options.inJustDecodeBounds = false;
		options.inInputShareable = true;
		options.inPurgeable = true;
		try {
			Log.i(TAG, "--------------缩放比例" + options.inSampleSize + "-------------");
			long time1 = System.currentTimeMillis();
			bitmap = BitmapFactory.decodeFile(path, options);
			long time2 = System.currentTimeMillis();
			Log.i(TAG, "--------------解码耗时"+ (time2 - time1) + "毫秒-------------");
			if (bitmap != null) {
				int w = bitmap.getWidth();
				int h = bitmap.getHeight();
				float scaleWidth = ((float) twidth) / w;
				float scaleHeight = ((float) theight) / h;
				Matrix matrix = new Matrix();
				matrix.postScale(scaleWidth, scaleHeight);
				bitmapResult = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
				if (bitmap != bitmapResult && !bitmap.isRecycled()) {
					bitmap.recycle();
					bitmap = null;
				}
			}
		} catch (OutOfMemoryError err) {
			err.printStackTrace();
			if (bitmap != null && !bitmap.isRecycled()) {
				bitmap.recycle();
				bitmap = null;
			}
			if (bitmapResult != null && !bitmapResult.isRecycled()) {
				bitmapResult.recycle();
				bitmapResult = null;
			}
			// todo清除程序缓存
			// 提示系统，进行内存回�??
			System.gc();
			System.runFinalization();
			// 压缩图片重新加载
			bitmapResult = loadImage(path, twidth, theight, options.inSampleSize + 1);
		}
		return bitmapResult;
	}

	/**
	 * 根据原始高宽和目标高宽生成样本比�??
	 * @param swidth
	 * @param sheight
	 * @param twidth
	 * @param theight
	 * @return
	 */
	private static int getinSampleSize(int swidth, int sheight, int twidth,
			int theight) {
		// return InSampleSizeStrategy1(swidth,sheight,twidth,theight);
		return InSampleSizeStrategy2(swidth, sheight, twidth, theight);
	}

//	private static int InSampleSizeStrategy1(int swidth, int sheight,
//			int twidth, int theight) {
//		int inSampleSize = 1;
//		if (sheight > theight || swidth > twidth) {
//			int heightRatio = Math.round((float) sheight / (float) theight);
//			int widthRatio = Math.round((float) swidth / (float) twidth);
//			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
//		}
//		return inSampleSize;
//	}

	private static int InSampleSizeStrategy2(int sWidth, int sHeight,
			int tWidth, int tHeight) {
		int minSideLength = tWidth < tHeight ? tWidth : tHeight;
		int initialSize = 1;
		double w = sWidth;
		double h = sHeight;
		int maxNumOfPixels = tWidth * tHeight;
		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));
		if (upperBound < lowerBound) {
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			initialSize = 1;
		} else if (minSideLength == -1) {
			initialSize = lowerBound;
		} else {
			initialSize = upperBound;
		}

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		if (w <= tWidth || h <= tHeight || roundedSize < 1)
			roundedSize = 1;
		return roundedSize;
	}

	/**
	 * 根据资源id加载图片
	 * @param context
	 * @param resourceId
	 * @return
	 */
	public static Bitmap loadImageResource(Context context, int resourceId) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);  
		return bitmap;
	}

	/**
	 * 根据资源id加载图片，并按照指定的宽度等比例缩放图片
	 * @param context
	 * @param resourceId
	 * @param newWidth
	 * @return
	 */
	public static Bitmap loadImageResourceByWidth(Context context, int resourceId, int newWidth) {
		Bitmap bitmap = loadImageResource(context, resourceId);
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleWidth);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		if(bitmap != resizedBitmap && !bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
		}
		return resizedBitmap;
	}
	
	/**
	 * 根据资源id加载图片，并按照指定的高度等比例缩放图片
	 * @param context
	 * @param resourceId
	 * @param newWidth
	 * @return
	 */
	public static Bitmap loadImageResourceByHeight(Context context, int resourceId, int newHeight) {
		Bitmap bitmap = loadImageResource(context, resourceId);
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float scaleWidth = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleWidth);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		if(bitmap != resizedBitmap && !bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
		}
		return resizedBitmap;
	}
	
	/**
	 * 根据资源id加载图片，并按照指定的宽度和高度来缩放图�??
	 * @param context
	 * @param resourceId
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static Bitmap loadImageResource(Context context, int resourceId, int newWidth, int newHeight) {
		Bitmap bitmap = loadImageResource(context, resourceId);
		return resizeImage(bitmap, newWidth, newHeight);
	}
	
	/**
	 * 按照指定的宽度和高度来缩放图�??
	 * @param bitmap
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static Bitmap resizeImage(Bitmap bitmap, int newWidth, int newHeight) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		if(bitmap != resizedBitmap && !bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
		}
		return resizedBitmap;
	}
	
//	public static String getImagePath(String remoteUrl)
//	{
//		String imageName= remoteUrl.substring(remoteUrl.lastIndexOf("/") + 1, remoteUrl.length());
//		String path =PathUtil.getInstance().getImagePath()+"/"+ imageName;
//        EMLog.d("msg", "image path:" + path);
//        return path;
//		
//	}
//	
//	public static String getThumbnailImagePath(String thumbRemoteUrl) {
//		String thumbImageName= thumbRemoteUrl.substring(thumbRemoteUrl.lastIndexOf("/") + 1, thumbRemoteUrl.length());
//		String path =PathUtil.getInstance().getImagePath()+"/"+ "th"+thumbImageName;
//        EMLog.d("msg", "thum image path:" + path);
//        return path;
//    }

}
