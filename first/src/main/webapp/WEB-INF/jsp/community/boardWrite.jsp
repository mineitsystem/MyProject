<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
 
<head>
</head>
<body >
    <form id="frm" enctype="multipart/form-data">
    	<div class="col-lg-12">	 	
			    <div class="panel-body">
			    	<div class="col-lg-12" >
			    		<div class="panel panel-default" >
		                    <div class="panel-heading">                       
		                    	<div class="form-group">
				                   <p class="fa fa-flag-o"><strong>&nbsp;No.Auto</strong></p>  			                                                                 
				                </div>	    		
					    		<div class="form-group">                    
				                    <input class="form-control" id="TITLE" name="TITLE" type="text" value="">                                                      
				                </div>
				                <div class="form-group">                    
				                 	<p class="fa fa-user"> Self</p> | <p class="fa fa-eye"> 0</p> | <p class="fa fa-clock-o"> Now</p>
				                </div>                     
		                    </div>
		                    <div class="panel-body">
		                        <div class="form-group">   
		                        	<textarea rows="20" cols="100" class="form-control"  id="CONTENTS" name="CONTENTS" ></textarea>                                                     
		                        </div>
		                    </div>
			                    <div class="panel-footer">	                    	
		                    		 <div id="fileDiv" class="form-group">               
			                            <input type="file" id="file" name="file_0"><input type="button" class="btn btn-default" value="삭제"  id="delete" name="delete"/>
		                       		 </div>                        	                                    	                        
			                        <div class="form-group">
			                        	  <button class="btn btn-default" id="list">목록으로</button>  
			                        	  <button class="btn btn-default" id="addFile">파일 추가</button>								  
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
    	var gfv_count = 1;
    	
        $(document).ready(function(){
        	 $("#list").on("click", function(e){//목록으로 버튼
                 e.preventDefault();
                 fn_openBoardList();
             });  
        	 
        	 $("#write").on("click", function(e){ //작성하기 버튼
        	        e.preventDefault();
        	        fn_insertBoard();
        	 });
        	 
        	 $("#addFile").on("click", function(e){ //파일 추가 버튼
                 e.preventDefault();
                 fn_addFile();
             });
              
             $("input[name='delete']").on("click", function(e){ //삭제 버튼
                 e.preventDefault();
                 fn_deleteFile($(this));
             });
        });
        
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/community/openBoardList.do' />");
            comSubmit.submit();
        }
        
        function fn_insertBoard(){
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='/community/insertBoard.do' />");
            comSubmit.submit();
        }
        
        function fn_addFile(){
            var str = "<p><input type='file' name='file_"+(gfv_count++)+"'><input type='button' class='btn btn-default' value='삭제'  id='delete' name='delete'/></p>";
            $("#fileDiv").append(str);
            $("input[name='delete']").on("click", function(e){ //삭제 버튼
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