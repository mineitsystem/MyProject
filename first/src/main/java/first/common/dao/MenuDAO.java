package first.common.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("MenuDAO")
public class MenuDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listLeftMenu(Map<String, Object> map) {
		
		return (List<Map<String, Object>>)selectList("menu.selectMenuList", map);
	}

}
