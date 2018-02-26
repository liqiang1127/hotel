<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增第三方合作</title>
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
	<form action="${pageContext.request.contextPath}/roomKind!editRoomKind.action" method="post" enctype="multipart/form-data" name="fom"
		id="fom" target="mainFrame">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">	
				<tr>
					<th class="tablestyle_title">房间种类新增页面</th>
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
										<legend>房间种类信息录入</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">
											<tr><input type="hidden" name="id" value="${roomKind.id }"/></tr>
											<tr>
												<td nowrap align="right" width="13%">房间名称:</td>
												<td width="41%"><input name="name" class="text"
													style="width: 250px" type="text" size="40"  value="${roomKind.name }"/> <span
													class="red"> *</span></td>
												<td nowrap align="right" width="13%">预定价格（天）:</td>
												<td width="41%"><input name="prePrice" class="text"
													style="width: 250px" type="text" size="40"  value="${roomKind.prePrice }"/> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="13%">入住价格（天）:</td>
												<td width="41%"><input name="priceOneNight" class="text"
													style="width: 250px" type="text" size="40"  value="${roomKind.priceOneNight }"/> <span
													class="red"> *</span></td>
												<td nowrap align="right">床位数:</td>
												<td><select name="bedNumber">
													<option value="1" ${roomKind.bedNumber == '1'?'selected':''}>1</option>
														<option value="2" ${roomKind.bedNumber == '2'?'selected':''}>2</option>
														<option value="3" ${roomKind.bedNumber == '3'?'selected':''}>3</option>
														<option value="4" ${roomKind.bedNumber == '4'?'selected':''}>4</option>
														<option value="5" ${roomKind.bedNumber == '5'?'selected':''}>5</option>
												</select><span class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="13%">计时价格（小时）:</td>
												<td width="41%"><input name="perHourPrice" class="text"
													style="width: 250px" type="text" size="40"  value="${roomKind.perHourPrice }"/> <span
													class="red"> *</span></td>
													<td nowrap align="right" width="13%">最短入住时长:</td>
												<td width="41%"><input name="minHours" class="text"
													style="width: 250px" type="text" size="40"  value="${roomKind.minHours }"/> <span
													class="red"> *</span></td>
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
