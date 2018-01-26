package first.message.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import first.common.dao.AbstractDAO;

@Repository("MessageDAO")
public class MessageDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMsgList(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectPagingEgovList("admin.selectMsgList", map);
	}
	
	@Transactional
	public void insertMsgList(Map<String, Object> map) {
		insert("admin.insertMsgKO", map);
		insert("admin.insertMsgEN", map);		
	}	

}
