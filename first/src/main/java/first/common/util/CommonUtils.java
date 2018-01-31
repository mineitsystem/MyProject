package first.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import first.common.service.MenuService;

public class CommonUtils {
	
	@Resource(name="messageSourceManagement")
	private MessageSourceManagement messageSourceManagement;
	
	@Resource(name = "MenuService")
    private MenuService service;
	
	 /** 영문화 처리를 위한 로케일 리졸버  **/
    @Resource(name = "localeResolver")
    private SessionLocaleResolver localeResolver;
	
	
	public static String getRandomString(){
	       return UUID.randomUUID().toString().replaceAll("-", "");
	}
	 
    protected void reloadMessage() throws Exception {
        messageSourceManagement.reload();
    }
    
   
    
    /**
     * 서버단 메세지처리하기위한 localeResolver를 통한 세션에담긴 영문구분 가지고 메세지를 분기해서 처리함.
     * 파라미터를 담는 메세지 처리 메소드.
     * @param code
     * @param msgArr
     * @return
     * @throws Exception
     */
    protected String getMessage(String code, String[] msgArr) throws Exception {
        return messageSourceManagement.getMessage(code, msgArr, localeResolver.resolveLocale(WebContext.getRequest()));
    }
    
    /**
     * 서버단 메세지처리하기위한 localeResolver를 통한 세션에담긴 영문구분 가지고 메세지를 분기해서 처리함.
     * @param code
     * @return
     * @throws Exception
     */
    protected String getMessage(String code) throws Exception {
        return messageSourceManagement.getMessage(code, null, localeResolver.resolveLocale(WebContext.getRequest()));
    }
    
    protected void setMenuSession(HttpServletRequest request, Map<String, Object> map) {
    	  	
    	try {
    		HttpSession session  = request.getSession(true);  
			session.setAttribute("LEFT_MENU", service.listLeftMenu(map));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
