package configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportArchiveHelper {

	public static String getReportFolder() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyy_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String reDate = "Run_" + dtf.format(now);

		return reDate;
	}

}
