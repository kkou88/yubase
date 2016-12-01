package com.yu.yubase.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import com.yu.jar.yujar.Y;

/**
 * @author lenovo
 *
 */
/**
 * @author lenovo
 *
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class PictureUtil {


	/**
	 * 根据路径获得突破并压缩返回bitmap用于显示
	 * 
	 * @param imagesrc
	 * @return
	 */
	public static boolean compressBitmap(String filePath, String targetPath) {
		boolean ttt = false;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		File file = new File(filePath);
		/**
		 * 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
		 */
		int degree = readPictureDegree(file.getAbsolutePath());
		// Bitmap bm = rotaingImageView(degree,
		// BitmapFactory.decodeFile(filePath));
		Bitmap bm = rotaingImageView(degree,
				ImageUtils.loadImage(filePath, 480, 800, 0));

		options.inJustDecodeBounds = true;
		// 获取这个图片的宽和高
		float width = bm.getWidth();
		float height = bm.getHeight();
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		// 计算宽高缩放�??
		float scaleHeight = ((float) 300) / height;
		if (scaleHeight < 1) {
			// 缩放图片动作
			matrix.postScale(scaleHeight, scaleHeight);
			bm = Bitmap.createBitmap(bm, 0, 0, (int) width, (int) height,
					matrix, true);
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(targetPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bm.compress(Bitmap.CompressFormat.PNG, 40, fos);
		ttt = new File(targetPath).exists();
		options = null;
		bm.recycle();
		bm = null;
		try {
			fos.close();
			fos = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ttt;
	}

	/**
	 * 旋转图片
	 * 
	 * @param angle
	 * @param bitmap
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		if (bitmap == null) {
			System.out.println("旋转图片时，资源为空！！");
			return null;
		}
		float scaleHeight = ((float) 960) / bitmap.getHeight();
		if (scaleHeight < 1) {
			// 缩放图片动作
			matrix.postScale(scaleHeight, scaleHeight);
		}
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		matrix = null;
		return resizedBitmap;
	}

	/**
	 * 读取图片属�?：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角�??
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 根据路径删除图片
	 * 
	 * @param path
	 */
	public static void deleteTempFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 添加到图�??
	 */
	public static void galleryAddPic(Context context, String path) {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(path);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		context.sendBroadcast(mediaScanIntent);
	}

	/**
	 * 获取保存图片的目�??
	 * 
	 * @return
	 */
	public static File getAlbumDir() {
		File dir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				getAlbumName());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * 保存方法
	 * 
	 * @param picpath
	 * @param bm
	 * @param name
	 */
	public static void saveBitmap(String picpath, Bitmap bm, String name) {
		FileUtil.creatFileDir(picpath);
		Y.e("PictureUtil", "saveBitmap", "�?始保存图�?");
//		Log.e("PictureUtil", "�?始保存图�?");
		File f = new File(picpath, name);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
			Y.e("PictureUtil", "saveBitmap", "完成保存图片");
//			Log.e("PictureUtil", "完成保存图片");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
//			if (!bm.isRecycled()) {
//				bm.recycle();
//				bm=null;
//			}
		}
	}

	/**
	 * 判断图片是否存在
	 * 
	 * @param picpath
	 * @param name
	 * @return
	 */
	public static boolean checkBitmap(String picpath, String name) {
		Boolean bitmapflag = false;
		File f = new File(picpath, name);
		if (f != null && f.exists()) {
			// PictureUtil.compressBitmap(f.getPath(), f.getPath());
			Bitmap photo = ImageUtils.loadImage(f.getPath(), 480, 800, 0);
			if (photo != null) {
				bitmapflag = true;
				photo.recycle();
				photo = null;
			} else {
				bitmapflag = false;
			}
		}
		return bitmapflag;
	}

	/**
	 * 获取保存 隐患�??��的图片文件夹名称
	 * 
	 * @return
	 */
	public static String getAlbumName() {
		return "sheguantong";
	}

//	/**
//	 * 获取图片的大�??
//	 * 
//	 * @param bitmap
//	 * @return
//	 */
//	@SuppressLint("NewApi")
//	public static int getBitmapSize(Bitmap bitmap) {
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // API 19
//			return bitmap.getAllocationByteCount();
//		}
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {// API
//																			// 12
//			return bitmap.getByteCount();
//		}
//		return bitmap.getRowBytes() * bitmap.getHeight(); // earlier version
//	}

	/**
	 * 图片缩放
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap small(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		float scaleHeight = ((float) 300) / bitmap.getHeight();
		if (scaleHeight < 1) {
			// 缩放图片动作
			matrix.postScale(scaleHeight, scaleHeight);
		} else {
			matrix.postScale(0.8f, 0.8f); // 长和宽放大缩小的比例
		}
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		bitmap = null;
		bitmap = resizeBmp.copy(Bitmap.Config.ARGB_8888, true);
		matrix = null;
		resizeBmp.recycle();
		resizeBmp = null;
		return bitmap;
	}
}
