package com.cafe24.louw0.captcha;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.cafe24.louw0.vo.CaptchaVo;

public class ApiCaptchaImage {
	 public String captchaImage(String key) {
		 	CaptchaVo captchaVo = new CaptchaVo();
		 	String clientId = captchaVo.getClientId(); //애플리케이션 클라이언트 아이디값";
	        String clientSecret = captchaVo.getClientSecret(); 	//애플리케이션 클라이언트 시크릿값";

	        //String key = "C8nBYTws6pSeinXR"; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
	        String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;

	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders);

	        return responseBody;
	    }

	    public String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }

	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return getImage(con.getInputStream());
	            } else { // 에러 발생
	                return error(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }

	    private HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }

	    private String getImage(InputStream is){
	        int read;
	        byte[] bytes = new byte[1024];
	        // 랜덤한 이름으로  파일 생성
	        Path folderPath = Paths.get("/captcah/");
	        folderPath = folderPath.toAbsolutePath();
	        File folder = new File(folderPath.toString());
	        if(!folder.exists()) {
	        	folder.mkdir();
	        }
	        String filename = Long.valueOf(new Date().getTime()).toString();
	        Path path = Paths.get("/captcha/"+filename+".jpg");
	        path = path.toAbsolutePath();
	        System.out.println(path);
	        File f = new File(path.toString());
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

	    private String error(InputStream body) {
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
