/**
 * 
 */
package com.mikero.mp3renamer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * @author Peter
 *
 */
public class YAMLUtil {
	public static void parseYAML(String fullfilename){

		File newConfiguration = new File(fullfilename);
		InputStream is = null;
		try {
			is = new FileInputStream(newConfiguration);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		Yaml yaml = new Yaml();

	    @SuppressWarnings("unchecked")
	    Map<String, Object> yamlParsers = (Map<String, Object>) yaml.load(is);	    
	}
}
