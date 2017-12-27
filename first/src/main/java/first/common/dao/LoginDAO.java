package first.common.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("LoginDAO")
public class LoginDAO extends AbstractDAO{

	public List<Map<String, Object>> getLoginInfo(List<Map<String, Object>> map) {
		
		return (List<Map<String, Object>>)selectList("login.selectLoginList", map);
	}

}
