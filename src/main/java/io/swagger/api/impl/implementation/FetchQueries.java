package io.swagger.api.impl.implementation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Mehtod "fetchQuery" accepts SQL file name as Input and
 * 
 * @return query which is resides in particular SQL File
 *
 */
public class FetchQueries {
	public String fetchQuery(String fileName) {
		String filePath = "resources/" + fileName + ".sql";
		String line = null;
		String query = null;
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				query = line;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return query;
	}

}