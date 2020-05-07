package com.cafe24.louw0.captcha;

import java.io.File;
import java.nio.file.Path;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cafe24.louw0.interceptor.CommonInterceptor;

@Component
public class ApiCaptchaRemove {
	
	@Scheduled(cron="0 0 1 * * *")
	public void captchaRemove() {
		Path serverPath = CommonInterceptor.SERVER_REAL_PATH;
		String folderPath = serverPath.toString()+ "\\resources\\captcha";
		File folder = new File(folderPath);
		File[] folderList = folder.listFiles();
		for(int i=0; i < folderList.length; i++) {
			folderList[i].delete();
		}
	}
}
