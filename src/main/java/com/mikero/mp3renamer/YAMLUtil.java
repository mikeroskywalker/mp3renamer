/**
 * 
 */
package com.mikero.mp3renamer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.yaml.snakeyaml.Yaml;

/**
 * @author Peter
 *
 */
public class YAMLUtil {
	public static Config parseYAML(String fullfilename){

		File newConfiguration = new File(fullfilename);
		InputStream is = null;
		try {
			is = new FileInputStream(newConfiguration);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		Yaml yaml = new Yaml();

	    Config config =  yaml.loadAs(is, Config.class);
	    return config;
	}
}
