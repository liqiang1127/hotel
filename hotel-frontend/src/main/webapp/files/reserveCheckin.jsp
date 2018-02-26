<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
<%-- 	<form action="${pageContext.request.contextPath}/room!addRoom.action" method="post" enctype="multipart/form-data" name="fom"
		id="fom" target="mainFrame"> --%>
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">	
				<tr>
					<th class="tablestyle_title">预订入住页面</th>
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
										<legend>预订单查询</legend>
											<table width="98%" border="0" align="center" cellpadding="0"
															cellspacing="0">
							<form name="fom" id="fom" method="post" action="${pageContext.request.contextPath}/reserve!queryReserve.action">
									<tr>
										<td width="21"><img src="${pageContext.request.contextPath}/images/ico07.gif" width="20"
											height="18" /></td>
										<td width="538">查看内容：手机号码： <input name="resMobile"
											type="text" size="20"  value="${resMobile }"/> &nbsp; &nbsp;&nbsp;
										<input type="hidden" value="reserveCheckin" name="fromPage"/>
											姓名： <input name="resName"
											type="text" size="20"  value="${resName }"/> &nbsp; &nbsp;&nbsp;
											<input type="hidden" value="0"  name="state">&nbsp; &nbsp;&nbsp;
											<input name="Submit4" type="submit" class="right-button02" value="查 询" /></td>
									</tr>
								</form>
								</table>
								<br />
								<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="9" align="center"
														style="font-size: 16px">客房预订列表</td>
												</tr>
												<tr bgcolor="#EEEEEE" align="center" >
													<td width="5%" height="30" >编号</td>
													<td width="7%">预订人姓名</td>
													<td width="7%">预订人手机号码</td>
													<td width="7%">预计抵店日期</td>
													<td width="10%">预计离店日期</td>
													<td width="10%">预订日期</td>
													<td width="10%">房间号</td>
													<td width="10%">已付钱数</td>
													<td width="10%">操作</td>
												</tr>
												<c:forEach var="reserveOrder" items="${reserveOrders }">
												<tr bgcolor="#FFFFFF">
													<td height="20">${reserveOrder.id }</td>
													<td>${reserveOrder.resName}</td>
													<td>${reserveOrder.resMobile}</td>
													<td>${reserveOrder.inDateStr}</td>
													<td>${reserveOrder.leaveDateStr}</td>
													<td>${reserveOrder.createTimeStr}</td>
													<td>${reserveOrder.roomNo }</td>
													<td>${reserveOrder.paidMoney}</td>
													<td>
														<button onclick="reserveCheckin(${reserveOrder.id })">入住</button>
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
									<fieldset id ="guests"style="height: 100%; display: none;">
										<legend>住客登记</legend>
										<form method="post" action="${pageContext.request.contextPath}/service!checkInFromRes.action">
										<table id="guestTable" border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">
											<TR>
												<TD colspan="4" align="center" height="50px"><input
													type="submit"  value="提交" class="button"
													 /> <input type="button" name="Submit2"
													value="返回" class="button" onclick="window.history.go(-1);" /></TD>
											</TR>
											<tr>
												<td nowrap align="right" width="13%">预订单编号:</td>
												<td><input type="text" name="reserveOrder_id" id="reserveOrder_id" value="" readonly="readonly" width="100px"/>
												 <span class="red"> *</span></td>
												</tr>
											<tr>
												<td nowrap align="right">入住人数:</td>
												<td><select style="width: 100px" id="guestNumber" name="guestNumber" onchange="showInputs()">
														<option value="1"  selected>1</option>
														<option value="2" >2</option>
														<option value="3" >3</option>
														<option value="4">4</option>
														<option value="5" >5</option>
														<option value="6" >6</option>
												</select><span
													class="red"> *</span></td>
											</tr>
										
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
<script type="text/javascript" defer>
	function reserveCheckin(id){
		var guestDiv= document.getElementById("guests");
		guestDiv.style.display="block";
		var reserveIdDiv = document.getElementById("reserveOrder_id");
		reserveIdDiv.value=id;
	}

	var rows = [];
	
	function showInputs(){
		var guestNumber= document.getElementById("guestNumber").value;		
		rows.forEach((row, i) => {
			if (i < guestNumber) row.forEach(r => r.style.display = "table-row");
			else  row.forEach(r => r.style.display = "none");
		});
	}

	for(var i=0;i<6;i++){
		var r1 = document.createElement('tr');
		var r2 = document.createElement('tr');
		r1.innerHTML = `
			<td nowrap align="right">姓名:</td>
			<td><input name="guestName\${i}" id="" class="text" type="text"
				style="width: 300px"/><span
				class="red"> *</span></td>
			<td nowrap align="right">身份证号码:</td>
			<td><input name="IDCardNo\${i}" id="" class="text" type="text"
				style="width: 300px"/><span
				class="red"> *</span></td>
		`;
		r2.innerHTML = `
			<td nowrap align="right">联系方式:<h1></h1></td>
			<td><input name="mobile\${i}" id="" class="text" type="text"
				style="width: 300px"/><h1></h1></td>
			<td nowrap align="right" >性别:<h1></h1></td>
			<td><select name="gender\${i}" style="width: 100px">
					<option value="0" >男</option>
					<option value="1" >女</option>
			</select><span
				class="red"> *</span>
				<h1></h1>
				</td>
		`;
		guestTable.appendChild(r1);
		guestTable.appendChild(r2);
 		rows.push([r1,r2]);
	}
	
	showInputs();
</script>
</body>
</html>
