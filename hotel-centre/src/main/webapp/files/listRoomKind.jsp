<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" " />
<title>酒店列表</title>

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
	<form name="fom" id="fom" method="post" action="${pageContext.request.contextPath}/roomKind!queryRoomKind.action">
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
										<td width="538">查看内容：房间种类名： <input name="name"
											type="text" size="20"  value="${name }"/> &nbsp; &nbsp;&nbsp;
											状态：<select name="state">
														<option value="">请选择</option>
														<option value="">正常</option>
														<option value="">挂起</option>
												</select>&nbsp; &nbsp;&nbsp;
													床位数：<select name="bedNumber">
														<option value=""  <c:if test="${bedNumber}">selected="selected"</c:if>>请选择</option>
														<option value="1" <c:if test="${bedNumber=='1'}">selected="selected"</c:if>>1</option>
														<option value="2"  <c:if test="${bedNumber=='2'}">selected="selected"</c:if>>2</option>
														<option value="3" <c:if test="${bedNumber=='3'}">selected="selected"</c:if>>3</option>
														<option value="4" <c:if test="${bedNumber=='4'}">selected="selected"</c:if>>4</option>
														<option value="5" <c:if test="${bedNumber=='5'}">selected="selected"</c:if>>5</option>
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
													<td height="22" colspan="8" align="center"
														style="font-size: 16px">房间种类信息列表</td>
												</tr>
												<tr bgcolor="#EEEEEE" align="center" >
													<td width="5%" height="30">编号</td>
													<td width="15%">类型名称</td>
													<td width="7%">床位数</td>
													<td width="10%">预订价格（天）</td>
													<td width="10%">入住价格（天）</td>
													<td width="10%">计时价格（小时）</td>
													<td width="8%">最短入住时长（小时）</td>
													<td width="10%">操作</td>
												</tr>
												<c:forEach var="roomKind" items="${roomKinds }">
												<tr bgcolor="#FFFFFF">
													<td height="20">${roomKind.id }</td>
													<td>${roomKind.name}</td>
													<td>${roomKind.bedNumber}</td>
													<td>${roomKind.prePrice}</td>
													<td>${roomKind.priceOneNight}</td>
													<td>${roomKind.perHourPrice}</td>
													<td>${roomKind.minHours}</td>
													<td>
														<a href="${pageContext.request.contextPath}/roomKind!goEditRoomKind.action?id=${roomKind.id}">修改基本信息</a>&nbsp; 
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