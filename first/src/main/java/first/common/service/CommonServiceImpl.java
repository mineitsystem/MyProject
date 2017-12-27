package first.common.service;
 
import java.util.Map;

import javax.annotation.Resource;
 
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import first.common.dao.CommonDAO;
 
@Service("CommonService")
public class CommonServiceImpl implements CommonService{
    Logger log = Logger.getLogger(this.getClass());
     
    @Resource(name="CommonDAO")
    private CommonDAO commonDAO;
    
    @Override
    public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
        return commonDAO.selectFileInfo(map);
    }
}