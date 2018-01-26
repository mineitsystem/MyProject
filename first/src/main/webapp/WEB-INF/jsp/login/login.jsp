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
                        <form role="form" name="frm" id="frm" method="post">
                            <fieldset id="login_success">
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
                                <input type="button" class="btn btn-lg btn-success btn-block" id="log_in" value="Login"/><br/>
                                <div style="width:100%;text-align:right;">
                                	<input type="button" class="btn btn-difault btn-xs" id="sign_in" value="Sign In" />
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </fieldset>
                            <fieldset id="login_fail" style="display:none">
                                <div class="form-group">
                                    	로그인 실패
                                </div>                                                             
                                <input type="button" class="btn btn-lg btn-success btn-block" id="reLogin" value="reLogin"/><br/>    
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>        
    </div>
    
    <div class="modal fade" id="modal_signin_content" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
         <div class="modal-dialog">
             <div class="modal-content">
                 <div class="modal-header" id="modal_header">
                     <button type="button" class="close" name="modal_close" aria-hidden="true">×</button>
                     <h4 class="modal-title" id="modal_signin_title">Sign In</h4>
                 </div>
                 <div class="modal-body" id="modal_signin_body">
                       <div class="row">
						    <div>
						      <form class="form-horizontal" role="form" id="signin_form">
						        									
						          <!-- Text input-->
						          <div class="form-group">
						            <label class="col-sm-2 control-label" for="textinput" >ID</label>
						            <div class="col-sm-10">
						              <input type="text" placeholder="ID" class="form-control" name="USER_ID" id="USER_ID">
						              <label class="col-sm-10" name="input_state"></label>
						            </div>
						          </div>
						          
						          <div class="form-group">
						            <label class="col-sm-2 control-label" for="textinput" >Name</label>
						            <div class="col-sm-10">
						              <input type="text" placeholder="Name" class="form-control" name="USER_NAME" id="USER_NAME">
						              <label class="col-sm-10" name="input_state"></label>
						            </div>
						          </div>
						
						          <!-- Text input-->
						          <div class="form-group" >
						            <label class="col-sm-2 control-label" for="textinput" >Password</label>
						            <div class="col-sm-10">
						              <input type="password" placeholder="Password" class="form-control" name="PASS" id="PASS" >
						              <label class="col-sm-10" name="input_state"></label>
						            </div>
						          </div>
						          
						          <div class="form-group" >
						            <label class="col-sm-2 control-label" for="textinput" >PW Check</label>
						            <div class="col-sm-10">
						              <input type="password" placeholder="Password Check" class="form-control" name="PASS2" id="PASS2" >
						              <label class="col-sm-10" name="input_state"></label>
						            </div>
						          </div>
						
						          <!-- Text input-->
						          <div class="form-group" >
						            <label class="col-sm-2 control-label" for="textinput" >E-Mail</label>
						            <div class="col-sm-10">
						              <input type="text" placeholder="E-Mail" class="form-control" name="EMAIL" id="EMAIL" >
						              <label class="col-sm-10" name="input_state"></label>
						            </div>
						          </div>
					          								
						          <!-- Text input-->
						          <div class="form-group">
						            <label class="col-sm-2 control-label" for="textinput">Authority</label>
						            <div class="col-sm-10">
						              <select class="form-control" id="AUTHORITY" name="AUTHORITY" style="background-color: #eee;" onFocus='this.initialSelect = this.selectedIndex;' onChange='this.selectedIndex = this.initialSelect;'>
						              		<option value="ROLE_USER">ROLE_USER</option>
						              		<option value="ROLE_ADMIN">ROLE_ADMIN</option>
						              </select>
						            </div>
						          </div>
												       
						      </form>
						    </div><!-- /.col-lg-12 -->
						</div><!-- /.row -->
                 </div>
                 <div class="modal-footer">
                 	 <button type="button" class="btn btn-primary" id="modal_signin_confirm">Save</button>
                     <button type="button" class="btn btn-default" name="modal_close">Cancel</button>                     
                 </div>
             </div>
             <!-- /.modal-content -->
         </div>
         <!-- /.modal-dialog -->
     </div>
   
    
    
	<script type="text/javascript">
	//함수 선언부
	
	var fn_selectLoginCallBack = function (data){
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
	
	var goLogin = function(e){
		if(e){
			if (e.keyCode != 13) {		
		        return false;
			}
		}
				
		if(!validationChk())return;
		 
		//일반 로그인 처리 
		//var comAjax = new ComAjax("frm");
        //comAjax.setUrl("<c:url value='/login/goLoginCheck.do' />");
        //comAjax.setCallback("fn_selectLoginCallBack");
        //comAjax.ajax(); 	
        //var pass = $("input[name='password']").val();
        //$.when(SHA256($("input[name='password']").val())).done(function(result) {      			
        //	$("input[name='password']").val(result);
        //});
        
        //security 로그인 처리
        $("#frm").attr("action", "<c:url value='/j_spring_security_check' />");        
        $("#frm").submit();
        
			
	}	
	
	var goSignin = function(){
		
		if(!validationChk_SignIn())return;		
		
		var comSubmit = new ComSubmit("signin_form");
        comSubmit.setUrl("<c:url value='/login/insertUser.do' />");
        comSubmit.submit();
		
	}

	
	var validationChk = function(){
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
	
	var validationChk_SignIn = function(){
		
		var passRule = /^[A-Za-z0-9]{6,12}$/;//숫자와 문자 포함 형태의 6~12자리 이내의 암호 정규식
		
		var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;//이메일 정규식
		
		$("label[name='input_state']").html("");
				
		if($("input[id='USER_ID']").val().isBlank()) {			
            return validationChk_SignIn_Set("USER_ID","아이디를");
        }
		
		if($("input[id='USER_NAME']").val().isBlank()) {
			return validationChk_SignIn_Set("USER_NAME","이름을");
        }
		
		if($("input[id='PASS']").val().isBlank()) {
			return validationChk_SignIn_Set("PASS","비밀번호를");
        }
		
		if($("input[id='PASS2']").val().isBlank()) {
			return validationChk_SignIn_Set("PASS2","비밀번호 확인을");
        }
		
		if($("input[id='PASS2']").val() != $("input[id='PASS']").val()) {
			return validationChk_SignIn_Set("PASS2","비밀번호를 동일하게");
        }
		
		if($("input[id='EMAIL']").val().isBlank()) {
			return validationChk_SignIn_Set("EMAIL","이메일을");
        }
		
		if(!passRule.test($("input[id='PASS']").val())) {
			$("input[id='PASS']").val("");
			$("input[id='PASS2']").val("");
			return validationChk_SignIn_Set("PASS","숫자와 문자 포함 형태의 6~12자리 암호를");
        }
		
		if(!emailRule.test($("input[id='EMAIL']").val())) {			
			$("input[id='EMAIL']").val("");
			return validationChk_SignIn_Set("EMAIL","이메일 형식을 정확히");
        }
		
		return true;
	}
	
	var validationChk_SignIn_Set = function(id,msg){		
		$("input[id='"+id+"']+label").html(msg+" 입력해주세요.");		
		$("input[id='"+id+"']+label").css("color","red");		
		$("input[id='"+id+"']").focus(); 
        return false;
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
		var login_type = "${result}";
		if(login_type){
			$("#login_fail").css("display","");
			$("#login_success").css("display","none");
		}
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
	    
	    $("#log_in").click(function(){	    
	    	goLogin();
	    });
	    
	    $("#sign_in").click(function(){	  
	    	$("label[name='input_state']").css("margin-top","5px");
	    	$("#signin_form label").css("margin-left","-5px");
	    	$("#signin_form label").css("font-size","12px");
	    	$("#signin_form input").css("margin-left","-12px");
	    	$("#signin_form select").css("margin-left","-12px");
	    		    	
	    	$("#modal_signin_content").modal("show");
	    });
	    
	    $("#modal_signin_confirm").click(function(e){	  
	    	e.preventDefault();
	    	goSignin();
	    });
	    
	    $("button[name='modal_close']").click(function(e){
	    	$("#signin_form")[0].reset();
	    	$("#modal_signin_content").modal("toggle");
	    });
	    
	    $('#modal_signin_content').on('hidden.bs.modal', function () {
	    	$("#signin_form")[0].reset();
	    });
	    
	    $("#reLogin").click(function(){	    	
	    	location.href="${contextPath}/login/login";
	    });	    	    	  
	    
	});
		
	</script>	
</body>