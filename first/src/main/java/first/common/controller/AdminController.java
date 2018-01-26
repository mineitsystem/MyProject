package first.common.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import first.common.dto.CommandMap;
import first.common.util.ScreenResolver;
import first.message.service.MessageService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="MessageService") 
	private MessageService messageService;
	
	@RequestMapping(value= "/admin")
    public ModelAndView openTilesView(CommandMap commandMap, ModelAndView mv) throws Exception{
		mv.setViewName("/admin/admin");
		mv.addObject("setHeader", ScreenResolver.resolve(this));
    	return mv;
    }
	
	@RequestMapping(value= {"/message","/message.do"})
    public ModelAndView openMessageBoardEgovList(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/admin/msgList");
    	
    	Map<String,Object> resultMap = messageService.selectMsgList(commandMap.getMap());
    	
    	mv.addObject("setHeader", ScreenResolver.resolve(this));
	    mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
	    mv.addObject("list", resultMap.get("result"));
	    
    	return mv;
    }
	
	@RequestMapping(value= {"/messageWrite","/messageWrite.do"})
    public ModelAndView openMessageWrite(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/admin/msgWrite");
    	        	
    	mv.addObject("setHeader", ScreenResolver.resolve(this));	    	    
	    
    	return mv;
    }
	
	@RequestMapping(value= {"/insertMsg","/insertMsg.do"})
    public ModelAndView insertMsg(CommandMap commandMap) throws Exception{		
    	ModelAndView mv = new ModelAndView("forward:/admin/message");
    	messageService.insertMsg(commandMap.getMap());        	    		    	    	   
    	return mv;
    }
	
}
