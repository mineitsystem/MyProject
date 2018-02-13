<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
</head>
<body>		
    <div class="col-lg-12">	 	
	    <div class="panel-body">
		    <div class="row">		    	
					<div class="col-lg-12">											   
				       <strong>Reload State</strong>
                       <span class="pull-right text-muted" id="reloadMessageState"><!-- 40% Complete --></span>                            
                       <div class="progress progress-striped active">
                           <div class="progress-bar progress-bar-success" id="reloadMessage" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
                               <span class="sr-only" id="reloadMessageStateBar"></span>
                           </div>
                       </div>                        
				   </div>		       
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
										<tr onclick="window.location='#this';">											
											<td class='title'>
													${row.MSG_ID }
					                                <input type="hidden" id="hid_msg_id" value="${row.MSG_ID }">
					                                <input type="hidden" id="hid_ko_msg" value="${row.KO_MSG}">
					                                <input type="hidden" id="hid_en_msg" value="${row.EN_MSG}">
											</td>
											<td  class='title'>
											         ${row.KO_MSG }
											</td>
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
							<sec:authorize ifAnyGranted="ROLE_ADMIN">
							<a href="#this" class="btn btn-outline btn-default" id="write">등록</a>							
							<a href="#this" class="btn btn-outline btn-default" id="reloadMsg">Reload Message</a>						    					    							
							<input type="hidden" id="currentPageNo" name="currentPageNo"/>																						 
							</sec:authorize>   														
						</div>																	
					</div>
			    </div>      			
			</div>
		</div>
	</div>
	
	  <div class="modal fade" id="modal_msg_content" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
         <div class="modal-dialog">
             <div class="modal-content">
                 <div class="modal-header" id="modal_header">
                     <button type="button" class="close" name="modal_close" aria-hidden="true">×</button>
                     <h4 class="modal-title" id="modal_signin_title">Message 수정</h4>
                 </div>
                 <div class="modal-body" id="modal_signin_body">
                       <div class="row">
						    <div>
						      <form class="form-horizontal" role="form" id="msg_modal_form">						          						
						          <!-- Text input-->
						          <div class="form-group">
						            <label class="col-sm-3 control-label" for="textinput">메세지 아이디</label>
						            <div class="col-sm-9">
						              <input type="text" placeholder="MSG_ID" class="form-control" name="MSG_ID" id="MSG_ID" readonly="readonly">
						              <label class="col-sm-10" name="input_state"></label>
						            </div>
						          </div>
						          
						          <div class="form-group">
						            <label class="col-sm-3 control-label" for="textinput" >메세지(국문)</label>
						            <div class="col-sm-9">
						              <input type="text" placeholder="메세지(국문)" class="form-control" name="KO_MSG" id="KO_MSG">
						              <label class="col-sm-10" name="input_state"></label>
						            </div>
						          </div>
						
						          <!-- Text input-->
						          <div class="form-group" >
						            <label class="col-sm-3 control-label" for="textinput" >메세지(영문)</label>
						            <div class="col-sm-9">
						              <input type="text" placeholder="메세지(영문)" class="form-control" name="EN_MSG" id="EN_MSG" >
						              <label class="col-sm-10" name="input_state"></label>
						            </div>
						          </div>												      
						      </form>
						    </div><!-- /.col-lg-12 -->
						</div><!-- /.row -->
                 </div>
                 <div class="modal-footer">
                 	 <button type="button" class="btn btn-primary" id="modal_msg_edit">수정</button>
                 	 <button type="button" class="btn btn-primary" id="modal_msg_delete">삭제</button>
                     <button type="button" class="btn btn-default" name="modal_close">취소</button>                     
                 </div>
             </div>
             <!-- /.modal-content -->
         </div>
         <!-- /.modal-dialog -->
     </div>
	<%-- 
	<spring:message code='MSG.input'/>
	<spring:message code='MSG.test'/>
	<spring:message code='UI.signin'/> 
	--%>
	<%@ include file="/WEB-INF/include/include-body.jspf" %>
    <script type="text/javascript">
    	var doubleSubmitFlag = false;
    	
    	 validationChk = function(){

 			// 사용자 MSG_NMKO 필수체크
 			if($("input[name='KO_MSG']").val().isBlank()) {
 				modalAlert("알림","메시지(한글)을 입력해주세요");			
 				$("input[name='MSG_NMKO']").focus();
 	            return false;
 	        }
 			// 사용자 MSG_NMEN 필수체크
 			if($("input[name='EN_MSG']").val().isBlank()) {
 				modalAlert("알림","메시지(영문)을 입력해주세요");			
 				$("input[name='MSG_NMEN']").focus();
 	            return false;
 	        }
 						
 	        return true;		
 		}
    	
        $(document).ready(function(){
        	$(".progress-bar").animate({
        	    width: "0%"
        	}, 100 );   
            $("#write").on("click", function(e){ //글쓰기 버튼
                e.preventDefault();
                fn_openMsgWrite();
            });
            
            $("#reloadMsg").on("click", function(e) { 
            	e.preventDefault();
            	if(doubleSubmitCheck()) return;          
            	fn_reloadMessage();
            });
            
            $("tr .title").on("click", function(e){ //제목
                e.preventDefault();
                fn_openMsgDetail($(this));
            });
            
            $("button[name='modal_close']").click(function(e){
    	    	$("#msg_modal_form")[0].reset();
    	    	$("#modal_msg_content").modal("toggle");
    	    });
            
            $("#modal_msg_edit").click(function(e){
            	e.preventDefault();
            	if(!validationChk())return;
            	fn_openMsgEdit();            	
            });
            
			$("#modal_msg_delete").click(function(e){	
				e.preventDefault();		
				fn_openMsgDelete();
            });
                     
        });
         
        /**
         * 중복서브밋 방지
         * 
         * @returns {Boolean}
         */        
        function doubleSubmitCheck(){
            if(doubleSubmitFlag){
                return doubleSubmitFlag;
            }else{
                doubleSubmitFlag = true;
                return false;
            }
        }
        
        
        function fn_openMsgWrite(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/admin/messageWrite.do' />");
            comSubmit.submit();
        }
        
        function fn_openMsgEdit(){
            var comSubmit = new ComSubmit("msg_modal_form");
            comSubmit.setUrl("<c:url value='/admin/messageEdit.do' />");
            comSubmit.submit();
        }
        
        function fn_openMsgDelete(){
            var comSubmit = new ComSubmit("msg_modal_form");
            comSubmit.setUrl("<c:url value='/admin/messageDelete.do' />");
            comSubmit.submit();
        }
      
        function fn_search(pageNo){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/admin/message.do' />");
            comSubmit.addParam("currentPageNo", pageNo);
            comSubmit.submit();
        }
        
        function fn_move(){
        	 var elem = document.getElementById("reloadMessage");
             var elemState = document.getElementById("reloadMessageState");   
             var elemStateBar = document.getElementById("reloadMessageStateBar");
             var width = 1;                           
             var id = setInterval(frame, 20);
             function frame() {
                 if (width >= 100) {                	                 	 
                     clearInterval(id);
                 } else {
                     width++; 
                     if(width%20 == 0){
                    	 $('.progress .progress-bar').css('width', width+'%').attr('aria-valuenow', width);                       
                         elemState.textContent = width + '% Complete';
                         elemStateBar.textContent = width + '% Complete';
                     }
                 }                     
             }
        	
        }
        
        function fn_reloadMessage(){
        	
        	
        	/* 
        	1. 성공일 경우 : success > complete > done > always
			2. 실패일 경우 : error > complete > fail > always				
			출처: http://doolyit.tistory.com/20 [동해둘리의 IT Study]
        	*/
        	$.ajax({
        		url: "<c:url value='/admin/reloadmsg'/>",
        		type: "post",
        		data: {},
        		contentType: "application/json",
        		success:function(data){
        			if(data.result){        				
        				return true;
        			}
        		},
        		error:function(data){
        			alert(data);
        		},
        		complete : function () {   // 정상이든 비정상인든 실행이 완료될 경우 실행될 함수
        			 $('.progress .progress-bar').css('width', '0%').attr('aria-valuenow', 0);
        		}

        	})
        	.done(function(){ console.log("done"); fn_move(); doubleSubmitFlag = false;})
        	.fail(function(){ console.log("fail"); })
        	.always(function(){ console.log("always"); });
        }
        
        function fn_openMsgDetail(obj){   
        	$("#msg_modal_form label").css("margin-left","-5px");
	    	$("#msg_modal_form label").css("font-size","13px");	    	
	    	$("#msg_modal_form input").css("margin-right","-15px");
        	$("#MSG_ID").val(obj.parent().find("#hid_msg_id").val());
        	$("#KO_MSG").val(obj.parent().find("#hid_ko_msg").val());
        	$("#EN_MSG").val(obj.parent().find("#hid_en_msg").val());
            $("#modal_msg_content").modal("show");
        }
               
    </script>
</body>
</html>