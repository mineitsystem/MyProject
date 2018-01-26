<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<head>
</head>
<body >
    <form id="frm" enctype="multipart/form-data">
    	<input type="hidden" id="USER_ID" name="USER_ID" value="<sec:authentication property="principal.username" />"/>
    	<div class="col-lg-12">	 	
			    <div class="panel-body">
			    	<div class="col-lg-12" >
			    		<div class="panel panel-default" >
		                    <div class="panel-heading">
		                    	<div class="form-group">                    
				                 	<p class="fa fa-user">&nbsp;<sec:authentication property="principal.username" /></p>				                 	
				                </div>                         
		                    	<div class="form-group">
		                    	   <label>MSG Type</label>
				                   <select class="form-control" name="MSG_ID1">
				                   		<option value="MSG">MSG</option>
				                   		<option value="UI">UI</option>
				                   </select>  			                                                                 
				                </div>	    		
					    		<div class="form-group">                    
					    			<label>MSG ID</label>
					    			<div>
					    			<input class="form-control col-xs-2" id="MSG_EX" name="MSG_EX" type="text" value="MSG." readonly="readonly" style="width:20%"/>
					    			<input class="form-control col-xs-8" id="MSG_ID2" name="MSG_ID2" type="text" value="" style="width:80%"/>
					    			</div>  					    							                   				                                                                     
				                </div>				               
				                 <div class="form-group">   
		                        	<label>MSG Name(KO)</label>
				                    <input class="form-control" id="MSG_NMKO" name="MSG_NMKO" type="text" value="">                                                     
		                        </div>   
		                         <div class="form-group">   
		                        	<label>MSG Name(EN)</label>
				                    <input class="form-control" id="MSG_NMEN" name="MSG_NMEN" type="text" value="">                                                     
		                        </div> 		                        
		                        <div class="form-group">
			                        	  <button class="btn btn-default" id="list">목록으로</button>  			                        	  					 
										  <button class="btn btn-default" id="write">작성하기</button>									  
			                    </div>                 
		                    </div>			                    
		                </div>		    		          
					</div>				    
			 	</div>            
			</div>
    </form>
     
    <%@ include file="/WEB-INF/include/include-body.jspf" %>
    <script type="text/javascript">
    
	    validationChk = function(){
    		//영어 알파벳과 숫자, 밑줄, 하이픈.	    	
	    	var msgIDRule =  /^[_A-Za-z0-9+]*$/

			// MSG_ID 필수체크
			if($("input[name='MSG_ID2']").val().isBlank()) {	
				modalAlert("알림","메시지 ID를 입력해주세요");			
				$("input[name='MSG_ID2']").focus(); 
	            return false;
	        }
    		
	    	if(!msgIDRule.test($("input[name='MSG_ID2']").val())) {
				modalAlert("알림","메시지 ID는 알파벳과 숫자, 밑줄, 하이픈만 가능합니다.");	
				return false;
	        }
    		
			// 사용자 MSG_NMKO 필수체크
			if($("input[name='MSG_NMKO']").val().isBlank()) {
				modalAlert("알림","메시지(한글)을 입력해주세요");			
				$("input[name='MSG_NMKO']").focus();
	            return false;
	        }
			// 사용자 MSG_NMEN 필수체크
			if($("input[name='MSG_NMEN']").val().isBlank()) {
				modalAlert("알림","메시지(영문)을 입력해주세요");			
				$("input[name='MSG_NMEN']").focus();
	            return false;
	        }
						
	        return true;		
		}
    	var gfv_count = 1;
    	
        $(document).ready(function(){
        	 $("#list").on("click", function(e){//목록으로 버튼
                 e.preventDefault();
                 fn_openBoardList();
             });  
        	 
        	 $("#write").on("click", function(e){ //작성하기 버튼
        	        e.preventDefault();
        	        fn_insertMsg();
        	 });
        	         	
             $("input[name='delete']").on("click", function(e){ //삭제 버튼
                 e.preventDefault();
                 fn_deleteFile($(this));
             });
             
             $("select[name='MSG_ID1']").on("change",function(e){
            	 e.preventDefault();
            	 $("input[name='MSG_EX']").val( $("select[name='MSG_ID1']").val()+".");
            	 
             });
        });
        
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/admin/message.do' />");
            comSubmit.submit();
        }
        
        function fn_insertMsg(){
        	
        	if(!validationChk())return;
        	
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='/admin/insertMsg.do' />");
            comSubmit.submit();
        }   
               
    </script>
</body>
</html>