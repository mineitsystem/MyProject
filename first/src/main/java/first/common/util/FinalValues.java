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
    
    
    /*
     *  1 : 로그인 성공
		2 : 로그인 실패(아이디 다름)		
		3 : 로그인 실패(비밀번호 다름)		
		4 : 로그인 실패(오류실패 횟수 초과)		
		5 : 로그인 실패(기타 에러 쿼리 등등)
     * */
    
    public final static String LOGIN_TYPE     = "L_TYPE";
    public final static String LOGIN_MSG      = "L_MSG";
    public final static String LOGIN_MSG_2    = "아이디가 다릅니다";
    public final static String LOGIN_MSG_3    = "비밀번호가 다릅니다";
    public final static String LOGIN_MSG_4    = "비밀번호 오류 횟수가 초과하였습니다\n인증이 필요합니다";
    	/*
    	 * false : 횟수증가 
    	 * true: 초기화 
    	 **/
    	public final static String LOGIN_ERRCNT_TYPE = "ERRTYPE";
    public final static String LOGIN_MSG_5    = "기타 에러";
    
	
    
}
