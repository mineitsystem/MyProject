package first.community.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;

@Repository("CommunityDAO")
public class CommunityDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectPagingEgovList("community.selectBoardList", map);
	}	

	public void insertBoard(Map<String, Object> map) throws Exception {
		insert("community.insertBoard", map);
	}
	
	public void updateHitCnt(Map<String, Object> map) throws Exception{
	    update("community.updateHitCnt", map);
	}
	 
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{
	    return (Map<String, Object>) selectOne("community.selectBoardDetail", map);
	}

	public void updateBoard(Map<String, Object> map) throws Exception {
		 update("community.updateBoard", map);
		
	}

	public void deleteBoard(Map<String, Object> map) throws Exception {
		 update("community.deleteBoard", map);
	}

	public void insertFile(Map<String, Object> map) throws Exception {
		insert("community.insertFile", map);
		
	}
	
	public void deleteFileList(Map<String, Object> map) throws Exception{
	    update("community.deleteFileList", map);
	}
	 
	public void updateFile(Map<String, Object> map) throws Exception{
	    update("community.updateFile", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("community.selectFileList", map);
	}

}
