<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
 
</head>
<body>		
    <div class="col-lg-12">	 	
	    <div class="panel-body">
		    <div class="row">
		    	<div class="col-lg-12">	
					<table class="table table-bordered table-hover table-striped">
						<colgroup>							
							<col width="*"/>
							<col width="30%"/>
							<col width="30%"/>
							<col width="15%"/>
							<col width="20%"/>
						</colgroup>
						<thead>
							<tr>
								<th>메세지명</th>
								<th>한국어</th>
								<th>영어</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${fn:length(list) > 0}">
									<c:forEach items="${list }" var="row">
										<tr class="title" onclick="window.location='#this';">											
											<td class='title'>
													${row.MSG_ID }
					                                <input type="hidden" id="IDX" value="${row.MSG_ID }">
											</td>
											<td>${row.KO_MSG }</td>
											<td>${row.EN_MSG }</td>
											<td>${row.CREA_ID }</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd" value="${row.CREA_DTM }"/></td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="4">조회된 결과가 없습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>
							
						</tbody>
					</table>		
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="col-lg-12">
							<div class="dataTables_paginate paging_simple_numbers" >
								<ul class="pagination">			
									<%-- Egov FrameWork Paging --%> 
									<c:if test="${not empty paginationInfo}">										
								        <ui:pagination paginationInfo = "${paginationInfo}" type="text" jsFunction="fn_search"/>
								    </c:if>     	
							    </ul>
						    </div>
					    </div>
						<div class="col-sm-6">
							<div>
								<sec:authorize ifAnyGranted="ROLE_ADMIN">
								<a href="#this" class="btn btn-outline btn-default" id="write">등록</a>
								<input type="hidden" id="currentPageNo" name="currentPageNo"/> 
								</sec:authorize>   		
							</div>
						</div>
					
					</div>
			    </div>      			
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/include/include-body.jspf" %>
    <script type="text/javascript">
        $(document).ready(function(){
        	
            $("#write").on("click", function(e){ //글쓰기 버튼
                e.preventDefault();
                fn_openMsgWrite();
            });
                     
        });
         
         
        function fn_openMsgWrite(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/admin/messageWrite.do' />");
            comSubmit.submit();
        }
      
        function fn_search(pageNo){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/admin/message.do' />");
            comSubmit.addParam("currentPageNo", pageNo);
            comSubmit.submit();
        }
        
       
    </script>
</body>
</html>