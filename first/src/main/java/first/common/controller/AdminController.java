package first.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.dto.CommandMap;
import first.common.util.ScreenResolver;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	
	@RequestMapping(value= {"/admin","/admin.do"})
    public ModelAndView openTilesView(CommandMap commandMap, ModelAndView mv) throws Exception{
		mv.setViewName("/admin/admin");
		mv.addObject("setHeader", ScreenResolver.resolve(this));
    	return mv;
    }
	
}
