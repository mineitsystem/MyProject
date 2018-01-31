package first.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import first.common.dao.CommonDAO;
import first.common.dto.BaseMap;

@Component
public class MessageResourceService  {

    @Resource(name = "CommonDAO")
    private CommonDAO cmDao;

    public List<BaseMap> loadAllMessages(BaseMap rMap) throws Exception {
        List<BaseMap> list = cmDao.srchMsgList(rMap);
        return list;
    }
}
