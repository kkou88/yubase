package com.yu.yubase.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;

public class FileUtil {
	public static boolean creatFileDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			System.out.println("创建目录" + destDirName + "失败，目标目录已存在�?");
			return true;
		}
		if (!destDirName.endsWith(File.separator))
			destDirName = destDirName + File.separator;
		// 创建单个目录
		if (dir.mkdirs()) {
			System.out.println("创建目录" + destDirName + "成功�?");
			return true;
		} else {
			System.out.println("创建目录" + destDirName + "失败�?");
			return false;
		}
	}

	/**
	 * 读取表情配置文件
	 * 
	 * @param context
	 * @return
	 */
	public static List<String> getEmojiFile(Context context) {
		try {
			List<String> list = new ArrayList<String>();
			InputStream in = context.getResources().getAssets().open("emoji");
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String str = null;
			while ((str = br.readLine()) != null) {
				list.add(str);
			}

			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 */
	public static void deleteFileDir(String filePath) {
		deleteFileDir(new File(filePath));
	}

	/**
	 * 删除文件�?
	 * 
	 * @param file
	 */
	public static void deleteFileDir(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文�?
				file.delete(); // delete()方法 你应该知�? 是删除的意�??;
			} else if (file.isDirectory()) { // 否则如果它是�?个目�?
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					FileUtil.deleteFileDir(files[i]); // 把每个文�? 用这个方法进行迭�?
				}
			}
			file.delete();
		} else {
			System.out.println("�?删除的文件不存在�?" + '\n');
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param file
	 */
	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		if (file != null && file.exists())
			return true;
		return false;
	}

	/**
	 * 从inStream拷贝到SD卡生成文�?
	 * 
	 * @return
	 */
	public static boolean createFileByStream(InputStream inStream,
			String dstFileName) {
		try {
			createPathByFile(dstFileName);// 创建多级目录结构

			int len = 0;
			byte[] bytes = new byte[1024];
			FileOutputStream fos = new FileOutputStream(new File(dstFileName));
			while ((len = inStream.read(bytes, 0, 1024)) > 0) {
				fos.write(bytes, 0, len);
			}
			inStream.close();
			fos.close();
		} catch (FileNotFoundException e) {
//			AppManager.showToastMessage("创建文件失败");
			return false;
		} catch (IOException e) {
//			AppManager.showToastMessage("读写错误,请检�?");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 读取文本文件
	 * 
	 * @param inputStream
	 *            �?输入�?
	 * @param charSet
	 *            字符�?:gbk,utf-8�?
	 * @return
	 */
	public static String getTxtStream(InputStream inputStream) {
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(inputStreamReader);
		StringBuffer sb = new StringBuffer("");
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				if (sb.length() > 0)
					sb.append("\n");
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getTxtFile(String fileName) {
		try {
			if (isFileExist(fileName)) {
				return getTxtStream(new FileInputStream(new File(fileName)));
			}else{
				return "";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void copyDir(String inputname, String outputname) {
		makeDirectory(outputname);
		File[] file = (new File(inputname)).listFiles();
		if (file == null)
			return;
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				try {
					file[i].toString();
					FileInputStream input = new FileInputStream(file[i]);
					// mkdir if destination does not exist
					File outtest = new File(outputname);
					if (!outtest.exists()) {
						outtest.mkdir();
					}
					FileOutputStream output;
					output = new FileOutputStream(outputname + "/"
							+ (file[i].getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (file[i].isDirectory()) {
				// System.out.print(file[i].getAbsolutePath()+ "/n" +
				// file[i].getName());
				System.out.print(file[i].toString() + "," + outputname + "//"
						+ file[i].getName());
				copyDir(file[i].toString(),
						outputname + "//" + file[i].getName());
			}
		}
	}

	// 建立目录
	public static boolean createPath(String path) {
		File dstfile = new File(path);
		if (!dstfile.exists())
			dstfile.mkdirs();// 创建多级目录结构
		return true;
	}

	// 根据文件建立目录
	public static boolean createPathByFile(String fileName) {
		int start = fileName.lastIndexOf("/");
		if (start > 0) {
			String path = fileName.substring(0, start);
			createPath(path);
		}
		return true;
	}

	// �?�? sd卡文件是否存�?
	public static boolean checkSDFileExits(String fileName, boolean sdName) {
		// 文件名没有带SD卡路径，加上
		if (!sdName) {
			fileName = Environment.getExternalStorageDirectory() + fileName;
		}
		File file = new File(fileName);

		return file.exists();
	}

	// �?查是否SDK准备�?
	public static boolean checkSDExist() {
		return Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

	// 获取SD卡的路径，如果SD卡没准备好返回null
	public static String getSDPath() {
		if (checkSDExist()) {
			return Environment.getExternalStorageDirectory().toString();// 获取跟目�?
		} else {
			return null;
		}
	}

	/**
	 * 将SDK的文件写入到Stream
	 * 
	 * @return
	 */
	public static boolean readFileToStream(String srcFileName,
			OutputStream outStream) {
		try {
			int len = 0;
			byte[] bytes = new byte[1024];
			FileInputStream fis = new FileInputStream(new File(srcFileName));
			while ((len = fis.read(bytes, 0, 1024)) > 0) {
				outStream.write(bytes, 0, len);
			}
			outStream.flush();
			outStream.close();
			fis.close();
		} catch (FileNotFoundException e) {
//			AppManager.showToastMessage("SD卡文件没有找�?");
			return false;
		} catch (IOException e) {
//			AppManager.showToastMessage("读写错误,请检�?");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 生产文件 如果文件�?在路径不存在则生成路�?
	 * 
	 * @param fileName
	 *            文件�? 带路�?
	 * @param isDirectory
	 *            是否为路�?
	 * @return
	 * @author yayagepei
	 * @date 2008-8-27
	 */
	public static File buildFile(String fileName, boolean isDirectory) {
		File target = new File(fileName);
		if (isDirectory) {
			target.mkdirs();
		} else {
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
				target = new File(target.getAbsolutePath());
			}
		}
		return target;
	}

	/**
	 * makeDirectory("c:/1/2/3/4/5/6"); �?级一级的建�?�目�?
	 * 
	 * @param path
	 *            三种格式�?"c:/1/2/3/4/5/6", "1/2/3/4/5/6", "/1/2/3/4/5/6"
	 */
	public static String makeDirectory(String path) {
		File f = new File(path);
		if (f.isDirectory()) {
			return "success: 已经存在这个目录";
		} else {
			if (f.mkdirs()) {
				return "success: 成功创建这个目录";
			} else {
				return "error: 创建目录失败, 路径:[" + path + "]";
			}
		}
	}

	/**
	 * 拷贝文件
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 */
	public static boolean copyFile(String sourceFile, String targetFile) {
		File source = new File(sourceFile);
		File target = new File(targetFile);
		try {
			target.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (source.exists() && target.exists())
			return copyFile(source, target);
		return false;

	}

	/**
	 * 拷贝文件
	 * 
	 * @param sourceDir
	 * @param targetDir
	 * @throws IOException
	 */
	public static boolean copyFile(File sourceFile, File targetFile) {
		// New file input stream and buffer
		FileInputStream input = null;
		BufferedInputStream inBuff = null;
		FileOutputStream output = null;
		BufferedOutputStream outBuff = null;

		try {
			input = new FileInputStream(sourceFile);
			inBuff = new BufferedInputStream(input);
			// New file output stream and buffer
			output = new FileOutputStream(targetFile);
			outBuff = new BufferedOutputStream(output);

			// Buffer byte array
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			outBuff.flush();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				// Close stream
				if (null != inBuff) {
					inBuff.close();
				}
				if (null != output) {
					output.close();
				}
				if (null != outBuff) {
					outBuff.close();
				}
				if (null != input) {
					input.close();
				}
			} catch (IOException e) {
			}
		}

		return true;
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		}
	}

	/**
	 * Delete file
	 * 
	 * @param file
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		deleteFile(file);
	}

	/**
	 * Write string into file
	 * 
	 * @param fileName
	 * @param newStr
	 * @throws IOException
	 */
	public static void writeFile(String fileName, String content)
			throws IOException {

		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(new File(fileName), "rw");
			raf.seek(raf.length());
			raf.write(content.getBytes("UTF-8"));
		} catch (IOException e) {
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
				}
			}
		}

	}

	/**
	 * Convert byte to Kbyte
	 * 
	 * @param fileByte
	 * @return
	 */
	public static float ConvertToKb(long fileByte) {

		return (fileByte / 1024f);
	}

	/**
	 * Read file content
	 * 
	 * @param fileName
	 * @return file content
	 */
	public static String readFileContent(String file) {

		StringBuffer content = new StringBuffer();
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String str = null;
			while ((str = br.readLine()) != null) {
				content.append(str + "\n");
			}

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			try {
				// Close Stream
				if (null != br) {
					br.close();
				}
				if (null != fr) {
					fr.close();
				}
			} catch (IOException e) {
			}
		}

		return content.toString();
	}

	public static String getFilesize(int size) {
		String rt_size = "";
		if (size >= 1024 && size < 1024 * 1024) {
			rt_size = size / 1024 + "KB";
		} else if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {
			rt_size = (size / 1024) / 1024 + "MB";
		} else {
			rt_size = size + "字节";
		}
		return rt_size;
	}

}
