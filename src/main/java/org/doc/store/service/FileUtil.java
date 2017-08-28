package org.doc.store.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

	public static String buildFilePath(String docName, String... dirPathVars) {
		StringBuffer fPath = new StringBuffer();
		if (dirPathVars != null) {
			for (String dirPath : dirPathVars) {
				fPath.append(dirPath).append(File.separator);
			}
		}

		fPath.append(docName);
		return fPath.toString();
	}

	public static void readDataFromISAndSaveToOS(InputStream is, String docUrl) throws IOException {
		readDataFromISAndSaveToOS(is, new FileOutputStream(docUrl));
	}

	public static void readDataFromISAndSaveToOS(InputStream is, OutputStream fos) throws IOException {
		try {
			byte[] byteArr = new byte[10 * 1024];
			int numOfBytesRead = is.read(byteArr);
			while (numOfBytesRead != -1) {
				fos.write(byteArr, 0, numOfBytesRead);
				numOfBytesRead = is.read(byteArr);
			}
			fos.flush();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

}
