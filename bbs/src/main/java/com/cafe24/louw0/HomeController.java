package com.cafe24.louw0;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.louw0.captcha.ApiCaptchaNKey;
import com.cafe24.louw0.captcha.ApiCaptchaImage;
import com.cafe24.louw0.service.BoardService;
import com.cafe24.louw0.vo.Board;
import com.google.gson.Gson;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model, HttpServletRequest request) {
		String sId = (String) session.getAttribute("sId");
		model.addAttribute("sId", sId);
		Map<String, String> map = getCaptcha(request);
		model.addAttribute("key", map.get("key"));
		model.addAttribute("captchaImage", map.get("image"));
		
		return "home";
	}
	
	public Map<String, String> getCaptcha(HttpServletRequest request) {
		
		Gson gson = new Gson();
		ApiCaptchaNKey aKey = new ApiCaptchaNKey();
		String captchaKey = aKey.getCaptchaKey();
		ApiCaptchaImage aImage = new ApiCaptchaImage();
		Map<String, String> map = new HashMap<String, String>();
		
		map = gson.fromJson(captchaKey, map.getClass());
		captchaKey = map.get("key");
		map.put("image", aImage.captchaImage(request, captchaKey));
		return map;
	}
	
}
