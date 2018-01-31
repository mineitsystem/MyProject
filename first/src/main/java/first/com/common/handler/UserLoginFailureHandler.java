package first.com.common.handler;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import first.common.dto.CommandMap;
import first.common.service.LoginService;
import first.common.util.CommonUtils;
import first.common.util.FinalValues;
import first.common.util.WebContext;

public class UserLoginFailureHandler extends CommonUtils implements AuthenticationFailureHandler{
	  
	 Logger logger = Logger.getLogger(this.getClass());
	 
	 @Resource(name="LoginService")
	 private LoginService loginService;
	  
	 @SuppressWarnings("unused")
	 @Override
	 public void onAuthenticationFailure(HttpServletRequest request,
	 HttpServletResponse response, AuthenticationException auth)
	 throws IOException, ServletException {
	 // TODO Auto-generated method stub
	 WebContext.set(request, response);	 
	
	 CommandMap commandMap = new CommandMap();	 
	 String errMessage = null;	 
	 String LoginType = FinalValues.LOGIN_TYPE;
	 
	 try {
					
		commandMap.getMap().put("id",request.getParameter("id"));
		 					
		Map<String,Object> resultInfoMap = loginService.getLoginInfo(commandMap.getMap());
		
		if(resultInfoMap != null) {//없는 아이디
			Map<String,Object> resultPassMap = loginService.getLoginPass(commandMap.getMap()); 
    		if((Integer)resultInfoMap.get("PASS_ERR") < 6) {    			
    			errMessage = "MSG.pass_err";
    			commandMap.getMap().put(FinalValues.LOGIN_ERRCNT_TYPE, false);
    			loginService.updatePassErr(commandMap.getMap());    			
    		}else {    			
    			errMessage = "MSG.pass_cnt_err";
    			
    		}
			
		}else {			
			errMessage = "MSG.id_err";				
			
		}
		
		logger.info("***************"+getMessage(errMessage)+"**************");
		
	 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	 } 
		   				
		logger.info(auth.getLocalizedMessage());
		logger.info(auth.getMessage());
		logger.info("==========================");
		logger.info("==========================");
		logger.info(request.getParameter("id"));	
		logger.info(request.getParameter("password"));				
		request.setAttribute("errMsg",auth.getMessage());
		logger.info("==========================");
		logger.info("==========================");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login");
		request.setAttribute("errresult", "false");
		request.setAttribute("errmessage", errMessage);    			
		dispatcher.forward(request, response);
		//response.sendRedirect(request.getContextPath()+"/login/login?result=false&errmessage="+errMessage);
	 }
	  
	} 