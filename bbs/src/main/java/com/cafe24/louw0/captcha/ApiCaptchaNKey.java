package com.cafe24.louw0.captcha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.cafe24.louw0.vo.CaptchaVo;


public class ApiCaptchaNKey {

	public String getCaptchaKey() {
		CaptchaVo captchaVo = new CaptchaVo();
	 	String clientId = captchaVo.getClientId(); //애플리케이션 클라이언트 아이디값";
        String clientSecret = captchaVo.getClientSecret(); 	//애플리케이션 클라이언트 시크릿값";
		
		String code = "0";
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
		
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);
		
		return responseBody;
	}
	
	private String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for(Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				return readBody(con.getInputStream());
			}else {
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		}finally {
			con.disconnect();
		}
	}
	
	private HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		}catch(MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : "+apiUrl, e);
		}catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다 : "+apiUrl, e);
		}
	}
	private String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);
		try(BufferedReader lineReader = new BufferedReader(streamReader)){
			StringBuilder responseBody = new StringBuilder();
			String line;
			while((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			return responseBody.toString();
		}catch(IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다", e);
		}
	}
}
