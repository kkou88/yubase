package com.yu.yubase.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import libcore.io.DiskLruCache;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v4.util.LruCache;

public class LruImageCache implements ImageCache {

	private static Context mContext = null;

	private static LruImageCache mLruImageCache;
	private LruCache<String, Bitmap> mLruCache;
	private DiskLruCache mDiskLruCache;

	public final String DISK_CACHE_DIR = "bitmap";
	public final long DISK_MAX_SIZE = 20 * 1024 * 1024;
	public final int IO_BUFFER_SIZE = 8 * 1024;

	public static LruImageCache getInstance(Context context) {
		if (mContext == null) {
			if (context == null) {
				return null;
			} else {
				mContext = context;
			}
		}
		if (mLruImageCache == null) {
			mLruImageCache = new LruImageCache();
		}
		return mLruImageCache;
	}

	private LruImageCache() {
		this.mLruCache = new LruCache<String, Bitmap>(getDefaultLruCacheSize()) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight() / 1024;
			}
		};

		try {
			File cacheDir = getDiskCacheDir(mContext, DISK_CACHE_DIR);
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			this.mDiskLruCache = DiskLruCache.open(cacheDir,
					getAppVersion(mContext), 1, DISK_MAX_SIZE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取内存缓存大小
	/**
	 * @return
	 * 获取内存缓存大小
	 */
	public int getDefaultLruCacheSize() {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 8;
		return cacheSize;
	}

	// 获取磁盘缓存路径
	@SuppressLint("NewApi")
	public File getDiskCacheDir(Context context, String uniqueName) {
		String cachePath="";
		if(Build.VERSION.SDK_INT >= 9){
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())
					|| !Environment.isExternalStorageRemovable()) {
				if (context.getExternalCacheDir()!=null) {
					cachePath = context.getExternalCacheDir().getPath();
				}
			} else {
				cachePath = context.getCacheDir().getPath();
			}
		} else {
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())) {
				cachePath = context.getExternalCacheDir().getPath();
			} else {
				cachePath = context.getCacheDir().getPath();
			}
		}
		return new File(cachePath + File.separator + uniqueName);
	}

	// 获取程序版本号
	/**
	 * @param context
	 * @return
	 * 获取程序版本号
	 */
	public int getAppVersion(Context context) {
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 1;
	}

	// MD5编码图片地址
	public String hashKeyForDisk(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}

	private String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	@Override
	public Bitmap getBitmap(String url) {
		String key = hashKeyForDisk(url);
		Bitmap bitmap = getBitmapFromLruCache(key);
		if (bitmap == null) {
			bitmap = getBitmapFromDiskLruCache(key); //如果图片大的话可能造成主线程卡顿
			// 从磁盘读出后，放入内存
			if (bitmap != null) {
				putBitmapToLruCache(key, bitmap);
			}
		}
		return bitmap;
	}

	@Override
	public void putBitmap(String url, final Bitmap bitmap) {
		final String key = hashKeyForDisk(url);
		putBitmapToLruCache(key, bitmap);
		new Thread() {
			@Override
			public void run() {
				putBitmapToDiskLruCache(key, bitmap);
			};
		}.start();
	}

	// 从内存缓存中获取Bitmap
	/**
	 * @param key
	 * @return
	 * 从内存缓存中获取Bitmap
	 */
	public Bitmap getBitmapFromLruCache(String key) {
		return mLruCache.get(key);
	}
	
	
	/**
	 * @param key
	 * @param bitmap
	 * 把Bitmap加入内存缓存
	 */
	public void putBitmapToLruCache(String key, Bitmap bitmap) {
		mLruCache.put(key, bitmap);
	}

	// 从内存缓存中移除Bitmap
	/**
	 * @param key
	 * 从内存缓存中移除Bitmap
	 */
	public void removeBitmapToLruCache(String key) {
		mLruCache.remove(key);
	}
		
	// 从磁盘缓存中获取Bitmap
	/**
	 * @param key
	 * @return
	 * 从磁盘缓存中获取Bitmap
	 */
	public Bitmap getBitmapFromDiskLruCache(String key) {
		InputStream inputStream = null;
		BufferedInputStream buffInputStream = null;
		DiskLruCache.Snapshot snapshot = null;
		try {
			snapshot = mDiskLruCache.get(key);
			if (snapshot != null) {
				inputStream = snapshot.getInputStream(0);
				if (inputStream != null) {
					buffInputStream = new BufferedInputStream(inputStream,
							IO_BUFFER_SIZE);
					Bitmap bitmap = BitmapFactory.decodeStream(buffInputStream);
					return bitmap;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (buffInputStream != null) {
				try {
					buffInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (snapshot != null) {
				snapshot.close();
			}
		}
		return null;
	}

	// 把Bitmap加入磁盘缓存
	/**
	 * @param key
	 * @param bitmap
	 * 把Bitmap加入磁盘缓存
	 */
	public void putBitmapToDiskLruCache(String key, Bitmap bitmap) {
		try {
			DiskLruCache.Editor editor = mDiskLruCache.edit(key);
			if (editor != null) {
				OutputStream outputStream = editor.newOutputStream(0);
				bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
				editor.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 在磁盘缓存中移除指定Bitmap
	/**
	 * @param key
	 * @return
	 * 在磁盘缓存中移除指定Bitmap
	 */
	public boolean removeBitmapInDiskLruCache(String key) {
		try {
			return mDiskLruCache.remove(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 清空磁盘缓存
	/**
	 * 清空磁盘缓存
	 */
	public void deleteDiskLruCache() {
		try {
			mDiskLruCache.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 关闭磁盘缓存
	/**
	 * 关闭磁盘缓存
	 */
	public void closeDiskLruCache() {
		try {
			mDiskLruCache.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 刷新磁盘缓存
	/**
	 * 刷新磁盘缓存
	 */
	public void flushDiskLruCache() {
		try {
			mDiskLruCache.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 判断磁盘缓存上是否存在该key
	/**
	 * @param key
	 * @return
	 * 判断磁盘缓存上是否存在该key
	 */
	public boolean containsKey(String key) {
		boolean contained = false;
		DiskLruCache.Snapshot snapshot = null;
		try {
			snapshot = mDiskLruCache.get(key);
			contained = snapshot != null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (snapshot != null) {
				snapshot.close();
			}
		}
		return contained;
	}
}