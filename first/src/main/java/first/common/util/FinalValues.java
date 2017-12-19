package first.common.util;


public class FinalValues {
	 /*
     * 중복 로그인을 체크할지 여부
     * true : DUPLICATION_USER_ID 에 선언된 ID를 제외하고는 중복 허용 안함.
     * false : 중복 로그인 허용
     */
    public static final boolean CHECK_DUP_USER = true;
    // 중복 허용하는 ID - 대문자로 ex) ADMIN1, ADMIN2
    public static final String[] DUPLICATION_USER_ID = {};
    
    // Server 최대 Session 유지 시간 - 초 단위
    public static final int SERVER_MAX_INACTIVE_INTERVAL = 30 * 60;
    
    public final static String COMM_USER_KEY     = "USER_INFO";
    public final static String COMM_USER_AUTH    = "USER_AUTH";
	
    
}
