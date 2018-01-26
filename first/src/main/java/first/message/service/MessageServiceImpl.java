package first.message.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import first.common.service.BaseService;
import first.common.util.FileUtils;
import first.community.dao.CommunityDAO;
import first.message.dao.MessageDAO;

@Service("MessageService")
public class MessageServiceImpl extends BaseService implements MessageService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="MessageDAO")
	private MessageDAO messageDAO;

	@Override
	public Map<String, Object> selectMsgList(Map<String, Object> map) throws Exception {
		return messageDAO.selectMsgList(map);
	}

	@Override
	public void insertMsg(Map<String, Object> map) throws Exception {
		messageDAO.insertMsgList(map);		
	}
		
}
