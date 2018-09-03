package org.kurento.tutorial.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class PropertyFileReader {
	private static final Logger LOG = LoggerFactory.getLogger(PropertyFileReader.class);

	private static Properties prop = new Properties();

	public static Properties readPropertyFile() throws Exception {
		if (prop.isEmpty()) {
			InputStream input = PropertyFileReader.class.getClassLoader().getResourceAsStream("ipCamera-url.properties");
			try {
				prop.load(input);
			} catch (IOException ex) {
				LOG.error(ex.getMessage());
				throw ex;
			} finally {
				if (input != null) {
					input.close();
				}
			}
		}
		return prop;
	}
}
