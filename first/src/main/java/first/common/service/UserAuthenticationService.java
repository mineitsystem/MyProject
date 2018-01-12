package first.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import first.common.dto.UserDetailsVO;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthenticationService implements UserDetailsService {
	
	Logger log = Logger.getLogger(this.getClass());	
	
	@Resource(name="UserService")
	private UserService userService;
	
	
	public UserAuthenticationService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Map<String, Object> user = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		try {
			map.put("username", username);
			user = userService.selecttUser(map);			
			if(user == null ) throw new UsernameNotFoundException(username);
			log.info(user.toString());
			
			gas.add(new SimpleGrantedAuthority(user.get("authority").toString()));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new UserDetailsVO(user.get("username").toString().trim(), user.get("password").toString().trim(), (Integer)user.get("enabled") == 1, true, true, true, gas,user.get("email").toString());
	}

}
