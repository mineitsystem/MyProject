/**
 * 프로그램명: CollectionMap.java
 * 내      용: BaseMap의 get 메소드는 항상 String형을 return
 *            MyBatis의 froeach문을 사용하기 위해 추가한 class
 *            (put한 value 타입그대로 취득)
 * 이      력:
 *  ---------------------------------------------------------------
 *  Revision    Date            Author      Description
 *  ---------------------------------------------------------------
 *  ver1.0      2016. 02. 03.   yeo         최초 작성
 */
package first.common.dto;

import java.util.HashMap;

/**
 * 
 * @author yeo
 *
 */
public class CollectionMap extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
}
