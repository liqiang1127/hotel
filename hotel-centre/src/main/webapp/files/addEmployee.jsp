<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工信息录入</title>
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
	<form action="${pageContext.request.contextPath}/employee!addEmployee.action" method="post" enctype="multipart/form-data" name="fom"
		id="fom" target="mainFrame">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">	
				<tr>
					<th class="tablestyle_title">员工信息录入页面</th>
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
										<legend>员工信息录入</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">
											<tr>
												<td nowrap align="right" width="13%">姓名:</td>
												<td width="41%"><input name="name" class="text"
													style="width: 250px" type="text" size="40"  value="${employee.name }"/> <span
													class="red"> *</span></td>
												<td nowrap align="right" width="13%">身份证号码:</td>
												<td width="41%"><input name="IDCardNo" class="text"
													style="width: 250px" type="text" size="40"  value="${employee.IDCardNo }"/> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="13%">手机号码:</td>
												<td width="41%"><input name="mobile" class="text"
													style="width: 250px" type="text" size="40"  value="${employee.mobile }"/> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right">状态</td>
												<td><select name="state">
														<option value="0"  selected="selected">在职</option>
														<option value="1" >休假</option>
														<option value="2" >离职</option>
												</select><span
													class="red"> *</span></td>
												<td align="right">职位:</td>
												<td><select name="role">
														<option value="0"  selected="selected">普通员工</option>
														<option value="1" >部门经理</option>
														<option value="2" >分店经理</option>
														<option value="3" >地区经理</option>
														<option value="4" >总经理</option>
														<option value="5" >董事长</option>
												</select><span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right">设置酒店</td>
												<td><select name="hotel_id">
														<option value="0"  selected="selected">选择酒店</option>
													<c:forEach items="${hotelsAll }" var="hotel">
														<option value="${hotel.id }" >${hotel.name }</option>
													</c:forEach>
												</select><span
													class="red"> *</span></td>
												<td align="right">设置部门:</td>
												<td><select name="department_id">
												<option value="0"  selected="selected">选择部门</option>
													<c:forEach items="${departmentsAll }" var="department">
														<option value="${department.id }" >${department.name}</option>
													</c:forEach>
												</select><span
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
