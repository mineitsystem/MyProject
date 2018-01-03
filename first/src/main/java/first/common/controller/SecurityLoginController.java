package first.common.controller;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
//@RequestMapping(value="/security")
public class SecurityLoginController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired(required=true)
	private ShaPasswordEncoder passwordEncoder;

	@RequestMapping(value="/signin", method = RequestMethod.GET)
	public String signin(@RequestParam(value="error", required=false) String error, Model model) {
		
		model.addAttribute("error", error); 
	// Sha 암호값을 보기 위한 테스트용. 		
		String guest_password = passwordEncoder.encodePassword("guest", null); 		
		String admin_password = passwordEncoder.encodePassword("admin", null); 
		logger.info(guest_password + "//" + admin_password); 
		return "/security/signin"; 
	} 
	
	@PreAuthorize("authenticated") 
	@RequestMapping(value="/mypage", method = RequestMethod.GET) 
	public String mypage(Model model) { 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		model.addAttribute("user_name", auth.getName()); 
		return "/security/mypage"; 
	} 
	
	@RequestMapping(value="/denied", method = RequestMethod.GET) 
	public String denied() { 
		return "/security/denied"; 
	}
}

	
