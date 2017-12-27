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
	public List<Map<String, Object>> getLoginInfo(List<Map<String, Object>> map) throws Exception {
		return lDao.getLoginInfo(map);
	}

}
