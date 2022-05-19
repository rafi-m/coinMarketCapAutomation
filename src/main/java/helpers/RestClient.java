package helpers;

import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@Component
public class RestClient {

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

	public static String getRequest(String url, Map<String, String> headers, Map<String, Object> queries)
			throws Exception {
		HttpRequest<?> request = Unirest.get(url);
		request.headers(headers);
		if(queries != null)
			request.queryString(queries);
		HttpResponse<String> response = request.asString();
		return response.getBody();
	}

	public static String postRequest(String url, Map<String, String> headers, String body) throws Exception {
		//	HttpRequest<?> request = Unirest.post(url).headers(headers).body(body).asString();
		HttpResponse<String> response =  Unirest.post(url).headers(headers).body(body).asString();
		System.out.println(response.getStatus());
		return response.getBody();
	}



	public static String putRequest(String url, Map<String, String> headers, String body) throws Exception {
		//	HttpRequest<?> request = Unirest.post(url).headers(headers).body(body).asString();
		HttpResponse<String> response =  Unirest.put(url).headers(headers).body(body).asString();
		System.out.println(response.getStatus());
		return response.getBody();
	}



	public static void main(String[] arg) throws Exception {

	}
}
