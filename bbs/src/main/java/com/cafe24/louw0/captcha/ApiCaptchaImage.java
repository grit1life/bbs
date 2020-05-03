package com.cafe24.louw0.captcha;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.cafe24.louw0.vo.CaptchaVo;

public class ApiCaptchaImage {
	 public String captchaImage(String key, HttpServletRequest request) {
		 	CaptchaVo captchaVo = new CaptchaVo();
		 	String clientId = captchaVo.getClientId(); //애플리케이션 클라이언트 아이디값";
	        String clientSecret = captchaVo.getClientSecret(); 	//애플리케이션 클라이언트 시크릿값";

	        //String key = "C8nBYTws6pSeinXR"; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
	        String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;

	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders, request);

	        return responseBody;
	    }

	    public static String get(String apiUrl, Map<String, String> requestHeaders, HttpServletRequest request){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }

	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return getImage(con.getInputStream(), request);
	            } else { // 에러 발생
	                return error(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }

	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }

	    private static String getImage(InputStream is, HttpServletRequest request){
	        int read;
	        byte[] bytes = new byte[1024];
	        // 랜덤한 이름으로  파일 생성
	        String filename = Long.valueOf(new Date().getTime()).toString();
	        String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
	        File f = new File(contextRoot+"src/main/webapp/resources/captcha/"+filename + ".jpg");
	        try(OutputStream outputStream = new FileOutputStream(f)){
	            f.createNewFile();
	            while ((read = is.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }
	            return filename;
	        } catch (IOException e) {
	            throw new RuntimeException("이미지 캡차 파일 생성에 실패 했습니다.",e);
	        }
	    }

	    private static String error(InputStream body) {
	        InputStreamReader streamReader = new InputStreamReader(body);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();

	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }

	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }
}
