package com.mikero.mp3renamer;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

public class ReadFromMusicFile {

	/**
	 * @param args
	 * @throws NotSupportedException 
	 */
	public static void main(String[] args) throws NotSupportedException {
		String yamlPath = args[0];

		YAMLUtil.parseYAML(yamlPath);
		File folderName = new File(mp3FolderPath);
		final String mp3Extension = ".mp3";
		
		Set<String> originalFiles = new HashSet<String>();
		Set<String> convertedFiles = new HashSet<String>();
		
		System.out.println("Total files amount: " + folderName.listFiles().length);

		for(File file: folderName.listFiles()){
			originalFiles.add(file.getName());
			try {
				//ignore non-mp3 files
				if(!file.getName().contains(mp3Extension)){
						System.out.println("No Way to convert this file: " + file.getName());
						convertedFiles.add(file.getName());
						file.renameTo(new File(mp3TargetFolderPath + file.getName()));
					}else{
						System.out.println(file.getName());
						Mp3File mp3file = new Mp3File(mp3FolderPath+file.getName());
						if(mp3file.hasId3v1Tag()){
							System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
							System.out.println(mp3file.getId3v1Tag().getTitle());
							if(mp3file.getId3v1Tag().getTitle() == null || "".equalsIgnoreCase(mp3file.getId3v1Tag().getTitle().trim())){								
								file.renameTo(new File(mp3TargetFolderPath + file.getName()));
							}else{
								byte[] fileNameBytes = mp3file.getId3v1Tag().getTitle().getBytes();
								String fileName =  new String(fileNameBytes, "UTF-8");
								file.renameTo(new File(mp3TargetFolderPath + fileName + mp3Extension));
							}
							convertedFiles.add(file.getName());
						}
						else if(mp3file.hasId3v2Tag()){
							System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
							String title = mp3file.getId3v2Tag().getTitle();
							if(title == null || "".equalsIgnoreCase(title.trim()))
							{
								file.renameTo(new File(mp3TargetFolderPath + file.getName()));
							}
							else
							{
								file.renameTo(new File(mp3TargetFolderPath + title + mp3Extension));
							}
							file.renameTo(new File(mp3TargetFolderPath + title + mp3Extension));	
							convertedFiles.add(file.getName());
						}
						else if(mp3file.hasCustomTag())
						{
							System.out.println(mp3file.hasCustomTag());
						}
						else{
							System.out.println("I missed sth");
						}
					}
			}catch (UnsupportedTagException e) {
				e.printStackTrace();
			} catch (InvalidDataException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(convertedFiles.size() == originalFiles.size()){
			System.out.print("Total converted: " + originalFiles.size());
		}else{
			System.out.println("Converted size is: " + convertedFiles.size() + " and Original size is " + originalFiles.size());
		}
	}
}