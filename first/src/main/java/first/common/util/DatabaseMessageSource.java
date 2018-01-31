package first.common.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import first.common.dto.BaseMap;
import first.common.service.MessageResourceService;

public class DatabaseMessageSource extends ReloadableResourceBundleMessageSource {
    private final Map<String, Map<String, String>> properties = new HashMap<String, Map<String, String>>();

    private MessageResourceService messageResourceService;

    public DatabaseMessageSource() {
        init();
    }

    public DatabaseMessageSource(MessageResourceService messageResourceService) {
        this.messageResourceService = messageResourceService;
        init();
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getMsg(code, locale);
        MessageFormat result = createMessageFormat(msg, locale);
        return result;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return getMsg(code, locale);
    }

    private String getMsg(String code, Locale locale) {
        Map<String, String> msgMap = null;

        String msg = null;
        if (properties.containsKey(code)) {
            msgMap = properties.get(code);
            msg = msgMap.get(locale.getLanguage());

            if (msg == null) {
                msg = msgMap.get(Locale.KOREA.getLanguage());
            }
        }
        if (msg == null) {
            logger.error("메시지 코드가 존재하지 않습니다. [" + code + "]");
        }

        return msg != null ? msg : code;
    }

    private void init() {
        reload();
    }

    public void reload() {
        Map<String, Map<String, String>> msg = loadMsg();
        properties.clear();
        properties.putAll(msg);
    }

    protected Map<String, Map<String, String>> loadMsg() {
        Map<String, Map<String, String>> m = new HashMap<String, Map<String, String>>();
        try {
            List<BaseMap> msgList = messageResourceService.loadAllMessages(null);
            for (BaseMap map : msgList) {
                Map<String, String> v = new HashMap<String, String>();
                v.put("en", map.get("EN_MSG"));
                v.put("ko", map.get("KO_MSG"));
                m.put(map.get("MSG_ID"), v);
            }
        } catch(Exception e) {
            logger.error("메시지 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return m;
    }

}
