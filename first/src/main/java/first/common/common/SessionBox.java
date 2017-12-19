/**
 * 프로그램명: SessionBox.java
 * 내     용:
 * 이     력:
 *  ---------------------------------------------------------------
 *  Revision    Date            Author      Description
 *  ---------------------------------------------------------------
 *  ver1.0      2013. 07. 01.   아무개              최초 작성
 */
package first.common.common;

import java.util.Hashtable;

/**
 * 세션을 담는 객체.
 * @author tilldawn
 *
 */
public class SessionBox extends BaseMap {
    private static final long serialVersionUID = 1L;
    private javax.servlet.http.HttpSession session = null;
    
   /*사용 예제
    HttpSession session = request.getSession(true);
    
    //사용자 정보 세션처리
	SessionBox sBox = new SessionBox(session, Constants.MEBICA_COMM_USER_KEY);
	session.setAttribute(Constants.MEBICA_COMM_USER_KEY, new Hashtable<String, String>());				
	sBox.put("USER_ID", userInfo.get("USER_ID"));
	sBox.put("USER_NM", userInfo.get("USER_NM"));
	sBox.put("LOC_ID", userInfo.get("LOC_ID"));
	sBox.put("PASSWORD", userInfo.get("PASSWORD"));
	sBox.put("USER_TYPE", userInfo.get("USER_TYPE"));
	sBox.put("EMAIL", userInfo.get("EMAIL"));
	sBox.put("TEL", userInfo.get("TEL"));
	sBox.put("LANG_TP", userInfo.get("LANG_TP"));
	sBox.put("USER_IP", userInfo.get("USER_IP"));
	sBox.put("USE_YN", userInfo.get("USE_YN"));
	sBox.put("LAST_LOGIN_DT", userInfo.get("LAST_LOGIN_DT"));
	sBox.put("VIEW_CNT", userInfo.get("VIEW_CNT"));
	sBox.put("VIEW_LAYOUT", userInfo.get("VIEW_LAYOUT"));
	*/

    public SessionBox(javax.servlet.http.HttpSession session, String name) {
        super(name);
        this.session = session;
    }


    public void put(String key, String value) {
        super.put(key, value);

        @SuppressWarnings("unchecked")
        Hashtable<String, String> sessionInfo = (Hashtable<String, String>) session.getAttribute(name);
        if (sessionInfo != null) {
            sessionInfo.put(key, value);
        }
    }

    public String getName() {
        return name;
    }

    public void release() {
        try {
            session.removeAttribute(name);
        } catch (Exception e) {
            System.out.println("Session release error!!!");
        }
        clear();
    }
}
