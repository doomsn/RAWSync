package org.tkr.filesync.rawsync;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class RAWSync {
	public static void main(String[] args) {

		System.out.println("\n\n---------------------------------------------------------");
		System.out.println("RAWSync ... I try to sync a RAW folder with a JPG folder.");
		System.out.println("---------------------------------------------------------\n");

		String userDir = System.getProperty("user.dir");
		File[] directories = new File(userDir).listFiles(File::isDirectory);
		List<File> fileList = Arrays.asList(directories);

		String jpgDir = userDir + File.separator + "jpg";
		System.out.println("Searching for jpg directory: " + jpgDir);

		boolean contJpg = fileList.contains(new File(jpgDir));
		if (!contJpg) {
			System.out.println("... No jpg folder! Aborting!");
			return;
		} else {
			System.out.println("... found it!");
		}

		String rawDir = userDir + File.separator + "raw";
		System.out.println("Searching for raw directory: " + rawDir);

		boolean contRaw = fileList.contains(new File(rawDir));
		if (!contRaw) {
			System.out.println("No raw folder! Aborting!");
			return;
		} else {
			System.out.println("... found it!");
		}

		System.out.println("Scanning through jpg files ...");
		File[] jpgFiles = new File(jpgDir).listFiles(File::isFile);
		List<String> jpgFileList = new ArrayList<String>();
		for (File jpgFile : jpgFiles) {
			String name = jpgFile.getName();
			String n1 = name.split("\\.")[0];
			jpgFileList.add(n1);
		}

		System.out.println("Scanning through raw files and deleting files not also present in the jpg folder ...");
		int nrDeletedFiles = 0;
		File[] rawFiles = new File(rawDir).listFiles(File::isFile);
		for (File rawFile : rawFiles) {
			String name = rawFile.getName();
			String n1 = name.split("\\.")[0];

			if (jpgFileList.contains(n1)) {
				continue;
			}

			System.out.println(n1);
			rawFile.delete();
			nrDeletedFiles++;
		}

		System.out.println("DONE --> I deleted " + nrDeletedFiles + " RAW files!");
	}
}
