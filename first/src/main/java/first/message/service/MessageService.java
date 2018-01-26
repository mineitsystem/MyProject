package first.message.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MessageService {

	Map<String, Object> selectMsgList(Map<String, Object> map) throws Exception;

	void insertMsg(Map<String, Object> map) throws Exception;	

}
