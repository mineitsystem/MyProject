package first.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import first.common.dto.UserDetailsVO;

@Repository("UserDAO")
public class UserDAO extends AbstractDAO {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Transactional
	public void insertUser(Map<String, Object> map) {		
		insert("login.insertUser", map);
	}


	@SuppressWarnings("unchecked")
	public Map<String, Object> selectUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Map<String, Object>) selectOne("login.selectUser",map);
	}
}
