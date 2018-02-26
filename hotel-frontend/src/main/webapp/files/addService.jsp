<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增房间</title>
<link rel="stylesheet" rev="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css"
	media="all" />
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->
</style>
</head>

<body class="ContentBody">
	<%-- 	<form action="${pageContext.request.contextPath}/room!addRoom.action" method="post" enctype="multipart/form-data" name="fom"
		id="fom" target="mainFrame"> --%>
	<div class="MainDiv">
		<table width="99%" border="0" cellpadding="0" cellspacing="0"
			class="CContent">
			<tr>
				<th class="tablestyle_title">增加服务页面</th>
			</tr>
			<tr>
				<td class="CPanel">

					<table border="0" cellpadding="0" cellspacing="0"
						style="width: 100%">
						<!-- <tr>
								<td align="left"><input type="button" name="Submit"
									value="保存" class="button" onclick="alert('保存成功！');" /> <input
									type="button" name="Submit2" value="返回" class="button"
									onclick="window.history.go(-1);" /></td>
							</tr> -->
						<TR>
							<TD width="100%">
								<fieldset style="height: 100%;">
									<legend>客房查询</legend>
									<table width="95%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<form name="fom" id="fom" method="post"
											action="${pageContext.request.contextPath}/room!queryRoom.action">
											<tr>
												<td width="21"><img
													src="${pageContext.request.contextPath}/images/ico07.gif"
													width="20" height="18" /></td>
												<td width="538">查看内容：房间号： <input name="no" type="text"
													size="20" value="${no}" /> &nbsp; &nbsp;&nbsp; <input
													type="hidden" value="addService" name="fromPage" /> 楼层： <input
													name="floor" type="text" size="20" value="${floor}" />
													&nbsp; &nbsp;&nbsp; 房间种类：<select name="roomKind_id">
														<option value="" ${roomKind.id==''?'selected':''}>请选择</option>
														<c:forEach items="${roomKindsAll }" var="roomKind">
															<option value="${roomKind.id }"
																${roomKind.id==roomKind_id_b?'selected':''}>${roomKind.name }</option>
														</c:forEach>
												</select>&nbsp; &nbsp;&nbsp; <input name="state" type="hidden"
													value="2" /> <input name="Submit4" type="submit"
													class="right-button02" value="查 询" /></td>
											</tr>
										</form>
									</table>
									<br />
									<table width="100%" border="0" cellpadding="4" cellspacing="1"
										bgcolor="#464646" class="newfont03">
										<tr class="CTitle">
											<td height="22" colspan="9" align="center"
												style="font-size: 16px">客房信息列表</td>
										</tr>
										<tr bgcolor="#EEEEEE" align="center">
											<td width="5%" height="30">编号</td>
											<td width="7%">房间号</td>
											<td width="7%">楼层</td>
											<td width="7%">朝向</td>
											<td width="10%">分机电话</td>
											<td width="10%">状态</td>
											<td width="10%">客房类型</td>
											<td width="10%">可用</td>
											<td width="10%">操作</td>
										</tr>
										<c:forEach var="room" items="${rooms }">
											<tr bgcolor="#FFFFFF">
												<td height="20">${room.id }</td>
												<td>${room.no}</td>
												<td>${room.floor}</td>
												<td>${room.area}</td>
												<td>${room.telphone}</td>
												<td>${room.state}</td>
												<td>${room.roomKindStr }</td>
												<td>${room.available}</td>
												<td>
														<button onclick="addService(${room.id })">增加服务</button>
													</td>
											</tr>
										</c:forEach>
									</table>
									<br />
									<h2 style="color:red; text-align: center;">${errorInfo }</h2>
								</fieldset>
							</TD>
						</TR>

						<TR>
							<TD width="100%">
								<fieldset style="height: 100%; display: none;" id="addServiceField">
									<legend>增加服务</legend>
									<form method="post"
										action="${pageContext.request.contextPath}/businese!addService.action">
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">

											<tr>
												<td nowrap align="right" width="13%">房间编号:</td>
												<td width="41%"><input name="room_id" class="text" id="room_id"
													style="width: 300px" type="text" size="40" readonly="readonly"
													 /> <span class="red"> *</span></td>
												<td nowrap align="right" width="13%">服务编号:</td>
												<td width="41%"><input name="serviceKind_id" class="text"
													style="width: 300px" type="text" size="40"
													value="" /> <span class="red"> *</span></td>
											</tr>
											<!-- <tr>
												<td nowrap align="right" height="120px">备注:</td>
												<td colspan="3"><textarea id="textarea" name="remark"
														rows="5" cols="80" ></textarea></td>
											</tr>		 -->								
											<TR>
												<TD colspan="4" align="center" height="50px"><input
													type="submit" name="Submit" value="保存" class="button"
													 /> <input type="button" name="Submit2"
													value="返回" class="button" onclick="window.history.go(-1);" /></TD>
											</TR>
										</table>
									</form>
									<br />
								</fieldset>
							</TD>
						</TR>
					</TABLE>
				</td>
			</tr>
		</TABLE>
		</td>
		</tr>
		</table>
	</div>
<script type="text/javascript">
	function addService(id){
		addServiceField.style.display="block";
		room_id.value=id;
	}
</script>
</body>
</html>
