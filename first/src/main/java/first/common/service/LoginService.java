package first.common.service;

import java.util.List;
import java.util.Map;

public interface LoginService {
	
	public List<Map<String, Object>> getLoginInfo(List<Map<String, Object>> map) throws Exception;
	
}
