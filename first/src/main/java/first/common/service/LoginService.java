package first.common.service;

import java.util.List;
import java.util.Map;

public interface LoginService {
	
	public Map<String, Object> getLoginInfo(Map<String, Object> map) throws Exception;

	public Map<String, Object> getLoginPass(Map<String, Object> map) throws Exception;

	public void updatePassErr(Map<String, Object> map) throws Exception;
	
}
