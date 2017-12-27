package first.community.service;

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

@Service("CommunityService")
public class CommunityServiceImpl extends BaseService implements CommunityService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;

	
	@Resource(name="CommunityDAO")
	private CommunityDAO communityDAO;
	
	@Override
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception {
		return communityDAO.selectBoardList(map);
		
	}
	
	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		communityDAO.insertBoard(map);		
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
        for(int i=0, size=list.size(); i<size; i++){
        	communityDAO.insertFile(list.get(i));
        }

	}

	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		communityDAO.updateHitCnt(map);
		Map<String, Object> resultMap = new HashMap<String,Object>();
	    Map<String, Object> tempMap = communityDAO.selectBoardDetail(map);
	    resultMap.put("map", tempMap);
	    List<Map<String,Object>> list = communityDAO.selectFileList(map);
	    resultMap.put("list", list);
	    return resultMap;
	}

	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		communityDAO.updateBoard(map);		
		
		communityDAO.deleteFileList(map);
	    List<Map<String,Object>> list = fileUtils.parseUpdateFileInfo(map, request);
	    Map<String,Object> tempMap = null;
	    for(int i=0, size=list.size(); i<size; i++){
	        tempMap = list.get(i);
	        if(tempMap.get("IS_NEW").equals("Y")){
	        	communityDAO.insertFile(tempMap);
	        }
	        else{
	        	communityDAO.updateFile(tempMap);
	        }
	    }
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		communityDAO.deleteBoard(map);
	}

}
