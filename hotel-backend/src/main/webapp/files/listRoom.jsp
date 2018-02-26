<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" " />
<title>客房列表</title>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.tabfont01 {
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}

.font051 {
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}

.font201 {
	font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}

html {
	overflow-x: auto;
	overflow-y: auto;
	border: 0;
}
-->
</style>

<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">
	
</script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form name="fom" id="fom" method="post" action="${pageContext.request.contextPath}/room!queryRoom.action">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">

			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" background="${pageContext.request.contextPath}/images/nav04.gif">
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="21"><img src="${pageContext.request.contextPath}/images/ico07.gif" width="20"
											height="18" /></td>
										<td width="538">查看内容：房间号： <input name="no"
											type="text" size="20"  value="${no}"/> &nbsp; &nbsp;&nbsp;
											楼层： <input name="floor"
											type="text" size="20"  value="${floor}"/> &nbsp; &nbsp;&nbsp;
											房间种类：<select name="roomKind_id">
											<option value= ""  ${roomKind.id==''?'selected':''}>请选择</option>
													<c:forEach items="${roomKindsAll }" var="roomKind">
														<option value="${roomKind.id }"  ${roomKind.id==roomKind_id_b?'selected':''}>${roomKind.name }</option>
														</c:forEach>
												</select>&nbsp; &nbsp;&nbsp;
											状态：<select name="state">
														<option value="" <c:if test="${state}">selected="selected"</c:if>>请选择</option>
														<option value="0"  <c:if test="${state=='0'}">selected="selected"</c:if>>空闲</option>
														<option value="1"  <c:if test="${state=='1'}">selected="selected"</c:if>>预订</option>
														<option value="2"  <c:if test="${state=='2'}">selected="selected"</c:if>>租用</option>
														<option value="3"  <c:if test="${state=='3'}">selected="selected"</c:if>>结账</option>
														<option value="4"  <c:if test="${state=='4'}">selected="selected"</c:if>>清洁</option>
														<option value="5"  <c:if test="${state=='5'}">selected="selected"</c:if>>锁房</option>
												</select>&nbsp; &nbsp;&nbsp;
		
											是否可用：<select name="available">
														<option value="" <c:if test="${available}">selected="selected"</c:if>>请选择</option>
														<option value="0"  <c:if test="${available=='0'}">selected="selected"</c:if>>可用</option>
														<option value="1"  <c:if test="${available=='1'}">selected="selected"</c:if>>不可用</option>
												</select>&nbsp; &nbsp;&nbsp;
											<input name="Submit4" type="submit" class="right-button02" value="查 询" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><table id="subtree1" style="DISPLAY:" width="100%"
						border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><table width="95%" border="0" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="9" align="center"
														style="font-size: 16px">客房信息列表</td>
												</tr>
												<tr bgcolor="#EEEEEE" align="center" >
													<td width="5%" height="30">编号</td>
													<td width="7%">房间号</td>
													<td width="7%">楼层</td>
													<td width="7%">朝向</td>
													<td width="10%">分机电话</td>
													<td width="10%">房间种类</td>
													<td width="10%">状态</td>
													<td width="10%">可用</td>
													<td width="20%">操作</td>
												</tr>
												<c:forEach var="room" items="${rooms }">
												<tr bgcolor="#FFFFFF">
													<td height="20">${room.id }</td>
													<td>${room.no}</td>
													<td>${room.floor}</td>
													<td>${room.area}</td>
													<td>${room.telphone}</td>
													<td>${room.roomKindStr }</td>
													<td>${room.state}</td>
													<td>${room.available}</td>
													<td>
														<a href="${pageContext.request.contextPath}/room!goEditRoom.action?id=${room.id}">修改基本信息</a>&nbsp; 
														<a href="${pageContext.request.contextPath}/room!stateRoom.action?id=${room.id}&op=1">清洁房间</a>&nbsp;
														<a href="${pageContext.request.contextPath}/room!stateRoom.action?id=${room.id}&op=2">锁定房间</a>&nbsp;
														<a href="${pageContext.request.contextPath}/room!stateRoom.action?id=${room.id}&op=3">恢复可用</a>&nbsp;
													</td>
												</tr>
												</c:forEach>
											</table>
										</td>
									</tr>
								</table>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="6"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1"
											height="1" /></td>
									</tr>
									<tr>
										<td height="33"><table width="100%" border="0"
												align="center" cellpadding="0" cellspacing="0"
												class="right-font08">
												<tr>
													<td width="50%">共 <span class="right-text09">1</span>
														页 | 第 <span class="right-text09">1</span> 页
													</td>
													<td width="49%" align="right">[<a href="#"
														class="right-font08">首页</a> | <a href="#"
														class="right-font08">上一页</a> | <a href="#"
														class="right-font08">下一页</a> | <a href="#"
														class="right-font08">末页</a>] 转至：
													</td>
													<td width="1%"><table width="20" border="0"
															cellspacing="0" cellpadding="0">
															<tr>
																<td width="1%"><input name="textfield3" type="text"
																	class="right-textfield03" size="1" /></td>
																<td width="87%"><input name="Submit23222"
																	type="submit" class="right-button06" value=" " /></td>
															</tr>
														</table></td>
												</tr>
											</table></td>
									</tr>
								</table></td>
						</tr>
					</table>
					</form>
</body>
</html>