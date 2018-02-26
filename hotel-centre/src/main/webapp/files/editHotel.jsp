<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增分店</title>
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
	<form action="${pageContext.request.contextPath}/branch!editHotel.action" method="post" enctype="multipart/form-data" name="fom"
		id="fom" target="mainFrame">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">	
				<tr>
					<th class="tablestyle_title">分店信息修改页面</th>
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
										<legend>酒店基本信息录入</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">
											<tr><input type="hidden" name="id" value="${hotel.id }"/></tr>
											<tr>
												<td nowrap align="right" width="13%">分店名称:</td>
												<td width="41%"><input name="name" class="text"
													style="width: 250px" type="text" size="40"  value="${hotel.name }"/> <span
													class="red"> *</span></td>
												<td align="right" width="10%">地图坐标:</td>
												<td>(&nbsp;<input name="x" id="Input22"
													class="text" style="width: 154px"  value="${hotel.x }"/>
														&nbsp;,&nbsp;
														<input name="y" id="Input22"
													class="text" style="width: 154px"  value="${hotel.y}"/>&nbsp;)</td>
											</tr>
											<tr>
												<td nowrap align="right">具体位置:</td>
												<td><input name="location" id="" class="text"
													style="width: 500px"  value="${hotel.location}" /><span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right">当前状态:</td>
												<td><select name="state">
														<option value="0"  ${hotel.state == '0'?'selected':''}>建设中</option>
														<option value="1"  ${hotel.state == '1'?'selected':''}>营业中</option>
														<option value="2"  ${hotel.state == '2'?'selected':''}>停业中</option>
												</select></td>
												<td align="right">服务标准:</td>
												<td><select name="star">
														<option value="1" ${hotel.star == '1'?'selected':''}>一星级</option>
														<option value="2"  ${hotel.star == '2'?'selected':''}>二星级</option>
														<option value="3"  ${hotel.star == '3'?'selected':''}>三星级</option>
														<option value="4"  ${hotel.star == '4'?'selected':''}>四星级</option>
														<option value="5"  ${hotel.star == '5'?'selected':''}>五星级</option>
												</select></td>
											</tr>
											<tr>
												<td nowrap align="right" height="120px">备注:</td>
												<td colspan="3"><textarea id="textarea" name="remark"
														rows="5" cols="80" >${hotel.remark }</textarea></td>
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
