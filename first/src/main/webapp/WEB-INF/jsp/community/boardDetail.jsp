<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
 
<html lang="ko">
<head>
</head>
<body >

	<div class="col-lg-12">	 	
	    <div class="panel-body">
	    	<div class="col-lg-12" >
	    		<div class="panel panel-default" >
                    <div class="panel-heading">                       
                    	<div class="form-group">
		                   <p class="fa fa-flag-o"><strong>&nbsp;No.${map.IDX}</strong></p>                                                    
		                </div>	    		
			    		<div class="form-group">                    
		                    <input class="form-control" type="text" value="${map.TITLE }" readonly="">                                                      
		                </div>
		                <div class="form-group">                    
		                 	<p class="fa fa-user"> ${map.CREA_ID }</p> | <p class="fa fa-eye"> ${map.HIT_CNT }</p> | <p class="fa fa-clock-o"> <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${map.CREA_DTM }"/></p>
		                </div>                     
                    </div>
                    <div class="panel-body">
                        <div class="form-group">   
                        	<textarea rows="20" cols="100" class="form-control"  id="CONTENTS" name="CONTENTS" readonly>${map.CONTENTS }</textarea>                                                     
                        </div>
                    </div>
                    <div class="panel-footer">
                    	<div class="form-group">   
                        	<c:forEach var="row" items="${list }">
			                    	<p class="fa fa-files-o">
			                        <input type="hidden" id="IDX" value="${row.IDX }">
			                        <a href="#this" name="file">${row.ORIGINAL_FILE_NAME }</a>
			                        (${row.FILE_SIZE }kb)
			                        </p>
			                </c:forEach>                                              
                        </div>
                        <div class="form-group">   
                        	  <button class="btn btn-default" class="btn" id="list">목록으로</button>
                        	    <sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                        	    	<sec:authentication var="user" property="principal" />
                        	    	<c:set var="authID" value="${user.username}"/>
	                        	    	<c:if test="${authID eq map.CREA_ID || authID eq 'admin' }">
	                        	    		<button class="btn btn-default" class="btn" id="update">수정하기</button>										    
										</c:if>							       							       
							    </sec:authorize>			    			  
                        </div>
                    </div>
                </div>
	    		           
		    </div>
	    </div>
    </div>
     
    <%@ include file="/WEB-INF/include/include-body.jspf" %>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#list").on("click", function(e){ //목록으로 버튼
                e.preventDefault();
                fn_openBoardList();
            });
             
            $("#update").on("click", function(e){
                e.preventDefault();
                fn_openBoardUpdate();
            });
            
            $("a[name='file']").on("click", function(e){ //파일 이름
                e.preventDefault();
                fn_downloadFile($(this));
            });
        });
         
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/community/openBoardList.do' />");
            comSubmit.submit();
        }
         
        function fn_openBoardUpdate(){
            var idx = "${map.IDX}";
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/community/openBoardUpdate.do' />");
            comSubmit.addParam("IDX", idx);
            comSubmit.submit();
        }
        
        function fn_downloadFile(obj){
            var idx = obj.parent().find("#IDX").val();            
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/common/downloadFile.do' />");
            comSubmit.addParam("IDX", idx);
            comSubmit.submit();
        }
        
    </script>
</body>
</html>