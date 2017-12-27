package first.login.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import first.common.common.CommandMap;
import first.common.service.LoginService;
import first.community.service.CommunityService;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="LoginService")
	private LoginService loginService;
	
	@RequestMapping(value="/login.do")
    public ModelAndView openTilesView(CommandMap commandMap, ModelAndView mv) throws Exception{
		mv.setViewName("/login/login");

    	return mv;
    }
	
	@RequestMapping(value="/goLoginCheck.do")
    public ModelAndView goLoginCheck(CommandMap commandMap, ModelAndView mv) throws Exception{
    	mv.setViewName("redirect:/community/openBoardList.do");    	
    	//Map<String,Object> resultMap = sampleService.selectBoardList(commandMap.getMap());
         
	    //mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
	    //mv.addObject("list", resultMap.get("result"));
    	
    	return mv;
    }
}
