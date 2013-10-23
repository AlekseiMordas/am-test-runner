package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

	public static File createDirectory(String dirPath) {
		File directory = new File("");
		directory = new File(directory.getAbsolutePath() + dirPath);
		directory.mkdirs();
		return directory;
	}

	public static File createFile(File dir, String fileName) {
		File f = new File(dir, fileName);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return f;
	}

	public static void copyFile(byte[] input, File file) {
		try {
			FileOutputStream output = new FileOutputStream(file);
			output.write(input);
			output.flush();
			if (null != output) {
				output.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
