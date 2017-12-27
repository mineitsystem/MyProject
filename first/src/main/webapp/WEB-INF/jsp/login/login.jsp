<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body>
	 <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" name="frm" id="frm">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="ID" name="id" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" onkeypress="goLogin(event)" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me" id="idSaveCheck">Remember ID
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="button" class="btn btn-lg btn-success btn-block" onclick="goLogin()" value="Login"/><br/>
                                <div style="width:100%;text-align:right;">
                                	<input type="button" class="btn btn-difault btn-xs" value="Sign In" />
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>        
    </div>
	<script type="text/javascript">
	//함수 선언부
	
	var goLogin = function(e){
		if(e){
			if (e.keyCode != 13) {		
		        return false;
			}
		}
				
		if(!validationChk())return;
		 
		var comAjax = new ComAjax("frm");
        comAjax.setUrl("<c:url value='/login/goLoginCheck.do' />");
        comAjax.setCallback("fn_selectLoginCallBack");
        comAjax.ajax();
			
	}
	
	function fn_selectLoginCallBack(data){
        var type = data.L_TYPE;
        var msg = data.L_MSG;
        var f_id = $("input[name='id']");
        var f_password = $("input[name='password']");
        
        if(type === 1){
        	location.href = "${pageContext.request.contextPath}/community/openBoardList.do";       
        }else{        	
        	switch(type){
        		case 2://아이디 다름 
        			f_id.val("");
        			f_password.val("");
        			f_id.focus();        			
        			break;
        		case 3://비밀번호 다름 
        			f_password.val("");
        			f_password.focus();
            		break;
        		case 4: //비밀번호 오류횟수가 초과
        			f_id.val("");
        			f_password.val("");
        			f_id.focus();
            		break; 
        		case 4: //기타에러
        			f_id.val("");
        			f_password.val("");
        			f_id.focus();
            		break; 
        	}
        	modalAlert("알림",msg);	
        }        
       
    }
	
	validationChk = function(){
		// 사용자 ID 필수체크
		if($("input[name='id']").val().isBlank()) {	
			modalAlert("알림","아이디를 입력해주세요");			
			$("input[name='id']").focus(); 
            return false;
        }
		// 사용자 PASSWORD 필수체크
		if($("input[name='password']").val().isBlank()) {
			modalAlert("알림","비밀번호를 입력해주세요");			
			$("input[name='password']").focus();
            return false;
        }
        return true;		
	}
	
	setCookie = function(cookieName, value, exdays){
	    var exdate = new Date();
	    exdate.setDate(exdate.getDate() + exdays);
	    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	    document.cookie = cookieName + "=" + cookieValue;
		}
	 
	deleteCookie = function(cookieName){
	    var expireDate = new Date();
	    expireDate.setDate(expireDate.getDate() - 1);
	    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
	 
	getCookie = function(cookieName){
	    cookieName = cookieName + '=';
	    var cookieData = document.cookie;
	    var start = cookieData.indexOf(cookieName);
	    var cookieValue = '';
	    if(start != -1){
	        start += cookieName.length;
	        var end = cookieData.indexOf(';', start);
	        if(end == -1)end = cookieData.length;
	        cookieValue = cookieData.substring(start, end);
	    }
	    return unescape(cookieValue);
	}
	
	
	//최종실행
	$(document).ready(function(){
		 // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
	    var userInputId = getCookie("userInputId");
	    $("input[name='id']").val(userInputId); 
	     
	    if($("input[name='id']").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	        $("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	    }
	     
	    $("#idSaveCheck").change(function(){ // 체크박스에 변화가 있다면,
	        if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
	            var userInputId = $("input[name='id']").val();
	            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
	        }else{ // ID 저장하기 체크 해제 시,
	            deleteCookie("userInputId");
	        }
	    });
	     
	    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	    $("input[name='id']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
	        if($("#idSaveCheck").is(":checked")){ // ID 저장하기를 체크한 상태라면,
	            var userInputId = $("input[name='id']").val();
	            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
	        }
	    });	    	  
	});
		
	</script>
	
</body>