package first.common.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("LoginDAO")
public class LoginDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public Map<String, Object> getLoginInfo(Map<String, Object> map) {
		
		return (Map<String, Object>) selectOne("login.selectLoginId", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getLoginPass(Map<String, Object> map) {
		
		return (Map<String, Object>) selectOne("login.selectLoginPass", map);
	}

	public void updatePassErr(Map<String, Object> map) {
		update("login.updatePassErr", map);
	}

}
