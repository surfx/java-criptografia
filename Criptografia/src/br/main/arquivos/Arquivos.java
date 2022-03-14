package br.main.arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Arquivos {

	public static void saveFile(String fileNamePath, String content) {
		File f = new File(fileNamePath);
		if (f.exists()) { f.delete(); }
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
		}
	}

	public static String readFile(String fileNamePath) {
		File f = new File(fileNamePath);
		if (!f.exists()) {return "";}
		try {
			InputStream inputStream = new FileInputStream(f);
			StringBuilder resultStringBuilder = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
				String line;
				while ((line = br.readLine()) != null) {
					resultStringBuilder.append(line).append("\n");
				}
			}
			return resultStringBuilder.toString();
		} catch (Exception e) {
		}
		return "";
	}

	public static String getCurrentPath() {
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		if (!path.endsWith("/")) { path += "/"; }
		path += "htmls/";

		File fPath = new File(path);
		if (!fPath.exists()) {
			fPath.mkdirs();
		}

		return path;
	}

}