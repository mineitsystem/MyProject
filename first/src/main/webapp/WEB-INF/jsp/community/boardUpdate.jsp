<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
 
</head>
<body >
    <form id="frm" enctype="multipart/form-data">
    	<div class="col-lg-12">	 	
		    <div class="panel-body">
		    	<div class="col-lg-12" >
		    		<div class="panel panel-default" >
	                    <div class="panel-heading">                       
	                    	<div class="form-group">
			                   <p class="fa fa-flag-o"><strong>&nbsp;No.${map.IDX}</strong></p>  
			                   <input type="hidden" id="IDX" name="IDX" value="${map.IDX }">                                                  
			                </div>	    		
				    		<div class="form-group">                    
			                    <input class="form-control" id="TITLE" name="TITLE" type="text" value="${map.TITLE }">                                                      
			                </div>
			                <div class="form-group">                    
			                 	<p class="fa fa-user"> ${map.CREA_ID }</p> | <p class="fa fa-eye"> ${map.HIT_CNT }</p> | <p class="fa fa-clock-o"> <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${map.CREA_DTM }"/></p>
			                </div>                     
	                    </div>
	                    <div class="panel-body">
	                        <div class="form-group">   
	                        	<textarea rows="20" cols="100" class="form-control"  id="CONTENTS" name="CONTENTS" >${map.CONTENTS }</textarea>                                                     
	                        </div>
	                    </div>
		                    <div class="panel-footer">	                    	
	                    		 <div id="fileDiv" class="form-group">               
		                            <c:forEach var="row" items="${list }" varStatus="var">
		                                <p class="fa fa-files-o">
		                                    <input type="hidden" id="IDX" name="IDX_${var.index }" value="${row.IDX }"><a href="#this" id="name_${var.index }" name="name_${var.index }">${row.ORIGINAL_FILE_NAME }</a>	                                    
		                                    <input type="file" id="file_${var.index }" name="file_${var.index }" style="margin-top:5px;">
		                                    (${row.FILE_SIZE }kb)
		                                    <input type="button" class="btn btn-default" value="삭제"  id="delete_${var.index }" name="delete_${var.index }"/>		                                    
		                                </p>
		                            </c:forEach>
	                       		 </div>                        	                                    	                        
		                        <div class="form-group">
		                        	  <button class="btn btn-default" id="list">목록으로</button>  
		                        	  <button class="btn btn-default" id="addFile">파일 추가</button>								  
									  <button class="btn btn-default" id="update">저장하기</button>
									  <button class="btn btn-default" id="delete">삭제하기</button> 
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
			// 사용자 ID 필수체크
			if($("input[name='TITLE']").val().isBlank()) {	
				modalAlert("알림","제목을 입력해주세요");			
				$("input[name='TITLE']").focus(); 
	            return false;
	        }
			// 사용자 PASSWORD 필수체크
			if($("textarea[name='CONTENTS']").val().isBlank()) {
				modalAlert("알림","내용을 입력해주세요");			
				$("textarea[name='CONTENTS']").focus();
	            return false;
	        }
	        return true;		
		}
    
    	var gfv_count = '${fn:length(list)+1}';
        $(document).ready(function(){
            $("#list").on("click", function(e){ //목록으로 버튼
                e.preventDefault();
                fn_openBoardList();
            });
             
            $("#update").on("click", function(e){ //저장하기 버튼
                e.preventDefault();
                fn_updateBoard();
            });
             
            $("#delete").on("click", function(e){ //삭제하기 버튼
                e.preventDefault();
                fn_deleteBoard();
            });
            $("#addFile").on("click", function(e){ //파일 추가 버튼
                e.preventDefault();
                fn_addFile();
            });
            
            //name^= Starts With Selector
            //name$= Ends With Selector
            //name*= Contains Selector
            //name!= Not Equal Selector
            $("input[name^='delete']").on("click", function(e){ //삭제 버튼
                e.preventDefault();
                fn_deleteFile($(this));
            });
        });
         
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/community/openBoardList.do' />");
            comSubmit.submit();
        }
         
        function fn_updateBoard(){
        	
        	if(!validationChk())return;
        	
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='/community/updateBoard.do' />");
            comSubmit.submit();
        }
         
        function fn_deleteBoard(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/community/deleteBoard.do' />");
            comSubmit.addParam("IDX", $("#IDX").val());
            comSubmit.submit();
             
        }
        
        function fn_addFile(){
            var str = "<p>" +
                    "<input type='file' id='file_"+(gfv_count)+"' name='file_"+(gfv_count)+"'>"+
                    "<input type='button' class='btn btn-default' id='delete_"+(gfv_count)+"' name='delete_"+(gfv_count)+"' value='삭제'/>" +
                "</p>";
            $("#fileDiv").append(str);
            $("#delete_"+(gfv_count++)).on("click", function(e){ //삭제 버튼
                e.preventDefault();
                fn_deleteFile($(this));
            });
        }
         
        function fn_deleteFile(obj){
            obj.parent().remove();
        }
    </script>
</body>
</html>