package first.com.common.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import first.common.util.WebContext;
import first.common.dto.CommandMap;
import first.common.dto.SessionBox;
import first.common.dto.UserDetailsVO;
import first.common.service.LoginService;
import first.common.service.MenuService;
import first.common.util.FinalValues;
import first.common.util.UserInfoContext;

public class UserLoginSuccessHandler 
extends SavedRequestAwareAuthenticationSuccessHandler 
implements AuthenticationSuccessHandler{
	  
	Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name="LoginService")
	 private LoginService loginService;
	
	@Resource(name = "MenuService")
    private MenuService service;
	  
	 @Override
	 public void onAuthenticationSuccess(HttpServletRequest request,
	   HttpServletResponse response, Authentication auth) throws IOException,
	   ServletException {	    
		CommandMap commandMap = new CommandMap();
		Map<String, Object> map = new HashMap<String, Object>();
				
		try {
			commandMap.getMap().put("id",request.getParameter("id"));
			
			Map<String,Object> resultInfoMap = loginService.getLoginInfo(commandMap.getMap());
			
			if((Integer)resultInfoMap.get("PASS_ERR") < 6) { 
				
				commandMap.getMap().put(FinalValues.LOGIN_ERRCNT_TYPE, true);
    			loginService.updatePassErr(commandMap.getMap());    	
				
				UserDetailsVO u = (UserDetailsVO) auth.getPrincipal();
				logger.info(auth.getName());
				logger.info(auth.getAuthorities().toString());
				logger.info(auth.getDetails().toString());
				logger.info(auth.getPrincipal().toString());
				logger.info(u.getEmail().toString());
				 
				HttpSession session = request.getSession(false);	            	        	
				session.removeAttribute(FinalValues.COMM_USER_KEY);	//유저 정보 초기화	
				//session.invalidate();
				
											
				session = request.getSession(true);
				
				//사용자 정보 세션처리
				SessionBox sBox = new SessionBox(session, FinalValues.COMM_USER_KEY);
				session.setAttribute(FinalValues.COMM_USER_KEY, new Hashtable<String, String>());				
				sBox.put("USER_ID", auth.getName());
				sBox.put("USER_NAME", u.getNickname());
				sBox.put("EMAIL", u.getEmail());
				for(GrantedAuthority a : auth.getAuthorities()){
					sBox.put("AUTH", a.getAuthority().toString());
					logger.info(a.getAuthority());
				}
				
				WebContext.set(request, response);
				logger.info("==========================================");
				logger.info("==========================================");
				logger.info("==========================================");			
				logger.info(UserInfoContext.getUserId());
				logger.info(UserInfoContext.getUserName());
				logger.info(UserInfoContext.getEmail());
				if(UserInfoContext.getAuth().equals("ROLE_ADMIN")) {
    				map.put("ISADMIN",true);        				
    			}else {
    				map.put("ISADMIN",false);
    			}
				
				session.setAttribute("LEFT_MENU", service.listLeftMenu(map));
				logger.info("==========================================");
				logger.info("==========================================");
				logger.info("==========================================");			
				/*
				logger.info(String.valueOf(u.isAccountNonExpired()));
				logger.info(String.valueOf(u.isAccountNonLocked()));
				logger.info(String.valueOf(u.isCredentialsNonExpired()));
				logger.info(String.valueOf(u.isEnabled()));
				*/
				
				super.onAuthenticationSuccess(request, response, auth);
    					
    		}else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login");
    			request.setAttribute("errresult", "false");
    			request.setAttribute("errmessage", "MSG.pass_cnt_err");    			
    			dispatcher.forward(request, response);
    		}
									
		}catch(Exception ex){
			ex.printStackTrace();
		}
					
	 }
	  
	} 