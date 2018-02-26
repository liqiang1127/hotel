<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增房间</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style.css"
	type="text/css" media="all" />
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
	<form action="${pageContext.request.contextPath}/room!editRoom.action" method="post" enctype="multipart/form-data" name="fom"
		id="fom" target="mainFrame">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">	
				<tr>
					<th class="tablestyle_title">客房修改页面</th>
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
								<h1 style="color: red ;text-align:center" >${errorInfo }</h1>
									<fieldset style="height: 100%;">
										<legend>客房基本信息修改</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">
											<input name="id" value="${room.id }" type="hidden"/>
											<input name="available" value="${room.available }" type="hidden"/>
											<input name="state" value="${room.state }" type="hidden"/>
											<input name="hotel_id" value="${room.hotel_id }" type="hidden"/>
											<tr>
												<td nowrap align="right" width="13%">客房号:</td>
												<td width="41%"><input name="no" class="text"
													style="width: 250px" type="text" size="40"  value="${room.no} "/> <span
													class="red"> *</span></td>
												<td align="right" width="10%">楼层:</td>
												<td><input name="floor" id="Input22"
													class="text" style="width: 154px" value="${room.floor }"/><span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right">分机电话:</td>
												<td><input name="telphone" id="" class="text"
													style="width: 500px"  value="${room.telphone }"/><span
													class="red"> *</span></td>
											</tr>
											<td nowrap align="right">客房种类:</td>
												<td><select name="roomKind_id">
													<c:forEach items="${roomKindsAll }" var="roomKind">
														<option value="${roomKind.id }"  ${roomKind.id==room.roomKind_id?'selected':''}>${roomKind.name }</option>
														</c:forEach>
												</select><span
													class="red"> *</span></td>
													<td nowrap align="right">朝向:</td>
												<td><select name="area">
														<option value="0"  ${room.area=='0'?'selected':''}>南</option>
														<option value="1"  ${room.area=='1'?'selected':''}>北</option>
												</select><span
													class="red"> *</span></td>
											<tr>
											</tr>
										</table>
										<br />
									</fieldset>
								</TD>
							</TR>

						</TABLE>
					</td>
				</tr>
				<TR>
					<TD colspan="2" align="center" height="50px"><input
						type="submit" name="Submit" value="保存" class="button"
						 /> <input type="button" name="Submit2"
						value="返回" class="button" onclick="window.history.go(-1);" /></TD>
				</TR>
			</TABLE>
			</td>
			</tr>
			</table>
		</div>
	</form>
</body>
</html>
