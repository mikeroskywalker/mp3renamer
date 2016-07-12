/**
 * 
 */
package com.mikero.mp3renamer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Peter
 *
 */
public class YAMLUtil {
	public static void parseYAML(){
		String fullfilename =
				System.getProperty("mp3folder") //$NON-NLS-1$
				+ File.separator
				+ "config.yml";

		File newConfiguration = new File(fullfilename);
		InputStream is = null;
		try {
			is = new FileInputStream(newConfiguration);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Yaml yaml = new Yaml();

	    @SuppressWarnings("unchecked")
	    Map<String, ArrayList> yamlParsers = (Map<String, ArrayList>) yaml.load(is);	    
	}
}
