package first.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.dto.SessionBox;
import first.common.dto.UserDetailsVO;
import first.common.dto.CommandMap;
import first.common.service.LoginService;
import first.common.service.ShaEncoder;
import first.common.service.UserService;
import first.common.util.FinalValues;
import first.common.util.ScreenResolver;
import first.common.util.UserInfoContext;
import first.common.util.WebContext;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="LoginService")
	private LoginService loginService;
	
	@Resource(name="UserService")
	private UserService userService;
	
	@Resource(name="shaEncoder")
	private ShaEncoder encoder;
	
	@RequestMapping(value= {"/login","/login.do"})
    public ModelAndView openTilesView(CommandMap commandMap, ModelAndView mv, String result, HttpServletRequest request,
    		   HttpServletResponse response) throws Exception{
		WebContext.set(request, response);		
		if(UserInfoContext.getUserId()!=null && !UserInfoContext.getUserId().isEmpty()) {
			mv.setViewName("redirect:/main/main");		
		}else {
			mv.setViewName("/login/login");
		}		
		mv.addObject("result", result);
    	return mv;
    }
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/goLoginCheck.do")
    public ModelAndView goLoginCheck(HttpServletRequest request, CommandMap commandMap, ModelAndView mv) throws Exception{
		
    	mv.setViewName("jsonView");    	  
    	
    	String LoginType = FinalValues.LOGIN_TYPE;
		String LoginMsg = FinalValues.LOGIN_MSG;
    	try {
    			
    			
    		    Map<String,Object> resultInfoMap = loginService.getLoginInfo(commandMap.getMap()); 
    		    boolean pass_err = false;
	    		   /*
	    		    *   |--------|----------|-----|-------------------|---------|-----|
	    				|USER_ID |USER_NAME |PASS |EMAIL              |PASS_ERR |AUTH |
	    				|--------|----------|-----|-------------------|---------|-----|
	    				|admin   |태산             |1    |ehdehd16@naver.com |0        |S    |
	    				|--------|----------|-----|-------------------|---------|-----|
	    		    * */
    	    	
    	    	if(resultInfoMap != null) {
    	    		Map<String,Object> resultPassMap = loginService.getLoginPass(commandMap.getMap()); 
    	    		if((Integer)resultInfoMap.get("PASS_ERR") < 6) {
    	    			
	    	    		if("1".equals(resultPassMap.get("PASSCNT").toString())){//비밀번호 일치	    	    			
	    	    			
    	    				HttpSession session = request.getSession(true);	            	        	
        	        		session.removeAttribute(FinalValues.COMM_USER_KEY);        	    			
        	    			session.invalidate();
            	    									
        	    			session = request.getSession(true);
        	        		
        	    			//사용자 정보 세션처리
        	    			SessionBox sBox = new SessionBox(session, FinalValues.COMM_USER_KEY);
        	    			session.setAttribute(FinalValues.COMM_USER_KEY, new Hashtable<String, String>());				
        	    			sBox.put("USER_ID", resultInfoMap.get("USER_ID"));
        	    			sBox.put("USER_NAME", resultInfoMap.get("USER_NAME"));
        	    			sBox.put("EMAIL", resultInfoMap.get("EMAIL"));
        	    			sBox.put("AUTH", resultInfoMap.get("AUTH"));
        	        		mv.addObject(LoginType, 1);            	        		
        	        		pass_err = true;	            	        
	    	    			
	    	    		} else {//비밀번호 다름
	    	    			pass_err = false;
	    	    			mv.addObject(LoginType, 3);
	    	    			mv.addObject(LoginMsg, FinalValues.LOGIN_MSG_3);
	    	    			
	    	    		}
	    	    		
	    	    		//초기화할지 카운트할지 정함
	    	    		commandMap.getMap().put(FinalValues.LOGIN_ERRCNT_TYPE, pass_err);
	    	    		loginService.updatePassErr(commandMap.getMap());
	    	    		
    	    		}else {//오류 실패횟수 초과
	    				pass_err = false;
	    				mv.addObject(LoginType, 4);
    	    			mv.addObject(LoginMsg, FinalValues.LOGIN_MSG_4);
	    			}    	    		    	    
    	    		
    	    	} else {//아이디 다름    	    		
    	    		mv.addObject(LoginType, 2);
    	    		mv.addObject(LoginMsg, FinalValues.LOGIN_MSG_2);    	    		
    	    	}
    	    	
    	    	
    		
    	}catch(Exception ex) {//쿼리 에러    		
    		mv.addObject(LoginType, 5);
    		mv.addObject(LoginMsg, FinalValues.LOGIN_MSG_5);
    		ex.printStackTrace();
    	}
    	
    	    
    	return mv;
    }
    
	
	@RequestMapping(value="/insertUser.do")
	public ModelAndView insertUser(CommandMap commandMap, HttpServletRequest request,  ModelAndView mv) throws Exception{
		mv.setViewName("forward:/login/login");
	    commandMap.getMap().put("PASS", encoder.saltEncoding(commandMap.getMap().get("PASS").toString(),commandMap.getMap().get("EMAIL").toString())); 
	    userService.insertUser(commandMap.getMap());
	     
	    return mv;
	}
	
	
	@RequestMapping(value= "/accessdenied")
    public ModelAndView accessDenied(CommandMap commandMap, ModelAndView mv) throws Exception{
		mv.setViewName("/error/accessdenied");
		mv.addObject("setHeader", ScreenResolver.resolve(this));
    	return mv;
    }
	
	@RequestMapping(value= "/checkAuth")
	@Secured({"ROLE_ADMIN","ROLE_USER"})
    public ModelAndView checkAuth(CommandMap commandMap, ModelAndView mv, Authentication auth) throws Exception{
		mv.setViewName("checkAuth");
		mv.addObject("setHeader", ScreenResolver.resolve(this));
		UserDetailsVO vo = (UserDetailsVO) auth.getPrincipal();
		log.info("Welcome checkAuth! Authentication is .=========="+ auth);
		log.info("UserDetailsVO ========="+ vo);
		mv.addObject("auth", auth );
		mv.addObject("vo", vo );
    	return mv;
    }
	
}
