package first.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import first.common.dto.CommandMap;
import first.common.util.ScreenResolver;

@Controller
@RequestMapping(value="/main")
public class MainController {
		
	@RequestMapping(value= {"/main","/main.do"})
    public ModelAndView openTilesView(CommandMap commandMap, ModelAndView mv) throws Exception{
		mv.setViewName("/main/main");
		mv.addObject("setHeader", ScreenResolver.resolve(this));
    	return mv;
    }
	
}

