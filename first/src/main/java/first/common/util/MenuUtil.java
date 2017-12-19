package first.common.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import first.common.service.MenuService;

@Component
public class MenuUtil {

    private static MenuService service;

    @Autowired(required = true)
    private MenuUtil(MenuService menuService) {
        if (service == null) {
            MenuUtil.service = menuService;
        }
    }
    
    public static String getMenuList(List<Map<String, Object>> map) {
    	
        List<?> menuList = null;
        StringBuffer menusb = new StringBuffer();
        /**/       
        try {        
            // LEFT 메뉴조회
            menuList = service.listLeftMenu(map);
            int menuSize = 0;
            
            menusb.append("<div id='_LEFT_MENU_' class='basic'>");
            String url = "";            

            if(menuList != null) {
                menuSize = menuList.size();
                for(int i=0; i<menuSize; i++) {
                	                
					@SuppressWarnings("unchecked")
					Map<String, Object> rtnMap = (Map<String, Object>) menuList.get(i);
					
                    	/*menusb.append("<p>");*/
                    	menusb.append("3".equals(rtnMap.get("MENU_DEPTH"))?"<p>":"");
                    	url = (String) rtnMap.get("SCRN_URL");
	                    url = rtnMap.get("_ctxpath")+url;
                    	menusb.append("<a href='#' onclick='javascript:_goMainScreenByLeft(\"");
                    	menusb.append(rtnMap.get("MID"));       	menusb.append("\",\"");
                    	menusb.append(url);		                   	menusb.append("\",\"");
                    	menusb.append(rtnMap.get("SCRN_GB"));      	menusb.append("\",\"");
                    	menusb.append(rtnMap.get("POP_WIDTH"));    	menusb.append("\",\"");
                    	menusb.append(rtnMap.get("POP_HEIGHT"));   	menusb.append("\",\"");
                    	menusb.append(rtnMap.get("MENU_DEPTH"));	menusb.append("\",\"");
                    	menusb.append(rtnMap.get("SCRN_URL"));		menusb.append("\",\"");
                    	menusb.append(rtnMap.get("SCRN_ID"));		menusb.append("\");'");
                    	
                    	/*if(StringUtil.isEmpty(rMap.get("_scrn_id")))
                    		menusb.append(" style='margin-left:10px;'>");
                    	else
                    		menusb.append(" class='selectm'>");*/
                    	                   
                    	
                    	menusb.append(rtnMap.get("MENU_NM"));
                    	/*menusb.append("</a></p>");*/
                    	menusb.append("</a>");
                    	menusb.append("3".equals(rtnMap.get("MENU_DEPTH"))?"</p>":"<div>");
                    }
                }//end for i
                menusb.append("</div>");            
            
            menusb.append("</div>");
   
        } catch (Exception e) {
            e.printStackTrace();            
        }
                
        return menusb.toString();
    }
}
