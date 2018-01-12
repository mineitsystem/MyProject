package first.common.service;


import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;
import first.common.dao.UserDAO;

@Service("UserService")
public class UserServiceImpl extends BaseService implements UserService {

	@Resource(name = "UserDAO")
	private UserDAO uDao;
		
	@Override	
	public void insertUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		uDao.insertUser(map);
	}

	@Override
	public Map<String, Object> selecttUser(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return uDao.selectUser(map);
	}

} 
