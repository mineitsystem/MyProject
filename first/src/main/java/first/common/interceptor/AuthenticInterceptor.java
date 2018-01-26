/**
 * 프로그램명: AuthenticInterceptor.java
 * 내     용: HandlerInterceptorAdapter 상속받은 권한 체크 핸들러인터셉터.
 *			 1.리퀘스트맵핑된 컨트롤러를 찾기전 preHandle에서 로그인체크. WebContext 셋
 *			 2.컨트롤러 처리후 postHandle에서 WebContext제거.
 *			   contentType이 text/html이면서 메뉴ID가 있다면 레프트메뉴 담기.
 *
 *			 로그인체크에 해당되지않는 컨트롤러 처리는
 *           egovframework/spring/dispatcher-servlet.xml에 있는 exclusionAuth에 추가해준다.
 *			 <mvc:interceptors>
 *               <mvc:interceptor>
 *                   <mvc:mapping path="/**" />
 *                   <bean class="egovframework.mebica.pub.fw.interceptor.AuthenticInterceptor" >
 *                       <property name="exclusionAuth">
 *                           <set>
 *                               <value>/invoke/LOGIN</value>
 *                               <value>/invoke/CM0000/</value>
 *                           </set>
 *                       </property>
 *                   </bean>
 *               </mvc:interceptor>
 *            </mvc:interceptors>
 * 이     력:
 *  ---------------------------------------------------------------
 *  Revision    Date            Author      Description
 *  ---------------------------------------------------------------
 *  ver1.0      2013. 07. 01.   아무개              최초 작성
 */
package first.common.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import first.common.util.FinalValues;
import first.common.util.MenuUtil;
import first.common.util.WebContext;
import first.common.listener.SessionHelperListener;
import first.common.service.MenuService;

/**
 * AuthenticInterceptor.java
 *
 * @author tilldawn
 *
 */
public class AuthenticInterceptor extends HandlerInterceptorAdapter {
	
	

    @Resource(name = "localeResolver")
    private SessionLocaleResolver localeResolver;
    
    @Resource(name = "MenuService")
    private MenuService service;

    private Set<String> exclusionAuth;

    public AuthenticInterceptor() {
        super();
    }

    public void setExclusionAuth(Set<String> exclusionAuth) {
        this.exclusionAuth = exclusionAuth;
    }

    @SuppressWarnings("unused")
	private boolean sessionCheck(HttpServletRequest request) throws Exception {
        boolean isLogin = true;
        HttpSession session = request.getSession(false);

        if (session == null) {
            isLogin = false;
        } else {
            Object obj = session.getAttribute(FinalValues.COMM_USER_KEY);
            if (obj == null) {
                isLogin = false;
            } else {
                // 강제 로그아웃 된 User 인지 체크
                if (SessionHelperListener.getInstance().isLogOutUser(session)) {
                    throw new Exception();
                }
            }
        }

        if (!isLogin) {
        	String url = request.getRequestURI();
        	if(url.matches(".*LOGIN/*.*")){
        		// 로그인 이전 처리, 로그인 세션 체크 예외
        	}else{
        		throw new Exception();
        	}
        }

        return isLogin;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        WebContext.set(request, response);
        
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        
        if (contextPath != null && !"".equals(contextPath)) {
            requestURI = StringUtils.replaceOnce(requestURI, contextPath, "");
        }
        //세션 체크... 
        
        
        //다국어 체크
        String lang = request.getParameter("lang");
        if (StringUtils.isEmpty(lang) || "ko".equals(lang)) {
        	// 언어 선택 없을 시 한국어로 초기 설정
        	localeResolver.setLocale(request, response, Locale.KOREA);
        } else {
        	localeResolver.setLocale(request, response, Locale.US);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
            throws Exception {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        
        if (contextPath != null && !"".equals(contextPath)) 
            requestURI = StringUtils.replace(requestURI, contextPath, "");
        
        if (requestURI.startsWith("/fdown/"))		        return;
        if (requestURI.startsWith("/zipfdown/"))		    return;
        if (requestURI.startsWith("/exception"))	        return;
        if (requestURI.startsWith("/common/downloadFile"))	return;
                       
        try {
        	HttpSession session  = request.getSession(true);
        	
        	String contentType = request.getContentType();
            if (contentType == null) 
                contentType = "text/html";
            else
                contentType = contentType.toLowerCase();                                  
            
            List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
        	String[] menu_point =  requestURI.split("/");    
        	if(mav !=null) {
        		if(session.getAttribute("LEFT_MENU") == null) {
        			session.setAttribute("LEFT_MENU", service.listLeftMenu(map));
        		}else {
        			mav.addObject("LEFT_MENU", session.getAttribute("LEFT_MENU"));
        		}
        		        		        		           
            	mav.addObject("MENU_POINT", menu_point);
            	//menu_point[2].toUpperCase().contains("BOARD");
            	mav.addObject("contextPath",contextPath);
            	mav.addObject("requestURI",requestURI);
        	
        	}        	

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            WebContext.remove();
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
            throws Exception {

        // nothing to do..
    }

}