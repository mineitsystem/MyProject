<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
 
<div class="navbar-default sidebar" role="navigation">						
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
	                                <button class="btn btn-default" type="button">
	                                    <i class="fa fa-search" style="margin: 1 1 5 1;"></i>
	                                </button>
                            	</span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <c:forEach var="main" items="${LEFT_MENU}">                          	              	 
                                <c:if test="${main.MENU_IDX eq 0}">
                                	<li>
	                                	<!--메인  -->	                                	
	                                	<a href="<c:url value='${main.LINK}'/>"  class="${fn:startsWith(requestURI, main.MENU_POINT) ? 'active' : '' }"><i class="${main.ICON}"></i> ${main.MENU_NAME}
	                                		<c:if test="${main.SUBMENU_CNT > 0}">	                                		 
	                                			<!-- 서브 메뉴 존재 여부  -->
	                                			<span class="fa arrow"></span>
	                                		</c:if>
	                                	</a>
	                                	<c:set var="MENU_POINT_SUB" value="${main.MENU_POINT_SUB}"/>
                                	    <c:if test="${main.SUBMENU_CNT > 0}"> 
                                	    	                             	
                                	    	<!--${fn:startsWith(fn:split(requestURI,'/')[1], MENU_POINT_SUB) ? 'collapse in' : '' }  -->                             	    	  
	                                	    <ul class="nav nav-second-level">	                                	                           
			                                	<c:forEach var="sub1" items="${LEFT_MENU}">				                        		
						                        		<c:if test="${main.PARENT_IDX eq sub1.PARENT_IDX and sub1.MENU_IDX eq 1}"> 					                        			
					                        				<li>					                        										                        					
						                                    	<a href="<c:url value='${sub1.LINK}'/>"  class="${fn:startsWith(requestURI, sub1.MENU_POINT) ? 'active' : '' }">${sub1.MENU_NAME}
						                                    		<c:if test="${sub1.SUBMENU_CNT > 0}">
							                                			<!-- 서브의 서브 메뉴 존재 여부  -->
							                                			<span class="fa arrow"></span>
							                                		</c:if>
						                                    	</a>					                               			                        					                        			
					                        					<!-- 첫번째 서브  -->
					                        		            <c:if test="${sub1.SUBMENU_CNT > 0}">
					                        		            
								                                	<ul class="nav nav-third-level">
								                                	
									                       			    <c:forEach var="sub2" items="${LEFT_MENU}">
									                       			    
									                        				<c:if test="${sub1.PARENT_IDX eq sub2.PARENT_IDX and sub2.MENU_IDX eq 2}">  
									                        					<!-- 두번째 서브  -->				                        														                                    										                                   
										                                        <li>
										                                            <a href="<c:url value='${sub2.LINK}'/>">${sub2.MENU_NAME}</a>
										                                        </li>								                                      
												                                    							                                    								                               							                        					
									                        				</c:if>
									                        				
									                       				</c:forEach>							                       				
								                       				</ul>	
							                                	</c:if>           			    
						                       				 </li>			                        			
						                        		</c:if>				                        		                    
				                        		</c:forEach> 
			                        		</ul>
                                	    </c:if> 
	                        		</li>
                                </c:if>               		                        	                         	                        	                     
                        </c:forEach>                     
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->