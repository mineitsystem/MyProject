package first.common.dao;
 
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dto.BaseMap;
 
@Repository("CommonDAO")
public class CommonDAO extends AbstractDAO{
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception{
	    return (Map<String, Object>)selectOne("common.selectFileInfo", map);
	}

	@SuppressWarnings("unchecked")
	public List<BaseMap> srchMsgList(BaseMap rMap) throws Exception  {
		// TODO Auto-generated method stub
		return selectList("admin.selectMsg", rMap);
	}

}
