package first.login.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.common.CommandMap;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="/login.do")
    public ModelAndView openTilesView(CommandMap commandMap, ModelAndView mv) throws Exception{
		mv.setViewName("/login/login");

    	return mv;
    }
}
