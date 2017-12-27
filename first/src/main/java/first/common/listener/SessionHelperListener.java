/**
 * 프로그램명: SessionHelperListener.java
 * 내     용: 세션을 셋할때랑 세션이 끝날때를 catch하는 Listener class
 * 이     력:
 *  ---------------------------------------------------------------
 *  Revision    Date            Author      Description
 *  ---------------------------------------------------------------
 *  ver1.0      2013. 07. 01.   아무개              최초 작성
 */
package first.common.listener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import first.common.util.FinalValues;

public class SessionHelperListener implements HttpSessionBindingListener {
	
	private final Logger logger = Logger.getLogger(this.getClass());

    // 특정 ID 중복 허용을 위해 ID를 Key로 잡지 않음.
    private static Hashtable<HttpSession, String> loggedInUsers = new Hashtable<HttpSession, String>();

    // 중복 로그인으로 인해 강제 로그아웃 된 User
    private static Hashtable<HttpSession, String> dupLoggedInUsers = new Hashtable<HttpSession, String>();

    private static SessionHelperListener instance = null;

    public static synchronized SessionHelperListener getInstance() {
        if (instance == null) {
            instance = new SessionHelperListener();
        }

        return instance;
    }

    private boolean isAllowedDup(String id) {
        boolean rtnVal = !FinalValues.CHECK_DUP_USER;

        if (FinalValues.CHECK_DUP_USER) {
            String inputUser = id.toUpperCase();

            for (String user : FinalValues.DUPLICATION_USER_ID) {
                if (user.equals(inputUser)) {
                    rtnVal = true;
                    break;
                }
            }
        }

        return rtnVal;
    }

    private Map<HttpSession, String> getSessions(String userId) {
        Map<HttpSession, String> sessionMap = new HashMap<HttpSession, String>();

        for (Entry<HttpSession, String> entry : loggedInUsers.entrySet()) {
            if (userId.equals(entry.getValue())) {
                sessionMap.put(entry.getKey(), userId);
            }
        }

        return sessionMap;
    }

    private String getUpperStr(String str) {
        return StringUtils.isEmpty(str) ? "" : str.toUpperCase();
    }

    public void setSession(HttpSession session, String userId) {
        String upperUserId = getUpperStr(userId);
        try {
            session.setAttribute(upperUserId, this);
            session.setMaxInactiveInterval(FinalValues.SERVER_MAX_INACTIVE_INTERVAL);
        } catch (Exception e) {
        }
    }

    public void removeSession(String userId) {
        String upperUserId = getUpperStr(userId);
        if (!isAllowedDup(upperUserId)) {
            dupLoggedInUsers.putAll(getSessions(upperUserId));
        }
    }

    public void removeSession(HttpSession session) {
        try {
            if (session != null) {
                session.invalidate();
            }
        } catch (Exception e) {
        }
    }

    public boolean isLoggedIn(String userId) {
        String upperUserId = getUpperStr(userId);

        boolean isLoggedIn = false;
        int inCnt = 0;
        int outCnt = 0;

        if (!isAllowedDup(upperUserId)) {
            for (String inUser : getAllUsers(loggedInUsers)) {
                if (inUser.equals(upperUserId)) {
                    inCnt++;
                }
            }

            if (inCnt > 0) {
                for (String outUser : getAllUsers(dupLoggedInUsers)) {
                    if (outUser.equals(upperUserId)) {
                        outCnt++;
                    }
                }

                if (inCnt != outCnt) {
                    isLoggedIn = true;
                }
            }
        }

        return isLoggedIn;
    }

    public Collection<String> getAllUsers(final Hashtable<HttpSession, String> user) {
        return user.values();
    }

    public boolean isLogOutUser(HttpSession session) {
        return dupLoggedInUsers.containsKey(session);
    }

    public void removeLogOutSession(HttpSession session) {
        try {
            if (session != null) {
                session.invalidate();
            }
        } catch (Exception e) {
        }
    }

    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        if (session != null) {
            logger.debug("Session Bound : " + session.getId());
            loggedInUsers.put(session, event.getName());
        }
    }

    public void valueUnbound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        if (session != null) {
            logger.debug("Session UnBound : " + session.getId());
            loggedInUsers.remove(session);
            dupLoggedInUsers.remove(session);
        }
    }
}
