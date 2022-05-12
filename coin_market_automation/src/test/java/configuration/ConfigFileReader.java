package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	public String getReportConfigPath() throws FileNotFoundException, IOException {
		Properties pConfigFile = new Properties();
		pConfigFile.load(new FileInputStream("config/ApplicationConfiguration.properties"));
		String reportConfigPath = pConfigFile.getProperty("reportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}

}
