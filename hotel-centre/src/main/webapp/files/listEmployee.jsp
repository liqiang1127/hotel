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
<SCRIPT language=JavaScript>
	function sousuo() {
		window
				.open(
						"gaojisousuo.htm",
						"",
						"depended=0,alwaysRaised=1,width=800,height=510,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
	}
	function selectAll() {
		var obj = document.fom.elements;
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				obj[i].checked = true;
			}
		}
	}

	function unselectAll() {
		var obj = document.fom.elements;
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				if (obj[i].checked == true)
					obj[i].checked = false;
				else
					obj[i].checked = true;
			}
		}
	}

	function link() {
		document.getElementById("fom").action = "addrenwu.htm";
		document.getElementById("fom").submit();
	}
</SCRIPT>

<body>
	<form name="fom" id="fom" method="post" action="${pageContext.request.contextPath}/employee!queryEmployee.action">
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
										<td width="538">查看内容：员工姓名： <input name="name"
											type="text" size="20"  value="${name }"/> &nbsp; &nbsp;
											工号：<input name="staffId"
											type="text" size="30"  value="${staffId }"/> &nbsp; &nbsp;
												酒店：<select name="hotel_id">
														<option value=""  <c:if test="${hotel_id_b}">selected="selected"</c:if>>选择酒店</option>
													<c:forEach items="${hotelsAll }" var="hotel">
														<option value="${hotel.id }"  ${hotel.id==hotel_id_b?'selected':''}>${hotel.name }</option>
													</c:forEach>
												</select>&nbsp; &nbsp;
											部门：<select name="department_id">
												<option value=""  <c:if test="${department_id_b}">selected="selected"</c:if>>选择部门</option>
													<c:forEach items="${departmentsAll }" var="department">
														<option value="${department.id }"  ${department.id==department_id_b?'selected':''}>${department.name}</option>
													</c:forEach>
												</select>&nbsp;&nbsp;
											状态：<select name="state">
														<option value=""  <c:if test="${state}">selected="selected"</c:if>>选择状态</option>
														<option value="0"  <c:if test="${state=='0'}">selected="selected"</c:if>>在职</option>
														<option value="1"  <c:if test="${state=='1'}">selected="selected"</c:if>>休假</option>
														<option value="2"  <c:if test="${state=='2'}">selected="selected"</c:if>>离职</option>
												</select>&nbsp; &nbsp;
											职位：<select name="role">
														<option value=""  <c:if test="${role}">selected="selected"</c:if>>选择职位</option>
														<option value="0" <c:if test="${role=='0'}">selected="selected"</c:if>>普通员工</option>
														<option value="1"  <c:if test="${role=='1'}">selected="selected"</c:if>>部门经理</option>
														<option value="2"  <c:if test="${role=='2'}">selected="selected"</c:if>>分店经理</option>
														<option value="3" <c:if test="${role=='3'}">selected="selected"</c:if>>地区经理</option>
														<option value="4"  <c:if test="${role=='4'}">selected="selected"</c:if>>总经理</option>
														<option value="5"  <c:if test="${role=='5'}">selected="selected"</c:if>>董事长</option>
												</select>&nbsp;&nbsp;
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
														style="font-size: 16px">员工信息列表</td>
												</tr>
												<tr bgcolor="#EEEEEE" align="center" >
													<td width="5%" height="30">编号</td>
													<td width="7%">姓名</td>
													<td width="7%">工号</td>
													<td width="15%">手机号码</td>
													<td width="7%">状态</td>
													<td width="7%">角色</td>
													<td width="15%">酒店</td>
													<td width="15%">部门</td>
													<td width="10%">操作</td>
												</tr>
												<c:forEach var="employee" items="${employees }">
												<tr bgcolor="#FFFFFF">
													<td height="20">${employee.id }</td>
													<td>${employee.name}</td>
													<td>${employee.staffId}</td>
													<td>${employee.mobile}</td>
													<td>${employee.state}</td>
													<td>${employee.role}</td>
													<td>${employee.hotelStr}</td>
													<td>${employee.departmentStr}</td>
													<td>
														<a href="${pageContext.request.contextPath}/employee!stateEmployee.action?id=${employee.id}&op=1">复职</a>&nbsp;
														<a href="${pageContext.request.contextPath}/employee!stateEmployee.action?id=${employee.id}&op=2">离职</a>&nbsp;
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