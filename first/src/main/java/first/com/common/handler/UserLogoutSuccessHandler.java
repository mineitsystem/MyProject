package first.com.common.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String refererUrl = request.getHeader("Referer");
		logger.info("Logout from: " + refererUrl);
		HttpSession session  = request.getSession(true);
		
		session.invalidate();
		
		response.sendRedirect(request.getContextPath()+"/");
	}

}
