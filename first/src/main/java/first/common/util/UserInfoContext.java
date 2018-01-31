package first.common.util;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;


public class UserInfoContext {
    private static String getLoginInfo(String key) {
        HttpSession session = WebContext.getRequest().getSession(false);
        if (session == null) {
            return "";
        }

        @SuppressWarnings("unchecked")
        Hashtable<String, String> sessionInfo = (Hashtable<String, String>) session.getAttribute(FinalValues.COMM_USER_KEY);
        if (sessionInfo == null) {
            return "";
        }

        return sessionInfo.get(key);
    }

    public static String getUserId() {
        return getLoginInfo("USER_ID");
    }
    public static String getUserName() {
        return getLoginInfo("USER_NAME");
    } 
    public static String getAuth() {
        return getLoginInfo("AUTH");
    }

    public static String getEmail() {
        return getLoginInfo("EMAIL");
    }
}
