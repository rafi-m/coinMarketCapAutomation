package configuration;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;


public class HttpsClientBkp {

	public String httpsPostRequest(String url, Map<String, String> headers, String content) throws Exception {
		URL u = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) u.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		Set<String> keys = headers.keySet();
		for (String key : keys) {

			conn.setRequestProperty(key, headers.get(key));
		}

		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(content);
		wr.flush();
		wr.close();
		String inputLine;
		StringBuffer response = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

	public String httpPostRequest(String url, Map<String, String> headers, String content) throws Exception {
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		Set<String> keys = headers.keySet();
		for (String key : keys) {

			conn.setRequestProperty(key, headers.get(key));
		}

		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(content);
		wr.flush();
		wr.close();
		String inputLine;
		StringBuffer response = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

	public String getRequest(String url, Map<String, String> headers) throws Exception {
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setRequestMethod("GET");
		Set<String> keys = headers.keySet();
		for (String key : keys) {
			conn.setRequestProperty(key, headers.get(key));
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

	public String getHttpsRequest(String url, Map<String, String> headers) throws Exception {
		URL u = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) u.openConnection();
		conn.setRequestMethod("GET");
		Set<String> keys = headers.keySet();
		for (String key : keys) {
			conn.setRequestProperty(key, headers.get(key));
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

	public static void main(String[] arg) throws Exception {
		// String req = FileUtils.readFileToString(new
		// File("C:/Users/mohamed.rafiudeen/eclipse-workspace/beeahtestautomation/tests/basemessage/setRoomTemprature.json"));
		String url = "https://api.das-dev.basf.com/prwrapper/2.7/services/getDetailedPRConfigData?country=DE&requestingSystem=xarvio&property=DPR-OP-BRSNW-PGR";
		Map<String, String> head = new HashMap<String, String>();
		head.put("Authorization", "Basic VTJBY2Nlc3NQUkVuZHBvaW50OqckJTkuOzc0OS0h");
		HttpsClientBkp client = new HttpsClientBkp();
		String resp = client.getRequest(url, head);
		System.out.println(resp);

	}
}
