package first.com.common.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class UserLoginFailureHandler implements AuthenticationFailureHandler{
	  
	Logger logger = Logger.getLogger(this.getClass());
	  
	 @Override
	 public void onAuthenticationFailure(HttpServletRequest req,
	   HttpServletResponse res, AuthenticationException auth)
	   throws IOException, ServletException {
	  // TODO Auto-generated method stub
		logger.info(auth.getLocalizedMessage());
		logger.info(auth.getMessage());
		logger.info(req.getParameter("password"));				
		req.setAttribute("errMsg",auth.getMessage());
		res.sendRedirect(req.getContextPath()+"/login/login?result=false");
	 }
	  
	} 