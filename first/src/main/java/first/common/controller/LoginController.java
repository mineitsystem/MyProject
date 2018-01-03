package first.common.controller;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.dto.SessionBox;
import first.common.dto.CommandMap;
import first.common.service.LoginService;
import first.common.util.FinalValues;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="LoginService")
	private LoginService loginService;
	
	@RequestMapping(value= {"/login","/login.do"})
    public ModelAndView openTilesView(CommandMap commandMap, ModelAndView mv) throws Exception{
		mv.setViewName("/login/login");
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
	            	        	if (!session.isNew()) {
	            	        		        		
	            	        		session.removeAttribute(FinalValues.COMM_USER_KEY);
	            	    			session.removeAttribute(FinalValues.COMM_USER_AUTH);
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
	            	        	}
	    	    			
	    	    			
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
}
