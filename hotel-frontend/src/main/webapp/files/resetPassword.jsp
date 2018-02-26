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
	
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">	
				<tr>
					<th class="tablestyle_title">个人信息修改页面</th>
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
										<legend>密码修改</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">
											<form action="${pageContext.request.contextPath}/employee!resetPassword.action" method="post"  target="mainFrame">
											<tr>
												<td nowrap align="right" width="13%">旧密码:</td>
												<td width="41%"><input name="passwordOld" class="text"
													style="width: 250px" type="text" size="40"  /> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="13%">新密码:</td>
												<td width="41%"><input name="passwordNew1" class="text"
													style="width: 250px" type="text" size="40"  /> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="13%">重复新密码:</td>
												<td width="41%"><input name="passwordNew2" class="text"
													style="width: 250px" type="text" size="40"  /> <span
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
			</form>
			</TABLE>
			</td>
			</tr>
			</table>
		</div>
</body>
</html>
