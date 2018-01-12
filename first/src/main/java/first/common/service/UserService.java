package first.common.service;

import java.util.Map;

public interface UserService {
	public void insertUser(Map<String, Object> map) throws Exception;	
	
	public Map<String, Object> selecttUser(Map<String, Object> map) throws Exception;	
} 
