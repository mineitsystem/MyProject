package first.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import first.common.dao.MenuDAO;

@Service("MenuService")
public class MenuServiceImpl extends BaseService implements MenuService {
	
	@Resource(name = "MenuDAO")
    private MenuDAO mDao;

	@Override
	public List<Map<String, Object>> listLeftMenu(Map<String, Object> map) throws Exception {
		
		return mDao.listLeftMenu(map);
	}

}
