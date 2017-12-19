package first.common.util;
/**
 * 프로그램명: WebContext.java
 * 내     용:
 * 이     력:
 *  ---------------------------------------------------------------
 *  Revision    Date            Author      Description
 *  ---------------------------------------------------------------
 *  ver1.0      2013. 07. 01.   아무개              최초 작성
 */

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ThreadLocal은 한 쓰레드에서 실행되는 코드가 동일한 객체를 사용할 수 있도록 해 주기 때문에<br/>
 * 쓰레드와 관련된 코드에서 파라미터를 사용하지 않고 객체를 전파하기 위한 용도로 주로 사용되며, 주요 용도는 다음과 같다.<br/>
 *
 * 1.사용자 인증정보 전파 - Spring Security에서는 ThreadLocal을 이용해서 사용자 인증 정보를 전파한다.<br/>
 * 2.트랜잭션 컨텍스트 전파 - 트랜잭션 매니저는 트랜잭션 컨텍스트를 전파하는 데 ThreadLocal을 사용한다.<br/>
 * 3.쓰레드에 안전해야 하는 데이터 보관<br/>
 * 이 외에도 쓰레드 기준으로 동작해야 하는 기능을 구현할 때 ThreadLocal을 유용하게 사용할 수 있다.<br/>
 *
 * ThreadLocal 사용시 주의 사항<br/>
 * 쓰레드 풀 환경에서 ThreadLocal을 사용하는 경우 ThreadLocal 변수에 보관된 데이터의 사용이 끝나면 반드시 해당 데이터를 삭제해 주어야 한다.<br/>
 * 그렇지 않을 경우 재사용되는 쓰레드가 올바르지 않은 데이터를 참조할 수 있다.<br/>
 *
 *
 * ThreadLocal의 사용방법은 너무 쉽다. 단지 다음의 네 가지만 해 주면 된다.<br/>
 * 1.ThreadLocal 객체를 생성한다.<br/>
 * 2.ThreadLocal.set() 메서드를 이용해서 현재 쓰레드의 로컬 변수에 값을 저장한다.<br/>
 * 3.ThreadLocal.get() 메서드를 이용해서 현재 쓰레드의 로컬 변수 값을 읽어온다.<br/>
 * 4.ThreadLocal.remove() 메서드를 이용해서 현재 쓰레드의 로컬 변수 값을 삭제한다.<br/>
 *
 * -출처 http://javacan.tistory.com/entry/ThreadLocalUsage<br/>
 *      http://happyourlife.com/138<br/>
 *
 *
 * egovframework.mebica.pub.fw.interceptor.AuthenticInterceptor<br/>
 * /PRJ_MEBICA/src/main/resources/egovframework/spring/dispatcher-servlet.xml - mvc:interceptors<br/>
 * 1. interceptor를 통해서 preHandle 에서 셋팅함.<br/>
 * 2. interceptor를 통해서 postHandle 에서 삭제함.<br/>
 * 3. 처리도중 에러가나면 postHandle 를 타지 않기때문에 ExceptionResolver를 통해서 에러가났을경우 삭제한다.<br/>
 *
 * @author tilldawn
 *
 */
public class WebContext {
    private static ThreadLocal<ServletContext>      context  = new ThreadLocal<ServletContext>();
    private static ThreadLocal<HttpServletRequest>  request  = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();

    public static void set(HttpServletRequest req, HttpServletResponse res) {
        request.set(req);
        response.set(res);
        context.set(req.getSession().getServletContext());
    }

    public static void remove() {
        request.remove();
        response.remove();
        context.remove();
    }

    public static HttpServletRequest getRequest() {
        return request.get();
    }

    public static HttpServletResponse getResponse() {
        return response.get();
    }

    public static ServletContext getContext() {
        return context.get();
    }
}
