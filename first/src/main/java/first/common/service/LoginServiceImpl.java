package first.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import first.common.dao.LoginDAO;

@Service("LoginService")
public class LoginServiceImpl extends BaseService implements LoginService {
	
	@Resource(name = "LoginDAO")
    private LoginDAO lDao;

	@Override
	public Map<String, Object> getLoginInfo(Map<String, Object> map) throws Exception {
		return lDao.getLoginInfo(map);
	}

	@Override
	public Map<String, Object> getLoginPass(Map<String, Object> map) {
		return lDao.getLoginPass(map);
	}

	@Override
	public void updatePassErr(Map<String, Object> map) throws Exception {
		lDao.updatePassErr(map);		
	}

}
